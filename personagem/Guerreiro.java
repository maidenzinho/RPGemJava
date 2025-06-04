// Guerreiro.java
// Classe concreta herdeira de Personagem

package personagem;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 150, 20);
    }

    @Override
    public void usarPoderEspecial() {
        System.out.println(nome + " usa Golpe de Espada!");
    }

    @Override
    public void atacar() {
        System.out.println(nome + " golpeia com sua espada!");
    }
}
