/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.products;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import com.mysql.jdbc.Connection;

/**
 * FXML Controller class
 *
 * @author daw2
 */
public class ProductsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Button btnOk;
    @FXML TextField documnetation;
    @FXML TextField client;
    @FXML TextField tel;
    @FXML TextField email;
    @FXML TextField nac;
    @FXML TextField ocupation;
    @FXML TextField status;
    @FXML Label errorTextDocumentation;
    @FXML Label errorTextName;
    @FXML Label errorTextTel;
    @FXML Label errorTextEmail;
    @FXML Label errorTextNac;
    @FXML Label errorTextOc;
    @FXML Label errorTextCiv;
    
    public ProductsController(){
        
    }
    
    private Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //oculta mensaje de error
        errorTextDocumentation.setVisible(false);
        errorTextName.setVisible(false);
        errorTextTel.setVisible(false);
        errorTextEmail.setVisible(false);
        errorTextNac.setVisible(false);
        errorTextOc.setVisible(false);
        errorTextCiv.setVisible(false);
        
        //cuando cualquier de los  textfield gane foco 
        //si existe mensaje de error visible -> se oculta
        
        documnetation.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextDocumentation.setVisible(false);
             }
        });
        
        client.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextName.setVisible(false);
             }
        });
        
        tel.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextName.setVisible(false);
             }
        });
        
        email.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextName.setVisible(false);
             }
        });
        
        nac.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextName.setVisible(false);
             }
        });
        
        ocupation.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextName.setVisible(false);
             }
        });
        
        status.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                errorTextName.setVisible(false);
             }
        });
    }
    public void setStage (Stage stage){
        this.stage = stage;
    }   
    
}
