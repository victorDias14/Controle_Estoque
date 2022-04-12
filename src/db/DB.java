package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    
    private static Connection conn = null;
    private static String url = "jdbc:mysql://localhost:3306/controle_estoque";
    private static String usuario = "root";
    private static String senha = "123456";

    public static Connection getConnection(){
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, usuario, senha);
            }

            catch(SQLException e) {
                throw new DbException(e.getMessage());
            }            
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }

            catch(SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            }

            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultset(ResultSet rs ){
        if (rs != null) {
            try {
                rs.close();
            }
            
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
