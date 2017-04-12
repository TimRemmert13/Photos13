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
/**
 * Class to control the admin.fxml file 
 * @author Tim Remmert
 *
 */
public class AdminController{
	/**
	 * label to show the window is the admin wondow
	 */
	@FXML
	Label adminLabel;
	/**
	 * label about list of user to indicate it is 
	 * a list of users
	 */
	@FXML
	Label list;
	/**
	 * Label to show the text field below is to create a
	 * user
	 */
	@FXML
	Label createUser;
	/**
	 * list view of all the users of the application
	 */
	@FXML
	ListView users;
	/**
	 * text field to input the username of the user we 
	 * want to create
	 */
	@FXML
	TextField username;
	/**
	 * text field to input the password of the user we 
	 * want to create
	 */
	@FXML
	TextField password;
	/**
	 * Button when pressed creates a user 
	 */
	@FXML
	Button create;
	/**
	 * button when pressed logs out from the admin window to
	 * the login window
	 */
	@FXML
	Button logout;
	/**
	 * button when pressed deletes a user from the admin list of users
	 */
	@FXML
	Button delete;
	/**
	 * Observable list of user to be displayed in list view
	 */
	ObservableList<User> members = FXCollections.observableArrayList();
	
    /**
     * Method to initialize the list of users
     * @param list the list of users for the application
     */
    public void setData(List<User> list){
		this.members = FXCollections.observableArrayList(list);
		users.setItems(members);
	}
/**
 * Method to create a new user
 * @param e action event when button is pressed
 */
	public void createUser(ActionEvent e){
		if(this.password == null ||  this.username == null){
			return;
		}
		
		String name = this.username.getText();
		String pass = this.password.getText();
		ArrayList<Album> albums = new ArrayList<Album>();
		List<Photo> photos = new ArrayList<Photo>();	
		
		File monkey = new File("images/monkey.jpg");
		File lake = new File("images/lake.jpeg");
		File rainbow_rose = new File("images/Rainbow_Rose.jpg");
		File waterfall = new File("images/waterfall.jpeg");
		
		List<Tag> t1 = new ArrayList<Tag>();
		List<Tag> t2 = new ArrayList<Tag>();
		List<Tag> t3 = new ArrayList<Tag>();
		List<Tag> t4 = new ArrayList<Tag>();
		
		Calendar calendar = Calendar.getInstance();
		
		Photo pmonkey = new Photo(monkey,t1,calendar);
		Photo plake = new Photo(lake, t2, calendar);
		Photo prainbow_rose = new Photo(rainbow_rose, t3,calendar);
		Photo pwaterfall = new Photo(waterfall, t4, calendar);
		
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
	/**
	 * Method to delete a user
	 * @param e action event when button is pressed
	 */
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
	/**
	 * Method to log out of admin window to the login window
	 * @param e action event when button is pressed
	 * @throws IOException
	 */
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
	/**
	 * Method to save the list of users and all there data user serializable file
	 */
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
