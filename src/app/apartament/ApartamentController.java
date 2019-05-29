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
    @FXML TextField estado;
    @FXML TextField tipo;
    @FXML TextField nhabSearch;
    
    // all Labels in page
    @FXML Label errorTextGlobal;
    
    // menuButton in page
    @FXML MenuButton optionDoc;
    
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
        cNhabitacion.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("habitacion"));
        cPiso.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("piso"));
        cEstado.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("estado"));
        cTipo.setCellValueFactory(new PropertyValueFactory<Apartamento,StringProperty>("tipo"));
        apartamentObj = new Apartament();
        if(Authentication.getTipus()==0){
            deshabilitarBtn();
        }
    }
    
    private void deshabilitarBtn(){
        save.setDisable(true);
        modify.setDisable(true);
        delete.setDisable(true);
    }
    
    public void btnSave(){
        System.out.println("Guardar");
        apartamentObj.create(nHab.getText(), piso.getText(), caract.getText(), precio.getText(), estado.getText(), tipo.getText());
    }
    
    public void btnModify(){
        apartamentObj.modify(nHab.getText(), piso.getText(), caract.getText(), precio.getText(), estado.getText(), tipo.getText());
    }
    
    public void btnDelete(){
        apartamentObj.delete(nHab.getText(), piso.getText());
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
    /*
    public float getPrecio(String apartamento){
        float precio = 0;
        ResultSet result = (ResultSet) apartamentObj.search(apartamento);
        try {
            while(result.next()) {
                precio = Float.parseFloat(result.getString("precio"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
        
        return precio;
    }*/
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
}
