package tad.fila;

import tad.fila.FilaCheiaException;
import tad.fila.FilaVaziaException;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Esta classe implementa uma fila dinâmica de inteiros baseada em lista
 * encadeada simples. Permite enfileirar e desenfileirar elementos,
 * além de consultar cabeça, cauda, tamanho e capacidade (ilimitada).
 * 
 * Objetivo: Exercitar o uso de estruturas dinâmicas (listas encadeadas)
 * para implementação de filas, evitando limitações de tamanho fixo.
 */

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	private class No {
		Integer dado;
		No proximo;

		No(Integer dado) {
			this.dado = dado;
			this.proximo = null;
		}
	}

	private No cabeca = null; // primeiro elemento da fila
	private No cauda = null;  // último elemento da fila
	private int count = 0;    // número de elementos

	public MinhaFilaEncadeada() {
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		No novo = new No(item);
		if (isEmpty()) {
			cabeca = novo;
			cauda = novo;
		} else {
			cauda.proximo = novo;
			cauda = novo;
		}
		count++;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		Integer valor = cabeca.dado;
		cabeca = cabeca.proximo;
		count--;
		if (cabeca == null) { // fila ficou vazia, atualiza cauda
			cauda = null;
		}
		return valor;
	}

	@Override
	public Integer verificarCauda() {
		if (isEmpty()) {
			return null;
		}
		return cauda.dado;
	}

	@Override
	public Integer verificarCabeca() {
		if (isEmpty()) {
			return null;
		}
		return cabeca.dado;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public boolean isFull() {
		// fila encadeada não tem limite
		return false;
	}

	@Override
	public int capacidade() {
		// fila encadeada é ilimitada - pode retornar 0 ou Integer.MAX_VALUE
		return Integer.MAX_VALUE;
	}

	@Override
	public int tamanho() {
		return count;
	}
}
