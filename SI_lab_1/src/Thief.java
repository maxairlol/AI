import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Thief {
    private List<City> cityList;
    private List<Item> itemList;
    private Knapsack knapsack;
    private double maxSpeed;
    private double minSpeed;
    private double currentSpeed;
    private double travelTime;
    private double fitness;

    public Thief(){
        cityList = new ArrayList<>();
        itemList = new ArrayList<>();
        knapsack = new Knapsack();
        currentSpeed = 1;
        travelTime = 0;
        fitness = 0;

        for(int i = 0; i < CityManager.numOfCities(); i++){
            cityList.add(null);
        }
    }

    public Thief(Thief other){
        cityList = new ArrayList<>();
        itemList = new ArrayList<>();
        minSpeed = other.minSpeed;
        maxSpeed = other.maxSpeed;
        currentSpeed = other.currentSpeed;
        travelTime = other.travelTime;
        fitness = other.fitness;
        knapsack = new Knapsack(other.knapsack);

        for(int i = 0; i < CityManager.numOfCities(); i++){
            cityList.add(null);
        }
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public void setKnapsack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }

    public int cityListSize() {
        return cityList.size();
    }

    public City getCity(int position) {
        return (City)cityList.get(position);
    }

    public void setCity(int position, City city) {
        cityList.set(position, city);
    }

    public boolean containsCity(City city){
        return cityList.contains(city);
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void createRoute(){
        for(int cityIndex = 0; cityIndex < CityManager.numOfCities(); cityIndex++){
            setCity(cityIndex,CityManager.getCity(cityIndex));
        }
        Collections.shuffle(cityList);
    }

    public void createItems(){
        boolean isFull = false;
        int dimensions = cityList.size();
        City fromCity;
        City toCity;
        for(int i = 0; i < dimensions; i++) {
            fromCity = cityList.get(i);
            if (i + 1 < dimensions) {
                toCity = cityList.get(i + 1);
            } else {
                toCity = cityList.get(0);
            }
            Item item = fromCity.mostValueItem();
            if (!isFull && item != null) {
                if (knapsack.getCapacity() + item.getWeight() < knapsack.getMaxCapacity()) {
                    itemList.add(item);
                    knapsack.setProfit(knapsack.getProfit() + item.getProfit());
                    knapsack.setCapacity(knapsack.getCapacity() + item.getWeight());
                    calculateCurrentSpeed();
                } else if (knapsack.getCapacity() >= knapsack.getMaxCapacity()) {
                    isFull = true;
                }
            }
            travelTime += fromCity.distanceTo(toCity) / currentSpeed;

            //TESTING
            /*
            System.out.println(i + "  " +travelTime);
            System.out.println("Speed: " + currentSpeed);
            System.out.println("Capacity: " + knapsack.getCapacity());
            */
        }
    }

    private void calculateCurrentSpeed(){
        currentSpeed = maxSpeed - knapsack.getCapacity()*((maxSpeed - minSpeed)/knapsack.getMaxCapacity());
    }

    public double getFitness(){
        if (fitness == 0) {
            fitness = knapsack.getProfit() - travelTime;
        }
        return fitness;
    }

    public static void main (String[] args){

    }
}
