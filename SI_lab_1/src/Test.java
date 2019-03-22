import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    private final static int population_size = 100;
    private final static int evolve_rate = 100;


    public static void main(String[] args) {
        Loader loader = new Loader();
        loader.load("hard_4.ttp");

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
        double[] yMin = new double[evolve_rate];
        double[] yAvg = new double[evolve_rate];
        double[] yMax = new double[evolve_rate];

        //Evolve population and set data for chart
        System.out.printf("%20s", "Max G(x,y)");
        System.out.printf("%20s", "Avg G(x,y)");
        System.out.printf("%20s", "Min G(x,y)");
        System.out.println();

        // Create Chart
        XYSeries max = new XYSeries("Max");
        XYSeries avg = new XYSeries("Avg");
        XYSeries min = new XYSeries("Min");



        for (int i = 0; i < evolve_rate; i++) {
            population = GA.evolvePopulation(population);
            xData[i] = i + 1;

            ArrayList<Double> fitnesses = new ArrayList<>();
            for (int j = 0; j < population_size; j++) {
                fitnesses.add(population.getThief(j).getFitness());
            }

            yMax[i] = population.getFittest().getFitness();
            yMin[i] = Collections.min(fitnesses);

            double sum = 0;
            if (!fitnesses.isEmpty()) {
                for (Double fitness : fitnesses) {
                    sum += fitness;
                }
            }
            yAvg[i] = sum / fitnesses.size();

                max.add(xData[i],yMax[i]);
                avg.add(xData[i],yAvg[i]);
                min.add(xData[i],yMin[i]);

            System.out.printf("%20s", yMax[i]);
            System.out.printf("%20s", yAvg[i]);
            System.out.printf("%20s %n", yMin[i]);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Solution:");
        System.out.println(population.getFittest().getFitness());

        //DISPLAY CHART
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(max);
        dataset.addSeries(avg);
        dataset.addSeries(min);

        XYLineChartExample chart = new XYLineChartExample(dataset,-1420000,-2100000);
        chart.setVisible(true);
    }
}
