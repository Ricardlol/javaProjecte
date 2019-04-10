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
public class Clients implements Connectionsdb, actions{
    
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String tabla = "cliente";
    
    private void connection(){
        conn = Connectionsdb.connectarMySQL();
        try{
            stmt=conn.createStatement();
        }catch(SQLException e){
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    public void create(String documnetation, String client, String tel, String email, String nac, String ocupation, String status) {
        connection();
        sSQL ="INSERT INTO "+tabla+" VALUES ('"+documnetation+"', '"+client+"', '"+nac+"',"+tel+", '"+email+"', '"+ocupation+"', '"+status+"');";
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

    public void modify(String documnetation, String client, String tel, String email, String nac, String ocupation, String status) {
        connection();
        sSQL ="UPDATE "+tabla+" SET nombre ='"+client+"', nacionalidad='"+nac+"', telefono='"+tel+"', email='"+email+"', ocupacion='"+ocupation+"', estadoCivil='"+ status+"' WHERE documento='"+documnetation+"';";
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
    public void search() {
        String connexString = Connectionsdb.getConnectionDB();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(String id) {
        connection();
        sSQL ="DELETE FROM "+tabla+" WHERE documento= '"+id+"';";
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

    
    
}

