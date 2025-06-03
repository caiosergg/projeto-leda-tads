package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.NodoListaEncadeada;
import tad.ElementoNaoEncontradoException;
import tad.listasEncadeadas.ListaVaziaException;
import tad.pilha.PilhaCheiaException;
import tad.pilha.PilhaVaziaException;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Implementação de uma pilha genérica de inteiros usando uma lista encadeada.
 * 
 * O topo da pilha corresponde ao início da lista encadeada para manter a ordem LIFO.
 * Operações básicas de empilhar, desempilhar e consultar topo são realizadas
 * com complexidade O(1) para inserção e remoção no início da lista.
 * 
 * Métodos multitop retornam uma nova pilha com os k elementos do topo.
 * 
 * Não possui limite fixo de capacidade.
 */

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

    private ListaEncadeadaIF<Integer> listaEncadeada = new ListaEncadeadaImpl<>();

    @Override
    public void empilhar(Integer item) throws PilhaCheiaException {
        // Insere no início da lista para manter LIFO (topo é o primeiro)
        listaEncadeada.insert(item, 0);
    }

    @Override
    public Integer desempilhar() throws PilhaVaziaException {
        if (listaEncadeada.isEmpty()) {
            throw new PilhaVaziaException("Pilha vazia!");
        }
        Integer topo;
        try {
            // Pega o array da lista (ordem inserida, topo é index 0)
            Integer[] array = listaEncadeada.toArray(Integer.class);
            topo = array[0];
            // Remove o topo da lista pelo valor
            listaEncadeada.remove(topo);
        } catch (ElementoNaoEncontradoException | ListaVaziaException e) {
            throw new PilhaVaziaException("Erro ao desempilhar: " + e.getMessage());
        }
        return topo;
    }

    @Override
    public Integer topo() {
        if (listaEncadeada.isEmpty()) {
            return null;
        }
        Integer[] array = listaEncadeada.toArray(Integer.class);
        return array[0];
    }

    @Override
    public PilhaIF<Integer> multitop(int k) throws PilhaCheiaException {
        if (k <= 0) {
            throw new IllegalArgumentException("k deve ser positivo");
        }
        MinhaPilhaEncadeada pilhaAux = new MinhaPilhaEncadeada();
        Integer[] array = listaEncadeada.toArray(Integer.class);
        int limite = Math.min(k, array.length);
        for (int i = 0; i < limite; i++) {
            pilhaAux.empilhar(array[i]);
        }
        return pilhaAux;
    }

    @Override
    public boolean isEmpty() {
        return listaEncadeada.isEmpty();
    }

	@Override
	public int capacidade() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int tamanho() {
		// TODO Auto-generated method stub
		return 0;
	}
}
