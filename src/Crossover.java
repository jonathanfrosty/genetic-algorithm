public class Crossover {
    public CandidateSolutionOne crossoverOne(CandidateSolutionOne sol, PopulationOne population) {
        CandidateSolutionOne secondChosen = (CandidateSolutionOne) population.getCandidateSolutions().peek();

        if(Math.random() > 0.5) {
            double firstChosenFirstDialValue = sol.getFirstDialValue();
            double secondChosenFirstDialValue = secondChosen.getFirstDialValue();

            double average = (firstChosenFirstDialValue + secondChosenFirstDialValue) / 2;

            double newFirstDialValue = (Math.random() > 0.5) ?
                    average + (0.5 * Math.abs(firstChosenFirstDialValue - secondChosenFirstDialValue) * Math.random() * 0.5) :
                    average - (0.5 * Math.abs(firstChosenFirstDialValue - secondChosenFirstDialValue) * Math.random() * 0.5);

            double newFitness = Fitness.getFitnessOne(newFirstDialValue, sol.getSecondDialValue());

            return new CandidateSolutionOne(newFirstDialValue, sol.getSecondDialValue(), newFitness);
        } else {
            double firstChosenSecondDialValue = sol.getSecondDialValue();
            double secondChosenSecondDialValue = secondChosen.getSecondDialValue();

            double average = (firstChosenSecondDialValue + secondChosenSecondDialValue) / 2;

            double newSecondDialValue = (Math.random() > 0.5) ?
                    average + (0.5 * Math.abs(firstChosenSecondDialValue - secondChosenSecondDialValue) * Math.random() * 0.5) :
                    average - (0.5 * Math.abs(firstChosenSecondDialValue - secondChosenSecondDialValue) * Math.random() * 0.5);

            double newFitness = Fitness.getFitnessOne(sol.getFirstDialValue(), newSecondDialValue);

            return new CandidateSolutionOne(sol.getFirstDialValue(), newSecondDialValue, newFitness);
        }
    }

    public CandidateSolutionTwo crossoverTwo(CandidateSolutionTwo sol, CandidateSolutionTwo sol2) {
        boolean[] firstBagEncoding = sol.getBagEncoding().clone();
        boolean[] secondBagEncoding = sol2.getBagEncoding().clone();
        boolean[] newBagEncoding = new boolean[Main.BAG_SIZE];

        for(int i = 0; i < sol.getBagEncoding().length; i++) {
            if(Math.random() > 0.5) newBagEncoding[i] = firstBagEncoding[i];
            else newBagEncoding[i] = secondBagEncoding[i];
        }

        double[] result = Fitness.getFitnessTwo(newBagEncoding);

        return new CandidateSolutionTwo(newBagEncoding, result[0], result[1], result[2]);
    }
}
