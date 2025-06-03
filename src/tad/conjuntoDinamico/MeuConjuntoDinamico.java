package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Esta classe implementa um conjunto dinâmico de inteiros baseado em array.
 * Permite inserção, remoção, busca, e operações como predecessor, sucessor,
 * mínimo e máximo.
 * 
 * Objetivo: Exercitar manipulação de arrays dinâmicos e lógica de busca em coleções.
 */

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private Integer[] meusDados = new Integer[10];
    private int posInsercao = 0;

    @Override
    public void inserir(Integer item) {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (posInsercao == meusDados.length) {
            meusDados = aumentarArray();
        }
        meusDados[posInsercao++] = item;
    }

    private Integer[] aumentarArray() {
        Integer[] novoArray = new Integer[meusDados.length * 2];
        System.arraycopy(meusDados, 0, novoArray, 0, meusDados.length);
        return novoArray;
    }

    @Override
    public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();

        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                Integer removido = meusDados[i];
                for (int j = i; j < posInsercao - 1; j++) {
                    meusDados[j] = meusDados[j + 1];
                }
                meusDados[--posInsercao] = null;
                return removido;
            }
        }
        throw new ElementoNaoEncontradoException("Elemento não encontrado!!");
    }

    @Override
    public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer pred = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i] < item) {
                if (pred == null || meusDados[i] > pred) {
                    pred = meusDados[i];
                }
            }
        }
        return pred;
    }

    @Override
    public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer suc = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i] > item) {
                if (suc == null || meusDados[i] < suc) {
                    suc = meusDados[i];
                }
            }
        }
        return suc;
    }

    @Override
    public int tamanho() {
        return posInsercao;
    }

    @Override
    public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");

        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        throw new ElementoNaoEncontradoException("Elemento não encontrado!!");
    }

    @Override
    public Integer minimum() throws ConjuntoDinamicoVazioException {
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] < min) {
                min = meusDados[i];
            }
        }
        return min;
    }

    @Override
    public Integer maximum() throws ConjuntoDinamicoVazioException {
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();

        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] > max) {
                max = meusDados[i];
            }
        }
        return max;
    }
}
