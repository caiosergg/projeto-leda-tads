package tad.conjuntoDinamico;

/**
 * Nome: Caio Sérgio Ramalho Lima
 * Matrícula: 211080411
 * 
 * Roteiro: Roteiro 5 - TADs
 * 
 * Objetivo: Implementar um conjunto dinâmico de inteiros baseado em array, com
 * capacidade de redimensionamento automático e operações básicas como inserção,
 * remoção, busca, sucessor, predecessor, mínimo e máximo.
 */

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private static final int CAPACIDADE_INICIAL = 5;
    private Integer[] meusDados = new Integer[CAPACIDADE_INICIAL];
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
        int novaCapacidade = meusDados.length * 2;
        Integer[] novoArray = new Integer[novaCapacidade];
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
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
    }

    @Override
    public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                if (i == 0) return null;
                return meusDados[i - 1];
            }
        }
        return null;
    }

    @Override
    public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (item == null)
            throw new IllegalArgumentException("Elemento não pode ser null");
        if (posInsercao == 0)
            throw new ConjuntoDinamicoVazioException();
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                if (i == posInsercao - 1) return null;
                return meusDados[i + 1];
            }
        }
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
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
        throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
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
