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
public class Apartamento {
    StringProperty habitacion;
    StringProperty piso;
    StringProperty estado;
    StringProperty tipo;
    StringProperty caracteristicas;
    StringProperty precio;
    
    public Apartamento(ResultSet result) throws SQLException {
        habitacion= new SimpleStringProperty(result.getString("nhabitacion"));
        piso=new SimpleStringProperty(result.getString("piso"));
        estado=new SimpleStringProperty(result.getString("estado"));
        tipo=new SimpleStringProperty(result.getString("tipo"));
        caracteristicas=new SimpleStringProperty(result.getString("caracteristicas"));
        precio=new SimpleStringProperty(result.getString("precio"));
    }
    public String getHabitacion(){
        return habitacion.get();
    }
    public String getPiso(){
        return piso.get();
    }
    public String getEstado(){
        return estado.get();
    }
    public String getTipo(){
        return tipo.get();
    }
    public String getCarac(){
        return caracteristicas.get();
    }
    public String getPrecio(){
        return precio.get();
    }
}
