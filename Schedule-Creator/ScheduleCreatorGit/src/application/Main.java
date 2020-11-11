/**
 * Schedule Creator Application
 * 
 * Main class of the application that handles launching the main view 
 * @author Hot Dog or Not Hot Dog
 */
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	public static Stage stage;

	/**
	 * Method that loads and shows the main view of the application
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			primaryStage.setScene(new Scene(root, 800, 800));
			primaryStage.show();
			
			stage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method for launching the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
