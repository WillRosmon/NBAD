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
import rosmon.vendors.business.Vendor;

/**
 *
 * @author wrosmon
 */
public class VendorAccessor {
    
    public static Vendor selectVendor(String vendorId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vendor vendor = null;
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT * FROM ")
                .append(DatabaseConstants.VENDORS_TABLE)
                .append(" WHERE ")
                .append(DatabaseConstants.ID_COL)
                .append(" = ?");
        
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, vendorId);
            rs = ps.executeQuery();
            if(rs.next()) {
                vendor = asVendor(rs);
            }
            return vendor;
        } catch (SQLException ex) {
            Logger.getLogger(VendorAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return vendor;
        } finally {
            if(ps != null){
                DBUtil.closePreparedStatement(ps);
            }
            if(rs != null) {
                DBUtil.closeResultSet(rs);
            }
            if(connection != null) {
                pool.freeConnection(connection);
            }
        }
    }
    
    public static int addVendor(Vendor vendor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
                .append(DatabaseConstants.VENDORS_TABLE)
                .append(" ( ")
                .append(DatabaseConstants.VENDOR_NAME_COL)
                .append(", ")
                .append(DatabaseConstants.VENDOR_STATUS_COL)
                .append(" ) VALUES ( ?, ? )");
        
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, vendor.getVendorName());
            ps.setString(2, vendor.getVendorStatus());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VendorAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            if(ps != null){
                DBUtil.closePreparedStatement(ps);
            }
            if(rs != null) {
                DBUtil.closeResultSet(rs);
            }
            if(connection != null){
                pool.freeConnection(connection);
            }
        }
    }
    
    public static List<Vendor> selectAllVendors() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT * FROM ")
                .append(DatabaseConstants.VENDORS_TABLE);
        
        try {
            ps = connection.prepareStatement(sb.toString());
            rs = ps.executeQuery();
            List<Vendor> vendorsList = new ArrayList<>();
            while(rs.next()) {
                vendorsList.add(asVendor(rs));
            }
            return vendorsList;
        } catch (SQLException ex) {
            Logger.getLogger(VendorAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if(ps != null) {
                DBUtil.closePreparedStatement(ps);
            }
            if(rs != null) {
                DBUtil.closeResultSet(rs);
            }
            if(connection != null) {
                pool.freeConnection(connection);
            }
        }
        
    }
    
    public static Vendor searchForVendorByName(String vendorName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT * FROM ")
                .append(DatabaseConstants.VENDORS_TABLE)
                .append(" WHERE ")
                .append(DatabaseConstants.VENDOR_NAME_COL)
                .append(" = ?");
        
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, vendorName);
            rs = ps.executeQuery();
            Vendor vendor = null;
            if(rs.next()) {
                vendor = asVendor(rs);
            }
            return vendor;
        } catch (SQLException ex) {
            Logger.getLogger(VendorAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if(ps != null) {
                DBUtil.closePreparedStatement(ps);
            } if(rs != null) {
                DBUtil.closeResultSet(rs);
            }
            if(connection != null) {
                pool.freeConnection(connection);
            }
        }
    }
    
    private static Vendor asVendor(ResultSet rs) {
        Vendor vendor = new Vendor();
        
        try {
            vendor.setVendorId(rs.getString(DatabaseConstants.ID_COL));
            vendor.setVendorName(rs.getString(DatabaseConstants.VENDOR_NAME_COL));
            vendor.setVendorStatus(rs.getString(DatabaseConstants.VENDOR_STATUS_COL));
            return vendor;
        } catch (SQLException ex) {
            Logger.getLogger(VendorAccessor.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return null;
    }
}
