package com.desitum.wallDefender.objects.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.wallDefender.data.Assets;
import com.desitum.wallDefender.screens.GameScreen;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/5/2015.
 */
public class Map {
    public static int xTiles = 20;
    private int yTiles = (xTiles/3 * 2);

    private float xAmount;
    private float yAmount;

    private int[][] tiles;
    private int[][] walls;
    private int[][] decor;

    private ArrayList<Tile> tileList;
    private ArrayList<Wall> wallList;
    private ArrayList<Decor> decorList;

    public Map(){
        int wallPlacement = xTiles - (xTiles/3);

        xAmount = GameScreen.FRUSTUM_WIDTH/xTiles;
        System.out.println("XAmount: " + xAmount);
        yAmount = GameScreen.FRUSTUM_HEIGHT/yTiles;
        System.out.println("YAmount: " + yAmount);

        tiles = new int[yTiles][xTiles];
        walls = new int[yTiles][xTiles];
        decor = new int[yTiles][xTiles];

        tileList = new ArrayList<Tile>();
        wallList = new ArrayList<Wall>();
        decorList = new ArrayList<Decor>();

        addCastle(wallPlacement);
        addMap(wallPlacement);
        addDecor(wallPlacement);

        fillMap();
        fillCastle();
        fillDecor();
    }

    //3 = wall, 4 = corner
    private void addCastle(int wallPlacement){
        for(int row = 0; row < walls.length; row++){
            for(int col = 0; col < walls[row].length; col++){
                if(col == wallPlacement){
                    if(row == 0 || row == (walls.length - 1)){
                        System.out.println("Made Corner at: " + row + col);
                        walls[row][col] = 4;
                    } else {
                        walls[row][col] = 3;
                    }
                } else {
                    walls[row][col] = 0;
                }
            }
        }
    }

    //1 = Grass, 2 = Stone
    private void addMap(int wallPlacement) {
        for(int row = 0; row < tiles.length; row++){
            for(int col = 0; col < tiles[row].length; col++){
                if(col < wallPlacement){
                    tiles[row][col] = 1;
                } else {
                    tiles[row][col] = 2;
                }
            }
        }
    }


    //5 = tall tree, 6 = short tree, 7 = bush, 8 = rock
    private void addDecor(int wallPlacement) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (col < wallPlacement) {
                    int randomChance = (int)(Math.random() * 12);
                    if(randomChance == 1){
                        int randomDecor = ((int)(Math.random() * 3) + 5); //3 is the amount of decorations, 5 gets it to the right ints for it
                        decor[row][col] = randomDecor;
                    } else {
                        decor[row][col] = 0;
                    }
                } else {
                    decor[row][col] = 0;
                }
            }
        }
    }

    //1 = Grass, 2 = Stone
    private void fillMap(){
        for(int row = 0; row < tiles.length; row++){
            for(int col = 0; col < tiles[row].length; col++){
                if(tiles[row][col] == 1){
                        tileList.add(new Tile(Assets.grassTile, Tile.GRASS_TILE, (col * xAmount), (row * yAmount), xAmount, yAmount));
                } else if(tiles[row][col] == 2){
                        tileList.add(new Tile(Assets.stoneTile, Tile.STONE_TILE, (col * xAmount), (row * yAmount), xAmount, yAmount));
                }
            }
        }
    }

    //3 = wall, 4 = corner
    private void fillCastle(){
        for(int row = walls.length - 1; row > -1; row--){
            for(int col = walls[row].length - 1; col > -1; col--){
                if(walls[row][col] == 3){
                    wallList.add(new Wall(Assets.wall, 3, (col * xAmount), (row * yAmount), xAmount, yAmount));
                } else if(walls[row][col] == 4){
                    wallList.add(new Wall(Assets.cornerWall, 4,  (col * xAmount), (row * yAmount), xAmount, yAmount * 1.5f));
                }
            }
        }
    }

    //5 = tall tree, 6 = short tree, 7 = bush, 8 = rock
    private void fillDecor(){
        for(int row = 0; row < decor.length; row++){
            for(int col = 0; col < decor[row].length; col++){
                if(decor[row][col] == 5){
                    decorList.add(new Decor(Assets.rock, (col * xAmount), (row * yAmount), xAmount, yAmount));
                } else if(decor[row][col] == 6){
                    decorList.add(new Decor(Assets.treeTall, (col * xAmount), (row * yAmount), xAmount, yAmount));
                } else if(decor[row][col] == 7){
                    decorList.add(new Decor(Assets.treeBush, (col * xAmount), (row * yAmount), xAmount, yAmount));
                } else if(decor[row][col] == 8){
                    decorList.add(new Decor(Assets.treeShort, (col * xAmount), (row * yAmount), xAmount, yAmount));
                }
            }
        }
    }


    public void drawMap(SpriteBatch gameBatch){
        for(int pos = tileList.size() - 1; pos >= 0; pos --){
            Tile tile = tileList.get(pos);
            tile.draw(gameBatch);
        }
    }

    public void drawCastle(SpriteBatch gameBatch){
        for(Wall wall: wallList){
            wall.draw(gameBatch);
        }
    }

    public void drawDecor(SpriteBatch gameBatch){
        for(Decor decor: decorList){
            decor.draw(gameBatch);
        }
    }



}
