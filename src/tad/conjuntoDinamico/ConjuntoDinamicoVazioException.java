package tad.conjuntoDinamico;

public class ConjuntoDinamicoVazioException extends Exception {
    private static final long serialVersionUID = 1L;
	public ConjuntoDinamicoVazioException() {
        super("Conjunto dinâmico Vazio!!!");
    }
    public ConjuntoDinamicoVazioException(String msg) {
        super(msg);
    }
}