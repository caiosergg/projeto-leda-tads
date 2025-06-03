package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Esta classe implementa uma lista encadeada simples genérica,
 * baseada em nodos que armazenam elementos do tipo T.
 * 
 * Permite inserir, buscar, remover elementos, além de imprimir a lista
 * em ordem normal e inversa, obter tamanho e converter para array.
 * 
 * Objetivo: Exercitar o uso de estruturas dinâmicas (listas encadeadas)
 * para manipulação genérica de dados que implementam Comparable.
 */

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

    private NodoListaEncadeada<T> cabeca;  // primeiro nodo da lista (null se vazia)
    private int tamanho;

    public ListaEncadeadaImpl() {
        cabeca = null;
        tamanho = 0;
    }

    @Override
    public void insert(T chave) {
        NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);

        if (cabeca == null) {
            // lista vazia
            cabeca = novo;
        } else {
            // inserir no fim da lista
            NodoListaEncadeada<T> atual = cabeca;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo);
        }
        tamanho++;
    }

    @Override
    public NodoListaEncadeada<T> search(T chave) {
        NodoListaEncadeada<T> atual = cabeca;

        while (atual != null) {
            // Para comparar, lidar com null corretamente:
            if (atual.getChave() == null) {
                if (chave == null)
                    return atual;
            } else {
                if (atual.getChave().equals(chave))
                    return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public String imprimeEmOrdem() {
        if (cabeca == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        NodoListaEncadeada<T> atual = cabeca;
        while (atual != null) {
            if (atual.getChave() == null) {
                sb.append("null");
            } else {
                sb.append(atual.getChave().toString());
            }
            if (atual.getProximo() != null)
                sb.append(", ");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    @Override
    public String imprimeInverso() {
        if (cabeca == null) {
            return "";
        }
        // Podemos usar recursão para construir a string invertida:
        return imprimeInversoRec(cabeca);
    }

    private String imprimeInversoRec(NodoListaEncadeada<T> nodo) {
        if (nodo == null)
            return "";
        String proximo = imprimeInversoRec(nodo.getProximo());
        String chaveStr = (nodo.getChave() == null) ? "null" : nodo.getChave().toString();

        if (proximo.isEmpty()) {
            return chaveStr;
        } else {
            return proximo + ", " + chaveStr;
        }
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);
        NodoListaEncadeada<T> atual = cabeca;
        int i = 0;
        while (atual != null) {
            array[i++] = atual.getChave();
            atual = atual.getProximo();
        }
        return array;
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) throws ElementoNaoEncontradoException, ListaVaziaException {
        if (cabeca == null) {
            throw new ListaVaziaException("A lista está vazia");
        }

        NodoListaEncadeada<T> atual = cabeca;
        NodoListaEncadeada<T> anterior = null;

        while (atual != null) {
            if (atual.getChave() == null) {
                if (chave == null) {
                    break; // elemento encontrado
                }
            } else if (atual.getChave().equals(chave)) {
                break; // elemento encontrado
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual == null) {
            throw new ElementoNaoEncontradoException("Elemento não encontrado: " + chave);
        }

        if (anterior == null) {
            // remover da cabeça
            cabeca = cabeca.getProximo();
        } else {
            anterior.setProximo(atual.getProximo());
        }

        tamanho--;
        return new NodoListaEncadeada<>(atual.getChave());
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
        NodoListaEncadeada<T> atual = cabeca;

        while (atual != null && !atual.getChave().equals(chave)) {
            atual = atual.getProximo();
        }

        if (atual == null || atual.getProximo() == null) {
            throw new ElementoNaoEncontradoException();
        }

        // Retorna um novo Nodo com chave do próximo nodo e proximo == null
        return new NodoListaEncadeada<T>(atual.getProximo().getChave());
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
        if (cabeca == null) {
            throw new ElementoNaoEncontradoException("Lista vazia");
        }

        NodoListaEncadeada<T> atual = cabeca;
        NodoListaEncadeada<T> anterior = null;

        while (atual != null) {
            if (atual.getChave() == null) {
                if (chave == null) {
                    if (anterior == null) {
                        throw new ElementoNaoEncontradoException("Predecessor nao encontrado para o elemento: " + chave);
                    }
                    return new NodoListaEncadeada<>(anterior.getChave());
                }
            } else if (atual.getChave().equals(chave)) {
                if (anterior == null) {
                    throw new ElementoNaoEncontradoException("Predecessor nao encontrado para o elemento: " + chave);
                }
                return new NodoListaEncadeada<>(anterior.getChave());
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        throw new ElementoNaoEncontradoException("Elemento nao encontrado: " + chave);
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Indice inválido: " + index);
        }
        NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);
        if (index == 0) {
            novo.setProximo(cabeca);
            cabeca = novo;
        } else {
            NodoListaEncadeada<T> atual = cabeca;
            for (int i = 0; i < index - 1; i++) {
                atual = atual.getProximo();
            }
            novo.setProximo(atual.getProximo());
            atual.setProximo(novo);
        }
    }

}
