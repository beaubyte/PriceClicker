import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import javafx.scene.layout.HBox;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import java.lang.Runnable;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class ClickerStage extends BorderPane{

    private Clicker clicker;
    private Text clickCountText;
    private ClickerShop shop;
    private Text buildingRateText;
    private HBox box1Theater;
    private HBox box2Theater;
    private HBox box3Theater;
    private HBox box4Theater;
    private HBox box5Theater;
    private SaveLoader saveLoader;
    public ClickerStage(){
        clicker = new Clicker();
        clickCountText = new Text();
        buildingRateText = new Text();
        shop = new ClickerShop();
        clicker.reset();
        saveLoader = new SaveLoader();
        clickCountText.setText("Clicks: 1");
        setupClickPane(clickCountText);
        setupHelperPane();
        setupShopPane();
        setupTitlePane();
        try{
            scheduledTask();
        } catch (InterruptedException e){
            System.out.println("Warning: Update Interrupted");
        }
    }

    public void setupClickPane(Text clickCountText){
        VBox clickPane = new VBox(15);
        Image clickBurger = new Image(ClickerStage.class.getResourceAsStream("assets/burger.jpg"));
        ImageView clickObject = new ImageView(clickBurger);
        Font clickFont = new Font ("Arial bold", 30);
        clickObject.setFitHeight(200);
        clickObject.setFitWidth(200);
        clickCountText.setFont(clickFont);
        buildingRateText.setFont(clickFont);
        HBox saveMenu = new HBox(20);
        StackPane save = new StackPane();
        StackPane load = new StackPane();
        Rectangle saveButton = new Rectangle(100,50);
        Rectangle loadButton = new Rectangle(100,50);
        saveButton.setFill(Color.WHITE);
        loadButton.setFill(Color.WHITE);
        Text saveText = new Text("Save");
        Text loadText = new Text("Load");
        saveText.setFont(clickFont);
        loadText.setFont(clickFont);
        save.getChildren().addAll(saveButton, saveText);
        load.getChildren().addAll(loadButton, loadText);
        saveMenu.getChildren().addAll(save,load);
        clickPane.getChildren().addAll(clickCountText, buildingRateText, clickObject, saveMenu);
        clickObject.setOnMouseClicked(event -> {
            clickCountText.setText("Clicks: " + String.valueOf(clicker.getClicks()));
            clicker.click();
            });
        save.setOnMouseClicked(event -> {
            try {
                saveLoader.saveGame(clicker, shop);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            
        });
        load.setOnMouseClicked(event -> {
            // reset of the theaters is done in this function
            try {
                saveLoader.loadGame(clicker, shop);
                box1Theater.getChildren().clear();
                box2Theater.getChildren().clear();
                box3Theater.getChildren().clear();
                box4Theater.getChildren().clear();
                box5Theater.getChildren().clear();
                for (int i = 3; i <= 7; i++){
                    for (int j = 7 - shop.getStock(i); j > 0; j--){
                        placeHelper(i);
                    }
                }
                clickCountText.setText("Clicks: " + String.valueOf(clicker.getClicks()));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        clickPane.setAlignment(Pos.CENTER);
        clickPane.setTranslateX(10);
        setLeft(clickPane);
        
    }
    public void setupHelperPane(){
        VBox helperPane = new VBox(10);
        Text helperTitle = new Text("Helpers");
        // creates the boxes to hold the helpers
        Rectangle box1 = new Rectangle(400,75);
        Rectangle box2 = new Rectangle(400,75);
        Rectangle box3 = new Rectangle(400,75);
        Rectangle box4 = new Rectangle(400,75);
        Rectangle box5 = new Rectangle(400,75);
        Color theaterColor = Color.rgb(134,171,191);
        // sets the color of the boxes for helpers
        box1.setFill(theaterColor);
        box2.setFill(theaterColor);
        box3.setFill(theaterColor);
        box4.setFill(theaterColor);
        box5.setFill(theaterColor);
        // creates stackpanes for boxes
        StackPane box1Pane = new StackPane();
        StackPane box2Pane = new StackPane();
        StackPane box3Pane = new StackPane();
        StackPane box4Pane = new StackPane();
        StackPane box5Pane = new StackPane();
        // create HBoxes for helpers
        box1Theater = new HBox(5);
        box2Theater = new HBox(5);
        box3Theater = new HBox(5);
        box4Theater = new HBox(5);
        box5Theater = new HBox(5);
        // set hbox locations
        box1Theater.setTranslateX(10);
        box1Theater.setTranslateY(10);
        box2Theater.setTranslateX(10);
        box2Theater.setTranslateY(10);
        box3Theater.setTranslateX(10);
        box3Theater.setTranslateY(10);
        box4Theater.setTranslateX(10);
        box4Theater.setTranslateY(10);
        box5Theater.setTranslateX(10);
        box5Theater.setTranslateY(10);
        // set stackpane sizes
        box1Pane.setMaxSize(400,75);
        box2Pane.setMaxSize(400,75);
        box3Pane.setMaxSize(400,75);
        box4Pane.setMaxSize(400,75);
        box5Pane.setMaxSize(400,75);
        // adds boxes to stackpanes
        box1Pane.getChildren().addAll(box1, box1Theater);
        box2Pane.getChildren().addAll(box2, box2Theater);
        box3Pane.getChildren().addAll(box3, box3Theater);
        box4Pane.getChildren().addAll(box4, box4Theater);
        box5Pane.getChildren().addAll(box5, box5Theater);
        // sets alignment for stuff
        helperPane.setAlignment(Pos.CENTER);
        helperPane.getChildren().addAll(box1Pane,box2Pane,box3Pane,box4Pane,box5Pane);
        setCenter(helperPane);
    }

    public void setupShopPane(){
        VBox shopPane = new VBox(10);
        HBox upgradePane = new HBox(5);
        Text helperText = new Text("Helpers:");
        // images the upgrades use
        Image alfredoPasta = new Image(ClickerStage.class.getResourceAsStream("assets/alfredo.jpg"));
        Image stirFry = new Image(ClickerStage.class.getResourceAsStream("assets/fried_rice.jpg"));
        Image macPizza = new Image(ClickerStage.class.getResourceAsStream("assets/mac_and_cheese_pizza.jpg"));
        // sets upgrade images
        ImageView upgrade1 = new ImageView(alfredoPasta);
        ImageView upgrade2 = new ImageView(stirFry);
        ImageView upgrade3 = new ImageView(macPizza);
        // image the helper buttons use
        Image helperButtonImage = new Image(ClickerStage.class.getResourceAsStream("assets/helperButton.jpg"));
        // font the helper buttons use
        Font helperFont = new Font("Arial italic", 20);
        // sets background of helper buttons
        ImageView helperButton = new ImageView(helperButtonImage);
        ImageView helperButton2 = new ImageView(helperButtonImage);
        ImageView helperButton3 = new ImageView(helperButtonImage);
        ImageView helperButton4 = new ImageView(helperButtonImage);
        ImageView helperButton5 = new ImageView(helperButtonImage);
        // sets text of buttons
        Text helper1Text = new Text("Ice Cream Machine\n100 clicks");
        Text helper2Text = new Text("Lunch Lady\n500 clicks");
        Text helper3Text = new Text("Soda Fountain\n1000 clicks");
        Text helper4Text = new Text("Checkout Counter\n2500 clicks");
        Text helper5Text = new Text("Dishwasher\n10000 clicks");
        // sets colors of helper buttons
        helper1Text.setFill(Color.WHITE);
        helper2Text.setFill(Color.WHITE);
        helper3Text.setFill(Color.WHITE);
        helper4Text.setFill(Color.WHITE);
        helper5Text.setFill(Color.WHITE);
        // sets fonts of helper buttons
        helper1Text.setFont(helperFont);
        helper2Text.setFont(helperFont);
        helper3Text.setFont(helperFont);
        helper4Text.setFont(helperFont);
        helper5Text.setFont(helperFont);
        // creates panes for the helper buttons
        StackPane helper1 = new StackPane();
        StackPane helper2 = new StackPane();
        StackPane helper3 = new StackPane();
        StackPane helper4 = new StackPane();
        StackPane helper5 = new StackPane();
        // adds content to helper panes
        helper1.getChildren().addAll(helperButton, helper1Text);
        helper2.getChildren().addAll(helperButton2, helper2Text);
        helper3.getChildren().addAll(helperButton3, helper3Text);
        helper4.getChildren().addAll(helperButton4, helper4Text);
        helper5.getChildren().addAll(helperButton5, helper5Text);

        // binds buy actions to the buttons
        upgrade1.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 0);
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        
        upgrade2.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 1);
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        upgrade3.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 2);
                
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        helper1.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 3);
                placeHelper(3);
                
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        helper2.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 4);
                placeHelper(4);
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        helper3.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 5);
                placeHelper(5);
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        helper4.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 6);
                placeHelper(6);
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
        helper5.setOnMouseClicked(event -> {
            try {
                shop.buyItem(clicker, 7);
                placeHelper(7);
            } catch (Exception brokeException){
                Alert alert = new Alert(AlertType.ERROR, brokeException.getMessage());
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("Error dismissed"));
            }
            });
            
        // adds content to upgrade pane
        upgradePane.getChildren().addAll(upgrade1,upgrade2,upgrade3);
        // adds all content together on the one pane
        shopPane.getChildren().addAll(upgradePane,helper1,helper2,helper3,helper4,helper5);
        shopPane.setAlignment(Pos.CENTER);
        shopPane.setTranslateX(-10);
        setRight(shopPane);
    }


    public void setupTitlePane(){
        HBox titlePane = new HBox(300);
        Font clickFont = new Font("Arial", 30);
        Text clickText = new Text("Price");
        Text helperText = new Text("Helpers");
        Text shopText = new Text("Shop");
        clickText.setFont(clickFont);
        helperText.setFont(clickFont);
        shopText.setFont(clickFont);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.getChildren().addAll(clickText,helperText,shopText);
        setTop(titlePane);
    }

    public void scheduledTask() throws InterruptedException{
        ScheduledExecutorService executorOfClickers = Executors.newScheduledThreadPool(1);
        Runnable runnableTask = () -> {
            clicker.compoundClicks();
            clickCountText.setText("Clicks: " + String.valueOf(clicker.getClicks()));
            buildingRateText.setText(clicker.getBuildingRate() + " clicks/sec");
        };
        executorOfClickers.scheduleAtFixedRate(runnableTask, 100, 1000, TimeUnit.MILLISECONDS);

    }

    public void placeHelper(int itemIndex){
        switch (itemIndex) {
            case 3:
                Image iceCreamImage = new Image(ClickerStage.class.getResourceAsStream("/assets/icecreammachine.jpg"));
                ImageView iceCream = new ImageView(iceCreamImage);
                box1Theater.getChildren().add(iceCream);
                break;
            case 4:
                Image lunchLadyImage = new Image(ClickerStage.class.getResourceAsStream("/assets/lunch_lady.jpg"));
                ImageView lunchLady = new ImageView(lunchLadyImage);
                box2Theater.getChildren().add(lunchLady);
                break;
            case 5:
                Image sodaFountainImage = new Image(ClickerStage.class.getResourceAsStream("/assets/soda_fountain.jpg"));
                ImageView sodaFountain = new ImageView(sodaFountainImage);
                box3Theater.getChildren().add(sodaFountain);
                break;
            case 6:
                Image checkoutCounterImage = new Image(ClickerStage.class.getResourceAsStream("/assets/credid_reader.jpg"));
                ImageView checkoutCounter = new ImageView(checkoutCounterImage);
                box4Theater.getChildren().add(checkoutCounter);
                break;
            case 7:
                Image dishWasherImage = new Image(ClickerStage.class.getResourceAsStream("/assets/dishwasher.jpg"));
                ImageView dishWasher = new ImageView(dishWasherImage);
                box5Theater.getChildren().add(dishWasher);
                break;
        }
    }
}