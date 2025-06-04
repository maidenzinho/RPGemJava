// Personagem.java
// Classe abstrata base para personagens

package personagem;

import item.Inventario;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int forca;
    protected Inventario inventario;

    public Personagem(String nome, int vida, int forca) {
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
        this.inventario = new Inventario();
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public abstract void usarPoderEspecial();

    public void atacar() {
        System.out.println(nome + " ataca com força " + forca);
    }
}
