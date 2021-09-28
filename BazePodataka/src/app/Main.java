package app;

import controllers.Controller;
import controllers.DodavanjeOsobaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainFXML.fxml"));
			Parent root = loader.load();
			
			Controller cont = (Controller) loader.getController();
			cont.init();
			Scene scene = new Scene(root, 1135, 550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
