import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoaderTest {
    private int dimension;
    private int num_of_items;
    private int capacity;
    private double min_speed;
    private double max_speed;
    private List<City> cities;
    private List<Item> items;
    private Scanner scanner;

    public List<City> getCities() {
        return cities;
    }

    public List<Item> getItems() {
        return items;
    }

    public LoaderTest(){
        cities = new ArrayList<>();
        items = new ArrayList<>();
    }

    public void load(String fileName) {
        File file = new File(fileName);
        try {
            scanner = new Scanner(file);
            readNextLine(2);
            String[] data = new String[5];
            for (int i = 0; i < 5; i++) {
                String[] line = scanner.nextLine().split("\\s+");
                data[i] = line[line.length - 1];
            }

            dimension = Integer.valueOf(data[0]);
            num_of_items = Integer.valueOf(data[1]);
            capacity = Integer.valueOf(data[2]);
            min_speed = Double.valueOf(data[3]);
            max_speed = Double.valueOf(data[4]);

            readNextLine(3);

            for(int i = 0; i < dimension; i++){
                City city = new City();
                String[] line = scanner.nextLine().split("\\s+");
                city.setIndex(Integer.valueOf(line[0]));
                city.setX(Double.valueOf(line[1]));
                city.setY(Double.valueOf(line[2]));
                cities.add(city);
            }

            scanner.nextLine();

            for(int i = 0; i < num_of_items; i++){
                Item item = new Item();
                String[] line = scanner.nextLine().split("\\s+");
                item.setIndex(Integer.valueOf(line[0]));
                item.setProfit(Integer.valueOf(line[1]));
                item.setWeight(Integer.valueOf(line[2]));
                item.setAssigned_city_number(Integer.valueOf(line[3]));
                items.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

        private void readNextLine(int n){
            for(int i = 0; i < n; i++){
                scanner.nextLine();
            }
        }
    }
