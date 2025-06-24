package com.rpg;

public abstract class Character extends GameObject {
    protected int health, maxHealth, speed;
    protected Animation anim;
    public Character(int x, int y, int width, int height, int maxHealth, int speed, Animation anim) {
        super(x, y, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.speed = speed;
        this.anim = anim;
    }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isDead() { return health <= 0; }
    public void takeDamage(int dmg) { health -= dmg; if (health < 0) health = 0; }
}
