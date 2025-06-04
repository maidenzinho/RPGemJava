// Game.java
// Lógica geral do jogo (controlador)
package jogo;

import item.*;
import java.util.ArrayList;
import mundo.*;
import personagem.*;

public class Game {
    private Personagem jogador; 
    private ArrayList<Local> locais;
    private Inventario inventario;

    public Game() {
        this.locais = new ArrayList<>();
        this.inventario = new Inventario();
        carregarMapa();
    }

    public void iniciarJogo(String nome, String classe) throws InvalidoException {
        if (classe.equalsIgnoreCase("guerreiro")) {
            jogador = new Guerreiro(nome);
        } else if (classe.equalsIgnoreCase("mago")) {
            jogador = new Mago(nome);
        } else {
            throw new InvalidoException("Classe inválida.");
        }
    }

    private void carregarMapa() {
        locais.add(new Cidade("Valória"));
        locais.add(new Floresta("Selva Negra"));
    }

    public Personagem getJogador() {
        return jogador;
    }

    public ArrayList<Local> getLocais() {
        return locais;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
