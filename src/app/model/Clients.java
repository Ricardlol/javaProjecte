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
 * @author ricardLopez & joseManuel
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
    
    /**
     * creacio dun registre a la taula cliente
     * @param documnetation documentacion del cliente
     * @param client nombre del cliente
     * @param tel telefono del cliente
     * @param email email del cliente
     * @param nac nacionalidad del cliente
     * @param ocupation ocupacion del cliente
     * @param status estado del cliente
     */
    public void create(String documnetation, String client, String tel, String email, String nac, String ocupation, String status) {
        connection();
        sSQL ="INSERT INTO "+tabla+" VALUES ('"+documnetation+"', '"+client+"', '"+nac+"',"+tel+", '"+email+"', '"+ocupation+"', '"+status+"');";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Cliente creado");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }
    
    /**
     * modificacio dun registre a la taula cliente
     * @param documnetation documentacion del cliente
     * @param client nombre del cliente
     * @param tel telefono del cliente
     * @param email email del cliente
     * @param nac nacionalidad del cliente
     * @param ocupation ocupacion del cliente
     * @param status estado del cliente
     */
    public void modify(String documnetation, String client, String tel, String email, String nac, String ocupation, String status) {
        connection();
        sSQL ="UPDATE "+tabla+" SET nombre ='"+client+"', nacionalidad='"+nac+"', telefono='"+tel+"', email='"+email+"', ocupacion='"+ocupation+"', estadoCivil='"+ status+"' WHERE documento='"+documnetation+"';";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Cliente modificado");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }
    
    /**
     * buscar un registre en la taula clente
     * @param id documento del client a buscar
     * @return 
     */
    @Override
    public Object search(String id) {
        Object rsend=null;
        connection();
        sSQL ="SELECT * FROM "+tabla+" WHERE documento LIKE('"+id+"') OR nombre LIKE('"+id+"') OR nacionalidad LIKE('"+id+"') OR email LIKE('"+id+"') OR telefono LIKE('"+id+"');";
        try {
            rs=stmt.executeQuery(sSQL);
            rsend=rs;
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return rsend;
    }
    
    /**
     * elimina un registre a la taula cliente
     * @param id documento del client a eliminar
     * @param id2 es null per tant no s'utilitza
     */
    @Override
    public void delete(String id, String id2) {
        connection();
        sSQL ="DELETE FROM "+tabla+" WHERE documento= '"+id+"';";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Cliente Eliminado");
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

