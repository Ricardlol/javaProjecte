/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.apartament;

import app.model.Clients;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
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
    @FXML TextField caract;
    @FXML TextField email;
    @FXML TextField nac;
    @FXML TextField ocupation;
    @FXML TextField status;
    @FXML TextField nameSearch;
    
    // all Labels in page
    @FXML Label errorTextDocumentation;
    @FXML Label errorTextName;
    @FXML Label errorTextTel;
    @FXML Label errorTextEmail;
    @FXML Label errorTextNac;
    @FXML Label errorTextOc;
    @FXML Label errorTextCiv;
    @FXML Label errorTextIncorrectEmail;
    
    // menuButton in page
    @FXML MenuButton optionDoc;
    
    // list in page
    @FXML ListView listView;
    
    
    private Clients clientobj;
    
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     public void btnSave(){
        clientobj.create(documnetation.getText(), client.getText(), tel.getText(), email.getText(), nac.getText(), ocupation.getText(), status.getText());
        /*ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            System.out.println("A la base de datos !!!");
        } else{
            System.out.println("Pon los bien puto");
        }*/
    }
    
    public void btnModify(){
        clientobj.modify(documnetation.getText(), client.getText(), tel.getText(), email.getText(), nac.getText(), ocupation.getText(), status.getText());
    }
    
    public void btnDelete(){
        clientobj.delete(documnetation.getText());
    }
    
    public void btnSearch(){
        ResultSet result = (ResultSet) clientobj.search(nameSearch.getText());
        try {
            while(result.next()) {
                String name = result.getString("nombre");
                System.out.println(name + "\n");
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
