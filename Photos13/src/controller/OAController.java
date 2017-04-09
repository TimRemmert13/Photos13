package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Calendar;

import application.*;

public class OAController {
	
	@FXML
	ImageView picture;
	
	@FXML
	Button back;
	
	@FXML
	Label tags;
	
	@FXML
	ListView tagList;
	
	@FXML
	Button deleteTag;
	
	@FXML
	TextField tag;
	
	@FXML
	Button addTag;
	
	@FXML 
	Label caption;
	
	@FXML
	TextField capField;
	
	@FXML
	Button updateCaption;
	
	@FXML
	Button addPhoto;
	
	@FXML
	Button removePhoto;
	
	@FXML
	ToggleButton next;
	
	@FXML
	ToggleButton prev;
	
	@FXML
	Button moveOrCopy;
	
	@FXML
	Button displayMode;
	
	Album album;
	
	public void setData(Album album){
		this.album = album;
	}
	
	public void addPhoto(ActionEvent e) throws MalformedURLException{
		FileChooser fileChooser = new FileChooser();
		//extenstion filers
		FileChooser.ExtensionFilter exfilJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter exfilPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(exfilJPG, exfilPNG);
        
		File file = fileChooser.showOpenDialog(null);
		
		Image image = new Image(file.toURL().toString(),353,341,true,true);
	    this.picture.setImage(image);
	    this.picture.setFitHeight(341);
	    this.picture.setFitWidth(353);
	    this.picture.setPreserveRatio(true);
	    Calendar calendar = Calendar.getInstance();
	    Photo photo = new Photo(image,calendar);
	    this.album.addPhoto(photo);
	}

}
