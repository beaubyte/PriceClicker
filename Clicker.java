public class Clicker {
    private int clickCount;
    private int clickModifier;
    private int clickBuildingRate;
    
    public Clicker(){
        clickCount = 0;
        clickModifier = 0;
        clickBuildingRate = 0;
    }
    public void click(){
        clickCount = clickCount + clickModifier;
        // when you click the food it go up
    }
    public int getClicks(){
        return clickCount;
    }

    public void removeClicks(int debt){
        clickCount = clickCount - debt;
    }
    public void compoundClicks(){
        clickCount = clickCount + clickBuildingRate;
    }
    public void reset(){
        clickModifier = 1;
        clickCount = 0;
        clickBuildingRate = 0;
        return;
    }
    public void setClickModifier(int newModifier){
        clickModifier = newModifier;
        return;
    }

    public int getClickModifier(){
        return clickModifier;
    }

    public void setBuildingRate(int newRate){
        clickBuildingRate = newRate;
        return;
    }

    public int getBuildingRate(){
        return clickBuildingRate;
    }

    public void setClickCount(int clickCount){
        this.clickCount = clickCount;
    }
}