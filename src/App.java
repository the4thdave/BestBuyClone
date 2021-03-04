// BestBuyClone Project
// CISC 3598 

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Group;


public class App extends Application
{	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			GridPane root = new GridPane();
			
			//create image
			Image logo = new Image("img/bestbuy.png", 160, 60, false, true);
			//change this
			
			//set image view
			ImageView logoView = new ImageView(logo);

			//set position/aspect ratio
			logoView.setX(0);
			logoView.setY(25);
			logoView.setPreserveRatio(true);

			//create new group
			Group imageGroup = new Group(logoView);

			//add to grid container
			root.getChildren().add(imageGroup);

			//create scene, color it blue
			Scene scene = new Scene(root,1280,720);
			scene.setFill(Color.BLUE);

			primaryStage.setTitle("best buy");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}