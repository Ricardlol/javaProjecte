/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.apartament;

import app.model.Apartament;
import app.model.Apartamento;
import app.model.Authentication;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ApartamentController implements Initializable {
    
    // all TextFields in page
    @FXML TextField nHab;
    @FXML TextField piso;
    @FXML TextArea caract;
    @FXML TextField precio;
    @FXML TextField tipo;
    @FXML TextField nhabSearch;
    
    // all Labels in page
    @FXML Label errorTextGlobal;
    @FXML Label Succesful;

    
    // menuButton in page
    @FXML MenuButton estado;
    
    // table in page
    @FXML TableView tabla;
    @FXML TableColumn cNhabitacion;
    @FXML TableColumn cPiso;
    @FXML TableColumn cEstado;
    @FXML TableColumn cTipo;
    
    // Botons
    @FXML Button save;
    @FXML Button modify;
    @FXML Button delete;
    
    private ObservableList<Apartamento> apartamentoData;
    
    private Apartament apartamentObj;
    
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ocultarMensajes();
        cNhabitacion.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("habitacion"));
        cPiso.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("piso"));
        cEstado.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("estado"));
        cTipo.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("tipo"));
        
        apartamentObj = new Apartament();
        
        if(Authentication.getTipus()==0){
            deshabilitarBtn();
        }
        tabla.setEditable(false);
        tabla.setRowFactory(tv -> {
           TableRow<Apartamento> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
               if(event.getClickCount() == 2 && (! row.isEmpty())){
                   Apartamento rowData = row.getItem();
                   nHab.setText(rowData.getHabitacion());
                   piso.setText(rowData.getPiso());
                   caract.setText(rowData.getCarac());
                   precio.setText(rowData.getPrecio());
                   tipo.setText(rowData.getTipo());
                   estado.setText(rowData.getEstado());
               }
           });
           return row;
        });
    }
    
    public void changeOcupado(){
        estado.setText("OCUPADO");
    }
    
    public void changeLibre(){
        estado.setText("LIBRE");
    }
    
    private void deshabilitarBtn(){
        save.setDisable(true);
        modify.setDisable(true);
        delete.setDisable(true);
    }
    
    public void btnSave(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            Succesful.setText("Se ha CREADO con éxito");
            Succesful.setVisible(true);
            apartamentObj.create(nHab.getText(), piso.getText(), caract.getText(), precio.getText(), estado.getText(), tipo.getText());
    
        }else{
            errorTextGlobal.setVisible(true);
        }
    }
    
    public void btnModify(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            Succesful.setText("Se ha MODIFICADO con éxito");
            Succesful.setVisible(true);
            apartamentObj.modify(nHab.getText(), piso.getText(), caract.getText(), precio.getText(), estado.getText(), tipo.getText());
        }else{
            errorTextGlobal.setVisible(true);
        }
    }
    
    public void btnDelete(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            Succesful.setText("Se ha ELIMINADO con éxito");
            Succesful.setVisible(true);
            apartamentObj.delete(nHab.getText(), piso.getText());
        }else{
            errorTextGlobal.setVisible(true);
        }
        
    }
    
    public void btnSearch(){
        ResultSet result = (ResultSet) apartamentObj.search(nhabSearch.getText());
        try {
            apartamentoData = FXCollections.observableArrayList();
            while(result.next()) {
                Apartamento apartamento = new Apartamento(result);
                apartamentoData.add(apartamento);
            }
            tabla.setItems(apartamentoData);
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    private void ocultarMensajes(){
       errorTextGlobal.setVisible(false);
       Succesful.setVisible(false);
    }
    
    private boolean comprovarCampos(){
        /*
        @FXML TextField nHab;
    @FXML TextField piso;
    @FXML TextArea caract;
    @FXML TextField precio;
    @FXML TextField estado;
    @FXML TextField tipo;
        */
        boolean result = true;
        if(nHab.getText().length()==0){
            result = false;
        }
        else if(piso.getText().length()==0){
            result = false;
        }
        else if(caract.getText().length()==0){
            result = false;
        }
        else if(precio.getText().length()==0){
            result = false;
        }
        else if(tipo.getText().length()==0){
            result = false;
        }
        else if(estado.getText().equals("---")){
            result = false;
        }
        return result;
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
}
