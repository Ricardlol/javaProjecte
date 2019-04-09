/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw2
 */
public class Clients implements Connectionsdb{
    
    private Connection conn;
    private String sSQL="";
    
    public Clients(){
        conn = Connectionsdb.connectarMySQL();
    }
    
    @Override
    public void buscar() {
        String connexString = Connectionsdb.getConnectionDB();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crear() {
        sSQL ="INSERT INTO cliente VALUES (53642474B, Ricard, España, 635640728, ricard@gmail.com, Practicas, Soltero)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sSQL);
        } catch (SQLException ex) {
            System.out.println("Error al insertar");
        }
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

