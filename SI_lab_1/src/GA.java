public class GA {
    private static final double crossover = 0.7;
    private static final double mutationRate = 0.01;
    private static final int tournamentSize = 25;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population();
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.addThief(pop.getFittest());
            elitismOffset = 1;
        }
        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < pop.populationSize(); i++) {
            if(Math.random() < crossover) {
                // Select parents
                //Thief parent1 = roulleteWheel(pop);
                //Thief parent2 = roulleteWheel(pop);

                Thief parent1 = tournamentSelection(pop);
                Thief parent2 = tournamentSelection(pop);
                // Crossover parents
                Thief child = crossover(parent1, parent2);
                // Add child to new population
                child.createItems();
                newPopulation.addThief(child);
            }
            else {
                newPopulation.addThief(pop.getThief(i));
            }
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getThief(i));
        }
        return newPopulation;
    }

    public static Thief crossover(Thief parent1, Thief parent2) {
        Thief child = new Thief();
        //System.out.println(child.getKnapsack().getMaxCapacity());
        //child.getKnapsack().setMaxCapacity(parent1.getKnapsack().getMaxCapacity());

        int startPos = (int) (Math.random() * parent1.cityListSize());
        int endPos = (int) (Math.random() * parent1.cityListSize());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.cityListSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.cityListSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.cityListSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Use swap mutation
    private static void mutate(Thief thief) {
        for(int position1 = 0; position1 < thief.cityListSize(); position1++){
            if(Math.random() < mutationRate){
                int position2 = (int) (Math.random() * thief.cityListSize());
                City city1 = thief.getCity(position1);
                City city2 = thief.getCity(position2);
                thief.setCity(position2, city1);
                thief.setCity(position1, city2);
            }
        }
    }

    // Selection
    private static Thief tournamentSelection(Population pop) {
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.addThief(pop.getThief(randomId));
        }
        return tournament.getFittest();
    }

    private static Thief roulleteWheel(Population pop){
        double totalFitness = 0;
        for(int i = 0; i < pop.populationSize(); i++){
            totalFitness += pop.getThief(i).getFitness();
        }
        double value = Math.random()*totalFitness;
        for(int i = 0; i < pop.populationSize(); i++){
            value -= pop.getThief(i).getFitness();
            if(value < 0)
                return pop.getThief(i);
        }
        return pop.getThief(pop.populationSize() - 1);


    }

}
