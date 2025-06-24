package com.rpg;

import javax.swing.*;

/**
 * Classe principal do jogo.
 */
public class Main {
    public static void main(String[] args) {
        // Executa a inicialização da interface gráfica na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Vampire Survivor Mini"); // Cria a janela principal
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao fechar a janela
            frame.setResizable(false); // Não permite redimensionar
            GamePanel panel = new GamePanel(); // Painel principal do jogo
            frame.setContentPane(panel); // Adiciona o painel ao frame
            frame.pack(); // Ajusta o tamanho da janela ao conteúdo
            frame.setLocationRelativeTo(null); // Centraliza a janela
            frame.setVisible(true); // Exibe a janela
            panel.startGame(); // Inicia o loop do jogo
        });
    }
}