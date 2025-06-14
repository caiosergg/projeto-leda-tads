package tad.fila;

/**
 * Nome: Caio Sérgio Ramalho Lima
 * Matrícula: 211080411
 *
 * Roteiro: Roteiro 5 - TADs
 *
 * Objetivo: Implementar uma fila de inteiros baseada em lista encadeada simples,
 * permitindo enfileirar, desenfileirar, consultar cabeça e cauda, além de operações
 * auxiliares como verificar se está vazia e obter o tamanho.
 */

public class MinhaFilaEncadeada implements FilaIF<Integer> {

    private static class Node {
        Integer valor;
        Node prox;
        Node(Integer v) { valor = v; }
    }

    private Node cabeca = null;
    private Node cauda = null;
    private int qtd = 0;

    @Override
    public void enfileirar(Integer item) {
        Node novo = new Node(item);
        if (cauda == null) {
            cabeca = cauda = novo;
        } else {
            cauda.prox = novo;
            cauda = novo;
        }
        qtd++;
    }

    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) throw new FilaVaziaException();
        Integer val = cabeca.valor;
        cabeca = cabeca.prox;
        if (cabeca == null) cauda = null;
        qtd--;
        return val;
    }

    @Override
    public Integer verificarCauda() {
        return (cauda == null) ? null : cauda.valor;
    }

    @Override
    public Integer verificarCabeca() {
        return (cabeca == null) ? null : cabeca.valor;
    }

    @Override
    public boolean isEmpty() {
        return qtd == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int capacidade() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int tamanho() {
        return qtd;
    }
}