package com.rpg;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Character {
    private boolean up, down, left, right, attacking;
    private String direction = "down";
    private World world;
    private BufferedImage img;

    public Player(int x, int y, World world) {
        super(x, y, 32, 32, 100, 3, null); // Atributos do player
        this.world = world;

        // Carregando a imagem do player
        try {
            img = ImageIO.read(new File("assets/sprites/player/player_walk0.png"));
        } catch (IOException e) {
            img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        }
    }

    @Override
    public void update() {
        int dx = 0, dy = 0;
        if (up)    { dy -= speed; direction = "up"; }
        if (down)  { dy += speed; direction = "down"; }
        if (left)  { dx -= speed; direction = "left"; }
        if (right) { dx += speed; direction = "right"; }

        // Verifica colisão com o mundo
        if (!world.isBlocked(x + dx, y + dy, width, height)) {
            x += dx;
            y += dy;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            die();
        }
    }

    public void die() {
        System.out.println("Player morreu!");
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public World getWorld() {
        return world;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = true; direction = "up"; break;
            case KeyEvent.VK_S: down = true; direction = "down"; break;
            case KeyEvent.VK_A: left = true; direction = "left"; break;
            case KeyEvent.VK_D: right = true; direction = "right"; break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = false; break;
            case KeyEvent.VK_S: down = false; break;
            case KeyEvent.VK_A: left = false; break;
            case KeyEvent.VK_D: right = false; break;
        }
    }

    // Adicionado para salvar/carregar vida
    public void setHealth(int h) { this.health = h; }
}