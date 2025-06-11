package tad.fila;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

    private static class Node {
        Integer valor;
        Node prox;
        Node(Integer v) { valor = v; }
    }

    private Node head = null;
    private Node tail = null;
    private int qtd = 0;

    @Override
    public void enfileirar(Integer item) {
        Node novo = new Node(item);
        if (tail == null) {
            head = tail = novo;
        } else {
            tail.prox = novo;
            tail = novo;
        }
        qtd++;
    }

    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) throw new FilaVaziaException();
        Integer val = head.valor;
        head = head.prox;
        if (head == null) tail = null;
        qtd--;
        return val;
    }

    @Override
    public Integer verificarCauda() {
        return (tail == null) ? null : tail.valor;
    }

    @Override
    public Integer verificarCabeca() {
        return (head == null) ? null : head.valor;
    }

    @Override
    public boolean isEmpty() {
        return qtd == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int capacidade() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int tamanho() {
        return qtd;
    }
}
