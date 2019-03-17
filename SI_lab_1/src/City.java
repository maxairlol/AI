import java.util.ArrayList;
import java.util.List;

public class City {
    private int index;
    private double x;
    private double y;
    private List<Item> items = new ArrayList<>();

    public City(){}

    public City(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    public City(City city){
        this.index = city.index;
        this.x = city.x;
        this.y = city.y;
    }

    public double distanceTo(City city){
        double xDis = Math.abs(this.getX() - city.getX());
        double yDis = Math.abs(this.getY() - city.getY());
        double distance=Math.sqrt(Math.pow(xDis,2)+ Math.pow(yDis,2));
        return (int)distance + 1;
    }

    public Item mostValueItem(){
        Item result = null;
        if(!items.isEmpty()){
            result = items.get(0);
            Item nextItem;
            double currValue = (double) result.getProfit()/result.getWeight();
            for(int i = 1; i < items.size() - 1; i++){
                nextItem = items.get(i);
                double value = (double) nextItem.getProfit()/nextItem.getWeight();
                if(value > currValue){
                    currValue = value;
                    result = nextItem;
                }
            }
        }
        return result;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString (){
        return "City{" + "index=" + index + ", x=" + x + ", y=" + y + '}';
    }
}
