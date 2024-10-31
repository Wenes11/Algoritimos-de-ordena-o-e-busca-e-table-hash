import java.util.ArrayList;
import java.util.Scanner;

public class SortedInsertion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> sortedList = new ArrayList<>();

        System.out.print("Digite o número de elementos que deseja inserir: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            int value = scanner.nextInt();
            insertInSortedOrder(sortedList, value);
        }

        System.out.println("Lista ordenada:");
        System.out.println(sortedList);
    }

    // Método para inserir um valor na lista de forma ordenada
    public static void insertInSortedOrder(ArrayList<Integer> list, int value) {
        int i = 0;

        // Encontrar a posição correta para inserir o valor
        while (i < list.size() && list.get(i) < value) {
            i++;
        }

        // Inserir o valor na posição correta
        list.add(i, value);
    }
}
