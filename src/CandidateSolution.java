abstract class CandidateSolution {
    private double fitness;

    public CandidateSolution(double fitness) {
        this.fitness = fitness;
    }

    public double getFitnessValue() {
        return fitness;
    }

    abstract void prettyPrint();
}