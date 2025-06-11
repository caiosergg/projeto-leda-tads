package tad.conjuntoDinamico;

/**
 * Nome: Caio Sérgio Ramalho Lima
 * Matrícula: 211080411
 * 
 * Roteiro: Roteiro 5 - TADs
 * 
 * Objetivo: Implementar um conjunto dinâmico de inteiros baseado em lista encadeada simples,
 * permitindo inserção, remoção, busca, sucessor, predecessor, mínimo e máximo, com gerenciamento
 * dinâmico da estrutura sem uso de arrays fixos.
 */

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private static class Node {
        Integer valor;
        Node prox;
        Node(Integer valor) {
            this.valor = valor;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int tamanho = 0;

    @Override
    public void inserir(Integer item) {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        Node novo = new Node(item);
        if (head == null) {
            head = tail = novo;
        } else {
            tail.prox = novo;
            tail = novo;
        }
        tamanho++;
    }

    @Override
    public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();
        Node ant = null, curr = head;
        while (curr != null) {
            if (curr.valor.equals(item)) {
                Integer val = curr.valor;
                if (ant == null) {
                    head = curr.prox;
                } else {
                    ant.prox = curr.prox;
                }
                if (curr == tail) {
                    tail = ant;
                }
                tamanho--;
                return val;
            }
            ant = curr;
            curr = curr.prox;
        }
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
    }

    @Override
    public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();
        Node prev = null, curr = head;
        while (curr != null) {
            if (curr.valor.equals(item)) {
                return prev == null ? null : prev.valor;
            }
            prev = curr;
            curr = curr.prox;
        }
        return null;
    }

    @Override
    public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();
        Node curr = head;
        while (curr != null) {
            if (curr.valor.equals(item)) {
                return curr.prox == null ? null : curr.prox.valor;
            }
            curr = curr.prox;
        }
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        Node curr = head;
        while (curr != null) {
            if (curr.valor.equals(item)) {
                return curr.valor;
            }
            curr = curr.prox;
        }
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
    }

    @Override
    public Integer minimum() throws ConjuntoDinamicoVazioException {
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();
        Node curr = head;
        Integer min = curr.valor;
        curr = curr.prox;
        while (curr != null) {
            if (curr.valor < min) min = curr.valor;
            curr = curr.prox;
        }
        return min;
    }

    @Override
    public Integer maximum() throws ConjuntoDinamicoVazioException {
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();
        Node curr = head;
        Integer max = curr.valor;
        curr = curr.prox;
        while (curr != null) {
            if (curr.valor > max) max = curr.valor;
            curr = curr.prox;
        }
        return max;
    }
}
