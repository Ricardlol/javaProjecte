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
 * @author ricard
 */
public class Productes implements Connectionsdb, actions{
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String id="";
    private final String tabla1 = "extras";
    private final String tabla2 = "gestionProductos";
    
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
    
    private void insertProduct(String nombre, String precio) throws SQLException{
        sSQL ="INSERT INTO "+tabla1+" (nombre, precio) VALUES ('"+nombre+"', '"+precio+"');";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    private void insertDescrip(String descripcion) throws SQLException{
        sSQL ="INSERT INTO "+tabla2+" VALUES ('"+id+"', '"+descripcion+"');";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    public void create(String nombre, String precio, String descripcion) {
        connection();
        try{
            insertProduct(nombre, precio);
            searchId(nombre);
            insertDescrip(descripcion);
        }catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }
    
    private void modProduct(String nombre, String precio) throws SQLException{
        sSQL ="UPDATE "+tabla1+" SET nombre='"+nombre+"', precio='"+precio+"' WHERE id='"+id+"';";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    private void modDescrip(String descripcion) throws SQLException{
        sSQL ="UPDATE "+tabla2+" SET descripcion='"+descripcion+"'WHERE fk_pk_productos='"+id+"';";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    public void modify(String nombre, String precio, String descripcion) {
        connection();
        try{
            searchId(nombre);
            modProduct(nombre, precio);
            modDescrip(descripcion);
        }catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }
    
    private void searchId(String nombre) {
        connection();
        sSQL ="SELECT * FROM "+tabla1+" WHERE nombre='"+nombre+"';";
        try {
            rs=stmt.executeQuery(sSQL);
            while(rs.next()){
                this.id = rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    @Override
    public Object search(String nombre) {
        Object rsend=null;
        connection();
        sSQL ="SELECT * FROM "+tabla1+" WHERE nombre='"+nombre+"';";
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
    
    private void elimnairProd(String idEl, String tablaAc){
        if(tablaAc.equals(this.tabla1)){
            sSQL ="DELETE FROM "+tablaAc+" WHERE id= '"+idEl+"';";
        }else{
            sSQL ="DELETE FROM "+tablaAc+" WHERE fk_pk_productos= '"+idEl+"';";
        }
        
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    @Override
    public void delete(String nombre, String id2) {
        connection();
        searchId(nombre);
        elimnairProd(id, tabla1);
        elimnairProd(id, tabla2);
        Connectionsdb.cerrarConnect(rs,stmt);
        
    }
}
