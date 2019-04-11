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
    
    public static Connection connectarMySQL(){
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("Error paquete");
            System.out.println(e.getMessage());
        }
        try{
            conn = DriverManager.getConnection(getConnectionDB(), getUserDB(), getPasswordBD());

        } catch(SQLException e){
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        return conn;
    }
    
    public static String getConnectionDB(){
        String database = "java_project";
        String hostname = "192.168.0.15";
        String port = "3306";
        String url = "jdbc:mysql://" + hostname +":"+ port+"/" + database;
        return url;
    };
    
    public static String getUserDB(){
        return "root";
    };
    
    public static String getPasswordBD(){
        return "";
        
    };
}
