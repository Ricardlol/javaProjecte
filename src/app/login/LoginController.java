package app.login;

import app.model.Authentication;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 * @web http://www.jc-mouse.net/
 * @author jc mouse
 */
public class LoginController implements Initializable {

    @FXML Button btnOk;
    @FXML TextField txtUser;
    @FXML TextField txtPass;
    @FXML Label txtMsgError;
    
    private Stage stage;
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //oculta mensaje de error
        txtMsgError.setVisible(false);
        
         //cuando cualquier de los  textfield gane foco 
         //si existe mensaje de error visible -> se oculta
        txtUser.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                txtMsgError.setVisible(false);
             }
        });
        txtPass.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                txtMsgError.setVisible(false);
             }
        });
    }     
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
}//LoginController:end
