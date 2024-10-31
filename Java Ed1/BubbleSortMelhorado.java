import java.util.Random;
import java.util.Scanner;

public class BubbleSortMelhorado {

    // Método para gerar um vetor aleatório
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100_001); // Valores entre 0 e 100.000
        }
        return array;
    }

    // Algoritmo BubbleSort melhorado
    public static void improvedBubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Troca os elementos
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // Se não houve troca, a lista já está ordenada
            if (!swapped) {
                break;
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de elementos que deseja inserir: ");
        int n = scanner.nextInt();

        int[] array = generateRandomArray(n);

        // Medindo o tempo de execução
        long startTime = System.nanoTime();
        improvedBubbleSort(array);
        long endTime = System.nanoTime();

        // Calculando o tempo gasto
        long duration = endTime - startTime;
        System.out.println("Tempo de execução do BubbleSort melhorado (nanosegundos): " + duration);

        // Imprimindo o vetor ordenado
        System.out.println("Vetor ordenado:");
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}
