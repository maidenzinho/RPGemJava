package com.rpg;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int current, delay, elapsed;

    public Animation(BufferedImage[] frames, int delay) {
        this.frames = frames;
        this.delay = delay;
        this.current = 0;
        this.elapsed = 0;
    }

    public void update() {
        elapsed += 16; // Atualiza a cada 16ms (aproximadamente 60 FPS)
        if (elapsed >= delay) {
            current = (current + 1) % frames.length;
            elapsed = 0;
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[current];
    }

    public void resetFrame() {
        current = 0;
        elapsed = 0;
    }
}
