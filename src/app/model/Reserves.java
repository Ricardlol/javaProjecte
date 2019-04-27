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
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author daw2
 */
public class Reserves implements Connectionsdb, actions{
    private Connection conn;
    private String sSQL="";
    private Statement stmt = null;
    private ResultSet rs = null;
    private String tabla = "reserva";
    
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
    public void create(String client, String apto, String usu, LocalDate fechaIn, String horaIn ,LocalDate fechaOut, String horaOut, String imp, String abo) {
    
    //public void create(String client, String apto, String usu, String fechaIn, String horaIn ,String fechaOut, String horaOut, String imp, String abo) {
        connection();
        //System.out.println(java.sql.Date.valueOf(fechaIn));
        //System.out.println(java.sql.Date.valueOf(fechaOut));
        sSQL ="INSERT INTO "+tabla+" (fk_cliente, fk_habitacion, fk_usuario, fehaIn, horaIn, fechafi, horafi, importe, abono ) VALUES ('"+client+"', '"+apto+"', '"+usu+"','"+java.sql.Date.valueOf(fechaIn)+"', '"+horaIn+"', '"+java.sql.Date.valueOf(fechaOut)+"', '"+horaOut+"', '"+imp+"', '"+abo+"');";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Reserva creada");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }

    public void modify(String client, String apto, String usu, LocalDate fechaIn, String horaIn ,LocalDate fechaOut, String horaOut, String imp, String abo, String id) {
        connection();
        //System.out.println(client+" "+ apto+" "+ usu+" "+ fechaini.getValue()+" "+ horaEntrada.getText()+" "+ fechafin.getValue()+" "+ horaSalida.getText()+" "+ Import.getText()+" "+ "Pendiente");
        //sSQL ="UPDATE "+tabla+" SET fk_cliente ='"+client+"', fk_habitacion='"+apto+"', fk_usuario='"+usu+"', fechaIn='"+java.sql.Date.valueOf(fechaIn)+"', horaIn='"+horaIn+"', fechafi='"+ java.sql.Date.valueOf(fechaOut)+"', horafi='"+ horaOut+"', , importe='"+ imp+"', abono='"+ abo+"' WHERE fk_habitacion='"+apto+"';";
        sSQL ="UPDATE "+tabla+" SET fk_cliente ='"+client+"', fk_habitacion='"+apto+"', fk_usuario='"+usu+"', fehaIn='"+java.sql.Date.valueOf(fechaIn)+"', horaIn='"+horaIn+"', fechafi='"+ java.sql.Date.valueOf(fechaOut)+"', horafi='"+ horaOut+"', importe='"+ imp+"', abono='"+ abo+"' WHERE id='"+id+"';";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Reserva Modificada");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            Connectionsdb.cerrarConnect(rs,stmt);
        }
    }
    
    public Object getNumeroReserva(){
        System.out.println("Max num reserva");
        Object rsend=null;
        connection();
        sSQL ="SELECT * FROM "+tabla+" WHERE id IN (SELECT MAX(id) FROM "+tabla+");";
        
       // sSQL ="SELECT * FROM "+tabla+" WHERE id='23';";
        //SELECT MAX(id)FROM reserva;
        try {
            rs=stmt.executeQuery(sSQL);
            rsend=rs;
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{            
            return rsend;
        }
    }
    
    
    
    @Override
    public Object search(String cliente) {
        Object rsend=null;
        connection();
        sSQL ="SELECT * FROM "+tabla+" WHERE fk_cliente='"+cliente+"';";
        try {
            rs=stmt.executeQuery(sSQL);
            rsend=rs;
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }finally{
            //Connectionsdb.cerrarConnect(rs,stmt);
            return rsend;
        }
    }
    
    @Override
    public void delete(String id, String cliente) {
        connection();
        sSQL ="DELETE FROM "+tabla+" WHERE id= '"+id+", fk_cliente="+cliente+"';";
        try {
            if(stmt.execute(sSQL)){
                rs=stmt.getResultSet();
                System.out.println("Reserva Eliminada");
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
