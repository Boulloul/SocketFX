package application;
	


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application implements Observer{
	private Chat controller;
	@Override
	public void start(final Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));
			controller = loader.getController();
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,700,400);
			primaryStage.setScene(scene);
			primaryStage.setOnHidden(e ->{
		
						
						System.out.println("Closed");
						Platform.exit();
				

			});
				
		
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void stop() throws Exception {
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Bonjour "+arg);
		
	}
}
