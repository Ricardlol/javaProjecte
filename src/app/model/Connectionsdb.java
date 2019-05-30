/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author daw2
 */
public interface Connectionsdb {
    
    final String database = "java_project";
    final String hostname = "192.168.12.169";
    final String port = "3306";
    final String user =  "root";
    final String password =  "";
    
    /**
     * tanca la connecxio a la base de dades
     * @param rs es el resultat de la consulta
     * @param stmt l'estat de la consulta
     */
    public static void cerrarConnect(ResultSet rs, Statement stmt){
        if(rs !=null){
                try{
                    rs.close();
                }catch(SQLException e){}
            }
            
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
    };
    
    /**
     * es conecta a la base de dades mysql
     * @return retorna la connecxio per poder fer les consultes
     */
    public static Connection connectarMySQL(){
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("Error paquete");
            System.out.println(e.getMessage());
        }
        try{
            conn = DriverManager.getConnection(getConnectionDB(), user, password);

        } catch(SQLException e){
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return conn;
    }
    /**
     * crea la url per connectarnos a la base de dades
     * @return retorna la url a la qual en conectarem 
     */
    public static String getConnectionDB(){
        return "jdbc:mysql://" + hostname +":"+ port+"/" + database;
    };
}
