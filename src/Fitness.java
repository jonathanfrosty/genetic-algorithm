public class Fitness {
    public static double getFitnessOne(double firstDial, double secondDial) {
        double modifiedFirstDial = firstDial - Main.FIRST_DIAL_OPTIMUM_VALUE;
        double modifiedSecondDial = secondDial - Main.SECOND_DIAL_OPTIMUM_VALUE;

        double modifiedFirstDialSquared = modifiedFirstDial * modifiedFirstDial;
        double modifiedSecondDialSquared = modifiedSecondDial * modifiedSecondDial;

        return modifiedFirstDialSquared + modifiedSecondDialSquared;
    }

    public static double[] getFitnessTwo(boolean[] encoding) {
        double[] bagData = new double[] {0.0, 0.0, 0.0};

        for(int i = 0; i < encoding.length; i++) {
            if(encoding[i]) {
                bagData[0] += Main.bagItems[i][0];
                bagData[1] += Main.bagItems[i][1];
            }
        }

        // set fitness to the utility value
        double fitness = bagData[1];

        // however, if the weight is too large, give it a proportionately bad fitness
        if(bagData[0] > Main.BAG_WEIGHT_LIMIT) fitness = bagData[0] * -1;

        bagData[2] = fitness;

        return bagData;
    }
}