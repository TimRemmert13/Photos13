package application;
	
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import controller.LoginController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
/**
 * Class to launch the GUI and load the login window
 * @author Tim Remmert
 *
 */
public class Photos extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<User> data = null;
		
		try {
		    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser") );
		    List<User> list = (List<User>) ois.readObject();
		    data = list;
		    ois.close();}
		catch(Exception e){
			e.printStackTrace();
		}
		     
	        
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/view/Login.fxml"));
		    AnchorPane root = (AnchorPane)loader.load();
		    LoginController loginController = loader.getController();
		    loginController.setData(data);
		    //loginController.changeToAnother(primaryStage);
			Scene scene = new Scene(root,600,600);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Photos13");
			primaryStage.show();
		//} catch(Exception e) {
			//e.printStackTrace();
		//}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}