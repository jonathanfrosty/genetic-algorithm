public class Mutation {
    public CandidateSolutionOne mutateOne(CandidateSolutionOne sol) {
        if(Math.random() > 0.5) {
            double dial1 = sol.getFirstDialValue();
            double newDial1 = (Math.random() > 0.5) ? (dial1 + 3) : (dial1 - 3);
            double newFit = Fitness.getFitnessOne(newDial1, sol.getSecondDialValue());

            return new CandidateSolutionOne(newDial1, sol.getSecondDialValue(), newFit);
        } else {
            double dial2 = sol.getSecondDialValue();
            double newDial2 = (Math.random() > 0.5) ? (dial2 + 3) : (dial2 - 3);
            double newFit = Fitness.getFitnessOne(sol.getFirstDialValue(), newDial2);

            return new CandidateSolutionOne(sol.getFirstDialValue(), sol.getSecondDialValue(), newFit);
        }
    }

    public CandidateSolutionTwo mutateTwo(CandidateSolutionTwo sol) {
        boolean[] bagEncoding = sol.getBagEncoding().clone();

        int randomIndex1 = (int) Math.round(Math.random() * (bagEncoding.length - 1));
        int randomIndex2 = (int) Math.round(Math.random() * (bagEncoding.length - 1));

        bagEncoding[randomIndex1] = !bagEncoding[randomIndex1];
        bagEncoding[randomIndex2] = !bagEncoding[randomIndex2];

        double[] result = Fitness.getFitnessTwo(bagEncoding);

        return new CandidateSolutionTwo(bagEncoding, result[0], result[1], result[2]);
    }
}
