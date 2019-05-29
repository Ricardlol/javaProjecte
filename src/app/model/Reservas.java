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
public class Reservas {
    StringProperty cliente;
    StringProperty importe;
    StringProperty abono;
    StringProperty habitacion;
    StringProperty fechaIn;
    StringProperty horaIn;
    StringProperty fechafi;
    StringProperty horafi;
    
    public Reservas(ResultSet result) throws SQLException {
        cliente= new SimpleStringProperty(result.getString("fk_cliente"));
        importe=new SimpleStringProperty(result.getString("importe"));
        abono=new SimpleStringProperty(result.getString("abono"));
        habitacion=new SimpleStringProperty(result.getString("fk_habitacion"));
        fechaIn=new SimpleStringProperty(result.getString("fehaIn"));
        horaIn=new SimpleStringProperty(result.getString("horaIn"));
        fechafi=new SimpleStringProperty(result.getString("fechafi"));
        horafi=new SimpleStringProperty(result.getString("horafi"));
    }
    
    public String getCliente(){
        return cliente.get();
    }
    public String getImporte(){
        return importe.get();
    }
    public String getAbono(){
        return abono.get();
    }
    public String getHabitacion(){
        return habitacion.get();
    }
    public String getFechaIn(){
        return fechaIn.get();
    }
    public String getHoraIn(){
        return horaIn.get();
    }
    public String getFechafi(){
        return fechafi.get();
    }
    public String getHorafi(){
        return horafi.get();
    }
}
