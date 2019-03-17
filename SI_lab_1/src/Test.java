import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.List;

public class Test {
    private final static int population_size = 100;
    private final static int evolve_rate = 100;
    private final static int gen = 100;

    public static void main(String[] args) {
        Loader loader = new Loader();
        loader.load("easy_0.ttp");

        //Load cities
        List<City> cities = loader.getCities();
        for(int i = 0; i < cities.size(); i++){
            CityManager.addCity(cities.get(i));
        }

        // Initialize population
        Population population = new Population(loader.getThief(), population_size);
        System.out.println("Start G(x,y): " + population.getFittest().getFitness());

        //Create data for chart
        double[] xData = new double[evolve_rate];
        double[] yData = new double[evolve_rate];

        //Evolve population and set data for chart
        for (int i = 0; i < gen; i++) {
            population = GA.evolvePopulation(population);
            xData[i] = i + 1;
            yData[i] = population.getFittest().getFitness();
            System.out.println("G(x,y): " + yData[i]);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Solution:");
        System.out.println(population.getFittest().getFitness());


        // Create Chart
        XYChart chart = QuickChart.getChart("TTP problem", "X", "Y", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
