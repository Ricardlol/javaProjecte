/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author daw2
 */
public interface Connectionsdb {
    public void buscar();
    public void crear();
    public void modificar();
    public void eliminar();
    public static Connection connectarMySQL(){
        Connection conn = null;

        try {
            Class.forName(geDriver());
            conn = DriverManager.getConnection(getConnectionDB(), getUserDB(), getPasswordBD());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static String getConnectionDB(){
        String database = "java_project";
        String hostname = "192.168.12.41";
        //String port = "3306";
        String url = "jdbc:mysql://" + hostname + "/" + database + "?useSSL=false";
        System.out.println(url);
        return url;
    };

    public static String geDriver(){
        return "com.mysql.jdbc.Driver";
    }
    public static String getUserDB(){
        return "root";
    };
    public static String getPasswordBD(){
        return "";
        
    };
}
