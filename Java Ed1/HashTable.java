public class HashTable {
    private Aluno[] tabela;
    private int tamanho;
    private double fatorDeCarga;
    private int count; // Contador de elementos na tabela

    private static final Aluno APAGADO = new Aluno(Integer.MIN_VALUE, "APAGADO");

    public HashTable(int tamanhoInicial, double fatorDeCarga) {
        this.tamanho = tamanhoInicial;
        this.tabela = new Aluno[tamanho];
        this.fatorDeCarga = fatorDeCarga;
        this.count = 0; // Inicializa o contador como zero
    }

    private int hashFunction(int chave) {
        return chave % tamanho; // Função hash
    }

    private void resize() {
        int novoTamanho = tamanho * 2; // Dobrar o tamanho
        Aluno[] tabelaAntiga = tabela; // Armazenar a tabela antiga
        tabela = new Aluno[novoTamanho]; // Criar nova tabela
        tamanho = novoTamanho; // Atualizar o tamanho
        count = 0; // Resetar o contador

        // Re-inserir os elementos da tabela antiga na nova tabela
        for (Aluno aluno : tabelaAntiga) {
            if (aluno != null && aluno != APAGADO) {
                inserir(aluno.getMatricula(), aluno.getNome());
            }
        }
    }

    public void inserir(int matricula, String nome) {
        // Verifica se o fator de carga excedeu o limite
        if (count >= (int) (tamanho * fatorDeCarga)) {
            resize(); // Redimensiona a tabela
        }

        int posicao = hashFunction(matricula);
        
        // Busca por uma posição livre ou a posição já existente
        while (tabela[posicao] != null && tabela[posicao] != APAGADO) {
            // Se a matrícula já estiver na tabela, não insere novamente
            if (tabela[posicao].getMatricula() == matricula) {
                System.out.println("Matrícula já existe: " + matricula);
                return;
            }
            posicao = (posicao + 1) % tamanho; // Endereçamento aberto (linear)
        }
        tabela[posicao] = new Aluno(matricula, nome); // Insere o aluno
        count++; // Incrementa o contador
        imprimirTabela(); // Imprime a tabela após a inserção
    }

    public Aluno buscar(int matricula) {
        int posicao = hashFunction(matricula);

        while (tabela[posicao] != null) {
            if (tabela[posicao] != APAGADO && tabela[posicao].getMatricula() == matricula) {
                return tabela[posicao]; // Retorna o aluno encontrado
            }
            posicao = (posicao + 1) % tamanho; // Continua a busca
        }
        return null; // Retorna null se não encontrar
    }

    public void remover(int matricula) {
        int posicao = hashFunction(matricula);
        
        while (tabela[posicao] != null) {
            if (tabela[posicao] != APAGADO && tabela[posicao].getMatricula() == matricula) {
                tabela[posicao] = APAGADO; // Marca como apagado
                count--; // Decrementa o contador
                System.out.println("Aluno removido: " + matricula);
                imprimirTabela(); // Imprime a tabela após a remoção
                return;
            }
            posicao = (posicao + 1) % tamanho; // Continua a busca
        }
        System.out.println("Matrícula não encontrada: " + matricula);
    }

    public void imprimirTabela() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) {
                System.out.println(i + ": null");
            } else if (tabela[i] == APAGADO) {
                System.out.println(i + ": APAGADO");
            } else {
                System.out.println(i + ": " + tabela[i]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(8, 0.75);

        // Inserção de elementos
        hashTable.inserir(52, "Aluno 52");
        hashTable.inserir(45, "Aluno 45");
        hashTable.inserir(64, "Aluno 64");
        hashTable.inserir(34, "Aluno 34");
        hashTable.inserir(69, "Aluno 69");
        hashTable.inserir(11, "Aluno 11");
        hashTable.inserir(10, "Aluno 10");
        hashTable.inserir(3, "Aluno 3");
        hashTable.inserir(6, "Aluno 6");
        hashTable.inserir(2, "Aluno 2"); // Deve acionar o resize

        // Busca de elementos
        Aluno aluno45 = hashTable.buscar(45);
        if (aluno45 != null) {
            System.out.println("Aluno encontrado: " + aluno45);
        } else {
            System.out.println("Aluno com matrícula 45 não encontrado.");
        }

        Aluno aluno75 = hashTable.buscar(75);
        if (aluno75 != null) {
            System.out.println("Aluno encontrado: " + aluno75);
        } else {
            System.out.println("Aluno com matrícula 75 não encontrado.");
        }

        // Remoção de um elemento
        hashTable.remover(11);
    }
}