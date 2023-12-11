import java.util.Random;

class No {
    int dado;
    No anterior, proximo;

    public No(int dado) {
        this.dado = dado;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    No inicio, fim;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
    }

    public void inserirOrdenado(int dado) {
        No novoNo = new No(dado);

        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
            return;
        }

        No atual = inicio;

        while (atual != null && atual.dado < dado) {
            atual = atual.proximo;
        }

        if (atual == null) {
            // Inserir no final
            novoNo.anterior = fim;
            fim.proximo = novoNo;
            fim = novoNo;
        } else if (atual.anterior == null) {
            // Inserir no início
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        } else {
            // Inserir no meio
            novoNo.anterior = atual.anterior;
            novoNo.proximo = atual;
            atual.anterior.proximo = novoNo;
            atual.anterior = novoNo;
        }
    }

    public void imprimirLista(boolean reverso) {
        No atual = reverso ? fim : inicio;

        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = reverso ? atual.anterior : atual.proximo;
        }

        System.out.println();
    }
}

public class Main {
    public static boolean ehPrimo(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Random random = new Random();

        // Gerar e imprimir 1000 números aleatórios
        int[] numerosAleatorios = new int[1000];
        System.out.println("Números Aleatórios Gerados:");
        for (int i = 0; i < 1000; i++) {
            numerosAleatorios[i] = random.nextInt(19999) - 9999;
            System.out.print(numerosAleatorios[i] + " ");
        }

        // Criar a lista duplamente encadeada e inserir números
        ListaDuplamenteEncadeada listaDupla = new ListaDuplamenteEncadeada();

        for (int num : numerosAleatorios) {
            listaDupla.inserirOrdenado(num);
        }

        // Imprimir lista em ordem crescente e decrescente
        System.out.println("\n\nLista em ordem crescente:");
        listaDupla.imprimirLista(false);

        System.out.println("\nLista em ordem decrescente:");
        listaDupla.imprimirLista(true);

        // Remover números primos da lista
        No atual = listaDupla.inicio;

        while (atual != null) {
            if (ehPrimo(atual.dado)) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    listaDupla.inicio = atual.proximo;
                }

                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                } else {
                    listaDupla.fim = atual.anterior;
                }
            }

            atual = atual.proximo;
        }

        // Imprimir lista após remover números primos
        System.out.println("\nLista após remover números primos:");
        listaDupla.imprimirLista(false);
    }
}
