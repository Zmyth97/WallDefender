package com.desitum.wallDefender.objects.game.enemies;

import com.desitum.wallDefender.data.Assets;
import com.desitum.wallDefender.objects.game.map.Map;
import com.desitum.wallDefender.screens.MenuScreen;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/6/2015.
 */
public class Wave {

    private int waveNumber;
    private ArrayList<Enemy> enemies;

    private float xAmount = MenuScreen.FRUSTUM_WIDTH/Map.xTiles;
    private float yAmount = MenuScreen.FRUSTUM_HEIGHT/Map.xTiles;

    public Wave(int waveNumber){
        this.waveNumber = waveNumber;
        enemies = new ArrayList<Enemy>();
        createWave(waveNumber);
    }

    private void createWave(int wave){
        if(wave < 3){
            for(int count = 0; count < (wave * 2); count++){
                int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT);

                Enemy enemy = new Enemy(Assets.basicEnemy, Enemy.BASIC, 0, randomY, xAmount, yAmount);
                enemies.add(enemy);
            }
        } else if(wave < 6){
            for(int count = 0; count < wave * 2; count++){
                int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT - Assets.basicEnemy.getHeight());

                Enemy enemy = new Enemy(Assets.basicEnemy, Enemy.BASIC, 0, randomY, xAmount, yAmount);
                enemies.add(enemy);
            }
            for(int count = 0; count < wave; count++){
                int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT - Assets.mediumEnemy.getHeight());

                Enemy enemy = new Enemy(Assets.mediumEnemy, Enemy.MEDIUM, 0, randomY, xAmount, yAmount);
                enemies.add(enemy);
            }
        } else{
            for(int count = 0; count < wave * 2; count++){
                int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT - Assets.basicEnemy.getHeight());

                Enemy enemy = new Enemy(Assets.basicEnemy, Enemy.BASIC, 0, randomY, xAmount, yAmount);
                enemies.add(enemy);
            }
            for(int count = 0; count < wave; count++){
                int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT - Assets.mediumEnemy.getHeight());

                Enemy enemy = new Enemy(Assets.mediumEnemy, Enemy.MEDIUM, 0, randomY, xAmount, yAmount);
                enemies.add(enemy);
            }
            for(int count = 0; count < wave/2; count++){
                int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT - Assets.heavyEnemy.getHeight());

                Enemy enemy = new Enemy(Assets.heavyEnemy, Enemy.HEAVY, 0, randomY, xAmount, yAmount);
                enemies.add(enemy);
            }
        }

        if(wave == 10){
            int randomY = (int)(Math.random() * MenuScreen.FRUSTUM_HEIGHT - Assets.eliteEnemy.getHeight());
            Enemy enemy = new Enemy(Assets.eliteEnemy, Enemy.ELITE, 0, randomY, xAmount, yAmount);
            enemies.add(enemy);
        }
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
}
