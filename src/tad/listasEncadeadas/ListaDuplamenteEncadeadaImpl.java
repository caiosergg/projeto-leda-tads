package tad.listasEncadeadas;

import tad.util.Conversor;

/**
 * Implementação de lista duplamente encadeada genérica com marcação sentinela.
 * 
 * Esta lista mantém nós com referências tanto para o próximo quanto para o anterior,
 * permitindo iteração em ambas as direções. Utiliza nós sentinelas para a cabeça e a cauda,
 * o que simplifica a manipulação da lista, evitando casos 
 * especiais para listas vazias ou listas com um único elemento.
 * 
 * Os elementos devem implementar Comparable para suportar operações de busca
 * baseadas na comparação de chaves.
 * 
 * @param <T> Tipo genérico que deve implementar Comparable para comparação de chaves.
 */

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
	
	NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaDuplamenteEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaDuplamenteEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();
		cabeca.setProximo(cauda);
		
		// lista circular
		cabeca.setAnterior(cauda);
		cauda.setAnterior(cabeca);
	}

	@Override
	public boolean isEmpty() {
		return cabeca.getProximo() == cauda;
	}

	@Override
	public int size() {
		int count = 0;
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			count++;
			atual = atual.getProximo();
		}
		return count;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			if (atual.getChave().compareTo(chave) == 0) {
				return (NodoListaDuplamenteEncadeada<T>) atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {
	    NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);

	    // Novo no vai antes da cauda
	    NodoListaDuplamenteEncadeada<T> anterior = cauda.getAnterior();

	    anterior.setProximo(novoNo);
	    novoNo.setAnterior(anterior);
	    novoNo.setProximo(cauda);
	    cauda.setAnterior(novoNo);
	}


	@Override
	public NodoListaDuplamenteEncadeada<T> remove(T elemento) throws ListaVaziaException {
	    if (isEmpty()) {
	        throw new ListaVaziaException();
	    }

	    NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
	    while (atual != cauda) {
	        if (atual.getChave().equals(elemento)) {
	            NodoListaDuplamenteEncadeada<T> anterior = (NodoListaDuplamenteEncadeada<T>) atual.getAnterior();
	            NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();

	            anterior.setProximo(proximo);
	            proximo.setAnterior(anterior);

	            // desconectar o nó removido
	            atual.setProximo(null);
	            atual.setAnterior(null);

	            return atual;
	        }
	        atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
	    }

	    throw new ListaVaziaException(); // se não achou o elemento
	}

	@Override
	public String imprimeEmOrdem() {
	    StringBuilder sb = new StringBuilder();
	    NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();

	    while (atual != cauda) {
	        sb.append(atual.getChave());
	        if (atual.getProximo() != cauda) {
	            sb.append(", ");
	        }
	        atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
	    }

	    return sb.toString();
	}

	@Override
	public String imprimeInverso() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();
		while (atual != cabeca) {
			sb.append(atual.getChave());
			if (atual.getAnterior() != cabeca) {
				sb.append(" ");
			}
			atual = atual.getAnterior();
		}
		return sb.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaDuplamenteEncadeada<T> no = search(chave);
		if (no == null || no.getProximo() == cauda) {
			return null;
		}
		return no.getProximo();
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaDuplamenteEncadeada<T> no = search(chave);
		if (no == null || no.getAnterior() == cabeca) {
			return null;
		}
		return no.getAnterior();
	}

	@Override
	public T[] toArray(Class<T> clazz) {
	    Conversor<T> conversor = new Conversor<>();
	    T[] array = conversor.gerarArray(clazz, size());
	    NodoListaEncadeada<T> atual = cabeca.getProximo();
	    int i = 0;
	    while (atual != cauda) {
	        array[i++] = atual.getChave();
	        atual = atual.getProximo();
	    }
	    return array;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		insert(elemento);
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() throws ListaVaziaException {
	    if (isEmpty()) {
	        throw new ListaVaziaException();
	    }
	    NodoListaDuplamenteEncadeada<T> ultimo = (NodoListaDuplamenteEncadeada<T>) cauda.getAnterior();
	    NodoListaDuplamenteEncadeada<T> penultimo = (NodoListaDuplamenteEncadeada<T>) ultimo.getAnterior();

	    penultimo.setProximo(cauda);
	    cauda.setAnterior(penultimo);

	    // desconectar o nó removido
	    ultimo.setProximo(null);
	    ultimo.setAnterior(null);

	    return ultimo;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() throws ListaVaziaException {
	    if (isEmpty()) {
	        throw new ListaVaziaException();
	    }
	    NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
	    NodoListaDuplamenteEncadeada<T> segundo = (NodoListaDuplamenteEncadeada<T>) primeiro.getProximo();

	    cabeca.setProximo(segundo);
	    segundo.setAnterior(cabeca);

	    // desconectar o nó removido
	    primeiro.setProximo(null);
	    primeiro.setAnterior(null);

	    return primeiro;
	}

	@Override
	public void insert(T chave, int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index fora do limite");
		}
		if (index == 0) {
			inserePrimeiro(chave);
			return;
		}
		NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
		for (int i = 0; i < index -1; i++) {
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);
		novoNo.setProximo(atual.getProximo());
		((NodoListaDuplamenteEncadeada<T>) atual.getProximo()).setAnterior(novoNo);
		novoNo.setAnterior(atual);
		atual.setProximo(novoNo);
	}

}
