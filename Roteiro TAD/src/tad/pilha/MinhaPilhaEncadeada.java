package tad.pilha;

/**
 * Nome: Caio Sérgio Ramalho Lima
 * Matrícula: 211080411
 * 
 * Roteiro: Roteiro 5 - TADs
 * 
 * Objetivo: Implementar uma pilha de inteiros com alocação dinâmica (baseada em lista encadeada),
 * permitindo operações básicas como empilhar, desempilhar, consultar o topo,
 * além de consultar tamanho e capacidade.
 */

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	private static class Node {
		Integer valor;
		Node prox;
		Node(Integer v, Node p) { valor = v; prox = p; }
	}

	private Node topo = null;
	private int tamanho = 0;

	@Override
	public void empilhar(Integer item) {
		topo = new Node(item, topo);
		tamanho++;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) throw new PilhaVaziaException();
		Integer val = topo.valor;
		topo = topo.prox;
		tamanho--;
		return val;
	}

	@Override
	public Integer topo() {
		return (topo == null) ? null : topo.valor;
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		if (k <= 0) throw new IllegalArgumentException();
		MinhaPilhaEncadeada nova = new MinhaPilhaEncadeada();
		Node atual = topo;
		int count = 0;
		while (atual != null && count < k) {
			nova.empilhar(atual.valor);
			atual = atual.prox;
			count++;
		}
		return nova;
	}

	@Override
	public boolean isEmpty() {
		return topo == null;
	}

	@Override
	public int capacidade() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int tamanho() {
		return tamanho;
	}

	/**
     * Compara esta pilha com outro objeto para verificar igualdade.
     * Duas pilhas são iguais se têm o mesmo tamanho e os mesmos elementos na mesma ordem.
     * 
     * @param obj objeto a comparar
     * @return true se forem iguais, false caso contrário
     */
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PilhaIF)) return false;
		PilhaIF<?> outra = (PilhaIF<?>) obj;
		if (this.tamanho() != outra.tamanho()) return false;
		Node atual = this.topo;
		Node outro = ((MinhaPilhaEncadeada)outra).topo;
		while (atual != null && outro != null) {
			if (!atual.valor.equals(outro.valor)) return false;
			atual = atual.prox;
			outro = outro.prox;
		}
		return atual == null && outro == null;
	}
}
