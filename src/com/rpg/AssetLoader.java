package com.rpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class AssetLoader {
    public static BufferedImage[] loadFrames(String folderPath) throws IOException {
        File dir = new File(folderPath);
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".png"));
        if (files == null || files.length == 0) {
            throw new IOException("Pasta vazia ou não encontrada: " + folderPath);
        }
        Arrays.sort(files);
        BufferedImage[] frames = new BufferedImage[files.length];
        for (int i = 0; i < files.length; i++) {
            frames[i] = ImageIO.read(files[i]);
        }
        return frames;
    }
    public static BufferedImage loadTile(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
