public class TrialBinarySearch<A> {

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

        try {
            TrialList<Comparable<A>> comparableList = (TrialList<Comparable<A>>)trialList;

            while (true) {
                if (start == end && comparableList.get(start).equals(objectToFind)) {
                    return start;
                } else if (start == end) {
                    return -1;
                }

                middle = (end + start) / 2 + 1;

                if (comparableList.get(middle).compareTo(objectToFind) == 0) {
                    return middle;
                } else if (comparableList.get(middle).compareTo(objectToFind) == -1) {
                    start = middle;
                } else {
                    end = middle - 1;
                }
            }
        } catch (ClassCastException e) {
            System.out.printf("%s Бинарный поиск работает только с объектами Comparable\n", e.getClass());
        }
        return -1;
    }
}

