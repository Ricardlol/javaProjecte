/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ricard
 */
public class Products {
    StringProperty nom;
    StringProperty descrip;
    StringProperty precio;
    
    public Products(ResultSet result) throws SQLException {
        nom=new SimpleStringProperty(result.getString("nombre"));
        precio=new SimpleStringProperty(result.getString("precio"));
    }
    
    public String getNom(){
        return nom.get();
    }
    
    public String getDescrip(){
        return descrip.get();
    }
    
    public void setDescrip(String desc){
        this.descrip=new SimpleStringProperty(desc);
    }
    
    public String getPrecio(){
        return precio.get();
    }
    
}
