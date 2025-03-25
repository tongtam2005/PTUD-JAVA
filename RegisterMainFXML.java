package register.fxml; 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class RegisterMainFXML extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
					 
				
				//Them layout vao Scene
				//BorderPane root = new BorderPane();
				Scene scene = new Scene(root);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				//Them scene vao stage
				primaryStage.setScene(scene);
				//Hien thi khung chua len
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
		
		public static void main(String[] args) {
			launch(args);
		}
	}