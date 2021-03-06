package controller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * 
 * @author Tim Remmert
 *
 */
public class SearchController {
	@FXML
	Button backButton;
	
	@FXML
	Button searchButton;
	
	@FXML
	Button creatButton;
	
	@FXML 
	 TextField beginingDate;
	
	@FXML
	TextField endingDate;
	
	@FXML
	TextField tagName;
	
	@FXML
	TextField tagValue;
	
	@FXML
	ListView<Tag> searchedTag;
	
	@FXML
	ListView<Photo> result;
	
	@FXML
	TextField albumName;
	
	
	List<Album> totalAlbums;
	User user;
	List<User> users;
	List<Photo> photoDis;
	List<Tag> tagList;
	
	ObservableList<Photo> photolisdis = FXCollections.observableArrayList();
	
	public void setData(List<Album> albums, User user, List<User> users){
		this.totalAlbums = albums;
		this.user = user;
		this.users = users;
	}
	public void backToUserHome(ActionEvent e) throws IOException{
		FXMLLoader load = new FXMLLoader();
	    load.setLocation(getClass().getResource("/view/UserHome.fxml"));
	    Parent admin_parent = (Parent)load.load();
	    UserController usercontroller = load.getController();
	    usercontroller.setData(user,users);
	    Scene admin_scene = new Scene(admin_parent);
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    photoStage.hide();
	    photoStage.setScene(admin_scene);
	    photoStage.show();
	}
	public void searchPhotos(ActionEvent e){
		if(tagName.getText()!=null&&tagValue.getText()!=null){
			String nameGet = tagName.getText();
			String valueGet= tagValue.getText();
			
			for(int i=0; i<totalAlbums.size();i++){
				for(int j=0; j<totalAlbums.get(i).getPhotos().size();j++){
					for(int z=0; z<totalAlbums.get(i).getPhotos().get(j).getTags().size();z++){
						if(nameGet.equals(totalAlbums.get(i).getPhotos().get(j).getTags().get(z).getName())
						&&valueGet.equals(totalAlbums.get(i).getPhotos().get(j).getTags().get(z).getValue())){
							result.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){	 
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
							result.setItems(photolisdis);
						}
					}
				}
			}
		}
		
		
	}
	/*List<Photo> photos = (List)photolisdis;
	public void creatAlbum(ActionEvent e){
		Album myAlbum = (albumName.getText(), (List)photolisdis);
		this.user.addAlbum(myAlbum);
	}*/
	
	
	
	

}
