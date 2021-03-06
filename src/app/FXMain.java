package app;

import app.clients.ClientsController;
import app.login.LoginController;
import app.principal.PrincipalController;
import java.io.InputStream;
import java.security.Principal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * @web http://www.jc-mouse.net/
 * @author jc mouse
 */
public class FXMain extends Application {
    
    private Stage stage;    
    
    @Override
    public void start(Stage stage) throws Exception {
         try {
            this.stage = stage;
            stage.setTitle("ACCESS USUARIS");   
            LoginController login = (LoginController) replaceSceneContent("login/LoginView.fxml");
            login.setStage(stage);
            stage.show();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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
        Scene scene = new Scene(page, 950,570);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
