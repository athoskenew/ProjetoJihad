package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory{
    public Connection getConnection(){
        try {
             return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bits_SGF", "postgres", "1234");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}