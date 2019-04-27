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
 * @author ricardLopez y JoseManuel
 */
public class Productes implements Connectionsdb, actions{
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String id="";
    private final String tabla1 = "extras";
    private final String tabla2 = "gestionProductos";
    
    // Connecta amb la base de dades
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
     * inserta en la taula extras el producte
     * @param nombre nom del producte
     * @param precio preu del producte
     * @throws SQLException 
     */
    private void insertProduct(String nombre, String precio) throws SQLException{
        sSQL ="INSERT INTO "+tabla1+" (nombre, precio) VALUES ('"+nombre+"', '"+precio+"');";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    /**
     * inserta registre en la taula gestionProductos
     * @param descripcion descripcio del producte
     * @throws SQLException 
     */
    private void insertDescrip(String descripcion) throws SQLException{
        sSQL ="INSERT INTO "+tabla2+" VALUES ('"+id+"', '"+descripcion+"');";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    /**
     * inserta el les tales extras y gestionProductos un registre
     * @param nombre nom del producte
     * @param precio preu del producte
     * @param descripcion descripcio del producte 
     */
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
    
    /**
     * modifica el producte de la taula extras
     * @param nombre nom del producte
     * @param precio preu del producte
     * @throws SQLException 
     */
    private void modProduct(String nombre, String precio) throws SQLException{
        sSQL ="UPDATE "+tabla1+" SET nombre='"+nombre+"', precio='"+precio+"' WHERE id='"+id+"';";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    /**
     * modifica el producte de la taula gestionProductos
     * @param descripcion descripcio del producte
     * @throws SQLException 
     */
    private void modDescrip(String descripcion) throws SQLException{
        sSQL ="UPDATE "+tabla2+" SET descripcion='"+descripcion+"'WHERE fk_pk_productos='"+id+"';";
        if(stmt.execute(sSQL)){
            rs=stmt.getResultSet();
        }
    }
    
    /**
     * modificacio del producte
     * @param nombre nom del producte
     * @param precio preu del producte
     * @param descripcion  descricio del producte
     */
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
    
    /**
     * opté el id del producte i el guarda en uan variable
     * @param nombre nom del producte
     */
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
    
    /**
     * opté totes les dades d'un producte de la taula extras
     * @param nombre nom del producte
     * @return retona les dades que del producte buscat
     */
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
    
    /**
     * opté totes les dades d'un producte de la taula extras
     * @param id id del producte el qual volem la descripcio
     * @return retona les dades que del producte buscat
     */
    
    public Object searchDescricio(String id) {
        Object rsend=null;
        connection();
        sSQL ="SELECT * FROM "+tabla2+" WHERE fk_pk_productos='"+id+"';";
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
     * elimina el registe en la taula extras
     * @param idEl id del producte a eliminar
     * @param tablaAc taula de la que s'eliminara
     */
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
    /**
     * eliminacio del producte
     * @param nombre nom del producte
     * @param id2 null 
     */
    @Override
    public void delete(String nombre, String id2) {
        connection();
        searchId(nombre);
        elimnairProd(id, tabla1);
        elimnairProd(id, tabla2);
        Connectionsdb.cerrarConnect(rs,stmt);
        
    }
}
