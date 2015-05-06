package com.desitum.wallDefender.world;

import com.badlogic.gdx.math.Vector3;
import com.desitum.wallDefender.data.Assets;
import com.desitum.wallDefender.libraries.CollisionDetection;
import com.desitum.wallDefender.libraries.animation.MovementAnimator;
import com.desitum.wallDefender.libraries.interpolation.Interpolation;
import com.desitum.wallDefender.objects.menu.MenuButton;
import com.desitum.wallDefender.objects.menu.MenuButtonOnClickListener;

import java.util.ArrayList;


/**
 * Created by Zmyth97 on 4/3/2015.
 */
public class MenuWorld {

    private ArrayList<MenuButton> buttons;

    private MenuInterface menuInterface;

    private static final int PLAY = 0;
    private static final int SETTINGS = 1;

    private MenuButton playButton;
    private MenuButton settingsButton;


    public MenuWorld(MenuInterface mi){
        menuInterface = mi;

        buttons = new ArrayList<MenuButton>();

        //Create the Menu Buttons
        playButton = new MenuButton(Assets.playButtonUp, Assets.playButtonDown, PLAY, 10, 0, 50, 20);
        settingsButton = new MenuButton(Assets.settingsButtonUp, Assets.settingsButtonDown, SETTINGS, 10, 0, 50, 20);

        //Animate/Add Play Button
        playButton.addAnimator(new MovementAnimator(playButton, -playButton.getHeight(), 60, 0.8f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        playButton.startAllAnimators();
        buttons.add(playButton);

        //Animate/Add Settings Button
        settingsButton.addAnimator(new MovementAnimator(settingsButton, -settingsButton.getHeight(), 20, 0.8f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        settingsButton.startAllAnimators();
        buttons.add(settingsButton);

        setupOnClickListeners();
    }

    public void update(float delta){
        for (MenuButton menuButton: buttons){
            menuButton.update(delta);
        }
    }

    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        for (MenuButton button : buttons) {
            boolean clickInArea = CollisionDetection.pointInRectangle(button.getBoundingRectangle(), touchPos);
            if (clickInArea && clickDown) {
                button.onClickDown();
            } else if (clickInArea) {
                button.onClickUp(true);
            } else {
                button.onClickUp(false);
            }
        }
    }

    private void setupOnClickListeners(){
        playButton.setOnClickListener(new MenuButtonOnClickListener() {
            @Override
            public void onClick() {
                menuInterface.playGame();
            }
        });
        settingsButton.setOnClickListener(new MenuButtonOnClickListener() {
            @Override
            public void onClick() {
                menuInterface.settings();
            }
        });
    }

    public ArrayList<MenuButton> getMenuButtons(){
        return this.buttons;
    }
}
