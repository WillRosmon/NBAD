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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rosmon.data.utilities.ConnectionPool;
import rosmon.data.utilities.DBUtil;
import rosmon.data.utilities.DatabaseConstants;
import rosmon.purchaseOrder.business.PurchaseOrder;
import rosmon.vendors.business.Vendor;

/**
 *
 * @author wrosmon
 */
public class PurchaseOrderAccessor {
    
    
    public static PurchaseOrder selectPurchaseOrder(String purchaseOrderId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT * FROM ")
                .append(DatabaseConstants.PURCHASE_ORDER_TABLE)
                .append(" WHERE ")
                .append(DatabaseConstants.ID_COL)
                .append(" = ? ");
        
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setInt(0, Integer.parseInt(purchaseOrderId));
            rs = ps.executeQuery();
            if(rs.next()) {
                PurchaseOrder purchaseOrder = asPurchaseOrder(rs);
                return purchaseOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
        } 
        finally {
            cleanup(ps, rs);
            pool.freeConnection(connection);
        }
        
        return null;
    }
    
    
    private static void cleanup(PreparedStatement ps, ResultSet rs) {
        if(ps != null) {
            DBUtil.closePreparedStatement(ps);
        }
        if(rs != null) {
            DBUtil.closeResultSet(rs);
        }
    }
    
    private static PurchaseOrder asPurchaseOrder(ResultSet rs) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        try {
            purchaseOrder.setPOSequenceNumber(rs.getInt(DatabaseConstants.ID_COL));
            purchaseOrder.setAmount(rs.getDouble(DatabaseConstants.ISSUE_AMOUNT));
            purchaseOrder.setAmountRemaining(rs.getDouble(DatabaseConstants.AMOUNT_REMAINING));
            purchaseOrder.setVendorId(Integer.toString(
                    rs.getInt(DatabaseConstants.VENDOR_ID_COL)));
            purchaseOrder.setUserId(rs.getString(DatabaseConstants.PO_ISSUER_COL));
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
        return purchaseOrder;
    }
    
    public static List<PurchaseOrder> selectAllPurchaseOrders() {
        
        
        return null;
    }
    
    public static List<PurchaseOrder> selectAllPurchaseOrdersByVendor(String vendorId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("SEECT * FROM ")
                .append(DatabaseConstants.PURCHASE_ORDER_TABLE)
                .append(" WHERE ")
                .append(DatabaseConstants.VENDOR_ID_COL)
                .append(" = ? ");
        
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setInt(0, Integer.parseInt(vendorId));
            rs = ps.executeQuery();
            List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
            while(rs.next()) {
                purchaseOrderList.add(asPurchaseOrder(rs));
            }
            return purchaseOrderList;
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cleanup(ps, rs);
            pool.freeConnection(connection);
        }
    }
    
    public static List<PurchaseOrder> selectAllPurchaseOrdersByVendor(Vendor vendor) {
        
        return selectAllPurchaseOrdersByVendor(vendor.getVendorId());
    }
}
