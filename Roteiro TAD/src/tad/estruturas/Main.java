package tad.estruturas;

import tad.ElementoNaoEncontradoException;
import tad.conjuntoDinamico.ConjuntoDinamicoVazioException;
import tad.listasEncadeadas.ListaVaziaException;
import tad.fila.FilaVaziaException;
import tad.fila.FilaCheiaException;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("=== Lista Encadeada ===");
            ListaEncadeadaImpl<Integer> lista = new ListaEncadeadaImpl<>();
            lista.insert(5);
            lista.insert(10);
            lista.insert(15);
            System.out.println("Lista atual: " + lista.imprimeEmOrdem());
            System.out.println("Removendo 10 da lista...");
            lista.remove(10);
            System.out.println("Lista após remoção: " + lista.imprimeEmOrdem());
            System.out.println();

            System.out.println("=== Fila Encadeada ===");
            FilaListaEncadeada<Integer> fila = new FilaListaEncadeada<>();
            fila.enfileirar(100);
            fila.enfileirar(200);
            fila.enfileirar(300);
            System.out.println("Fila - cabeça: " + fila.verificarCabeca());
            System.out.println("Fila - cauda: " + fila.verificarCauda());
            System.out.println("Desenfileirando da fila: " + fila.desenfileirar());
            System.out.println("Fila após desenfileirar: tamanho = " + fila.tamanho());
            System.out.println();

            System.out.println("=== Conjunto Dinâmico Encadeado ===");
            MeuConjuntoDinamicoEncadeado conjunto = new MeuConjuntoDinamicoEncadeado();
            conjunto.inserir(7);
            conjunto.inserir(3);
            conjunto.inserir(9);
            System.out.println("Conjunto tamanho: " + conjunto.tamanho());
            System.out.println("Removendo 3 do conjunto...");
            conjunto.remover(3);
            System.out.println("Tamanho após remoção: " + conjunto.tamanho());
            System.out.println("Sucessor de 7: " + conjunto.sucessor(7));
            System.out.println("Predecessor de 9: " + conjunto.predecessor(9));
            System.out.println("Mínimo: " + conjunto.minimum());
            System.out.println("Máximo: " + conjunto.maximum());

        } catch (FilaVaziaException | FilaCheiaException | ElementoNaoEncontradoException |
                 ListaVaziaException | ConjuntoDinamicoVazioException e) {
            e.printStackTrace();
        }
    }
}
