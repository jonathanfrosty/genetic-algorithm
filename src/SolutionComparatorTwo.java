class SolutionComparatorTwo extends SolutionComparator {
    public int compare(CandidateSolution sol1, CandidateSolution sol2) {
        return Double.compare(sol2.getFitnessValue(), sol1.getFitnessValue());
    }
}
