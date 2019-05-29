/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.products;

import app.model.Productes;
import app.model.Authentication;
import app.model.Products;

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
public class ProductsController implements Initializable {
    // textfield de la pagina
    private @FXML TextField nombreProduct;
    private @FXML TextField precioProduct;
    private @FXML TextField prodBuscar;
    
    private @FXML TextArea descprod;
    
    // todos los labels de la pagina
    @FXML Label errorTextGlobal;
    @FXML Label Succesful;
    
    // table in page
    @FXML TableView tabla;
    @FXML TableColumn cNombre;
    @FXML TableColumn cPrecio;
    @FXML TableColumn cDes;
    
    // Botons
    @FXML Button save;
    @FXML Button modify;
    @FXML Button delete;
    
    private ObservableList<Products> productsData;
    /**
     * Initializes the controller class.
     */
    
    private Productes productes;
    
    private Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cNombre.setCellValueFactory(new PropertyValueFactory<Products,StringProperty>("nom"));
        cPrecio.setCellValueFactory(new PropertyValueFactory<Products,StringProperty>("precio"));
        cDes.setCellValueFactory(new PropertyValueFactory<Products,StringProperty>("descrip"));
        
        ocultarMensajes();
        tabla.setEditable(false);
        
        productes= new Productes();
        
        if(Authentication.getTipus()==0){
            deshabilitarBtn();
        }
        
        tabla.setRowFactory(tv -> {
           TableRow<Products> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
               if(event.getClickCount() == 2 && (! row.isEmpty())){
                   Products rowData = row.getItem();
                   nombreProduct.setText(rowData.getNom());
                   precioProduct.setText(rowData.getPrecio());
                   descprod.setText(rowData.getDescrip());
               }
           });
           return row;
        });
    }
    
    private void ocultarMensajes(){
       errorTextGlobal.setVisible(false);
       Succesful.setVisible(false);
    }
    
    private void deshabilitarBtn(){
        save.setDisable(true);
        modify.setDisable(true);
        delete.setDisable(true);
    }
    
    public void setStage (Stage stage){
        this.stage = stage;
    }
    
    public void btnSave(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            Succesful.setText("Se ha CREADO con éxito");
            Succesful.setVisible(true);
            productes.create(nombreProduct.getText(), precioProduct.getText(), descprod.getText());
        } else{
            errorTextGlobal.setVisible(true);
        }
    }
    
    public void btnModify(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            Succesful.setText("Se ha MODIFICADO con éxito");
            Succesful.setVisible(true);
            productes.modify(nombreProduct.getText(), precioProduct.getText(), descprod.getText());
        } else{
            errorTextGlobal.setVisible(true);
        }
    }
    
    public void btnDelete(){
        ocultarMensajes();
        boolean campos = comprovarCampos();
        if(campos){
            Succesful.setText("Se ha ELIMINADO con éxito");
            Succesful.setVisible(true);
            productes.delete(nombreProduct.getText(), descprod.getText());
        } else{
            errorTextGlobal.setVisible(true);
        }
        
    }
    
    public void btnSearch(){
        int i=1;
        ResultSet result = (ResultSet) productes.search(prodBuscar.getText());
        try {
            productsData = FXCollections.observableArrayList();
            while(result.next()) {
                Products producte = new Products(result);
                ResultSet resultdesc = (ResultSet) productes.searchDescricio(result.getString("id"));
                while(resultdesc.next()){
                    producte.setDescrip(resultdesc.getString("descripcion"));
                }
                productsData.add(producte);
            }
            tabla.setItems(productsData);
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
            System.out.println("SQLState"+ e.getSQLState());
            System.out.println("VendorError"+ e.getErrorCode());
        }
    }
    private boolean comprovarCampos(){
        boolean result = true;
        if(nombreProduct.getText().length()==0){
            result = false;
        }
        else if(precioProduct.getText().length()==0){
            result = false;
        }
        else if(descprod.getText().length()==0){
            result = false;
        }
        return result;
    }
    
}
