// Cidade.java
// Subclasse de Local

package mundo;

public class Cidade extends Local {
    public Cidade(String nome) {
        super(nome);
    }

    @Override
    public void explorar() {
        System.out.println("Você está na cidade de " + nome + ", cheia de comércio e NPCs.");
    }
}
