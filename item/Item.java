// Item.java
// Representa um item no jogo

package item;

public class Item {
    private String nome;
    private int poder;

    public Item(String nome, int poder) {
        this.nome = nome;
        this.poder = poder;
    }

    public String getNome() {
        return nome;
    }

    public int getPoder() {
        return poder;
    }

    public String toString() {
        return nome + " (+" + poder + ")";
    }
}
