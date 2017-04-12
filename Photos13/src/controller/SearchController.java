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
	TextField tag;
	
	@FXML
	ListView<Tag> searchedTag;
	
	@FXML
	ListView<Photo> result;
	
	@FXML
	TextField albumName;
	
	
	
	

}
