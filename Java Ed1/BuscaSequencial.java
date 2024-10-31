import java.util.ArrayList;
import java.util.List;

public class BuscaSequencial {

    public static void main(String[] args) {
        int[] vetor = {0, 5, 0, 5, 0, 5, 0, 0, 5, 0};  // vetor de exemplo com múltiplas ocorrências
        int chave = 5;  // chave a ser procurada
        
        // Busca Sequencial
        List<Integer> resultadoSequencial = buscaSequencial(vetor, chave);
        if (!resultadoSequencial.isEmpty()) {
            System.out.println("Chave encontrada nas posições (busca sequencial): " + resultadoSequencial);
        } else {
            System.out.println("Chave não encontrada (busca sequencial).");
        }

        // Busca Binária (assumindo vetor ordenado para binária)
        int[] vetorOrdenado = {0, 0, 0, 0, 0, 5, 5, 5, 5, 5};  // vetor ordenado de exemplo
        List<Integer> resultadoBinario = buscaBinaria(vetorOrdenado, chave);
        if (!resultadoBinario.isEmpty()) {
            System.out.println("Chave encontrada nas posições (busca binária): " + resultadoBinario);
        } else {
            System.out.println("Chave não encontrada (busca binária).");
        }
    }

    public static List<Integer> buscaSequencial(int[] vetor, int chave) {
        List<Integer> posicoes = new ArrayList<>();  // lista para armazenar as posições

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == chave) {
                posicoes.add(i);  // adiciona a posição na lista
            }
        }
        return posicoes;  // retorna a lista de posições (vazia se a chave não for encontrada)
    }

    
    public static List<Integer> buscaBinaria(int[] vetor, int chave) {
        List<Integer> posicoes = new ArrayList<>();  // lista para armazenar as posições
        int posInicial = 0;
        int posFinal = vetor.length - 1;

        
        while (posInicial <= posFinal) {
            int posMeio = (posInicial + posFinal) / 2;
            if (vetor[posMeio] == chave) {
        
                int i = posMeio;
                while (i >= 0 && vetor[i] == chave) {  // busca para a esquerda
                    posicoes.add(i);
                    i--;
                }
                i = posMeio + 1;
                while (i < vetor.length && vetor[i] == chave) {  // busca para a direita
                    posicoes.add(i);
                    i++;
                }
                break; 
            } else if (vetor[posMeio] < chave) {
                posInicial = posMeio + 1;
            } else {
                posFinal = posMeio - 1;
            }
        }
        return posicoes;  
    }
}
