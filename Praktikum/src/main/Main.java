package main;

import gui.TeppichControl;
import gui.TeppichenView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new TeppichControl(primaryStage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
