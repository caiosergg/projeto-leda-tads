package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int tamanho = 5;
	private Integer[] meusDados;
	private int topo = -1;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
	}
	
	public MinhaPilha() {
		this.meusDados = new Integer[tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (topo + 1 == tamanho) throw new PilhaCheiaException();
		meusDados[++topo] = item;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) throw new PilhaVaziaException();
		Integer val = meusDados[topo];
		meusDados[topo--] = null;
		return val;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) return null;
		return meusDados[topo];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		if (k <= 0) throw new IllegalArgumentException();
		MinhaPilha nova = new MinhaPilha(tamanho);
		int limite = Math.min(k, topo + 1);
		for (int i = topo; i >= topo - limite + 1; i--) {
			try {
				nova.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				// Não deve acontecer pois nova pilha tem mesmo tamanho
			}
		}
		return nova;
	}

	@Override
	public boolean isEmpty() {
		return topo == -1;
	}

	@Override
	public int capacidade() {
		return tamanho;
	}

	@Override
	public int tamanho() {
		return topo + 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PilhaIF)) return false;
		PilhaIF<?> outra = (PilhaIF<?>) obj;
		if (this.tamanho() != outra.tamanho()) return false;
		for (int i = 0; i < this.tamanho(); i++) {
			if (!this.meusDados[i].equals(((MinhaPilha)outra).meusDados[i])) return false;
		}
		return true;
	}
}
