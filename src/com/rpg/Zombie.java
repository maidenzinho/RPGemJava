// filepath: h:\Projetos\RPGemJava\src\com\rpg\Zombie.java
package com.rpg;

import java.awt.*;

public class Zombie extends Character {
    private Player target;
    private int attackCooldown = 0;

    public Zombie(int x, int y, Player target, Animation anim) {
        super(x, y, 32, 32, 30, 2, anim);
        this.target = target;
    }

    @Override
    public void update() {
        if (isDead()) return;

        // Cálculo da distância entre o zumbi e o player
        int dx = target.x - x;
        int dy = target.y - y;
        double dist = Math.hypot(dx, dy);  // Distância euclidiana entre o zumbi e o jogador

        if (dist > 0) {
            // Movendo o zumbi em direção ao player
            x += (int) Math.round(speed * dx / dist);
            y += (int) Math.round(speed * dy / dist);
        }

        anim.update();

        // Verificar se o zumbi pode atacar o player
        if (attackCooldown == 0 && getBounds().intersects(target.getBounds())) {
            target.takeDamage(10);  // O zumbi aplica dano ao player
            attackCooldown = 30;  // Cooldown entre ataques
        }
        if (attackCooldown > 0) attackCooldown--;  // Diminuir o tempo do cooldown
    }

    @Override
    public void draw(Graphics g) {
        if (!isDead()) {
            // Desenhando o zumbi com a animação
            g.drawImage(anim.getCurrentFrame(), x, y, width, height, null);
        }
    }

    // Método para retornar o retângulo de colisão do zumbi
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Adicionado para salvar/carregar vida
    public void setHealth(int h) { this.health = h; }
}