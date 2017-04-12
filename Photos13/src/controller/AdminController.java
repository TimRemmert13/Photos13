package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;



import application.*;

public class AdminController{
	
	@FXML
	Label adminLabel;
	
	@FXML
	Label list;
	
	@FXML
	Label createUser;
	
	@FXML
	ListView users;
	
	@FXML
	TextField username;
	
	@FXML
	TextField password;
	
	@FXML
	Button create;
	
	@FXML
	Button logout;
	
	@FXML
	Button delete;
	
	ObservableList<User> members = FXCollections.observableArrayList();
	
    
    public void setData(List<User> list){
		this.members = FXCollections.observableArrayList(list);
		users.setItems(members);
	}

	public void createUser(ActionEvent e){
		if(this.password == null ||  this.username == null){
			return;
		}
		
		System.out.println("button pressed");
		String name = this.username.getText();
		String pass = this.password.getText();
		ArrayList<Album> albums = new ArrayList<Album>();
		List<Photo> photos = new ArrayList<Photo>();	
		File monkey = new File("images/monkey.jpg");
		File lake = new File("images/lake.jpeg");
		File rainbow_rose = new File("images/Rainbow_Rose.jpg");
		File waterfall = new File("images/waterfall.jpeg");
		List<Tag> tagsPhoto = new ArrayList<Tag>();
		Calendar calendar = Calendar.getInstance();
		Photo pmonkey = new Photo(monkey,tagsPhoto,calendar);
		Photo plake = new Photo(lake, tagsPhoto, calendar);
		Photo prainbow_rose = new Photo(rainbow_rose, tagsPhoto,calendar);
		Photo pwaterfall = new Photo(waterfall, tagsPhoto, calendar);
		List<Photo> stockList = new ArrayList<Photo>();
		Album stock = new Album("stock", stockList);
		stock.addPhoto(plake);
		stock.addPhoto(pwaterfall);
		stock.addPhoto(prainbow_rose);
		stock.addPhoto(pmonkey);
		ArrayList<Tag> tags = new ArrayList<Tag>();
		albums.add(stock);
		User temp = new User(name, pass, albums,tags);
		members.add(temp);
		this.username.setText("");
		this.password.setText("");
		users.setItems(members);
		this.save();
	}
	
	public void deleteUser(ActionEvent e){
		System.out.println("button pressed");
		int target = users.getSelectionModel().getSelectedIndex();
		User temp = (User) users.getSelectionModel().getSelectedItem();
		if(members.contains(temp)){
			members.remove(target);
		}
		users.setItems(members);
		this.save();
	}
	
	public void logOut(ActionEvent e)
	throws IOException{
		FXMLLoader load = new FXMLLoader();
	    load.setLocation(getClass().getResource("/view/Login.fxml"));
	    Parent admin_parent = (Parent)load.load();
	    LoginController logincontroller = load.getController();
	    logincontroller.setData(members);
	    Scene admin_scene = new Scene(admin_parent);
	    Stage photoStage = (Stage)((Node) e.getSource()).getScene().getWindow();
	    photoStage.hide();
	    photoStage.setScene(admin_scene);
	    photoStage.show();
	}
	
	public void save(){
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("users.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(new ArrayList<User> (members));
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in users.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}

}
