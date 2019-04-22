import java.util.PriorityQueue;

public class Main {
    final private static int PROBLEM_ONE_RUNTIME_MS = 10000;
    final private static int PROBLEM_TWO_RUNTIME_MS = 20000;

    final private static int POPULATION_ONE_SIZE = 1000;
    final private static int POPULATION_TWO_SIZE = 100;

    final static double FIRST_DIAL_OPTIMUM_VALUE = 5.0;
    final static double SECOND_DIAL_OPTIMUM_VALUE = 28.0;

    final static int BAG_SIZE = 100;
    final static double BAG_WEIGHT_LIMIT = 500.0;
    static double[][] bagItems = new double[BAG_SIZE][2];

    private static PopulationOne generateInitialPopulationOne() {
        PopulationOne population = new PopulationOne();

        while(population.getCandidateSolutions().size() < POPULATION_ONE_SIZE) {
            double val1 = (Math.random() * 1000) - 500;
            double val2 = (Math.random() * 1000) - 500;
            double fit = Fitness.getFitnessOne(val1, val2);
            population.addToPopulation(new CandidateSolutionOne(val1, val2, fit));
        }

        return population;
    }

    private static PopulationTwo generateInitialPopulationTwo() {
        PopulationTwo population = new PopulationTwo();

        while(population.getCandidateSolutions().size() < POPULATION_TWO_SIZE) {
            boolean[] bagEncoding = new boolean[BAG_SIZE];

            for(int j = 0; j < bagEncoding.length; j++) {
                bagEncoding[j] = Math.random() > 0.5;
            }

            double[] result = Fitness.getFitnessTwo(bagEncoding);
            population.addToPopulation(new CandidateSolutionTwo(bagEncoding, result[0], result[1], result[2]));
        }

        return population;
    }

    private static CandidateSolutionOne problemOne() {
        long startT = System.currentTimeMillis();

        RouletteWheelSelection rws = new RouletteWheelSelection();
        Crossover co = new Crossover();
        Mutation mu = new Mutation();

        PopulationOne currentPopulation = generateInitialPopulationOne();

        while(System.currentTimeMillis() - startT <= PROBLEM_ONE_RUNTIME_MS) {
            PopulationOne nextPopulation = new PopulationOne();

            while(nextPopulation.getCandidateSolutions().size() != currentPopulation.getCandidateSolutions().size()) {
                CandidateSolutionOne chosenSolution = (CandidateSolutionOne) rws.selectCandidationSolution(currentPopulation);

                // perform crossover with 80% probability
                CandidateSolutionOne newSolution = (Math.random() <= 0.8) ? co.crossoverOne(chosenSolution, currentPopulation) : chosenSolution;

                // perform mutation with 1% probability
                newSolution = (Math.random() <= 0.01) ? mu.mutateOne(newSolution) : newSolution;

                nextPopulation.addToPopulation(chosenSolution);
                nextPopulation.addToPopulation(newSolution);
            }

            currentPopulation = nextPopulation;
        }

        return (CandidateSolutionOne) currentPopulation.getCandidateSolutions().peek();
    }

    private static CandidateSolutionTwo problemTwo() {
        generateBagItems();

        long startT = System.currentTimeMillis();

        TournamentSelection ts = new TournamentSelection();
        Crossover co = new Crossover();
        Mutation mu = new Mutation();

        PopulationTwo currentPopulation = generateInitialPopulationTwo();

        CandidateSolutionTwo bestSolution = null;

        while(System.currentTimeMillis() - startT <= PROBLEM_TWO_RUNTIME_MS) {
            PopulationTwo nextPopulation = new PopulationTwo();

            while(nextPopulation.getCandidateSolutions().size() != currentPopulation.getCandidateSolutions().size()) {
                CandidateSolutionTwo chosenSolution = (CandidateSolutionTwo) ts.selectCandidationSolution(currentPopulation);
                CandidateSolutionTwo chosenSolution2 = (CandidateSolutionTwo) ts.selectCandidationSolution(currentPopulation);

                // perform crossover with 90% probability
                CandidateSolutionTwo newSolution = (Math.random() <= 0.9) ? co.crossoverTwo(chosenSolution, chosenSolution2) : chosenSolution;

                // perform mutation with 10% probability
                newSolution = (Math.random() <= 0.1) ? mu.mutateTwo(newSolution) : newSolution;

                nextPopulation.addToPopulation(chosenSolution);
                nextPopulation.addToPopulation(newSolution);
            }

            currentPopulation = nextPopulation;

            bestSolution = getBestSolution(nextPopulation.getCandidateSolutions(), bestSolution);
        }

        return bestSolution;
    }

    private static void generateBagItems() {
        for(int i = 0; i < BAG_SIZE; i++) {
            bagItems[i][0] = Math.random() * 50; // weight
            bagItems[i][1] = Math.random() * 10; // utility
        }
    }

    private static CandidateSolutionTwo getBestSolution(PriorityQueue<CandidateSolution> solutions, CandidateSolutionTwo previousBestSolution) {
        PriorityQueue<CandidateSolution> tempQueue = new PriorityQueue<>(solutions);

        CandidateSolutionTwo bestSolution = previousBestSolution;

        while(tempQueue.size() > 0) {
            CandidateSolutionTwo sol = (CandidateSolutionTwo) tempQueue.poll();
            if(bestSolution == null || sol.getFitnessValue() > bestSolution.getFitnessValue()) bestSolution = sol;
        }

        return bestSolution;
    }

    public static void main(String[] args){
        long startT = System.currentTimeMillis();

        // This scenario has two numerical dials, each with potential values ranging from -500.0 to +500.0.
        // The goal is to find the value for each dial which gives the lowest fitness, as defined by the corresponding fitness function.
        // The lowest possible fitness is 0.0 which, by default, is produced by dial values of 5.0 and 28.0.
        // This particular implementation uses roulette wheel analysis to evolve each population of candidate solutions, given initial random dial data.
        System.out.println("Problem One:");
        CandidateSolutionOne bestSolutionOne = problemOne();
        bestSolutionOne.prettyPrint();

        // This scenario replicates the 'KNAPSACK' problem. There are some items to potentially put into a bag; each item has a weight and utility value.
        // The restriction for the bag is that the total weight must not exceed a certain value (500.0 by default), and the goal is to maximise the utility value.
        // A particular bag is represented/encoded using a boolean array - if a given index is 'true', then that item number is put into the bag, and 'false' otherwise.
        // This particular implementation uses tournament selection to evolve each population of candidate solutions, given initial random bag data.
        System.out.println("\nProblem Two:");
        CandidateSolutionTwo bestSolutionTwo = problemTwo();
        bestSolutionTwo.prettyPrint();

        long endT = System.currentTimeMillis();
        System.out.println("\nTotal execution time was: " +  ((endT - startT) / 1000.0) + " seconds");
    }
}