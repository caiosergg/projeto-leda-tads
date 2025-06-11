package tad.listasEncadeadas;

/**
 * Nome: Caio Sérgio Ramalho Lima
 * Matrícula: 211080411
 * 
 * Roteiro: Roteiro 5 - TADs
 * 
 * Objetivo: Implementar uma lista encadeada simples genérica com nós sentinela,
 * suportando operações básicas como inserção, remoção, busca, sucessor, predecessor,
 * conversão para array e impressão em ordem e inversa.
 */

import tad.ElementoNaoEncontradoException;
import tad.util.Conversor;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

    private NodoListaEncadeada<T> cabeca;
    private NodoListaEncadeada<T> cauda;
    private int tamanho = 0;

    public ListaEncadeadaImpl() {
        cabeca = new NodoListaEncadeada<T>();
        cauda = new NodoListaEncadeada<T>();
        cabeca.setProximo(cauda);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo() == cauda;
    }

    @Override
    public int size() {
        return tamanho;
    }

    /**
     * Busca o primeiro nó cujo conteúdo é igual à chave fornecida.
     * Retorna uma cópia do nó encontrado, contendo o valor e (se existir) uma cópia do próximo nó.
     * 
     * @param chave o valor a ser buscado na lista
     * @return uma cópia do nó encontrado, ou null se não encontrado
     */

    @Override
    public NodoListaEncadeada<T> search(T chave) {
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            if (chave == null) {
                if (atual.getChave() == null) return new NodoListaEncadeada<T>(null);
            } else if (chave.equals(atual.getChave())) {
                NodoListaEncadeada<T> novo = new NodoListaEncadeada<T>(atual.getChave());
                if (atual.getProximo() != cauda) {
                    novo.setProximo(new NodoListaEncadeada<T>(atual.getProximo().getChave()));
                }
                return novo;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        NodoListaEncadeada<T> novo = new NodoListaEncadeada<T>(chave);
        NodoListaEncadeada<T> atual = cabeca;
        while (atual.getProximo() != cauda) {
            atual = atual.getProximo();
        }
        atual.setProximo(novo);
        novo.setProximo(cauda);
        tamanho++;
    }

    /**
     * Remove o nó cujo conteúdo é igual à chave fornecida.
     * Lança ListaVaziaException se a lista estiver vazia.
     * Lança ElementoNaoEncontradoException se o elemento não for encontrado.
     * Retorna uma cópia do nó removido, contendo o valor e (se existir) uma cópia do próximo nó.
     * 
     * @param chave o valor a ser removido
     * @return uma cópia do nó removido
     * @throws ElementoNaoEncontradoException se a chave não for encontrada
     * @throws ListaVaziaException se a lista estiver vazia
     */

    @Override
    public NodoListaEncadeada<T> remove(T chave) throws ElementoNaoEncontradoException, ListaVaziaException {
        if (isEmpty()) throw new ListaVaziaException();
        NodoListaEncadeada<T> anterior = cabeca;
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                anterior.setProximo(atual.getProximo());
                tamanho--;
                NodoListaEncadeada<T> retorno = new NodoListaEncadeada<T>(atual.getChave());
                if (atual.getProximo() != cauda) {
                    retorno.setProximo(new NodoListaEncadeada<T>(atual.getProximo().getChave()));
                }
                return retorno;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        throw new ElementoNaoEncontradoException();
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        Conversor<T> conversor = new Conversor<T>();
        T[] array = conversor.gerarArray(clazz, tamanho);
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        int i = 0;
        while (atual != cauda) {
            array[i++] = atual.getChave();
            atual = atual.getProximo();
        }
        return array;
    }

    @Override
    public String imprimeEmOrdem() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            sb.append(atual.getChave());
            if (atual.getProximo() != cauda) sb.append(", ");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    @Override
    public String imprimeInverso() {
        if (isEmpty()) return "";
        // Usando array para inverter
        Object[] temp = new Object[tamanho];
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        int i = 0;
        while (atual != cauda) {
            temp[i++] = atual.getChave();
            atual = atual.getProximo();
        }
        StringBuilder sb = new StringBuilder();
        for (int j = tamanho - 1; j >= 0; j--) {
            sb.append(temp[j]);
            if (j != 0) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda && atual.getProximo() != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                NodoListaEncadeada<T> prox = atual.getProximo();
                return new NodoListaEncadeada<T>(prox.getChave());
            }
            atual = atual.getProximo();
        }
        throw new ElementoNaoEncontradoException();
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
        NodoListaEncadeada<T> anterior = cabeca;
        NodoListaEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            if ((chave == null && atual.getChave() == null) || (chave != null && chave.equals(atual.getChave()))) {
                if (anterior == cabeca) throw new ElementoNaoEncontradoException();
                return new NodoListaEncadeada<T>(anterior.getChave());
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        throw new ElementoNaoEncontradoException();
    }

    /**
     * Insere um novo nó com a chave especificada na posição indicada pelo índice.
     * Lança IndexOutOfBoundsException se o índice for inválido.
     * O índice 0 indica inserção logo após a cabeça (início da lista).
     * 
     * @param chave o valor a ser inserido
     * @param index posição onde o novo nó será inserido (0 <= index <= tamanho)
     */

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > tamanho) throw new IndexOutOfBoundsException();
        NodoListaEncadeada<T> novo = new NodoListaEncadeada<T>(chave);
        NodoListaEncadeada<T> atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }
        novo.setProximo(atual.getProximo());
        atual.setProximo(novo);
        tamanho++;
    }
}
