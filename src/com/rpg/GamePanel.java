package com.rpg;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    public static final int WIDTH = 1024, HEIGHT = 768;
    private Timer timer;
    private World world;
    private Player player;
    private long startTime;
    private long elapsed;
    private boolean gameOver = false;

    // Menu
    private boolean showMenu = true;
    private JButton btnNovo, btnContinuar;

    // Pausa
    private boolean paused = false;
    private JButton btnPause, btnSalvar, btnRetomar;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        this.world = new World("data/world.csv");
        this.player = new Player(world.getPlayerStartX(), world.getPlayerStartY(), world);

        this.timer = new Timer(16, this);

        // Menu de início
        btnNovo = new JButton("Novo Jogo");
        btnContinuar = new JButton("Continuar");
        btnNovo.setBounds(WIDTH/2-100, HEIGHT/2-60, 200, 40);
        btnContinuar.setBounds(WIDTH/2-100, HEIGHT/2, 200, 40);

        setLayout(null);
        add(btnNovo);
        add(btnContinuar);

        btnNovo.addActionListener(e -> {
            remove(btnNovo); remove(btnContinuar);
            showMenu = false;
            this.player = new Player(world.getPlayerStartX(), world.getPlayerStartY(), world);
            world.spawnZombies(player);
            gameOver = false;
            timer.start();
            requestFocusInWindow();
            startTime = System.currentTimeMillis();
        });

        btnContinuar.addActionListener(e -> {
            remove(btnNovo); remove(btnContinuar);
            showMenu = false;
            this.player = new Player(world.getPlayerStartX(), world.getPlayerStartY(), world);
            world.loadGame(player);
            gameOver = false;
            timer.start();
            requestFocusInWindow();
            startTime = System.currentTimeMillis();
        });

        // Botão de pausa
        btnPause = new JButton("Pausar");
        btnPause.setBounds(WIDTH - 120, 20, 100, 32);
        add(btnPause);
        btnPause.setVisible(false);

        btnPause.addActionListener(e -> {
            if (!paused && !gameOver && !showMenu) {
                paused = true;
                timer.stop();
                btnSalvar.setVisible(true);
                btnRetomar.setVisible(true);
            }
        });

        // Botão de salvar durante pausa
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(WIDTH/2-110, HEIGHT/2, 100, 40);
        add(btnSalvar);
        btnSalvar.setVisible(false);

        btnSalvar.addActionListener(e -> {
            world.saveGame(player);
            JOptionPane.showMessageDialog(this, "Jogo salvo!");
        });

        // Botão de retomar
        btnRetomar = new JButton("Retomar");
        btnRetomar.setBounds(WIDTH/2+10, HEIGHT/2, 100, 40);
        add(btnRetomar);
        btnRetomar.setVisible(false);

        btnRetomar.addActionListener(e -> {
            paused = false;
            btnSalvar.setVisible(false);
            btnRetomar.setVisible(false);
            timer.start();
            requestFocusInWindow();
        });
    }

    public void startGame() {
        if (!showMenu) {
            startTime = System.currentTimeMillis();
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !showMenu && !paused) {
            player.update();
            world.updateZombies();
            elapsed = System.currentTimeMillis() - startTime;
            if (player.isDead()) {
                gameOver = true;
                timer.stop();
                world.saveBestTime(elapsed);
                world.saveGame(player);
            }
        }
        repaint();
    }

    private long getBestTime() {
        long best = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("data/save.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    long t = Long.parseLong(line.trim());
                    if (t > best) best = t;
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException ignored) {}
        return best;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        btnPause.setVisible(!showMenu && !gameOver);

        if (showMenu) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Vampire Survivor Mini", WIDTH/2-250, HEIGHT/2-100);
            return;
        }

        world.draw(g);
        player.draw(g);

        g.setColor(Color.RED);
        g.fillRect(20, 20, player.getHealth() * 2, 16);
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, player.getMaxHealth() * 2, 16);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Tempo: " + (elapsed / 1000.0) + "s", 20, 60);

        if (paused) {
            g.setColor(new Color(0,0,0,180));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("PAUSADO", WIDTH/2-120, HEIGHT/2-60);
        }

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 42));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", WIDTH / 2 - 120, HEIGHT / 2 - 40);
            g.setFont(new Font("Arial", Font.PLAIN, 28));
            g.setColor(Color.WHITE);
            g.drawString("Sobreviveu por: " + (elapsed / 1000.0) + " segundos", WIDTH / 2 - 160, HEIGHT / 2);

            long best = getBestTime();
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.setColor(Color.YELLOW);
            g.drawString("Melhor tempo: " + (best / 1000.0) + " segundos", WIDTH / 2 - 120, HEIGHT / 2 + 40);
        }
    }

    @Override public void keyPressed(KeyEvent e) { if (!showMenu && !paused) player.keyPressed(e); }
    @Override public void keyReleased(KeyEvent e) { if (!showMenu && !paused) player.keyReleased(e); }
    @Override public void keyTyped(KeyEvent e) { }
}