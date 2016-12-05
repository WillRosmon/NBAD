/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rosmon.data.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rosmon.data.utilities.ConnectionPool;
import rosmon.data.utilities.DBUtil;
import rosmon.purchase.business.Purchase;
import rosmon.purchaseOrder.business.PurchaseOrder;
import rosmon.data.utilities.DatabaseConstants;

/**
 *
 * @author wrosmon
 */
public class PurchaseAccessor {
    
    public static Purchase addPurchase(Purchase purchase) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("INSERT INTO ")
                .append(DatabaseConstants.PURCHASE_TABLE)
                .append(" ( ")
                .append(DatabaseConstants.VENDOR_ID_COL)
                .append(", ")
                .append(DatabaseConstants.PO_NUMBER_COL)
                .append(", ")
                .append(DatabaseConstants.INVOICE_COL)
                .append(", ")
                .append(DatabaseConstants.PAYMENT_COL)
                .append(", ")
                .append(DatabaseConstants.AMOUNT_COL)
                .append(" ) Values(?, ?, ?, ?, ? )");
        
        try {
            ps = connection.prepareStatement(sb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(0, purchase.getVendorId());
            ps.setInt(1, purchase.getPo().getPOSequenceNumber());
            ps.setString(2, DatabaseConstants.DEFAULT_ID);
            ps.setString(3, DatabaseConstants.DEFAULT_ID);
            ps.setDouble(4, purchase.getAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            purchase.setPurchaseSeqNum(rs.getInt(DatabaseConstants.ID_COL));
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return purchase;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        return purchase;
    }
    
    private static Purchase asPurchase(ResultSet rs) {
        Purchase purchase = new Purchase();
        try {
            purchase.setAmount(rs.getDouble(DatabaseConstants.AMOUNT_COL));
            purchase.setPurchaseSeqNum(rs.getInt(DatabaseConstants.ID_COL));
            purchase.setVendorId(rs.getString(DatabaseConstants.VENDOR_ID_COL));
            purchase.setInvoiceNum(rs.getString(DatabaseConstants.INVOICE_COL));
            purchase.setPaymentNum(rs.getString(DatabaseConstants.PAYMENT_COL));
            
            PurchaseOrder purchaseOrder = new PurchaseOrder();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return purchase;
    }
}
