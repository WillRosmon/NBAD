/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wrosmon
 */
public class DBUtil {
    public static void closeStatement(Statement s) {
        try {
            if( s != null) {
                s.close();
            }
        } catch (SQLException e) {
              System.out.println(e.getMessage());      
        }
    }
    
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void closeResultSet (ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
