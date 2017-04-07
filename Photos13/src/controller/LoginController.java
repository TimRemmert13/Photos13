package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	Label welcomeLabel;
	
	@FXML
	Label authorLabel;
	
	@FXML
	Label username;
	
	@FXML
	Label password;
	
	@FXML
	TextField usernameBox;
	
	@FXML
	PasswordField passwordBox;
	
	@FXML
	Button logInButton;
	
	
	public void changeToAnother(ActionEvent event) throws IOException{
		Button b = (Button)event.getSource();
		if (b==logInButton) {
			String userName = usernameBox.getText();
			if(userName.equals("admin")){
			    Parent admin_parent = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
			    Scene admin_scene = new Scene(admin_parent);
			    Stage photoStage = (Stage)((Node) event.getSource()).getScene().getWindow();
			    photoStage.hide();
			    photoStage.setScene(admin_scene);
			    photoStage.show();
			}else{
			    Parent user_parent = FXMLLoader.load(getClass().getResource("/view/UserHome.fxml"));
			    Scene user_scene = new Scene(user_parent);
			    Stage photoStage = (Stage)((Node) event.getSource()).getScene().getWindow();
			    photoStage.hide();
			    photoStage.setScene(user_scene);
			    photoStage.show();				
			}
			
	    }
    }
}