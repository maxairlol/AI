import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Thief> thiefList;

    public Population() {
        thiefList = new ArrayList<>();
    }

    public Population(Thief thief, int populationSize) {
        thiefList = new ArrayList<>(populationSize);
            for (int i = 0; i < populationSize; i++) {
                Thief newThief = new Thief(thief);
                newThief.createRoute();
                newThief.createItems();
                addThief(newThief);
            }
    }

    public List<Thief> getThiefList() {
        return thiefList;
    }

    public void setThiefList(List<Thief> thiefList) {
        this.thiefList = thiefList;
    }

    public Thief getThief(int index) {
        return thiefList.get(index);
    }

    public void addThief(Thief thief)
    {
        thiefList.add(thief);
    }

    public Thief getFittest() {
        Thief fittest = thiefList.get(0);
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getThief(i).getFitness()) {
                fittest = getThief(i);
            }
        }
        return fittest;
    }

    public int populationSize() {
        return thiefList.size();
    }
}

