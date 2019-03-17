import java.util.ArrayList;

public class CityManager {
    private static ArrayList<City> destinationCities = new ArrayList<>();

    public static void addCity(City city){
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static int numOfCities(){
        return destinationCities.size();
    }

    public static ArrayList<City> getDestinationCities() {
        return destinationCities;
    }
}
