import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TournamentSelection {
    public CandidateSolution selectCandidationSolution(PopulationTwo population) {
        PriorityQueue<CandidateSolution> solutions = population.getCandidateSolutions();
        List<CandidateSolution> tournamentSolutions = getTournamentSolutions(solutions);
        return getBestTournamentSolution(tournamentSolutions);
    }

    private List<CandidateSolution> getTournamentSolutions(PriorityQueue<CandidateSolution> solutions) {
        List<CandidateSolution> solutionsList = new ArrayList<>(solutions);
        Collections.shuffle(solutionsList);
        return solutionsList.subList(0, 1 + (int) (Math.random() * (solutionsList.size() - 1)));
    }

    private CandidateSolution getBestTournamentSolution(List<CandidateSolution> tournamentSolutions) {
        CandidateSolutionTwo bestTournamentSolution = (CandidateSolutionTwo) tournamentSolutions.get(0);

        for(int i = 1; i < tournamentSolutions.size(); i++) {
            CandidateSolutionTwo sol = (CandidateSolutionTwo) tournamentSolutions.get(i);

            if(sol.getFitnessValue() > bestTournamentSolution.getFitnessValue()) bestTournamentSolution = sol;
        }

        return bestTournamentSolution;
    }
}