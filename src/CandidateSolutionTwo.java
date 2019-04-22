import java.util.ArrayList;
import java.util.Arrays;

class CandidateSolutionTwo extends CandidateSolution {
    private boolean[] bagEncoding;
    private double totalWeight;
    private double totalUtility;

    public CandidateSolutionTwo(boolean[] bagEncoding, double totalWeight, double totalUtility, double fitness) {
        super(fitness);
        this.bagEncoding = bagEncoding;
        this.totalWeight = totalWeight;
        this.totalUtility = totalUtility;
    }

    public boolean[] getBagEncoding() {
        return bagEncoding;
    }

    private ArrayList<Integer> getBagItems() {
        ArrayList<Integer> bagItems = new ArrayList<>();

        for(int i = 0; i < bagEncoding.length; i++) {
            if(bagEncoding[i]) bagItems.add(i);
        }

        return bagItems;
    }

    public void prettyPrint() {
        System.out.println("    Bag Items:      " + getBagItems());
        System.out.println("    Weight/Utility: " + Arrays.asList(totalWeight, totalUtility));
        System.out.println("    Fitness:        " + getFitnessValue());
    }
}
