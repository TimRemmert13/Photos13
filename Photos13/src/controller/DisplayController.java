package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;

import application.*;
/**
 * Class to control the DisplayMode.fxml file
 * @author Tim Remmert
 *
 */
public class DisplayController {
	/**
	 * Image view to display the photo image
	 */
	@FXML
	ImageView picture;
	/**
	 * button when pressed return the window to the open album window
	 */
	@FXML
	Button back;
	/**
	 * Button when pressed toggle to the previous photo in the album
	 */
	@FXML
	Button prev;
	/**
	 * button when pressed toggles to the next photo in the album
	 */
	@FXML
	Button next;
	/**
	 * label to show the text field below is for the caption 
	 */
	@FXML
	Label caption;
	/**
	 * Text field to display the photo's caption
	 */
	@FXML
	TextField capField;
	/**
	 *Label to show the below text field is the date of the photo
	 */
	@FXML
	Label date;
	/**
	 * Test field to display the date of the photo
	 */
	@FXML
	TextField dateField;
	/**
	 * Label to show the list view is of tags
	 */
	@FXML
	Label tags;
	/**
	 * List view to display the tags of a photo
	 */
	@FXML
	ListView tagList;
	/**
	 * observable list of tags for the photo
	 */
	ObservableList<Tag> taglist = FXCollections.observableArrayList();
	/**
	 * observable list of users of the application
	 */
	ObservableList<User> members = FXCollections.observableArrayList();
	/**
	 * album that is currently open
	 */
	Album album;
	/**
	 * User that is currently using the application
	 */
	User user;
	/**
	 * photo that was selected when entering display mode
	 */
	Photo photo;
	/**
	 * Method to set the list of users, the selected photo, open album, and current user to 
	 * the given parameters
	 * @param members list of user of the application
	 * @param photo selected photo when entering display mode
	 * @param album the currently open album
	 * @param user the user currently using the application
	 * @throws MalformedURLException for invalid URL
	 */
	public void setData(List<User> members, Photo photo, Album album, User user) throws MalformedURLException{
		this.members = FXCollections.observableArrayList(members);
	    this.album = album;
	    this.user = user;
	    this.photo = photo;
	}
	/**
	 * Method to set the selected photo to the image view 
	 * @param photo the photo selected when entering the display mode
	 * @throws MalformedURLException for invalid URL
	 * @throws IOException if file is not found
	 */
	public void initalizeImage(Photo photo) throws MalformedURLException, IOException{
		String path = photo.getFile().getAbsolutePath();
		InputStream instream = new FileInputStream(path);
		Image image = new Image(instream);
		picture.setImage(image);
		System.out.print(photo.getDate().toString());
		String date = photo.getDate().toString().substring(0, 11);
		dateField.setText(date);
	    taglist = FXCollections.observableArrayList(photo.getTags());
		tagList.setItems(taglist);
		capField.setText(photo.getCaption());
	}
	/**
	 * Method to go back to the open album window
	 * @param e Action event that is triggered when the back button is pressed
	 * @throws IOException if file is not found
	 */
	public void back(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/view/OpenAlbum.fxml"));
	    Parent oa_parent = (Parent)loader.load();
	    OAController oacontroller = loader.getController();
	    oacontroller.setData(album, members, user);
	    Scene admin_scene = new Scene(oa_parent);
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    photoStage.hide();
	    photoStage.setScene(admin_scene);
	    photoStage.show();
	}
	/**
	 * Method to toggle to the next photo in the album
	 * @param e Action event that is triggered when the next button is pressed
	 * @throws FileNotFoundException if image file is not found
	 */
	public void next(ActionEvent e) throws FileNotFoundException{
		List<Photo> photos = this.album.getPhotos();
		if(photos.get(photos.size()-1) != this.photo){
			for(int i = 0; i < photos.size()-1; i ++){
				if(photos.get(i) == this.photo){
					this.photo = photos.get(i+1);
					String path = photo.getFile().getAbsolutePath();
					InputStream instream = new FileInputStream(path);
					Image image = new Image(instream);
					picture.setImage(image);
					String date = photo.getDate().toString().substring(0, 11);
					dateField.setText(date);
				    taglist = FXCollections.observableArrayList(photo.getTags());
					tagList.setItems(taglist);
					capField.setText(photo.getCaption());
					break;
				}
			}
		}
		
		
	}
	/**
	 * Method to toggle to the previous photo in the album 
	 * @param e Action event that is triggered when the prev button is pressed
	 * @throws FileNotFoundException if image file is not found
	 */
	public void prev(ActionEvent e) throws FileNotFoundException{
		List<Photo> photos = this.album.getPhotos();
		if(photos.get(0) != this.photo){
			for(int i = 0; i < photos.size(); i ++){
				if(photos.get(i) == this.photo){
					this.photo = photos.get(i-1);
					String path = photo.getFile().getAbsolutePath();
					InputStream instream = new FileInputStream(path);
					Image image = new Image(instream);
					picture.setImage(image);
					String date = photo.getDate().toString().substring(0, 11);
					dateField.setText(date);
				    taglist = FXCollections.observableArrayList(photo.getTags());
					tagList.setItems(taglist);
					capField.setText(photo.getCaption());
					break;
				}
			}
		}
		
	}

}
