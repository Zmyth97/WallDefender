package com.desitum.wallDefender.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.wallDefender.objects.game.map.Map;
import com.desitum.wallDefender.screens.GameScreen;

/**
 * Created by Zmyth97 on 4/3/2015.
 */
public class GameRenderer {

    private SpriteBatch gameBatch;
    private OrthographicCamera gameCam;
    private GameWorld world;
    private Map map;
    private boolean setup;

    public GameRenderer (GameWorld world, SpriteBatch batch){
        this.world = world;
        this.gameBatch = batch;
        map = new Map();

        gameCam = new OrthographicCamera(GameScreen.FRUSTUM_WIDTH, GameScreen.FRUSTUM_HEIGHT);
        gameCam.position.set(GameScreen.FRUSTUM_WIDTH / 2, GameScreen.FRUSTUM_HEIGHT / 2, 0);
    }

    public void draw(){
        gameCam.position.set(GameScreen.FRUSTUM_WIDTH / 2, GameScreen.FRUSTUM_HEIGHT / 2, 0);
        gameCam.update();
        gameBatch.setProjectionMatrix(gameCam.combined);

        map.drawMap(gameBatch);
        map.drawCastle(gameBatch);
        map.drawDecor(gameBatch);

    }

    public OrthographicCamera getCam(){
        return gameCam;
    }

    public void resetCam(){
        gameCam.position.set(GameScreen.FRUSTUM_WIDTH/2, GameScreen.FRUSTUM_HEIGHT/2, 0);
    }
}
