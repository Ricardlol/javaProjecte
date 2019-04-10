/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author daw2
 */
public class Clients implements Connectionsdb{
    
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public Clients(){
        conn = Connectionsdb.connectarMySQL();
        try{
            stmt=conn.createStatement();
        }catch(SQLException e){
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    @Override
    public void buscar() {
        String connexString = Connectionsdb.getConnectionDB();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crear() {
        sSQL ="INSERT INTO cliente VALUES ('53642474W', 'Ricard', 'España',"+ 635640728+", 'ricard@gmail.com', 'Practicas', 'Soltero');";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

