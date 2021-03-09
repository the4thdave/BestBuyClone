package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.scene.Group; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Image bbLogo = new Image(new FileInputStream("src/img/bestbuy.png"));
			ImageView imageView = new ImageView(bbLogo);
			imageView.setX(50);
			imageView.setY(25);
			imageView.setFitHeight(100);
			imageView.setFitWidth(150);
			imageView.setPreserveRatio(true);

			TextField searchBar = new TextField();
			
			Button login = new Button("Login");
			login.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	 // Login button
                     // blank for now
	            }
	        });

			Button cart = new Button("Cart");
			cart.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	 // Cart button
                     // blank for now
	            }
	        });
			

			GridPane root = new GridPane();
			root.add(imageView, 0, 0);
			root.add(new Label(), 0, 0);
			root.add(searchBar, 1, 0);
			root.add(login, 2, 0);
			root.add(cart, 3, 0);


			Scene scene = new Scene(root, 1500, 750);
			primaryStage.setScene(scene);
			primaryStage.show();
            primaryStage.setTitle("BestBuy");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	public static void main(String[] args) {
		launch(args);
	}
}
