import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BuscaVetor1000 {
    public static void main(String[] args) {
        int[] vetor = new int[1000]; // Criando vetor com 1000 posições
        int num;
        Scanner sc = new Scanner(System.in);

        // Solicitando o número a ser buscado
        System.out.println("Informe o número para ser buscado de 1 a 10.000: ");
        num = sc.nextInt();

        Random rand = new Random(); // Preenchendo o vetor com valores aleatórios de 1 a 10.000

        // Preenchendo o vetor com valores aleatórios
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(10000) + 1; // Gera números entre 1 e 10.000
        }

        // Ordenando o vetor antes da busca binária
        Arrays.sort(vetor);

        System.out.println("Vetor gerado e ordenado: ");
        for (int value : vetor) {
            System.out.print(value + " ");
        }
        System.out.println(); // Nova linha após imprimir o vetor

        // Iniciando a contagem do tempo de busca
        long inicio = System.nanoTime(); // Tempo de início

        // Realizando a busca binária
        int resultado = buscaBinaria(vetor, num);

        long fim = System.nanoTime();
        long tempoTotal = fim - inicio;

        // Convertendo o tempo total para segundos
        double tempoEmSegundos = tempoTotal / 1_000_000_000.0; // Converte nanosegundos para segundos

        if (resultado != -1) {
            System.out.println("Número " + num + " encontrado na posição: " + resultado);
        } else {
            System.out.println("Número " + num + " não encontrado no vetor.");
        }

        // Exibindo o tempo de busca em segundos
        System.out.println("Tempo de busca: " + tempoEmSegundos + " segundos");

        sc.close();
    }

    // Função de busca binária
    public static int buscaBinaria(int[] vetor, int chave) {
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (vetor[meio] == chave) {
                return meio; // Número encontrado, retorna a posição
            } else if (vetor[meio] < chave) {
                inicio = meio + 1; // Buscar na metade direita
            } else {
                fim = meio - 1; // Buscar na metade esquerda
            }
        }

        return -1; // Número não encontrado
    }
}
