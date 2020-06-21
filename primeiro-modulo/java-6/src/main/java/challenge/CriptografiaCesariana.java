package challenge;

public class CriptografiaCesariana implements Criptografia {

    private final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
    private final int cifra = 3;

    public enum Operacao {
        CRIPTOGRAFAR,
        DESCRIPTOGRAFAR
    }

    @Override
    public String criptografar(String texto) {
        return this.operacaoStrategy(texto, Operacao.CRIPTOGRAFAR);
    }

    @Override
    public String descriptografar(String texto) {
        return this.operacaoStrategy(texto, Operacao.DESCRIPTOGRAFAR);
    }

    public String operacaoStrategy(String texto, Operacao operacao) {
        if(texto.isEmpty())
            throw new IllegalArgumentException("O texto não pode ser vazio!");

        int index;
        char[] letras = texto.toLowerCase().toCharArray();
        for (int i = 0; i < letras.length; i++) {
            index = ALFABETO.indexOf(letras[i]);

            if(index >= 0) {
                index = operacao == Operacao.CRIPTOGRAFAR ? index + cifra: Math.abs(index - cifra);
                letras[i] = ALFABETO.toCharArray()[index % ALFABETO.length()];
            }
        }
        return new String(letras);
    }
}
