package com.rpg;

import java.awt.*;

public class Resource extends GameObject {
    private ResourceType type;
    private Animation anim;

    public Resource(int x, int y, ResourceType type, Animation anim) {
        super(x, y, 32, 32);
        this.type = type;
        this.anim = anim;
    }

    @Override
    public void update() { anim.update(); }

    @Override
    public void draw(Graphics g) {
        g.drawImage(anim.getCurrentFrame(), x, y, width, height, null);
    }

    public ResourceType getType() { return type; }
}
