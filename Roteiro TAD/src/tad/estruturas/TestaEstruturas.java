package tad.estruturas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tad.ElementoNaoEncontradoException;
import tad.conjuntoDinamico.ConjuntoDinamicoVazioException;
import tad.fila.FilaCheiaException;
import tad.fila.FilaVaziaException;
import tad.listasEncadeadas.ListaVaziaException;

public class TestaEstruturas {

    private ListaEncadeadaImpl<Integer> lista;
    private FilaListaEncadeada<Integer> fila;
    private MeuConjuntoDinamicoEncadeado conjunto;

    @BeforeEach
    public void setup() {
        lista = new ListaEncadeadaImpl<>();
        fila = new FilaListaEncadeada<>();
        conjunto = new MeuConjuntoDinamicoEncadeado();
    }

    @Test
    public void testListaEncadeada() throws ListaVaziaException, ElementoNaoEncontradoException {
        lista.insert(1);
        lista.insert(2);
        lista.insert(3);
        assertEquals("1, 2, 3", lista.imprimeEmOrdem());
        lista.remove(2);
        assertEquals("1, 3", lista.imprimeEmOrdem());
        assertThrows(ElementoNaoEncontradoException.class, () -> lista.remove(5));
    }

    @Test
    public void testFilaListaEncadeada() throws FilaVaziaException, FilaCheiaException {
        fila.enfileirar(10);
        fila.enfileirar(20);
        fila.enfileirar(30);
        assertEquals(3, fila.tamanho());
        assertEquals(10, fila.verificarCabeca());
        assertEquals(30, fila.verificarCauda());

        int desenfileirado = fila.desenfileirar();
        assertEquals(10, desenfileirado);
        assertEquals(2, fila.tamanho());
        assertEquals(20, fila.verificarCabeca());

        // Desenfileirar até ficar vazia
        fila.desenfileirar();
        fila.desenfileirar();
        assertTrue(fila.isEmpty());
        assertThrows(FilaVaziaException.class, () -> fila.desenfileirar());
    }

    @Test
    public void testMeuConjuntoDinamicoEncadeado() throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        conjunto.inserir(5);
        conjunto.inserir(10);
        conjunto.inserir(15);
        assertEquals(3, conjunto.tamanho());

        conjunto.remover(10);
        assertEquals(2, conjunto.tamanho());

        assertEquals(15, conjunto.sucessor(5));
        assertNull(conjunto.predecessor(5)); // predecessor do primeiro é null

        assertEquals(5, conjunto.minimum());
        assertEquals(15, conjunto.maximum());

        assertThrows(ElementoNaoEncontradoException.class, () -> conjunto.remover(20));
        assertThrows(ElementoNaoEncontradoException.class, () -> conjunto.buscar(20));
    }
}
