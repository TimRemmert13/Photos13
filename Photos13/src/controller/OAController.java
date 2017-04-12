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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import application.*;

public class OAController {
	
	@FXML
	ListView<Photo> photosList;
	
	@FXML
	Button back;
	
	@FXML
	Label tags;
	
	@FXML
	ListView<Tag> tagList;
	
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
	
	@FXML
	TextField value;
	
	@FXML 
	Label tagLabel;
	
	Album album;
	
    ObservableList<Photo> pictures = FXCollections.observableArrayList();
    
    ObservableList<Tag> tagsInPhoto = FXCollections.observableArrayList();
    
    List<User> members;
    
    User user;
	
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
                        	BufferedImage img = null;
							try {
								img = ImageIO.read(t.getURL());
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	Image image = SwingFXUtils.toFXImage(img,null);
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
                        	BufferedImage img = null;
							try {
								img = ImageIO.read(t.getURL());
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	Image image = SwingFXUtils.toFXImage(img,null);
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
		                        	BufferedImage img = null;
									try {
										img = ImageIO.read(t.getURL());
									} catch (MalformedURLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
		                        	Image image = SwingFXUtils.toFXImage(img,null);
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
	    
	    public void deleteTag(ActionEvent e) throws IOException{
	    	Photo target = (Photo) photosList.getSelectionModel().getSelectedItem();
	    	Tag selected = (Tag) tagList.getSelectionModel().getSelectedItem();
	    	if(target.getTags().contains(selected)){
	    		target.removeTag(selected);
	    	}
	    	tagsInPhoto = FXCollections.observableArrayList(target.getTags());
	    	tagList.setItems(tagsInPhoto);
	    	this.save();
	    }
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
	    
	    public void displayMode(ActionEvent e) throws IOException{
	    	FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/view/Display.fxml"));
		    Parent admin_parent = (Parent)loader.load();
		    DisplayController displaycontroller = loader.getController();
		    Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
		    displaycontroller.setData(members, selected, album, user);
		    displaycontroller.initalizeImage(selected);
		    Scene admin_scene = new Scene(admin_parent, 1500, 900);
		    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
		    photoStage.hide();
		    photoStage.setScene(admin_scene);
		    photoStage.show();
	    }
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
	                        	BufferedImage img = null;
								try {
									img = ImageIO.read(t.getURL());
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                        	Image image = SwingFXUtils.toFXImage(img,null);
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
	    
	    public void next(ActionEvent e){
	    	Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
	    	if(pictures.get(pictures.size()-1) != selected){
	    		photosList.getSelectionModel().selectNext();
	    	}
	    }
	    
	    public void prev(ActionEvent e){
	    	Photo selected = (Photo) photosList.getSelectionModel().getSelectedItem();
	    	if(pictures.get(0) != selected){
	    		photosList.getSelectionModel().selectPrevious();
	    	}
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
