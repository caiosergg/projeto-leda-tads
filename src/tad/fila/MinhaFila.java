package tad.fila;

import tad.fila.FilaCheiaException;
import tad.fila.FilaVaziaException;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Esta classe implementa uma fila de inteiros utilizando um array fixo e
 * uma estratégia circular para gerenciar os índices da cabeça e da cauda.
 * 
 * Permite operações de enfileirar, desenfileirar, verificar elementos na
 * cabeça e na cauda, além de consultar se a fila está cheia ou vazia, 
 * tamanho atual e capacidade máxima.
 * 
 * Objetivo: Exercitar o uso de estruturas lineares estáticas com lógica
 * circular para otimização do uso do espaço em arrays.
 */

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */

public class MinhaFila implements FilaIF<Integer> {
	
	private int tamanho = 5;
	
	private int cauda = 0;  // índice onde será inserido o próximo elemento (posição "fim")
	private int cabeca = 0; // índice do primeiro elemento da fila
	
	private Integer[] meusDados = null;
	private int count = 0; // quantidade atual de elementos

	public MinhaFila(int tamanhoInicial) {
	    if (tamanhoInicial <= 0) {
	        throw new IllegalArgumentException("Tamanho deve ser positivo");
	    }
	    tamanho = tamanhoInicial;
	    meusDados = new Integer[tamanho];
	    cauda = 0;
	    cabeca = 0;
	    count = 0;
	}
	
	public MinhaFila() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if (isFull()) {
			throw new FilaCheiaException();
		}
		meusDados[cauda] = item;
		cauda = (cauda + 1) % tamanho;
		count++;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		Integer item = meusDados[cabeca];
		meusDados[cabeca] = null; // ajuda o GC
		cabeca = (cabeca + 1) % tamanho;
		count--;
		return item;
	}

	@Override
	public Integer verificarCauda() {
		if (isEmpty()) {
			return null;
		}
		// cauda aponta para a próxima posição livre, então o último elemento está em (cauda - 1)
		int ultimo = cauda - 1;
		if (ultimo < 0) {
			ultimo = tamanho - 1;
		}
		return meusDados[ultimo];
	}

	@Override
	public Integer verificarCabeca() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[cabeca];
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public boolean isFull() {
		return count == tamanho;
	}

	@Override
	public int capacidade() {
		return tamanho;
	}

	@Override
	public int tamanho() {
		return count;
	}
}
