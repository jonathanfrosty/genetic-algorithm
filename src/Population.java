import java.util.PriorityQueue;

abstract public class Population {
    private PriorityQueue<CandidateSolution> candidateSolutions;
    private double overallFitness = 0;

    public Population(SolutionComparator sc) {
        this.candidateSolutions = new PriorityQueue<>(sc);
    }

    public PriorityQueue<CandidateSolution> getCandidateSolutions() {
        return candidateSolutions;
    }

    public void addToPopulation(CandidateSolution sol) {
        candidateSolutions.add(sol);
        overallFitness += sol.getFitnessValue();
    }

    public double getOverallFitness() {
        return overallFitness;
    }
}
