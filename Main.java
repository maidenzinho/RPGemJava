// Main.java
// Classe principal que inicializa o jogo e exibe a interface gráfica

import ui.GameUI;

public class Main {
    public static void main(String[] args) {
        // Executa o jogo na thread de interface gráfica (EDT)
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GameUI(); // Cria e exibe a interface gráfica do jogo
        });
    }
}
