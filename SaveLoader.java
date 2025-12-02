import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoader{
    public SaveLoader(){
    }
    // saves data to ArrayList, then writes the data to the file
    public void saveGame(Clicker clicker, ClickerShop shop) throws Exception{
        File saveFile = new File("save.txt");
        FileWriter fw = new FileWriter(saveFile);
        ArrayList<Integer> gameData = new ArrayList<Integer>();
        gameData.add(clicker.getClicks());
        gameData.add(clicker.getClickModifier());
        gameData.add(clicker.getBuildingRate());
        for (int i = 0; i <= 7; i++){
            gameData.add(shop.getStock(i));
        }
        for (int i = 0; i < gameData.size(); i++){
            fw.write(gameData.get(i) + " ");
        }
        fw.close();
    }
    // loads data from the file into the game's variables
    public void loadGame(Clicker clicker, ClickerShop shop) throws Exception{
        FileInputStream fileInStream = new FileInputStream("save.txt");
        Scanner fileScanner = new Scanner(fileInStream);
        clicker.setClickCount(fileScanner.nextInt());
        clicker.setClickModifier(fileScanner.nextInt());
        clicker.setBuildingRate(fileScanner.nextInt());
        for (int i = 0; i <= 7; i++){
            shop.setStock(i,fileScanner.nextInt());
        }
        fileScanner.close();
    }

}