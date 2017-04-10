package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.List;

import application.*;

public class OAController {
	
	@FXML
	ListView photosList;
	
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
	
    ObservableList<Photo> pictures = FXCollections.observableArrayList();
	
	public void setData(Album album){
		this.album = album;
	}
	
	@SuppressWarnings("unchecked")
	public void addPhoto(ActionEvent e) throws MalformedURLException{
		FileChooser fileChooser = new FileChooser();
		//extenstion filers
		FileChooser.ExtensionFilter exfilJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter exfilPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(exfilJPG, exfilPNG);
        
		File file = fileChooser.showOpenDialog(null);
		ImageView picture = new ImageView();
		Image image = new Image(file.toURL().toString(),353,341,true,true);
		picture.setImage(image);
	    picture.setFitHeight(341);
	    picture.setFitWidth(353);
	    picture.setPreserveRatio(true);
	    Calendar calendar = Calendar.getInstance();
	    Photo photo = new Photo(image,calendar);
	    photo.setCaption("monkey");
	    this.pictures.add(photo);
	    //this.album.addPhoto(photo);
	    photosList.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo>>(){
	    	 
            @Override
            public ListCell<Photo> call(ListView<Photo> p) {
                 
                ListCell<Photo> cell = new ListCell<Photo>(){
 
                    @Override
                    protected void updateItem(Photo t, boolean boo) {
                        super.updateItem(t, boo);
                        if (t != null) {
                        	ImageView imageView = new ImageView();
                        	imageView.setImage(t.getImage());
                        	setText(t.getCaption());
                            setGraphic(imageView);
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	    photosList.setItems(pictures);
		
	}
}
