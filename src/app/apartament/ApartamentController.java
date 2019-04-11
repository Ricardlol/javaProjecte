/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.apartament;

import app.model.Apartament;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML Label errorTextHabit;
    @FXML Label errorTextPiso;
    @FXML Label errorTextCara;
    @FXML Label errorTextPrecio;
    @FXML Label errorTextEstado;
    @FXML Label errorTextTipo;
    @FXML Label errorTextGlobal;
    
    // menuButton in page
    @FXML MenuButton optionDoc;
    
    // list in page
    @FXML ListView listView;
    
    
    private Apartament apartamentObj;
    
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apartamentObj = new Apartament();
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
            while(result.next()) {
                String id = result.getString("id");
                String habitacion = result.getString("nhabitacion");
                System.out.println(id +" "+ habitacion+"\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
}
