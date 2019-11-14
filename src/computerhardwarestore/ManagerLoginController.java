/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerhardwarestore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author razubhuiyan
 */
public class ManagerLoginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField userPasswordField;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    //TO DO
    }
    
    @FXML
    private void handleUserCancelAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleUserLoginAction(ActionEvent event) {
         System.out.println("Login");
        String username = userNameField.getText();
        String password = userPasswordField.getText();

        if (username.equals("admin") && password.equals("test")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ManagerPanel.fxml"));

                Scene scene = new Scene(root);
//                Stage stage = new Stage();
                Stage stage = ComputerHardwareStore.getMainStage();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {

            }
        }

        
    }
    
}
