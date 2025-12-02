public class ShopItem{
    private int itemPrice;
    private boolean isBuilding;
    private int itemModifier;
    private int stockCount;

    public ShopItem(int itemPrice, boolean isBuilding, int itemModifier, int stockCount){
        this.itemPrice = itemPrice;
        this.isBuilding = isBuilding;
        this.itemModifier = itemModifier;
        this.stockCount = stockCount;
    }
     // Getter and Setter for itemPrice
    public int getItemPrice() {
        return itemPrice;
    }

    public int getStockCount() {
        return stockCount;
    }
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }


    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    // Getter and Setter for isBuilding
    public boolean isBuilding() {
        return isBuilding;
    }

    public void setBuilding(boolean isBuilding) {
        this.isBuilding = isBuilding;
    }

    // Getter and Setter for itemModifier
    public int getItemModifier() {
        return itemModifier;
    }

    public void setItemModifier(int itemModifier) {
        this.itemModifier = itemModifier;
    }

}