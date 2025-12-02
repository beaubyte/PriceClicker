import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import java.util.concurrent.TimeUnit;
import javafx.scene.layout.StackPane;

public class Main extends Application{
   
   public static void main(String [] args) {
      launch(args);
   }

    public void start(Stage priceStage){
        ClickerStage root = new ClickerStage();
        StackPane appPane = new StackPane();
        Pane blue = new Pane();
        blue.setStyle("-fx-background-color: LIGHTBLUE;");
        appPane.getChildren().addAll(blue,root);
        Scene scene1 = new Scene(appPane, 1000,500,Color.LIGHTBLUE); // lightblue is unnessessary but I don't want to change it
        priceStage.setScene(scene1);
        priceStage.setTitle("Price Clicker!");
        priceStage.show();
        
    }
}