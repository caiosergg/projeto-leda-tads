package tad.estruturas;

/**
 * Nome: Caio Sérgio Ramalho Lima
 * Matrícula: 211080411
 *
 * Roteiro: Roteiro 5 - TADs
 *
 * Objetivo: Implementar uma fila baseada em lista encadeada simples,
 * permitindo operações de enfileiramento, desenfileiramento, verificação
 * da cabeça e da cauda da fila, com gerenciamento dinâmico sem o uso de arrays fixos.
 *
 */

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;

public class FilaListaEncadeada<E extends Comparable<E>> implements FilaIF<E> {

	private ListaEncadeadaImpl<E> lista;

	FilaListaEncadeada() {
		this.lista = new ListaEncadeadaImpl<>();
	}

	@Override
	public void enfileirar(E item) throws FilaCheiaException {
		// Como a lista é dinâmica, a fila nunca fica cheia
		lista.insert(item);
	}

	@Override
	public E desenfileirar() throws FilaVaziaException {
		try {
			NodoListaEncadeada<E> removido = lista.removeInicio();
			return removido.getChave();
		} catch (ListaVaziaException e) {
			throw new FilaVaziaException();
		}
	}

	@Override
	public E verificarCauda() {
		if (isEmpty()) return null;
		// Busca o último elemento da lista
		E ultimo = null;
		NodoListaEncadeada<E> atual = lista.cabeca.getProximo();
		while (atual != lista.cauda) {
			ultimo = atual.getChave();
			atual = atual.getProximo();
		}
		return ultimo;
	}

	@Override
	public E verificarCabeca() {
		if (isEmpty()) return null;
		return lista.cabeca.getProximo().getChave();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public boolean isFull() {
		// Lista encadeada dinâmica, nunca está cheia
		return false;
	}

	@Override
	public int capacidade() {
		// Não há limite
		return Integer.MAX_VALUE;
	}

	@Override
	public int tamanho() {
		return lista.size();
	}
}
