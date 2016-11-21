/*
 * ProductDB class for interacting with the Music Database
 */
package music.data;

import music.business.Product;
import java.sql.*;
import java.util.*;

/**
 * ProductDB class for interacting with the Music Database
 * @author wrosmon
 */
public class ProductDB {

    /**
     * Insert method for inserting a new product into the database
     * @param product product to be added
     * @return 0 upon failure, another number upon success
     */
    public static int insert(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        if(productCodeExists(product.getCode(), connection)) {
            System.out.println("A product with that code already exists.");
            return 0;
        }
        PreparedStatement ps = null;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
                .append(MusicConstants.PRODUCT_TABLE_NAME)
                .append(" (")
                .append(MusicConstants.PRODUCT_CODE_COLUMN)
                .append(", ")
                .append(MusicConstants.PRODUCT_DESCRIPTION_COLUMN)
                .append(", ")
                .append(MusicConstants.PRODUCT_PRICE_COLUMN)
                .append(") Values (?, ?, ?)");

        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    /**
     * Read method, for selecting a single product in the Music database
     * @param productCode code to search for
     * @return the Music product associated with that product code
     */
    public static Product selectProduct(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(MusicConstants.PRODUCT_TABLE_NAME)
                .append(" WHERE ")
                .append(MusicConstants.PRODUCT_CODE_COLUMN)
                .append(" = ?");

        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            if(rs.next()) {
                return asProduct(rs);
            } else {
                System.out.println("No Product in the database matches "
                        + "that product Code");
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    /**
     * Read method to select all products from the Products Table
     * in the music database
     * @return a list of all Music Products in the database
     */
    public static List<Product> selectAllProducts() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(MusicConstants.PRODUCT_TABLE_NAME);
        
        try {
            ps = connection.prepareStatement(sb.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                productList.add(asProduct(rs));
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }

    /**
     * Delete method for removing a particular item from the database
     * @param product music product to be deleted from the database
     * @return 0 upon failure, another number upon success
     */
    public static int deleteProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                .append(MusicConstants.PRODUCT_TABLE_NAME)
                .append(" WHERE ")
                .append(MusicConstants.PRODUCT_CODE_COLUMN)
                .append(" = ?");
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, product.getCode());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    /**
     * Update method for updating a single music product in the database
     * @param product product to be updated with new values, code and ID cannot be updated
     * @return 0 upon failure, another number upon success
     */
    public static int updateProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                .append(MusicConstants.PRODUCT_TABLE_NAME)
                .append(" SET ")
                .append(MusicConstants.PRODUCT_DESCRIPTION_COLUMN)
                .append(" = ?, ")
                .append(MusicConstants.PRODUCT_PRICE_COLUMN)
                .append(" = ? WHERE ")
                .append(MusicConstants.PRODUCT_CODE_COLUMN)
                .append(" = ?");
        
        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, product.getDescription());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCode());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    /**
     * Outside access for checking to see if a product exists.
     * from inside this class use the productCodeExists(String, Connection) method
     * This method will assign a new connection to the database
     * @param productCode The code that should be checked for existence in the database
     * @return boolean value whether the product code exists in the database
     */
    public static boolean productCodeExists(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        boolean returnVal = productCodeExists(productCode, connection);
        pool.freeConnection(connection);
        return returnVal;
    }

    /**
     * Method to check if a product exists in the database
     * Use this method if a connection to the database already exists
     * @param productCode product code to check for existence
     * @param connection connection to the music database
     * @return boolean value whether the product code exists in the database
     */
    private static boolean productCodeExists(String productCode, Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
                .append(MusicConstants.PRODUCT_CODE_COLUMN)
                .append(" FROM ")
                .append(MusicConstants.PRODUCT_TABLE_NAME)
                .append(" WHERE ")
                .append(MusicConstants.PRODUCT_CODE_COLUMN)
                .append(" = ?");

        try {
            ps = connection.prepareStatement(sb.toString());
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }

    /**
     * Helper method to convert a ResultSet to a product object
     * @param rs ResultSet from executing a query against the product table in the music database
     * @return a Product representation of the result set
     * @throws SQLException 
     */
    private static Product asProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        try {
            product.setCode(rs.getString(MusicConstants.PRODUCT_CODE_COLUMN));
            product.setId(rs.getLong(MusicConstants.PRODUCT_ID_COLUMN));
            product.setDescription(rs.getString(MusicConstants.PRODUCT_DESCRIPTION_COLUMN));
            product.setPrice(rs.getDouble(MusicConstants.PRODUCT_PRICE_COLUMN));
            return product;
        } catch (SQLException e) {
            System.out.println("Error converting ResultSet to Product");
            throw e;
        }
    }
}
