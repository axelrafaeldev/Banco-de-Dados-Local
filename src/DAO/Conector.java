package DAO;

/**
 *
 * @author Axel
 */

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;
//Criando a conexão com o banco de dados , arquivo jar inserido na biblioteca
public class Conector {
    
    public Connection conectaBD(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/bancoteste?user=root&password=";
            conn = DriverManager.getConnection(url);
    }catch (SQLException e){
        JOptionPane.showMessageDialog(null,"CONEXÃO DAO"+ e.getMessage());
    }
        return conn;
    }
}
