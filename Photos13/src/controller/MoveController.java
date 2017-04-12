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
/**
 * 
 * @author Tim Remmert
 *
 */
public class MoveController {
	@FXML 
	Button moveButton;

	@FXML
	Button copyButton;

	@FXML
	Button cancelButton;

	@FXML
	Button finishButton;

	@FXML 
	ListView<Album> albumMoveList;

	@FXML
	ListView<Album> albumCopyList;

	User user;
	List<User> members;
	Album album;
	Photo currentPhoto;
	List<Album> totalAlbums;
	
	
	ObservableList<Album> moveAlbums = FXCollections.observableArrayList();

	ObservableList<Album> copyAlbums = FXCollections.observableArrayList();
	
	public void setData(User user, Album album, Photo photo, List<User> members){
		//System.out.println("getinto setData");
	    this.user = user;
	    this.album = album;
	    this.currentPhoto = photo;
	    this.members = members;
		//this.members = users;
		moveAlbums = FXCollections.observableArrayList(user.getAlbums());
		copyAlbums = FXCollections.observableArrayList(user.getAlbums());
		albumMoveList.setItems(moveAlbums);
		albumCopyList.setItems(copyAlbums);
	}
	public void cancel(ActionEvent e)
			throws IOException{
				FXMLLoader load = new FXMLLoader();
			    load.setLocation(getClass().getResource("/view/OpenAlbum.fxml"));
			    Parent admin_parent = (Parent)load.load();
			    OAController oacontroller = load.getController();
			    oacontroller.setData(album,members,user);
			    Scene admin_scene = new Scene(admin_parent);
			    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
			    photoStage.hide();
			    photoStage.setScene(admin_scene);
			    photoStage.show();
			}
	public void moveToAnother(ActionEvent e) throws IOException{
		Album target = (Album)albumMoveList.getSelectionModel().getSelectedItem();
		target.addPhoto(currentPhoto);
		album.removePhoto(currentPhoto);
		this.save();
	
	}
	
	public void copyToAnother(ActionEvent e) throws IOException{
		System.out.println("copy??");
		Album target = (Album)albumCopyList.getSelectionModel().getSelectedItem();
		target.addPhoto(currentPhoto);
		this.save();
	}
	
	public void finishMC(ActionEvent e) throws IOException{
		FXMLLoader load = new FXMLLoader();
	    load.setLocation(getClass().getResource("/view/OpenAlbum.fxml"));
	    Parent admin_parent = (Parent)load.load();
	    OAController oacontroller = load.getController();
	    oacontroller.setData(album, members, user);
	    Scene admin_scene = new Scene(admin_parent);
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    photoStage.hide();
	    photoStage.setScene(admin_scene);
	    photoStage.show();
	    this.save();
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
