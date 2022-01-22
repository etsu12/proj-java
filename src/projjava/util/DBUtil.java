/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projjava.util;

import com.mysql.jdbc.CachedResultSetMetaData;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.sql.rowset.*;


/**
 *
 * @author Zang
 */
public class DBUtil {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    private static Connection connection = null;
    
    private static final String connStr = "jdbc:mysql://localhost/javadb";
    
    public static void dbConnect() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch(ClassNotFoundException e) {
            System.out.println("Gdzie jest twoj Driver?!");
            e.printStackTrace();
            throw e;
        }
        
        System.err.println("JDBC Driver zostal zarejestrowany");
        
        try{
            connection = (Connection) DriverManager.getConnection(connStr, "root","");
        }
        catch(SQLException e) {
            System.out.println("Polaczenie nie powiodlo sie");
            throw e;
        }
    }
    
    public static void dbDisconnect() throws SQLException {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public static void dbExecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = (Statement) connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
        catch(SQLException e) {
            System.out.println("Problem przy dbExecuteQuery"+e);
            throw e;
        }
        finally {
            if(stmt!=null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
    
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSet crs = null;
                
        
        try {
            dbConnect();
            stmt = (Statement) connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            
        }
        catch(SQLException e) {
            System.err.println("Błąd pzy dbExecute"+e);
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
}
