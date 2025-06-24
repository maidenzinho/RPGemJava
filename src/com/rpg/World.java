package com.rpg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class World {
    private String[][] tileMap;
    private List<Zombie> zombies = new ArrayList<>();
    private List<Resource> resources = new ArrayList<>();
    private int cols, rows;
    private BufferedImage grassTile, waterTile;
    private int playerStartX, playerStartY;

    // Para spawn dinâmico de zumbis
    private long lastSpawnTime = System.currentTimeMillis();
    private Player playerRef;

    public World(String csvPath) {
        List<String[]> lines = CSVUtil.readCSV(csvPath);
        rows = lines.size();
        cols = lines.get(0).length;
        tileMap = new String[rows][cols];

        try {
            grassTile = AssetLoader.loadTile("assets/sprites/floor/grass.png");
            waterTile = AssetLoader.loadTile("assets/sprites/floor/water.png");
        } catch (IOException e) {
            grassTile = waterTile = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        }

        for (int r = 0; r < rows; r++) {
            String[] row = lines.get(r);
            for (int c = 0; c < cols; c++) {
                String cell = row[c].trim();
                tileMap[r][c] = cell.isEmpty() ? "GRASS" : cell;

                // Adicionando recursos ao mapa
                if (cell.equals("TREE") || cell.equals("ROCK") || cell.equals("BUSH")) {
                    try {
                        ResourceType type = ResourceType.valueOf(cell);
                        Animation anim = null;
                        switch (type) {
                            case TREE:
                                anim = new Animation(
                                    AssetLoader.loadFrames("assets/sprites/tree"), 300
                                );
                                break;
                            case ROCK:
                                anim = new Animation(
                                    AssetLoader.loadFrames("assets/sprites/rock"), 300
                                );
                                break;
                            case BUSH:
                                anim = new Animation(
                                    AssetLoader.loadFrames("assets/sprites/bush"), 300
                                );
                                break;
                        }
                        resources.add(new Resource(c * 32, r * 32, type, anim));
                    } catch (Exception e) {
                        // Se der erro, adiciona um recurso "vazio"
                        resources.add(new Resource(c * 32, r * 32, ResourceType.valueOf(cell),
                            new Animation(new BufferedImage[]{new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB)}, 300)));
                    }
                }
            }
        }

        // Encontrar uma área de GRASS para o jogador
        playerStartX = 0;
        playerStartY = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (tileMap[r][c].equals("GRASS")) {
                    playerStartX = c * 32;
                    playerStartY = r * 32;
                    break;
                }
            }
            if (playerStartX != 0 && playerStartY != 0) break;
        }
    }

    // Método para spawnar zumbis (inicial e referência para spawn dinâmico)
    public void spawnZombies(Player player) {
        this.playerRef = player;
        zombies.clear();
        try {
            Animation anim = new Animation(
                AssetLoader.loadFrames("assets/sprites/zombie"), 200
            );
            zombies.add(new Zombie(5 * 32, 5 * 32, player, anim));
            zombies.add(new Zombie(10 * 32, 10 * 32, player, anim));
            zombies.add(new Zombie(15 * 32, 15 * 32, player, anim));
        } catch (IOException ignored) { }
    }

    // Atualiza zumbis e faz spawn dinâmico a cada 10 segundos
    public void updateZombies() {
        for (Zombie z : zombies) {
            z.update();
        }
        // Spawna 3 zumbis a cada 10 segundos
        long now = System.currentTimeMillis();
        if (playerRef != null && now - lastSpawnTime >= 10000) {
            try {
                Animation anim = new Animation(
                    AssetLoader.loadFrames("assets/sprites/zombie"), 200
                );
                Random rand = new Random();
                int spawned = 0;
                while (spawned < 3) {
                    // Spawna zumbi em volta do player, mas não em cima dele
                    int px = playerRef.x + (rand.nextInt(7) - 3) * 32;
                    int py = playerRef.y + (rand.nextInt(7) - 3) * 32;
                    // Garante que não spawna em tile bloqueado
                    if (!isBlocked(px, py, 32, 32)) {
                        zombies.add(new Zombie(px, py, playerRef, anim));
                        spawned++;
                    }
                }
            } catch (IOException ignored) { }
            lastSpawnTime = now;
        }
    }

    public void draw(Graphics g) {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                BufferedImage img = tileMap[r][c].equals("WATER") ? waterTile : grassTile;
                g.drawImage(img, c * 32, r * 32, 32, 32, null);
            }
        for (Resource res : resources) res.draw(g);
        for (Zombie z : zombies) z.draw(g);
    }

    public List<Zombie> getZombies() { return zombies; }

    public int getPixelWidth() { return cols * 32; }
    public int getPixelHeight() { return rows * 32; }
    public int getPlayerStartX() { return playerStartX; }
    public int getPlayerStartY() { return playerStartY; }

    public List<Resource> getResources() {
        return resources;
    }

    // Corrigido: checa todos os cantos do retângulo do player
    public boolean isBlocked(int x, int y, int w, int h) {
        int[][] points = {
            {x, y},
            {x + w - 1, y},
            {x, y + h - 1},
            {x + w - 1, y + h - 1}
        };
        for (int[] p : points) {
            int tileX = p[0] / 32;
            int tileY = p[1] / 32;
            if (tileX < 0 || tileY < 0 || tileX >= cols || tileY >= rows)
                return true;
            String tile = tileMap[tileY][tileX];
            if (tile.equals("WATER")) return true;
        }
        return false;
    }

    public void saveBestTime(long time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/save.csv", true))) {
            writer.println(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    // --- SALVAR E CARREGAR O ESTADO DO JOGO ---

    public void saveGame(Player player) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/game_save.csv"))) {
            writer.println("PLAYER," + player.x + "," + player.y + "," + player.getHealth());
            for (Zombie z : zombies) {
                if (!z.isDead())
                    writer.println("ZOMBIE," + z.x + "," + z.y + "," + z.getHealth());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(Player player) {
        zombies.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/game_save.csv"))) {
            String line;
            Animation zombieAnim = new Animation(AssetLoader.loadFrames("assets/sprites/zombie"), 200);
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("PLAYER")) {
                    player.x = Integer.parseInt(parts[1]);
                    player.y = Integer.parseInt(parts[2]);
                    player.setHealth(Integer.parseInt(parts[3]));
                } else if (parts[0].equals("ZOMBIE")) {
                    int zx = Integer.parseInt(parts[1]);
                    int zy = Integer.parseInt(parts[2]);
                    int zhp = Integer.parseInt(parts[3]);
                    Zombie z = new Zombie(zx, zy, player, zombieAnim);
                    z.setHealth(zhp);
                    zombies.add(z);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.playerRef = player;
    }
}