package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;

import application.*;

public class DisplayController {
	
	@FXML
	ImageView picture;
	
	@FXML
	Button back;
	
	@FXML
	Button prev;
	
	@FXML
	Button next;
	
	@FXML
	Label caption;
	
	@FXML
	TextField capField;
	
	@FXML
	Label date;
	
	@FXML
	TextField dateField;
	
	@FXML
	Label tags;
	
	@FXML
	ListView tagList;
	
	ObservableList<Tag> taglist;
	
	ObservableList<User> members = FXCollections.observableArrayList();
	
	public void setData(List<User> members, Photo photo, Album album, User user) throws MalformedURLException{
		this.members = FXCollections.observableArrayList(members);
	
	}
	
	public void initalizeImage(Photo photo) throws MalformedURLException, IOException{
		String path = photo.getFile().getAbsolutePath();
		InputStream instream = new FileInputStream(path);
		Image image = new Image(instream);
		picture.setImage(image);
		
	}

}
