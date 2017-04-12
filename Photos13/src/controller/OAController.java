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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import application.*;
/**
 * Class to control the OpenAlbum.fxml file 
 * @author Tim Remmert
 *
 */
public class OAController {
	/**
	 * list view of all the photos in an album
	 */
	@FXML
	ListView<Photo> photosList;
	/**
	 * Button when pressed goes back to the user home window
	 */
	@FXML
	Button back;
	/**
	 * Label to show the list view below is of tags for the 
	 * selected photo
	 */
	@FXML
	Label tags;
	/**
	 * List view of tags for the selected photo
	 */
	@FXML
	ListView<Tag> tagList;
	/**
	 * Button when pressed deletes a selected tag
	 */
	@FXML
	Button deleteTag;
	/**
	 * Text field for input of a tag name
	 */
	@FXML
	TextField tag;
	/**
	 * Button when pressed creates a new tag
	 */
	@FXML
	Button addTag;
	/**
	 * label to show the test field below is for a photos caption
	 */
	@FXML 
	Label caption;
	/**
	 * Test field for input a caption for a photo
	 */
	@FXML
	TextField capField;
	/**
	 * Button to update a photos caption 
	 */
	@FXML
	Button updateCaption;
	/**
	 * button to add a photo to the album
	 */
	@FXML
	Button addPhoto;
	/**
	 * Button to remove a photo from the album
	 */
	@FXML
	Button removePhoto;
	/**
	 * button to toggle to the next photo in the album
	 */
	@FXML
	ToggleButton next;
	/**
	 * Button to toggle to the previous photo in the album
	 */
	@FXML
	ToggleButton prev;
	/**
	 * button to move or copy a photo to another album
	 */
	@FXML
	Button moveOrCopy;
	/**
	 * button to enter display mode
	 */
	@FXML
	Button displayMode;
	/**
	 * text field to enter the value of a tag
	 */
	@FXML
	TextField value;
	/**
	 * Label to show the list view is of tags
	 */
	@FXML 
	Label tagLabel;
	/**
	 * the album currently open
	 */
	Album album;
	/**
	 * observable list of photos in the album
	 */
    ObservableList<Photo> pictures = FXCollections.observableArrayList();
    /**
     * observable list of tags for each photo
     */
    ObservableList<Tag> tagsInPhoto = FXCollections.observableArrayList();
    /**
     * List of users for the application
     */
    List<User> members;
    /**
     * the user currently using the application
     */
    User user;
	/**
	 * Method to set the album, list of users, and current user to the given parameters
	 * @param album the album open
	 * @param members the list of users for the application
	 * @param user the user currently using the appliction
	 */
	public void setData(Album album, List<User> members, User user){
		this.album = album;
		this.members = members;
		this.user = user;
		pictures =FXCollections.observableArrayList(album.getPhotos());
		photosList.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){	 
            @Override
            public ListCell<Photo> call(ListView<Photo> p) {
                 
                ListCell<Photo> cell = new ListCell<Photo>(){
 
                    @Override
                    protected void updateItem(Photo t, boolean boo) {
                        super.updateItem(t, boo);
                        if (t != null) {
                        	ImageView imageView = new ImageView();
                        	String path = t.getFile().getAbsolutePath();
                    		InputStream instream = null;
							try {
								instream = new FileInputStream(path);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		Image image = new Image(instream);
                        	imageView.setImage(image);
                        	imageView.setFitHeight(100);
                        	imageView.setFitWidth(100);
                        	imageView.setPreserveRatio(true);
                        	setText(t.getCaption());
                            setGraphic(imageView);
                        }
                    }
 
                };  
                return cell;
            }
        });
	    photosList.setItems(pictures);
	    photosList.getSelectionModel().selectFirst();
	    Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
	    tagsInPhoto = FXCollections.observableArrayList(selected.getTags());
	    tagList.setItems(tagsInPhoto);
	    
	}
	/**
	 * Method to add a photo from the users computer to the open album
	 * @param e Action event triggered when add photo button is pressed
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void addPhoto(ActionEvent e) throws IOException{
		FileChooser fileChooser = new FileChooser();
		//Extension filers
		FileChooser.ExtensionFilter exfilJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter exfilPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(exfilJPG, exfilPNG);
        
		File file = fileChooser.showOpenDialog(null);
		if(file == null){
			return;
		}
		
		ImageView picture = new ImageView();
		Image image = new Image(file.toURL().toString(),353,341,true,true);
		picture.setImage(image);
	    picture.setFitHeight(341);
	    picture.setFitWidth(353);
	    picture.setPreserveRatio(true);
	    Calendar calendar = Calendar.getInstance();
	    List<Tag> tags = new ArrayList<Tag>();		
	    Photo photo = new Photo(file,tags,calendar);
	    this.pictures.add(photo);
	    this.album.addPhoto(photo);
	    
	    photosList.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){
	    	 
            @Override
            public ListCell<Photo> call(ListView<Photo> p) {
                 
                ListCell<Photo> cell = new ListCell<Photo>(){
 
                    @Override
                    protected void updateItem(Photo t, boolean boo) {
                        super.updateItem(t, boo);
                        if (t != null) {
                        	ImageView imageView = new ImageView();
                        	String path = t.getFile().getAbsolutePath();
                    		InputStream instream = null;
							try {
								instream = new FileInputStream(path);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		Image image = new Image(instream);
                        	imageView.setImage(image);
                        	imageView.setFitHeight(100);
                        	imageView.setFitWidth(100);
                        	imageView.setPreserveRatio(true);
                        	setText(t.getCaption());
                            setGraphic(imageView);
                        }
                    }
 
                };  
                return cell;
            }
        });
	    photosList.setItems(pictures);
	    this.save();
	}
	    /**
	     * Method to add a caption to a selected photo
	     * @param e Action Event triggered when edit caption button is pressed
	     * @throws IOException
	     */
	    public void addCaption(ActionEvent e) throws IOException{
	    	String caption = this.capField.getText();
	    	if(!(caption.equals(null))){
	    		Photo target = (Photo) photosList.getSelectionModel().getSelectedItem();
	    		target.setCaption(caption);
		        photosList.setItems(pictures);
		        photosList.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){
			    	 
		            @Override
		            public ListCell<Photo> call(ListView<Photo> p) {
		                 
		                ListCell<Photo> cell = new ListCell<Photo>(){
		 
		                    @Override
		                    protected void updateItem(Photo t, boolean boo) {
		                        super.updateItem(t, boo);
		                        if (t != null) {
		                        	ImageView imageView = new ImageView();
		                        	String path = t.getFile().getAbsolutePath();
		                    		InputStream instream = null;
									try {
										instream = new FileInputStream(path);
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
		                    		Image image = new Image(instream);
		                        	imageView.setImage(image);
		                        	imageView.setFitHeight(100);
		                        	imageView.setFitWidth(100);
		                        	imageView.setPreserveRatio(true);
		                        	setText(t.getCaption());
		                            setGraphic(imageView);
		                        }
		                    }
		 
		                };  
		                return cell;
		            }
		        });
			    photosList.setItems(pictures);
			    capField.setText("");
			    this.save();
	    	}
	    }
	    /**
	     * Method to add a tag to a photo
	     * @param e Action event triggered when add tag button is pressed
	     * @throws IOException
	     */
	    public void addTag(ActionEvent e) throws IOException{
	    	String name = tag.getText();
	    	String value = this.value.getText();
	    	if(!(name.equals(null) && value.equals(null))){
	    		for(Tag tag: this.user.getTags()){
	    			if(tag.getName().equals(name) && tag.getValue().equals(value)){
	    				e.consume();;
	    			}
	    		}
	    		
	    		List<Photo> photos = new ArrayList<Photo>();
	    		Photo tagged = (Photo) photosList.getSelectionModel().getSelectedItem();
	    		photos.add(tagged);
	    		Tag newTag = new Tag(name,value, photos);
	    		this.user.addTag(newTag);
	    		tagged.addTag(newTag);
	    		tagsInPhoto.add(newTag);
	    		tagList.setItems(tagsInPhoto);
	    		tag.setText("");
	    		this.value.setText("");
	    		this.save();
	    	}

	    }
	    /**
	     * Method to delete a tag from a photo
	     * @param e Action event triggered when delete tag button is pressed
	     * @throws IOException
	     */
	    public void deleteTag(ActionEvent e) throws IOException{
	    	Photo target = (Photo) photosList.getSelectionModel().getSelectedItem();
	    	Tag selected = (Tag) tagList.getSelectionModel().getSelectedItem();
	    	if(target.getTags().contains(selected)){
	    		target.removeTag(selected);
	    		this.user.deleteTag(selected);
	    	}
	    	tagsInPhoto = FXCollections.observableArrayList(target.getTags());
	    	tagList.setItems(tagsInPhoto);
	    	this.save();
	    }
	    /**
	     * Method to go from the open album window to the user home window
	     * @param e Action event triggered when back button is pressed
	     * @throws IOException
	     */
	    public void back(ActionEvent e) throws IOException{
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/view/UserHome.fxml"));
		    Parent admin_parent = (Parent)loader.load();
		    UserController usercontroller = loader.getController();
		    usercontroller.setData(user, members);
		    Scene admin_scene = new Scene(admin_parent);
		    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
		    photoStage.hide();
		    photoStage.setScene(admin_scene);
		    photoStage.show();
	    }
	    /**
	     * Method to enter the display mode window
	     * @param e Action event triggered when display mode button is pressed
	     * @throws IOException
	     */
	    public void displayMode(ActionEvent e) throws IOException{
	    	FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/view/Display.fxml"));
		    Parent admin_parent = (Parent)loader.load();
		    DisplayController displaycontroller = loader.getController();
		    Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
		    displaycontroller.setData(members, selected, album, user);
		    displaycontroller.initalizeImage(selected);
		    Scene admin_scene = new Scene(admin_parent, 1300, 850);
		    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
		    photoStage.hide();
		    photoStage.setScene(admin_scene);
		    photoStage.show();
	    }
	    /**
	     * Method to remove a photo from the open album
	     * @param e Action event triggered when delete button is pressed
	     * @throws IOException
	     */
	    public void removePhoto(ActionEvent e) throws IOException{
	    	Photo target = (Photo) photosList.getSelectionModel().getSelectedItem();
	        if(this.album.getPhotos().contains(target)){
	        	this.album.removePhoto(target);
	        }
	        pictures.remove(target);
	        photosList.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){
		    	 
	            @Override
	            public ListCell<Photo> call(ListView<Photo> p) {
	                 
	                ListCell<Photo> cell = new ListCell<Photo>(){
	 
	                    @Override
	                    protected void updateItem(Photo t, boolean boo) {
	                        super.updateItem(t, boo);
	                        if (t != null) {
	                        	ImageView imageView = new ImageView();
	                        	String path = t.getFile().getAbsolutePath();
	                    		InputStream instream = null;
								try {
									instream = new FileInputStream(path);
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                    		Image image = new Image(instream);
	                        	imageView.setImage(image);
	                        	imageView.setFitHeight(100);
	                        	imageView.setFitWidth(100);
	                        	imageView.setPreserveRatio(true);
	                        	setText(t.getCaption());
	                            setGraphic(imageView);
	                        }
	                    }
	 
	                };  
	                return cell;
	            }
	        });
	        photosList.setItems(pictures);
	        this.save();
	    }
	    /**
	     * Method to toggle to the next photo in the album
	     * @param e Action event triggered when next button is pressed
	     */
	    public void next(ActionEvent e){
	    	Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
	    	if(pictures.get(pictures.size()-1) != selected){
	    		photosList.getSelectionModel().selectNext();
	    	}
	    }
	    /**
	     * Method to toggle to the previous photo in the album
	     * @param e Action event triggered when previous button is pressed
	     */
	    public void prev(ActionEvent e){
	    	Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
	    	if(pictures.get(0) != selected){
	    		photosList.getSelectionModel().selectPrevious();
	    	}
	    }
	    /**
	     * Method to update the list view of tags for the currently selected photo
	     * @param m Mouse event triggered when the user clicks on a photo
	     */
		public void updateTag(MouseEvent m){
			System.out.println("clicked");
			Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
			tagsInPhoto = FXCollections.observableArrayList(selected.getTags());
			tagList.setItems(tagsInPhoto);
		}
	    /**
	     * Method to save users data in a serializable file
	     * @throws IOException
	     */
		public void save() throws IOException{
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/view/Admin.fxml"));
		    Parent admin_parent = (Parent)loader.load();
		    AdminController admincontroller = loader.getController();
		    admincontroller.setData(this.members);
		    admincontroller.save();
		}
}
