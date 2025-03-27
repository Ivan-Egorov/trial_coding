public class TrialBinarySearch<A extends TrialComparable<A>> {

    public TrialBinarySearch() {
    }

    public int find(TrialList<A> trialList, A objectToFind) {
        if (trialList == null ||
                objectToFind == null ||
                trialList.isEmpty()) {

            return -1;
        }

        int start = 0;
        int end = trialList.size() - 1;
        int middle;

        while (start <= end) {
            if (start == end && trialList.get(start).equals(objectToFind)) {
                return start;
            } else if (start == end) {
                return -1;
            }

            middle = (end + start) / 2;

            if (trialList.get(middle).compareTo(objectToFind) == 0) {
                return middle;
            } else if (trialList.get(middle).compareTo(objectToFind) == -1) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
}
