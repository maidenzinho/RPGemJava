// Inventario.java
// Armazena os itens do personagem

package item;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public void adicionar(Item item) {
        itens.add(item);
    }

    public void listar() {
        for (Item item : itens) {
            System.out.println(item);
        }
    }

    public ArrayList<Item> getItens() {
        return itens;
    }
}
