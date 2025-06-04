// Inimigo.java
// Representa inimigos do jogo

package combate;

public class Inimigo {
    private String nome;
    private int vida;

    public Inimigo(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public void atacar() {
        System.out.println(nome + " ataca ferozmente!");
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }
}
