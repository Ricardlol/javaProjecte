package app.login;


import app.FXMain;
import app.model.Authentication;
import app.principal.*;
import app.model.User;
import app.principal.PrincipalController;
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
 * @author ricardLopez & joseManuel
 */
public class LoginController implements Initializable {

    @FXML Button btnOk;
    @FXML TextField txtUser;
    @FXML TextField txtPass;
    @FXML Label txtMsgError;
    
    //private Authentication authenticationObj;
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

        //cuando cualquier de los textfield gane foco
        //si existe mensaje de error visible -> se oculta
        txtUser.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                txtMsgError.setVisible(false);
            }
        });
        txtPass.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
        Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                txtMsgError.setVisible(false);
            }
        });
    }     
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public void verifyUserData(ActionEvent event) throws Exception{
        Authentication authentication = new Authentication(txtUser.getText(), txtPass.getText());
        //verifica que datos introducidos sean correctos
        boolean response = authentication.verifyUserData(txtUser.getText(), txtPass.getText());
      
        
       if( response ){ // los datos son correctos
            //reemplaza el stage actual por el de la vista "principalView"
            try {
                this.stage = stage;
                if(Authentication.getTipus()==1){
                    //Si el tipo es 1 el usuario es administrador
                    stage.setTitle("ADMINISTRADOR");
                }else{
                    //Si no es un usuario recepcionista
                    stage.setTitle("RECEPCIONISTA");
                }                   
                PrincipalController principal = (PrincipalController) replaceSceneContent("principal/principalView.fxml");
                principal.setStage(stage);
                stage.show();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        else{
            txtMsgError.setVisible(true);
        }
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = FXMain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(FXMain.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 970,570);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
}//LoginController:end
