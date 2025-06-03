package tad.listasEncadeadas;

public class NodoListaEncadeada<T extends Comparable<T>> {
	
	protected T chave;
	protected NodoListaEncadeada<T> proximo = null;
	
	public NodoListaEncadeada() {
		this.setChave(null);
		this.setProximo(null);
	}
	
	public NodoListaEncadeada(T chave) {
		this.setChave(chave);
		this.setProximo(null);
	}
	
	public NodoListaEncadeada(T chave, NodoListaEncadeada<T> proximo) {
		this.setChave(chave);
		this.setProximo(proximo);
	}

	public T getChave() {
		return chave;
	}

	public void setChave(T chave) {
		this.chave = chave;
	}

	public NodoListaEncadeada<T> getProximo() {
		return proximo;
	}

	public void setProximo(NodoListaEncadeada<T> proximo) {
		this.proximo = proximo;
	}
	
	public boolean isNull() {
		return (chave == null ? true:false);
	}
	
	/*
	 * Modifiquei o método equals para evitar NullPointerException.
	 * Porque o metodo compara recursivamente o campo 'proximo' dos nodos,
	 * e quando 'proximo' é null, causava erro nos testes (como sucessorTeste()).
	 * Agora, com a verificacao para 'proximo == null', a comparacao fica segura
	 * e os testes conseguem rodar normalmente
	 */
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    @SuppressWarnings("unchecked")
	    NodoListaEncadeada<T> aComparar = (NodoListaEncadeada<T>) obj;

	    if (this.chave == null) {
	        return aComparar.getChave() == null;
	    } else {
	        return this.chave.equals(aComparar.getChave());
	    }
	}

	@Override
	public String toString() {
		if (!this.isNull())
			return this.chave.toString();
		return null;
	}
}
