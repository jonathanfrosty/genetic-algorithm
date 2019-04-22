import java.util.Arrays;

class CandidateSolutionOne extends CandidateSolution {
    private double dial1;
    private double dial2;

    public CandidateSolutionOne(double dial1, double dial2, double fitness) {
        super(fitness);
        this.dial1 = dial1;
        this.dial2 = dial2;
    }

    public double getFirstDialValue() {
        return dial1;
    }

    public double getSecondDialValue() {
        return dial2;
    }

    public void prettyPrint(){
        System.out.println("    Dial values: " + Arrays.asList(dial1, dial2));
        System.out.println("    Fitness:     " + getFitnessValue());
    }
}
