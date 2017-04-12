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
	Button deleteButton;
	
	@FXML
	Button searchButton;
	
	@FXML
	Button finishButton;
	
	@FXML
	Button logoutButton;
	
	@FXML
	Button openAlbum;
	
	@FXML
	ListView allAlbums;
	
	@FXML 
	Button createAlbum;
	
	@FXML
	TextField albumName;
	
	@FXML
	TextField editName;
	
	@FXML
	TextField editNum;
	
	@FXML
	TextField editRange;

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
		//System.out.println("getin?");
		Album target = (Album)allAlbums.getSelectionModel().getSelectedItem();
		FXMLLoader loader = new FXMLLoader();
		//System.out.println("this step?");
	    loader.setLocation(getClass().getResource("/view/OpenAlbum.fxml"));
	    //System.out.println("this more step?");
	    Parent admin_parent = (Parent)loader.load();
	    //System.out.println("this line1?");
	    OAController oacontroller = loader.getController();
	    //System.out.println("this line2?");
	    oacontroller.setData(target, members, user);
	    //System.out.println("this line3?");
	    Scene admin_scene = new Scene(admin_parent);
	    //System.out.println("this line4?");
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    //System.out.println("this line5?");
	    photoStage.hide();
	    //System.out.println("this line6?");
	    photoStage.setScene(admin_scene);
	    //System.out.println("this line7?");
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
	
	public void deleteAlbum(ActionEvent e) throws IOException{
		System.out.println("delete album button pressed");
		int target = allAlbums.getSelectionModel().getSelectedIndex();
		Album temp = (Album) allAlbums.getSelectionModel().getSelectedItem();
		if(show.contains(temp)){
			show.remove(target);
			//allAlbums.setItems(show);
			//this.save();
		}
		allAlbums.setItems(show);
		this.save(); 
	}
	public void switchToSearch(ActionEvent e)
			throws IOException{
				FXMLLoader load = new FXMLLoader();
			    load.setLocation(getClass().getResource("/view/Search.fxml"));
			    Parent admin_parent = (Parent)load.load();
			    //SearchController SearchController = load.getController();
			    //SearchController.setData(members);
			    Scene admin_scene = new Scene(admin_parent);
			    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
			    photoStage.hide();
			    photoStage.setScene(admin_scene);
			    photoStage.show();
			}

    public void renameAlbum(ActionEvent e) throws IOException{
    	Album album = (Album)allAlbums.getSelectionModel().getSelectedItem();
    	int index = show.indexOf(album);
    	album.setName(editName.getText());
    	show.set(index, album);
    	allAlbums.setItems(show);
    	this.save();
    	
    	
    }

}
