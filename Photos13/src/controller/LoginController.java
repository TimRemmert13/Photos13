package controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.*;
/**
 * 
 * @author Tim Remmert
 *
 */
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
	
	ObservableList<User> members = FXCollections.observableArrayList();
		
	
	public void changeToAnother(ActionEvent event) throws IOException{
		Button b = (Button)event.getSource();
		if (b==logInButton) {
			String userName = usernameBox.getText();
			String passWord = passwordBox.getText();
			if(userName.equals("admin")){
				FXMLLoader load = new FXMLLoader();
			    load.setLocation(getClass().getResource("/view/Admin.fxml"));
			    Parent admin_parent = (Parent)load.load();
			    AdminController admincontroller = load.getController();
			    admincontroller.setData(members);
			    Scene admin_scene = new Scene(admin_parent);
			    Stage photoStage = (Stage)((Node) event.getSource()).getScene().getWindow();
			    photoStage.hide();
			    photoStage.setScene(admin_scene);
			    photoStage.show();
			 
			}else{
				for(User user: members){
					if(userName.equals(user.getUsername()) && 
							passWord.equals(user.getPassword())){
						FXMLLoader loader = new FXMLLoader();
					    loader.setLocation(getClass().getResource("/view/UserHome.fxml"));
					    Parent user_parent = (Parent)loader.load();
					    UserController usercontroller = loader.getController();
					    usercontroller.setData(user,members);
					    Scene admin_scene = new Scene(user_parent);
					    Stage photoStage = (Stage)((Node) event.getSource()).getScene().getWindow();
					    photoStage.hide();
					    photoStage.setScene(admin_scene);
					    photoStage.show();
					}
				}			
			}
			
	    }
    }
	public void setData(List<User> members){
		this.members = FXCollections.observableArrayList(members);
	}
	
}