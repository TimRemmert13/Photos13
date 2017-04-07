package application;
	
import java.io.IOException;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
//test
public class Photos extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		//try {
			FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/view/Login.fxml"));
		    AnchorPane root = (AnchorPane)loader.load();
		    //LoginController loginController = loader.getController();
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