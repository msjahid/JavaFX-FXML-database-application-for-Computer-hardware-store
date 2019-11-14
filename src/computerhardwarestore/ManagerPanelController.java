/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerhardwarestore;

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
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author razubhuiyan
 */
public class ManagerPanelController implements Initializable {

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private TableView<Product> tableView;
    private int selectedIndex;
    ObservableList<Product> productList;
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
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            productList = FXCollections.observableArrayList();
            tableView.setItems(productList);
            
            productIdColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getProductId()));
            productNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProductName()));
            brandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBrandName()));
            modelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));
            categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
            descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
            priceColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()));
            
              try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/chproductdb",
                    "root", "12345");
            Statement statement = connection.createStatement();
            
            String query = "select * from productTable;";
            ResultSet resultSet = statement.executeQuery(query);
            
            //resultSet = statement.executeQuery(query);
            
            
            while(resultSet.next()){
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String brandName = resultSet.getString("brandName");
                String model = resultSet.getString("model");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                Product product = new Product(productId, productName, brandName, model, category, description, price);
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }

 }
   

    @FXML
    private void handleAddProductAction(ActionEvent event) {
        int productId = Integer.parseInt(productIdField.getText());
        String productName = productNameField.getText();
        String brandName = brandField.getText();
        String model = modelField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        int price = Integer.parseInt(priceField.getText());
        
         
       
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/chproductdb",
                    "root", "12345");
             Statement statement = connection.createStatement();
             String query ="INSERT INTO productTable VALUES("+ productId +", '"+ productName +"', '"+ brandName +"', '"+ model +"', '"+ category +"', '"+ description +"',"+ price +")";
             statement.executeUpdate(query);
             Product product = new Product(productId, productName, brandName, model, category, description, price);
             productList.add(product);
             
             productIdField.clear();
             productNameField.clear();
             brandField.clear();
             modelField.clear();
             categoryField.clear();
             descriptionField.clear();
             priceField.clear();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        ObservableList<Integer> selectedIndices = tableView.getSelectionModel().getSelectedIndices();
        
        for (int i = 0; i < selectedIndices.size(); i++){
            int productId = Integer.parseInt(productIdField.getText());
        String productName = productNameField.getText();
        String brandName = brandField.getText();
        String model = modelField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        int price = Integer.parseInt(priceField.getText());
        
         
       
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/chproductdb",
                    "root", "12345");
             Statement statement = connection.createStatement();
             String query = "update productTable set productName = ' " + productName +" ',  brandName= ' "+ brandName +" ', model = ' "+ model +" ', category = ' "+ category +" ',  price = "+ price+" where productId = "+ productId +";";
             statement.executeUpdate(query);
             Product product = new Product(productId, productName, brandName, model, category, description, price);
             productList.set(selectedIndex, product);
             
             productIdField.clear();
             productNameField.clear();
             brandField.clear();
             modelField.clear();
             categoryField.clear();
             descriptionField.clear();
             priceField.clear();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
         ObservableList<Integer> selectedIndices = tableView.getSelectionModel().getSelectedIndices();
        
        for (int i = 0; i < selectedIndices.size(); i++){
        try {
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/chproductdb",
                    "root", "12345");
             Statement statement = connection.createStatement();
             int productId = productList.get(selectedIndices.get(i)).getProductId();
             String productName = productList.get(selectedIndices.get(i)).getProductName();
             statement.executeUpdate("DELETE FROM productTable where productId = "+ productId +" AND productName = '"+ productName +"'");
             productList.remove(selectedIndex);
             
             productIdField.clear();
             productNameField.clear();
             brandField.clear();
             modelField.clear();
             categoryField.clear();
             descriptionField.clear();
             priceField.clear();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }

    @FXML
    private void handleTableClickAction(MouseEvent event) {
        selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        productIdField.setText(productList.get(selectedIndex).getProductId() + "");
        productNameField.setText(productList.get(selectedIndex).getProductName());
        brandField.setText(productList.get(selectedIndex).getBrandName());
        modelField.setText(productList.get(selectedIndex).getModel());
        categoryField.setText(productList.get(selectedIndex).getCategory());
        descriptionField.setText(productList.get(selectedIndex).getDescription());
        priceField.setText(productList.get(selectedIndex).getPrice() + "");
    }
    

}