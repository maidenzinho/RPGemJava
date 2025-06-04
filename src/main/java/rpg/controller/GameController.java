package rpg.controller;

import rpg.model.*;
import rpg.persistence.DataPersistence;
import rpg.exception.GameException;

import java.util.List;
import java.util.Random;

public class GameController {
    private GameData gameData;
    private Random random;

    public GameController(Player player) {
        this.gameData = new GameData(player);
        this.random = new Random();
    }

    public void startBattle() {
        // Implementação da batalha
    }

    public void endBattle(boolean playerWon) {
        if (playerWon) {
            Enemy defeatedEnemy = gameData.getCurrentDungeon().getEnemies().get(0);
            gameData.getPlayer().gainExperience(defeatedEnemy.getExperienceReward());
            
            // Chance de drop de item
            Item loot = gameData.getCurrentDungeon().getRandomLoot();
            if (loot != null) {
                gameData.getPlayer().getInventory().addItem(loot);
            }
        }
    }

    public void moveToNextFloor() {
        gameData.moveToNextFloor();
    }

    public void saveGame() throws GameException {
        DataPersistence.saveGame(gameData);
    }

    public void loadGame() throws GameException {
        this.gameData = DataPersistence.loadGame();
    }

    // Getters
    public GameData getGameData() { return gameData; }
    public Player getPlayer() { return gameData.getPlayer(); }
    public Dungeon getCurrentDungeon() { return gameData.getCurrentDungeon(); }
}