package application;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Alert {
  public static Scene display(String title){
    //this class initializes a new popup scene, accepting a title in the parameter
    //returns the basic scene to be manipulated further in main
    Stage window = new Stage();

    //blocks input events until this popup is dealt with by the user
    window.initModality(Modality.APPLICATION_MODAL);
    //basic window setup
    window.setTitle(title);
    window.setMinWidth(250);

    VBox layout = new VBox(10);
    Scene scene = new Scene(layout);

    return scene;
  }
}
