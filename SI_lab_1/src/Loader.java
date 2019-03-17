import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Loader{
    List<Item> items;
    List<City> cities;
    Knapsack knapsack;
    Thief thief;
    Scanner sc;
    public Loader(){
        items = new ArrayList<>();
        cities = new ArrayList<>();
        knapsack = new Knapsack();
        thief = new Thief();
    }
    public void load(String filename){
        try{
            sc = new Scanner(new File(filename));
            sc.useLocale(Locale.UK);
            while (sc.hasNext()){
                String capacityLine=sc.findInLine("CAPACITY OF KNAPSACK");
                String maxSpeedLine=sc.findInLine("MAX SPEED");
                String minSpeedLine=sc.findInLine("MIN SPEED");
                String citiesLine=sc.findInLine("(INDEX, X, Y)");
                String itemsLine=sc.findInLine("ITEMS SECTION");
                if (capacityLine!=null){
                    knapsack.setMaxCapacity(findNextInt());
                    thief.setKnapsack(knapsack);
                }
                if (minSpeedLine!=null){
                    thief.setMinSpeed(findNextDouble());
                }
                if (maxSpeedLine!=null){
                    thief.setMaxSpeed(findNextDouble());
                }
                if (citiesLine!=null){
                    findAllCities();
                }
                if (itemsLine!=null){
                    findAllItems();
                }

                sc.nextLine();
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void findAllItems (){
        Item item;
        while (!sc.hasNextInt()){
            sc.next();
        }
        while (sc.hasNextInt()){
            int index=sc.nextInt();
            int profit=sc.nextInt();
            int weight=sc.nextInt();
            int assignedNodeNumber=sc.nextInt();
            item = new Item(index,profit,weight,assignedNodeNumber);
            addItemToCity(item);
            items.add(item);
        }
    }
    private void findAllCities (){
        City city;
        while (!sc.hasNextInt()){
            sc.next();
        }
        while (sc.hasNextInt()){
            int index = sc.nextInt();
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            city = new City(index,x,y);
            cities.add(city);
        }
    }

    private void addItemToCity(Item item){
        int pos = item.getAssigned_city_number();
        cities.get(pos-1).getItems().add(item);
    }

    public int findNextInt (){
        while (!sc.hasNextInt()){
            sc.next();
        }
        return sc.nextInt();
    }

    public double findNextDouble(){
        while (!sc.hasNextFloat()){
            sc.next();
        }
        return sc.nextDouble();
    }

    public List<Item> getItems (){
        return items;
    }

    public void setItems (List<Item> items){
        this.items = items;
    }

    public List<City> getCities(){
        return cities;
    }

    public void setCities(List<City> cities){
        this.cities = cities;
    }

    public Knapsack getKnapsack (){
        return knapsack;
    }

    public void setKnapsack (Knapsack knapsack){
        this.knapsack = knapsack;
    }

    public Thief getThief (){
        return thief;
    }

    public void setThief (Thief thief){
        this.thief = thief;
    }
}
