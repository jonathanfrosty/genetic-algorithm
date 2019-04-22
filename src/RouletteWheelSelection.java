import java.util.PriorityQueue;

public class RouletteWheelSelection {
    public CandidateSolution selectCandidationSolution(PopulationOne population) {
        PriorityQueue<CandidateSolution> solutions = population.getCandidateSolutions();

        double randomValue = Math.random();
        double cumulativeFitnessProportion = 0;

        Object[] clonedArray = solutions.toArray().clone();

        // choose chosenSolution to initially be a random candidate in the population, to ensure it is never null.
        // in most cases, this is reassigned through roulette wheel selection in the following for-loop.
        // the anomaly is when all fitness proportions are summed, but the cumulativeFitnessProportion variable is a value JUST below 1, instead of exactly 1,
        // due to floating-point arithmetic rounding errors; if the computed randomValue variable is between cumulativeFitnessProportion and 1, no solution is chosen,
        // hence the default declaration here:
        CandidateSolution chosenSolution = (CandidateSolutionOne) clonedArray[(int) Math.round(Math.random() * (clonedArray.length - 1))];

        for (CandidateSolution sol : solutions) {
            double fitnessProportion = (1 - (sol.getFitnessValue() / population.getOverallFitness())) / (solutions.size() - 1);
            cumulativeFitnessProportion += fitnessProportion;

            if (cumulativeFitnessProportion >= randomValue) {
                chosenSolution = sol;
                break;
            }
        }

        return chosenSolution;
    }
}
