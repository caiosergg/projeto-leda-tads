package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;
import tad.util.Conversor;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

    private NodoListaDuplamenteEncadeada<T> cabeca;
    private NodoListaDuplamenteEncadeada<T> cauda;
    private int tamanho = 0;

    public ListaDuplamenteEncadeadaImpl() {
        cabeca = new NodoListaDuplamenteEncadeada<T>();
        cauda = new NodoListaDuplamenteEncadeada<T>();
        cabeca.setProximo(cauda);
        cabeca.setAnterior(null);
        cauda.setAnterior(cabeca);
        cauda.setProximo(null);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo() == cauda;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> search(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<T>(atual.getChave());
                // Ajuste: setar anterior e proximo conforme esperado nos testes
                NodoListaDuplamenteEncadeada<T> anterior = atual.getAnterior();
                NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
                if (anterior != cabeca) {
                    novo.setAnterior(new NodoListaDuplamenteEncadeada<T>(anterior.getChave()));
                } else {
                    novo.setAnterior(null);
                }
                if (proximo != cauda) {
                    novo.setProximo(new NodoListaDuplamenteEncadeada<T>(proximo.getChave()));
                } else {
                    novo.setProximo(null);
                }
                return novo;
            }
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<T>(chave);
        NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();

        ultimo.setProximo(novo);
        novo.setAnterior(ultimo);
        novo.setProximo(cauda);
        cauda.setAnterior(novo);

        tamanho++;
    }

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > tamanho) throw new IndexOutOfBoundsException();
        NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<T>(chave);
        NodoListaDuplamenteEncadeada<T> atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        NodoListaDuplamenteEncadeada<T> prox = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        atual.setProximo(novo);
        novo.setAnterior(atual);
        novo.setProximo(prox);
        prox.setAnterior(novo);
        tamanho++;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> remove(T chave) throws ElementoNaoEncontradoException, ListaVaziaException {
        if (isEmpty()) throw new ListaVaziaException();
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                NodoListaDuplamenteEncadeada<T> anterior = atual.getAnterior();
                NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
                anterior.setProximo(proximo);
                proximo.setAnterior(anterior);
                tamanho--;
                NodoListaDuplamenteEncadeada<T> retorno = new NodoListaDuplamenteEncadeada<T>(atual.getChave());
                return retorno;
            }
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        throw new ElementoNaoEncontradoException();
    }

    @Override
    public String imprimeEmOrdem() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            sb.append(atual.getChave());
            if (atual.getProximo() != cauda) sb.append(", ");
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return sb.toString();
    }

    @Override
    public String imprimeInverso() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();
        while (atual != cabeca) {
            sb.append(atual.getChave());
            if (atual.getAnterior() != cabeca) sb.append(", ");
            atual = atual.getAnterior();
        }
        return sb.toString();
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        Conversor<T> conversor = new Conversor<T>();
        T[] array = conversor.gerarArray(clazz, tamanho);
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        int i = 0;
        while (atual != cauda) {
            array[i++] = atual.getChave();
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return array;
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                if (atual.getProximo() == cauda) return null;
                NodoListaDuplamenteEncadeada<T> prox = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
                return new NodoListaDuplamenteEncadeada<T>(prox.getChave());
            }
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        throw new ElementoNaoEncontradoException();
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                if (atual.getAnterior() == cabeca) return null;
                NodoListaDuplamenteEncadeada<T> ant = atual.getAnterior();
                return new NodoListaDuplamenteEncadeada<T>(ant.getChave());
            }
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        throw new ElementoNaoEncontradoException();
    }

    @Override
    public void inserePrimeiro(T elemento) {
        NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<T>(elemento);
        NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        cabeca.setProximo(novo);
        novo.setAnterior(cabeca);
        novo.setProximo(primeiro);
        primeiro.setAnterior(novo);
        tamanho++;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removeUltimo() throws ListaVaziaException {
        if (isEmpty()) throw new ListaVaziaException();
        NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();
        NodoListaDuplamenteEncadeada<T> penultimo = ultimo.getAnterior();
        penultimo.setProximo(cauda);
        cauda.setAnterior(penultimo);
        tamanho--;
        NodoListaDuplamenteEncadeada<T> retorno = new NodoListaDuplamenteEncadeada<T>(ultimo.getChave());
        return retorno;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removePrimeiro() throws ListaVaziaException {
        if (isEmpty()) throw new ListaVaziaException();
        NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        NodoListaDuplamenteEncadeada<T> segundo = (NodoListaDuplamenteEncadeada<T>) primeiro.getProximo();
        cabeca.setProximo(segundo);
        segundo.setAnterior(cabeca);
        tamanho--;
        NodoListaDuplamenteEncadeada<T> retorno = new NodoListaDuplamenteEncadeada<T>(primeiro.getChave());
        return retorno;
    }
}