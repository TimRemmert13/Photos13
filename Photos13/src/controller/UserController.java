package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController {
	
	@FXML
	Button openAlbum;
	
	@FXML
	ListView allAlbums;
	
	@FXML 
	Button createAlbum;
	
	@FXML
	TextField albumName;

	User user;
	
	ObservableList<Album> show = FXCollections.observableArrayList();
	
	List<User> members;
	
	public void setData(User user,List<User> users){
	    this.user = user;
		this.members = users;
		show = FXCollections.observableArrayList(user.getAlbums());
		allAlbums.setItems(show);
	}
	
	public void createNewAlbum(ActionEvent e) throws IOException{
		System.out.println(this.user);
		String name = albumName.getText();
		List<Photo> photos = new ArrayList<Photo>();
		Album al = new Album(name, photos);
		this.user.addAlbum(al);
		show.add(al);
		allAlbums.setItems(show);
		this.save();
	}
	
	public void openAlbum(ActionEvent e)
	throws Exception{
		Album target = (Album)allAlbums.getSelectionModel().getSelectedItem();
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/view/OpenAlbum.fxml"));
	    Parent admin_parent = (Parent)loader.load();
	    OAController oacontroller = loader.getController();
	    oacontroller.setData(target, members, user);
	    Scene admin_scene = new Scene(admin_parent);
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    photoStage.hide();
	    photoStage.setScene(admin_scene);
	    photoStage.show();
	}
	
	public void save() throws IOException{
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/view/Admin.fxml"));
	    Parent admin_parent = (Parent)loader.load();
	    AdminController admincontroller = loader.getController();
	    admincontroller.setData(this.members);
	    admincontroller.save();
	}
}
