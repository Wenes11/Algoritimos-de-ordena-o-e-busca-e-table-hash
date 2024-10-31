import java.util.Random;
import java.util.Scanner;

public class QuickSort {

    // Método para gerar um vetor aleatório
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100_001); // Valores entre 0 e 100.000
        }
        return array;
    }

    // Método para realizar o particionamento Dual-Pivot
    public static void dualPivotPartition(int[] array, int low, int high) {
        if (low < high) {
            // Escolhendo pivôs
            if (array[low] > array[high]) {
                // Troca se o pivô esquerdo é maior que o pivô direito
                int temp = array[low];
                array[low] = array[high];
                array[high] = temp;
            }

            int p1 = array[low];  // Primeiro pivô
            int p2 = array[high]; // Segundo pivô
            int i = low + 1; // Começa o loop a partir do segundo elemento
            int j = high - 1; // Começa o loop a partir do penúltimo elemento
            int k = low + 1; // Índice para a partição

            while (k <= j) {
                if (array[k] < p1) {
                    // Troca os elementos menores que o primeiro pivô para a esquerda
                    int temp = array[i];
                    array[i] = array[k];
                    array[k] = temp;
                    i++;
                } else if (array[k] >= p2) {
                    // Troca os elementos maiores que o segundo pivô para a direita
                    while (array[j] > p2 && k < j) {
                        j--;
                    }
                    int temp = array[k];
                    array[k] = array[j];
                    array[j] = temp;
                    j--;
                    if (array[k] < p1) {
                        // Se após a troca, o elemento é menor que p1, troque com o índice de menor
                        temp = array[i];
                        array[i] = array[k];
                        array[k] = temp;
                        i++;
                    }
                }
                k++;
            }
            i--; j++;
            // Troca os pivôs para as suas posições corretas
            int temp = array[low];
            array[low] = array[i];
            array[i] = temp;

            temp = array[high];
            array[high] = array[j];
            array[j] = temp;

            // Recursão nas três partes
            dualPivotPartition(array, low, i - 1);
            dualPivotPartition(array, i + 1, j - 1);
            dualPivotPartition(array, j + 1, high);
        }
    }

    // Método para ordenar o array usando Dual-Pivot Quicksort
    public static void dualPivotQuickSort(int[] array) {
        dualPivotPartition(array, 0, array.length - 1);
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de elementos que deseja inserir: ");
        int n = scanner.nextInt();

        int[] array = generateRandomArray(n);

        // Medindo o tempo de execução
        long startTime = System.nanoTime();
        dualPivotQuickSort(array);
        long endTime = System.nanoTime();

        // Calculando o tempo gasto
        long duration = endTime - startTime;
        System.out.println("Tempo de execução do Dual-Pivot QuickSort (nanosegundos): " + duration);

        // Imprimindo o vetor ordenado
        System.out.println("Vetor ordenado:");
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}
