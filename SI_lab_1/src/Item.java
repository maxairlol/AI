public class Item {
    private int index;
    private int profit;
    private int weight;
    private int assigned_city_number;

    public Item(){}

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAssigned_city_number() {
        return assigned_city_number;
    }

    public void setAssigned_city_number(int assigned_city_number) {
        this.assigned_city_number = assigned_city_number;
    }

    public Item(int index, int profit, int weight, int assigned_city_number) {
        this.index = index;
        this.profit = profit;
        this.weight = weight;
        this.assigned_city_number = assigned_city_number;
    }
}
