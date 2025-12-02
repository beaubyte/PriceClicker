import java.util.ArrayList;
public class ClickerShop {
    private ShopItem upgrade1Item;
    private ShopItem upgrade2Item;
    private ShopItem upgrade3Item;
    private ShopItem helper1Item;
    private ShopItem helper2Item;
    private ShopItem helper3Item;
    private ShopItem helper4Item;
    private ShopItem helper5Item;
    private ArrayList<ShopItem> shopItems;

    public ClickerShop(){
        // initilizes shop items, @param instantiated with [price], [isHelper] [modifier]
        upgrade1Item = new ShopItem(500, false, 2, 1);
        upgrade2Item = new ShopItem(2500, false, 5, 1);
        upgrade3Item = new ShopItem(10000, false, 20, 1);
        helper1Item = new ShopItem(100, true, 1, 7);
        helper2Item = new ShopItem(500, true, 2, 7);
        helper3Item = new ShopItem(1000, true, 5, 7);
        helper4Item = new ShopItem(2500, true, 10, 7);
        helper5Item = new ShopItem(10000, true, 25, 7);
        // adds all shop items to an arraylist
        shopItems = new ArrayList<ShopItem>();
        shopItems.add(upgrade1Item);
        shopItems.add(upgrade2Item);
        shopItems.add(upgrade3Item);
        shopItems.add(helper1Item);
        shopItems.add(helper2Item);
        shopItems.add(helper3Item);
        shopItems.add(helper4Item);
        shopItems.add(helper5Item);
    }

    // 
    public void buyItem(Clicker clicker, int itemIndex) throws Exception{
        
        if (shopItems.get(itemIndex).getStockCount() < 1){
            Exception outOfStockException = new Exception("Insufficent stock to purchase item");
            throw outOfStockException;
        }
        if (clicker.getClicks() < shopItems.get(itemIndex).getItemPrice()){
            Exception brokeException = new Exception("Insufficent funds (" + shopItems.get(itemIndex).getItemPrice() + " clicks)");
            throw brokeException;
        }
        clicker.removeClicks(shopItems.get(itemIndex).getItemPrice());
        shopItems.get(itemIndex).setStockCount(shopItems.get(itemIndex).getStockCount() - 1);
        if (shopItems.get(itemIndex).isBuilding() == false){
            clicker.setClickModifier(clicker.getClickModifier() + shopItems.get(itemIndex).getItemModifier());
        } else {
            clicker.setBuildingRate(clicker.getBuildingRate() + shopItems.get(itemIndex).getItemModifier());
        }
    }

    public int getStock(int itemIndex){
        return shopItems.get(itemIndex).getStockCount();
    }

    public void setStock(int itemIndex, int newStock){
        shopItems.get(itemIndex).setStockCount(newStock);
    }
}