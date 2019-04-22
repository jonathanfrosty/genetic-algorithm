class SolutionComparatorOne extends SolutionComparator {
    public int compare(CandidateSolution sol1, CandidateSolution sol2) {
        return Double.compare(sol1.getFitnessValue(), sol2.getFitnessValue());
    }
}
