// Mago.java
// Classe concreta herdeira de Personagem

package personagem;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 100, 25);
    }

    @Override
    public void usarPoderEspecial() {
        System.out.println(nome + " lança Bola de Fogo!");
    }

    @Override
    public void atacar() {
        System.out.println(nome + " dispara magia arcana!");
    }
}
