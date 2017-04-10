package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.*;

public class AdminController{
	
	@FXML
	Label adminLabel;
	
	@FXML
	Label list;
	
	@FXML
	Label createUser;
	
	@FXML
	ListView users;
	
	@FXML
	TextField username;
	
	@FXML
	TextField password;
	
	@FXML
	Button create;
	
	@FXML
	Button logout;
	
	@FXML
	Button delete;
	
	ObservableList<User> members = FXCollections.observableArrayList();
	
    
    public void setData(List<User> list){
		this.members = FXCollections.observableArrayList(list);
		users.setItems(members);
	}

	public void createUser(ActionEvent e){
		if(this.password == null ||  this.username == null){
			return;
		}
		
		System.out.println("button pressed");
		String name = this.username.getText();
		String pass = this.password.getText();
		ArrayList<Album> albums = new ArrayList<Album>();
		ArrayList<Tag> tags = new ArrayList<Tag>();
		User temp = new User(name, pass, albums,tags);
		members.add(temp);
		this.username.setText("");
		this.password.setText("");
		users.setItems(members);
		this.save();
	}
	
	public void deleteUser(ActionEvent e){
		System.out.println("button pressed");
		int target = users.getSelectionModel().getSelectedIndex();
		User temp = (User) users.getSelectionModel().getSelectedItem();
		if(members.contains(temp)){
			members.remove(target);
		}
		users.setItems(members);
		this.save();
	}
	
	public void logOut(ActionEvent e)
	throws IOException{
		FXMLLoader load = new FXMLLoader();
	    load.setLocation(getClass().getResource("/view/Login.fxml"));
	    Parent admin_parent = (Parent)load.load();
	    LoginController logincontroller = load.getController();
	    logincontroller.setData(members);
	    Scene admin_scene = new Scene(admin_parent);
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    photoStage.hide();
	    photoStage.setScene(admin_scene);
	    photoStage.show();
	}
	
	public void save(){
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("users.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(new ArrayList<User> (members));
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in users.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}

}
