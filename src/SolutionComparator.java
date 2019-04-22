import java.util.Comparator;

abstract class SolutionComparator implements Comparator<CandidateSolution> {
    abstract public int compare(CandidateSolution sol1, CandidateSolution sol2);
}
