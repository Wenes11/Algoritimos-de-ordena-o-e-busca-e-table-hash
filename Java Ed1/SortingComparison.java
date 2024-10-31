import java.util.Random;

public class SortingComparison {

    // Método para gerar um vetor aleatório
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100_001); // Valores entre 0 e 100.000
        }
        return array;
    }

    // MergeSort
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    // QuickSort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // BubbleSort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // ShellSort
    public static void shellSort(int[] array) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }

    // InsertionSort
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // SelectionSort
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }

    // Método principal
    public static void main(String[] args) {
        final int ARRAY_SIZE = 100_000;
        final int RUNS = 10;
        long[][] results = new long[RUNS][6]; // 6 algoritmos

        for (int run = 0; run < RUNS; run++) {
            int[] array = generateRandomArray(ARRAY_SIZE);

            // Medindo MergeSort
            int[] mergeArray = array.clone();
            long startTime = System.nanoTime();
            mergeSort(mergeArray, 0, mergeArray.length - 1);
            long endTime = System.nanoTime();
            results[run][0] = endTime - startTime;

            // Medindo QuickSort
            int[] quickArray = array.clone();
            startTime = System.nanoTime();
            quickSort(quickArray, 0, quickArray.length - 1);
            endTime = System.nanoTime();
            results[run][1] = endTime - startTime;

            // Medindo BubbleSort
            int[] bubbleArray = array.clone();
            startTime = System.nanoTime();
            bubbleSort(bubbleArray);
            endTime = System.nanoTime();
            results[run][2] = endTime - startTime;

            // Medindo ShellSort
            int[] shellArray = array.clone();
            startTime = System.nanoTime();
            shellSort(shellArray);
            endTime = System.nanoTime();
            results[run][3] = endTime - startTime;

            // Medindo InsertionSort
            int[] insertionArray = array.clone();
            startTime = System.nanoTime();
            insertionSort(insertionArray);
            endTime = System.nanoTime();
            results[run][4] = endTime - startTime;

            // Medindo SelectionSort
            int[] selectionArray = array.clone();
            startTime = System.nanoTime();
            selectionSort(selectionArray);
            endTime = System.nanoTime();
            results[run][5] = endTime - startTime;
        }

        // Imprimindo resultados
        System.out.println("Execution Times (nanoseconds):");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n", "Run", "Merge", "Quick", "Bubble", "Shell", "Insertion", "Selection");
        for (int run = 0; run < RUNS; run++) {
            System.out.printf("%-10d %-10d %-10d %-10d %-10d %-10d %-10d%n", run + 1, results[run][0], results[run][1], results[run][2], results[run][3], results[run][4], results[run][5]);
        }
    }
}
