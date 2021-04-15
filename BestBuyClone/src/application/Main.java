package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;


public class Main extends Application {
	TextField clock;
	int numOfItems = 0; 
	double total = 0.0;
	String cartItems = ""; 
	
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		try {

			
			//clock text area
	        clock = new TextField();
	        clock.setEditable(false);
	        clock.setPrefHeight(100);   
	        clock.setPrefWidth(200);
			
			// BestBuy Logo Image
			Image bbLogo = new Image(new FileInputStream("src/img/bestbuy.png"));
			ImageView logoView = new ImageView(bbLogo);
			logoView.setX(50);
			logoView.setY(25);
			logoView.setFitHeight(100);
			logoView.setFitWidth(200);
			logoView.setPreserveRatio(true);

			// Item1 (RTX 3080) Image, Price Label, Add to Cart button
			Image item1 = new Image(new FileInputStream("src/img/geforce-rtx-3080-shop-600-p@2x.png"));
			ImageView item1View = new ImageView(item1);
			item1View.setFitHeight(170);
			item1View.setFitWidth(350);
			double item1p = 699.99; 
			Label item1Desc = new Label("Nvidia RTX 3080\n" + item1p);
			item1Desc.setFont(new Font("Arial", 24));

			Button addToCart1 = new Button("Add to Cart");
			addToCart1.setStyle("-fx-background-color: yellow");
			addToCart1.setMaxSize(100, 50);
			addToCart1.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	    			cartItems += "Nvidia RTX 3080\t";
	    			cartItems += String.valueOf(item1p) + "\n";
	    			numOfItems++; 
	    			total += item1p;
	            }
	        });

			// Item2 (GTX 1660 SUPER) Image, Price Label, Add to Cart button
			Image item2 = new Image(new FileInputStream("src/img/qfs1dgkk1othuvtzc2aj.png"));
			ImageView item2View = new ImageView(item2);
			item2View.setFitHeight(170);
			item2View.setFitWidth(350);
			double item2p = 269.99;
			Label item2Desc = new Label("MSI GTX 1660 SUPER\n" + item2p);
			item2Desc.setFont(new Font("Arial", 24));

			Button addToCart2 = new Button("Add to Cart");
			addToCart2.setStyle("-fx-background-color: yellow");
			addToCart2.setMaxSize(100, 50);
			addToCart2.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	cartItems += "MSI GTX 1660 SUPER\t";
	    			cartItems += String.valueOf(item2p) + "\n";
	    			numOfItems++; 
	    			total += item2p;
	            }
	        });

			// Item3 (Radeon RX590) Image, Price Label, Add to Cart button
			Image item3 = new Image(new FileInputStream("src/img/2003251115030.png"));
			ImageView item3View = new ImageView(item3);
			item3View.setFitHeight(170);
			item3View.setFitWidth(350);
			double item3p = 299.99;
			Label item3Desc = new Label("Radeon RX590\n" + item3p);
			item3Desc.setFont(new Font("Arial", 24));

			Button addToCart3 = new Button("Out of Stock");
			addToCart3.setStyle("-fx-background-color: red");
			addToCart3.setMaxSize(100, 50);
			addToCart3.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	cartItems += "Radeon RX590\t";
	    			cartItems += String.valueOf(item3p) + "\n";
	    			numOfItems++; 
	    			total += item3p;
	            }
	        });

			// Item4 (13-inch MacBook Pro) Image, Price Label, Add to Cart button
			Image item4 = new Image(new FileInputStream("src/img/mbp_shop__fn4wvksqg5aq_large_2x.png"));
			ImageView item4View = new ImageView(item4);
			item4View.setFitHeight(170);
			item4View.setFitWidth(340);
			double item4p = 1299.99;
			Label item4Desc = new Label("13-inch MacBook Pro\n" + item4p);
			item4Desc.setFont(new Font("Arial", 24));

			Button addToCart4 = new Button("Add to Cart");
			addToCart4.setStyle("-fx-background-color: yellow");
			addToCart4.setMaxSize(100, 50);
			addToCart4.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	cartItems += "13-inch MacBook Pro\t";
	    			cartItems += String.valueOf(item4p) + "\n";
	    			numOfItems++; 
	    			total += item4p;
	            }
	        });
			
			// TextFields & Labels
			TextField searchBar = new TextField();
			Label filter = new Label("Filter: ");
			Label price = new Label("Price: ");
			Label lowtohigh = new Label("Low to High: ");
			Label hightolow = new Label("High to Low: ");
			
			//vbox for side menu to set background colour
			VBox vbox = new VBox (20, filter, price, lowtohigh, hightolow);
			vbox.setPadding(new Insets(50, 20, 50, 20)); 
			vbox.setStyle("-fx-background-color: white");

			// Buttons
			Button login = new Button("Login");
			login.setMaxSize(100, 20);
			login.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	 // Login button
                     // blank for now
	            }
	        });

			Button cart = new Button("Cart");
			cart.setStyle("-fx-background-color: yellow");
			cart.setMaxSize(100, 20);
			cart.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {

								final Stage dialog = new Stage();
								dialog.initModality(Modality.APPLICATION_MODAL);
								dialog.initOwner(primaryStage);
								dialog.setTitle("Cart");

								TextArea ta = new TextArea("");
								TextArea ta2 = new TextArea("");
								
								ta.setText(cartItems);
								
								cartItems = "";
								
								Button exitCart = new Button("Exit From Cart");

								Button checkout = new Button("Checkout");

								exitCart.setOnAction(new EventHandler<ActionEvent>()
									{
											@Override public void handle(ActionEvent e)
											{
												dialog.close();
											}
									});
								
								checkout.setOnAction(new EventHandler<ActionEvent>()
								{
										@Override public void handle(ActionEvent e)
										{
							            	Platform.runLater(new Runnable() 
											 {
											        public void run() 
											        {
											        	String rs=null;
											            socketUtils su = new socketUtils();
											            
											            if (su.socketConnect() == true) //this always seems to be false for whatever reason
											            {
											            	String strDouble = String.format("%.2f", total);
											            	String msg = "Transaction>kiosk#001" + "," + numOfItems + "," + strDouble;
							            	                su.sendMessage(msg);				            	
							            	                String ackOrNack = su.recvMessage();
							            	                
							            	                
							            	                msg = "quit";
							            	                su.sendMessage(msg);
							            	                rs = su.recvMessage();
							            	                
							            	                //
							            	                // close the socket connection
							            	                //
							            	                su.closeSocket();
							            	                
							            	                // 
							            	                // write to transaction log
							            	                //
							            	                msg = "CLIENT : Transaction>kiosk#001" + "," + numOfItems + "," + strDouble;
							            	                fileIO trans = new fileIO();
							            	                trans.wrTransactionData(msg);
							            	                
							            	                
							            	                // initialize variables back to zero
							            	                total=0.0;
							            	                numOfItems=0;        
							            	                
							            	                ta.setText("");
							            	                ta2.setText("");
							            	                
							            	                if (ackOrNack.startsWith("ACK") == true)
							            	                {
							            	                	ta2.setText("Success!    Message was received and processed by the Socket Server!");
							            	                }
							            	                else
							            	                {
							            	                   ta2.setText("RECV : " + ackOrNack);
							            	                   ta2.appendText(rs);
							            	                }
											            }
											            else
											            {
											            	// 
							            	                // write to transaction log
							            	                //
											            	String strDouble = String.format("%.2f", total);
							            	                String msg = "CLIENT NETWORK ERROR : Transaction>kiosk#001" + "," + numOfItems + "," + strDouble;
							            	                
							            	                fileIO trans = new fileIO();
							            	                trans.wrTransactionData(msg);
							            	                
							            	                
											            	Alert alert = new Alert(Alert.AlertType.ERROR);
													        alert.setTitle("--- Network Communications Error ---");
													        alert.setHeaderText("Unable to talk to Socket Server!");
													          
													        alert.showAndWait();
											            }
											        }
											 });
										}
								
								});

								VBox cartSetup = new VBox(20);
								HBox dialogVbox = new HBox(20);

								dialogVbox.setAlignment(Pos.CENTER);
								dialogVbox.getChildren().addAll(exitCart, checkout);
								cartSetup.getChildren().addAll(ta, dialogVbox, ta2);
								cartSetup.setStyle("-fx-background-color: lightblue");

								Scene dialogScene = new Scene(cartSetup, 500, 400);
	
								dialog.setScene(dialogScene);
								dialog.show();
	            }
	        });


			Button exit = new Button("Exit");
			exit.setStyle("-fx-background-color: red");
			exit.setMaxSize(100, 20);
			exit.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
								final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
								dialog.setTitle("Exit?");
                HBox dialogVbox = new HBox(20);

								Button confirmExit = new Button("Exit");
								confirmExit.setStyle("fx-background-color: red");

								Button dontExit = new Button("Return to Program");
				
								confirmExit.setOnAction((new EventHandler<ActionEvent>()
								{
										@Override public void handle(ActionEvent e)
											{								
												System.exit(0);
											}
									}));

								dontExit.setOnAction((new EventHandler<ActionEvent>()
									{
											@Override public void handle(ActionEvent e)
											{
												dialog.close();
											}
									}));
								
                dialogVbox.getChildren().add(dontExit);
                dialogVbox.getChildren().add(confirmExit);
								dialogVbox.setAlignment(Pos.CENTER);
								dialogVbox.setStyle("-fx-background-color: lightblue");

                Scene dialogScene = new Scene(dialogVbox, 300, 200);
	
                dialog.setScene(dialogScene);
                dialog.show();

	            }
	        });
			
			// GridPane setup
			GridPane root = new GridPane();
			root.setPadding(new Insets(10, 10, 10, 10));
			root.setHgap(50);
			root.setStyle("-fx-background-color: RoyalBlue");
			root.add(logoView, 0, 0);
			root.add(new Label(), 0, 0);
			root.add(searchBar, 1, 0);
			root.add(login, 2, 0);
			root.add(cart, 3, 0);
			root.add(exit, 4, 0);

			root.add(vbox, 0, 1, 1, 4);
			root.add(clock, 0, 5);
			root.add(item1View, 1, 1);
			root.add(item1Desc, 2, 1);
			root.add(addToCart1, 3, 1);
			root.add(item2View, 1, 2);
			root.add(item2Desc, 2, 2);
			root.add(addToCart2, 3, 2);
			root.add(item3View, 1, 3);
			root.add(item3Desc, 2, 3);
			root.add(addToCart3, 3, 3);
			root.add(item4View, 1, 4);
			root.add(item4Desc, 2, 4);
			root.add(addToCart4, 3, 4);

			refreshClock();
			
			// Scene setup

			Scene scene = new Scene(root, 1200, 950);

			primaryStage.setScene(scene);
			primaryStage.show();
            primaryStage.setTitle("BestBuy");
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshClock()
    {
    	Thread refreshClock = new Thread()
		   {  
			  public void run()
			  {	 
				while (true)
				{
					Date dte = new Date();
		
					String clockText = dte.toString();					      
				    clock.setText(clockText); 
			               
				    try
				    {
					   sleep(1000L);
				    }
				    catch (InterruptedException e) 
				    {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
				    }
				  
	            }  // end while ( true )
				 
		    } // end run thread
		 };
		 refreshClock.start();
    }


	public static void main(String[] args) {
		launch(args);
	}

}

