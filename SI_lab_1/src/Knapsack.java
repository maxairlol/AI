public class Knapsack{
    private int maxCapacity;
    private int capacity;
    private int profit;

    public Knapsack(){
        capacity = 0;
        profit = 0;
    }
    public Knapsack (int maxCapacity){
        this.maxCapacity = maxCapacity;
        capacity = 0;
        profit = 0;
    }

    public Knapsack(Knapsack other){
        maxCapacity = other.maxCapacity;
        capacity = other.capacity;
        profit = other.profit;
    }

    public int getMaxCapacity (){
        return maxCapacity;
    }

    public void setMaxCapacity (int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
