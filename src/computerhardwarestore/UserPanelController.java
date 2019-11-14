/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerhardwarestore;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author razubhuiyan
 */
public class UserPanelController implements Initializable {

    @FXML
    private ListView<String> productListView;
    @FXML
    private TableView<Product> tableView;
    ObservableList<String> categoryList;
    ObservableList<Product> productList;
    ObservableList<Product> filteredProductList;
    @FXML
    private TableColumn<Product, Number> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> brandColumn;
    @FXML
    private TableColumn<Product, String> modelColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryList = FXCollections.observableArrayList();
        productList = FXCollections.observableArrayList();
        filteredProductList = FXCollections.observableArrayList();
        productListView.setItems(categoryList);
        tableView.setItems(filteredProductList);
        
         productIdColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getProductId()));
            productNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProductName()));
            brandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBrandName()));
            modelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));
            categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
            descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
            priceColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()));
        
     
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/chproductdb",
                    "root", "12345");
             Statement statement = connection.createStatement();
            
            String query = "select * from productCategory";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                 String category = resultSet.getString("category");
                 categoryList.add(category);
            }
            
            
            query = "select * from productTable";
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                String category = resultSet.getString("category");
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String brandName = resultSet.getString("brandName");
                String model = resultSet.getString("model");
               // String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                Product product = new Product(productId, productName, brandName, model, category, description, price);
                productList.add(product);
            }
            
            filteredProductList.addAll(productList);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }    

    @FXML
    private void handleAdminAction(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage = ComputerHardwareStore.getMainStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCategorySelectionAction(MouseEvent event) {
        
        String category = productListView.getSelectionModel().getSelectedItem();
        filteredProductList.clear();
        for(Product product: productList){
            if(product.getCategory().equals(category))
                filteredProductList.add(product);
                
                }
    }
    
}
