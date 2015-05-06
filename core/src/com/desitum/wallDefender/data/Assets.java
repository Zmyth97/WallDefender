package com.desitum.wallDefender.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Zmyth97 on 4/3/2015.
 */
public class Assets {
    //Button Textures
    public static Texture playButtonUp;
    public static Texture playButtonDown;
    public static Texture connectButtonUp;
    public static Texture connectButtonDown;
    public static Texture settingsButtonUp;
    public static Texture settingsButtonDown;
    public static Texture cancelButtonUp;
    public static Texture cancelButtonDown;
    public static Texture okButtonUp;
    public static Texture okButtonDown;
    public static Texture saveButtonUp;
    public static Texture saveButtonDown;
    public static Texture exitButtonUp;
    public static Texture exitButtonDown;

    //Menu Textures
    public static Texture textCursor;
    public static Texture textFieldBackground;
    public static Texture menuBackground;

    //Tile Textures
    public static Texture grassTile;
    public static Texture cornerWall;
    public static Texture wall;
    public static Texture stoneTile;
    public static Texture rock;
    public static Texture treeTall;
    public static Texture treeBush;
    public static Texture treeShort;


    //Misc Textures
    public static BitmapFont textFieldFont;

    public static void loadMenuButtons(){
        playButtonUp = new Texture("menu/play_button_up.png");
        playButtonDown = new Texture("menu/play_button_down.png");
        settingsButtonUp = new Texture("menu/settings_button_up.png");
        settingsButtonDown = new Texture("menu/settings_button_down.png");
        cancelButtonUp = new Texture("menu/cancel_button_up.png");
        cancelButtonDown = new Texture("menu/cancel_button_down.png");
        okButtonUp = new Texture("menu/ok_button_up.png");
        okButtonDown = new Texture("menu/ok_button_down.png");
        saveButtonUp = new Texture("menu/save_button_up.png");
        saveButtonDown = new Texture("menu/save_button_down.png");
        exitButtonUp = new Texture("menu/exit_button_up.png");
        exitButtonDown = new Texture("menu/exit_button_down.png");

        textCursor = new Texture("menu/textFieldBar.png");
        textFieldBackground = new Texture("menu/textFieldBackground.png");
        menuBackground = new Texture("menu/menu_bg.png");

        textFieldFont = new BitmapFont(Gdx.files.internal("font/cartoon.fnt"), Gdx.files.internal("font/cartoon.png"), false);
        textFieldFont.setScale(0.15f);
        textFieldFont.setColor(Color.BLACK);
    }

    public static void loadGameTextures(){
        grassTile = new Texture("game/map/grassTile.png");
        cornerWall = new Texture("game/map/cornerTile.png");
        wall = new Texture("game/map/wallTile.png");
        stoneTile = new Texture("game/map/stoneTile.png");
        rock = new Texture("game/map/rock.png");
        treeTall = new Texture("game/map/treeTall.png");
        treeBush = new Texture("game/map/treeBush.png");
        treeShort = new Texture("game/map/treeShort.png");
    }


    public static void dispose(){
        playButtonUp.dispose();
        playButtonDown.dispose();
        connectButtonUp.dispose();
        connectButtonDown.dispose();
        settingsButtonUp.dispose();
        settingsButtonDown.dispose();
        //Lots to add to this.....
    }
}
