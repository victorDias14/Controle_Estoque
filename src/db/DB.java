package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    
    private static Connection conn = null;
    private static String dadosConn = "jdbc:mysql://localhost:3306/controle_estoque";
    private static String usuario = "root";
    private static String senha = "123456";

    public static Connection getConnection(){
        if (conn == null) {
            try {
                //Properties props = loadProperties();
                //String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(dadosConn, usuario, senha);
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
}
