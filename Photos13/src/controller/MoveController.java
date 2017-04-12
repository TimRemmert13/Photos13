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

	ObservableList<Album> moveAlbums = FXCollections.observableArrayList();

	ObservableList<Album> copyAlbums = FXCollections.observableArrayList();

	

}
