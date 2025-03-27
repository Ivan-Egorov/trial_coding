public class TrialSort {

    private static int minRun(int listLenght)
    {
        int start = 0;
        while (listLenght >= 64) {
            start |= listLenght & 1;
            listLenght >>= 1;
        }
        return listLenght + start;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void insertionSort(Object[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Object tempI = array[i];
            int j = i - 1;
            TrialComparable key = (TrialComparable) array[j];
            while (j >= 0 && key.compareTo(tempI) > 0 && j >= left) {
                array[j + 1] = array[j];
                j--;
                key = j >= 0 ? (TrialComparable) array[j] : null;
            }
            array[j + 1] = tempI;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void merge(Object[] array, int left, int mid, int right) {

        int leftArrLen = mid - left + 1;
        int rightArrLen = right - mid;
        Object[] leftArr = new Object[leftArrLen];
        Object[] rightArr = new Object[rightArrLen];

        System.arraycopy(array, left, leftArr, 0, leftArrLen);
        for (int x = 0; x < rightArrLen; x++) {
            rightArr[x] = array[mid + 1 + x];
        }


        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftArrLen && j < rightArrLen) {
            TrialComparable tmp = (TrialComparable) leftArr[i];
            if (tmp.compareTo(rightArr[j]) <= 0) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftArrLen) {
            array[k] = leftArr[i];
            k++;
            i++;
        }

        while (j < rightArrLen) {
            array[k] = rightArr[j];
            k++;
            j++;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends TrialComparable<T>> TrialList<T> sort(TrialList<T> list) {

        Object[] array = list.toArray();

        int length = list.size();
        int run = minRun(length);

        for (int i = 0; i < length; i += run) {

            insertionSort(array, i, Math.min((i + 31), (length - 1)));
        }

        for (int size = run; size < length; size = 2 * size) {
            for (int left = 0; left < length; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (length - 1));
                merge(array, left, mid, right);
            }
        }

        TrialList<T> sortArray = new TrialList<>();
        for (Object e : array) {
            sortArray.add((T) e);
        }
        return sortArray;
    }
}
