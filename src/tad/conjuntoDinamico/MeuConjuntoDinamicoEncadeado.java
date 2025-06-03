package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Esta classe implementa um conjunto dinâmico de inteiros utilizando uma
 * estrutura encadeada (lista ligada). Permite operações como inserção,
 * remoção, busca, além de buscar predecessor, sucessor, mínimo e máximo.
 * 
 * Objetivo: Exercitar manipulação de listas encadeadas e lógica de busca e
 * atualização em estruturas dinâmicas não baseadas em arrays.
 */

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private class No {
        Integer valor;
        No prox;

        No(Integer valor) {
            this.valor = valor;
        }
    }

    private No head = null;
    private int tamanho = 0;

    @Override
    public void inserir(Integer item) {
        if (item == null)
            throw new IllegalArgumentException("Elemento nao pode ser null");
        No novo = new No(item);
        novo.prox = head;
        head = novo;
        tamanho++;
    }

    @Override
    public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento nao pode ser null");
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();

        No atual = head, anterior = null;
        while (atual != null) {
            if (atual.valor.equals(item)) {
                if (anterior == null) {
                    head = atual.prox;
                } else {
                    anterior.prox = atual.prox;
                }
                tamanho--;
                return atual.valor;
            }
            anterior = atual;
            atual = atual.prox;
        }

        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
    }

    @Override
    public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer pred = null;
        No atual = head;
        while (atual != null) {
            if (atual.valor < item) {
                if (pred == null || atual.valor > pred) {
                    pred = atual.valor;
                }
            }
            atual = atual.prox;
        }

        if (pred == null)
            throw new ElementoNaoEncontradoException("Predecessor não encontrado");
        return pred;
    }


    @Override
    public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer suc = null;
        No atual = head;
        while (atual != null) {
            if (atual.valor > item) {
                if (suc == null || atual.valor < suc) {
                    suc = atual.valor;
                }
            }
            atual = atual.prox;
        }
        return suc;
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");

        No atual = head;
        while (atual != null) {
            if (atual.valor.equals(item)) {
                return atual.valor;
            }
            atual = atual.prox;
        }
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
    }

    @Override
    public Integer minimum() throws ConjuntoDinamicoVazioException {
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer min = head.valor;
        No atual = head.prox;
        while (atual != null) {
            if (atual.valor < min) {
                min = atual.valor;
            }
            atual = atual.prox;
        }
        return min;
    }

    @Override
    public Integer maximum() throws ConjuntoDinamicoVazioException {
        if (tamanho == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer max = head.valor;
        No atual = head.prox;
        while (atual != null) {
            if (atual.valor > max) {
                max = atual.valor;
            }
            atual = atual.prox;
        }
        return max;
    }
}
