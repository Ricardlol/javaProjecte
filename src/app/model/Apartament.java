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
public class Apartament implements Connectionsdb, actions{
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String tabla = "habitacion";
    
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
     * crear una entrada a la taula habitacion
     * @param nhab numero d'habitacio que insertarem
     * @param piso numero de pis
     * @param caract caracteristiques del pis
     * @param precio preu del pis
     * @param estado si esta lliure o ocupat
     * @param tipo el tipus del pis
     */
    
    public void create(String nhab, String piso, String caract, String precio, String estado, String tipo) {
        connection();
        sSQL ="INSERT INTO "+tabla+" (nhabitacion, piso, caracteristicas, precio, estado, tipo) VALUES ('"+nhab+"', '"+piso+"', '"+caract+"',"+precio+", '"+estado+"', '"+tipo+"');";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Abitación creada");
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
     * modifica una entrada de la taula habitacion
     * @param nhab numero d'habitacio
     * @param piso numero de pis
     * @param caract caracteristiques del pis
     * @param precio preu del pis
     * @param estado si esta lliure o ocupat
     * @param tipo el tipus del pis
     */
    public void modify(String nhab, String piso, String caract, String precio, String estado, String tipo) {
        connection();
        
        sSQL ="UPDATE "+tabla+" SET nhabitacion ='"+nhab+"', piso='"+piso+"', caracteristicas='"+caract+"', precio="+precio+", estado='"+estado+"', tipo='"+ tipo+"' WHERE nhabitacion='"+nhab+"';";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Apartamento modificado");
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
     * busca un registre en la taula habitacion
     * @param nhab numero d'habitacio
     * @return retorna totes les habilacion amb aquell numero
     */
    
    @Override
    public Object search(String nhab) {
        Object rsend=null;
        connection();
        sSQL ="SELECT * FROM "+tabla+" WHERE nhabitacion='"+nhab+"';";
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
     * eliminacio d'un registre de la taula habitacion
     * @param id numero de l'abitacio
     * @param id2 numero del pis
     */
    @Override
    public void delete(String id, String id2) {
        connection();
        sSQL ="DELETE FROM "+tabla+" WHERE nhabitacion= '"+id+", piso="+id2+"';";
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
