package tad.fila;

public class MinhaFila implements FilaIF<Integer> {

    private int tamanho = 5;
    private int cauda = 0;
    private int cabeca = 0;
    private int qtd = 0;
    private Integer[] meusDados = null;

    public MinhaFila(int tamanhoInicial) {
        tamanho = tamanhoInicial;
        meusDados = new Integer[tamanho];
    }

    public MinhaFila() {
        meusDados = new Integer[tamanho];
    }

    @Override
    public void enfileirar(Integer item) throws FilaCheiaException {
        if (isFull()) throw new FilaCheiaException();
        meusDados[cauda] = item;
        cauda = (cauda + 1) % tamanho;
        qtd++;
    }

    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) throw new FilaVaziaException();
        Integer valor = meusDados[cabeca];
        meusDados[cabeca] = null;
        cabeca = (cabeca + 1) % tamanho;
        qtd--;
        return valor;
    }

    @Override
    public Integer verificarCauda() {
        if (isEmpty()) return null;
        int idx = (cauda - 1 + tamanho) % tamanho;
        return meusDados[idx];
    }

    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) return null;
        return meusDados[cabeca];
    }

    @Override
    public boolean isEmpty() {
        return qtd == 0;
    }

    @Override
    public boolean isFull() {
        return qtd == tamanho;
    }

    @Override
    public int capacidade() {
        return tamanho;
    }

    @Override
    public int tamanho() {
        return qtd;
    }
}
