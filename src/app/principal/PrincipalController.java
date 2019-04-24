/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.principal;

import app.FXMain;

import java.io.InputStream;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import app.clients.ClientsController;
import app.apartament.ApartamentController;
import app.products.ProductsController;
import app.reserves.ReservesController;

/**
 * FXML Controller class
 *
 * @author ricard
 */
public class PrincipalController implements Initializable {
    
   
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void apartamentsView(ActionEvent event){
        try {
            this.stage = new Stage();
            stage.setTitle("GESTION DE HABITACIONES");   
            ApartamentController apartament = (ApartamentController) replaceSceneContent("apartament/apartamentView.fxml");
            apartament.setStage(stage);
            stage.show();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void productsView(ActionEvent event){
        try {
            this.stage = new Stage();
            stage.setTitle("GESTION DE PRODUCTOS");
            ProductsController productes = (ProductsController) replaceSceneContent("products/ProductsView.fxml");
            productes.setStage(stage);
            stage.show();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void clientView(ActionEvent event){
        try {
            this.stage = new Stage();
            stage.setTitle("GESTION DE CLIENTES"); 
            stage.setMinHeight(570);
            stage.setMinWidth(970);
            stage.setMaxHeight(570);
            stage.setMaxWidth(970);
            ClientsController clients = (ClientsController) replaceSceneContent("clients/clientsView.fxml");
            clients.setStage(stage);
            stage.show();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void reservesView(ActionEvent event){
        try {
            this.stage = new Stage();
            stage.setTitle("GESTION DE RESERVAS");
            ReservesController reserves = (ReservesController) replaceSceneContent("reserves/ReservesView.fxml");
            reserves.setStage(stage);
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
        Scene scene = new Scene(page, 970,570);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
}
