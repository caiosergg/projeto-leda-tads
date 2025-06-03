package tad.pilha;

import tad.util.Conversor;

/**
 * @author Caio Sérgio
 * Roteiro 5 - Projeto LEDA
 * 
 * Esta classe implementa uma pilha de inteiros com capacidade limitada,
 * baseada em array interno. Permite empilhar, desempilhar, consultar o topo,
 * verificar se está vazia, e recuperar múltiplos elementos do topo.
 * 
 * Objetivo: Exercitar o uso de estruturas estáticas para implementação de pilhas
 * e manipulação de elementos com controle de capacidade e exceções.
 */

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int capacidade = 5;
	private Integer[] meusDados = null;
	private int topoIndex = -1;  // índice do topo da pilha, -1 indica pilha vazia

	public MinhaPilha(int capacidade) {
		if (capacidade <= 0) {
			throw new IllegalArgumentException("Capacidade deve ser maior que zero");
		}
		this.capacidade = capacidade;
		this.meusDados = new Integer[capacidade];
	}
	
	public MinhaPilha() {
		this.meusDados = new Integer[capacidade];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (topoIndex + 1 >= capacidade) {
			throw new PilhaCheiaException();
		}
		topoIndex++;
		meusDados[topoIndex] = item;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		Integer elemento = meusDados[topoIndex];
		meusDados[topoIndex] = null; // ajuda o garbage collector
		topoIndex--;
		return elemento;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[topoIndex];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		if (k <= 0) {
			throw new IllegalArgumentException("k deve ser maior que zero");
		}
		MinhaPilha resultado = new MinhaPilha(k > tamanho() ? tamanho() : k);
		for (int i = topoIndex; i >= topoIndex - k + 1 && i >= 0; i--) {
			try {
				resultado.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				// não deve ocorrer porque criamos capacidade correta
			}
		}
		return resultado;
	}

	@Override
	public boolean isEmpty() {
		return topoIndex == -1;
	}

	@Override
	public int capacidade() {
		return capacidade;
	}

	@Override
	public int tamanho() {
		return topoIndex + 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MinhaPilha))
			return false;
		MinhaPilha outra = (MinhaPilha) obj;
		if (this.tamanho() != outra.tamanho())
			return false;
		for (int i = 0; i <= topoIndex; i++) {
			if (!this.meusDados[i].equals(outra.meusDados[i]))
				return false;
		}
		return true;
	}
}
