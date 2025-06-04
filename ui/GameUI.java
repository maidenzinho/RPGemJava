package ui;

import java.awt.*;
import javax.swing.*;
import jogo.Game;

public class GameUI extends JFrame {
    private Game game;
    private JTextArea output;
    private JTextField input;

    public GameUI() {
        super("RPG - Aventura Épica");
        this.game = new Game();
        configurarJanela();
    }

    private void configurarJanela() {
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        output = new JTextArea();
        output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.CENTER);

        input = new JTextField();
        add(input, BorderLayout.SOUTH);

        input.addActionListener(e -> processarComando(input.getText()));
        mostrarMensagem("Bem-vindo ao RPG!\nDigite: guerreiro <nome> ou mago <nome>");
        setVisible(true);
    }

    private void processarComando(String comando) {
        try {
            if (game.getJogador() == null) {
                String[] partes = comando.split(" ");
                game.iniciarJogo(partes[1], partes[0]);
                mostrarMensagem("Bem-vindo, " + game.getJogador().getNome() + "!");
            } else {
                mostrarMensagem("Comando recebido: " + comando);
            }
        } catch (Exception e) {
            mostrarMensagem("Erro: " + e.getMessage());
        }
        input.setText("");
    }

    private void mostrarMensagem(String msg) {
        output.append(msg + "\n");
    }
}
