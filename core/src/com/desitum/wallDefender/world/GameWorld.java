package com.desitum.wallDefender.world;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.desitum.wallDefender.WallDefender;
import com.desitum.wallDefender.data.Assets;
import com.desitum.wallDefender.libraries.animation.MovementAnimator;
import com.desitum.wallDefender.libraries.interpolation.Interpolation;
import com.desitum.wallDefender.objects.menu.GUI.PopupButton;
import com.desitum.wallDefender.objects.menu.GUI.PopupButtonListener;
import com.desitum.wallDefender.objects.menu.GUI.PopupMenu;
import com.desitum.wallDefender.objects.menu.GUI.PopupSlider;
import com.desitum.wallDefender.screens.GameScreen;

/**
 * Created by Zmyth97 on 4/3/2015.
 */
public class GameWorld implements GameInterface {


    private WallDefender wallDefender;

    private PopupMenu settingsMenu;
    private PopupSlider volumeSlider;

    public static final int RUNNING = 0;
    public static final int PAUSED = 1;

    private int state = RUNNING;

    public GameWorld(WallDefender sl){
        this.wallDefender = sl;

        setupPopupMenu();
    }

    public void update(float delta){
        settingsMenu.update(delta);

    }


    public void updateKeys(int key){
        if (state == RUNNING) {
            switch (key) {
                case Input.Keys.SPACE:
                    //Stuff
                    break;
                case Input.Keys.A:
                    //Stuff
                    break;
                case Input.Keys.D:
                    //Stuff
                    break;
                case Input.Keys.S:
                    //Stuff
                    break;
            }
        }
    }

    public void updateTouch(Vector3 touchPoint, boolean isTouched){
        if (state == PAUSED){
            settingsMenu.updateTouchInput(touchPoint, isTouched);
        }
    }


    private void setupPopupMenu(){
        float POPUP_WIDTH = 50;
        float POPUP_HEIGHT = GameScreen.FRUSTUM_HEIGHT - 20;
        settingsMenu = new PopupMenu(Assets.menuBackground, 50f,
                -POPUP_HEIGHT, POPUP_WIDTH, POPUP_HEIGHT);
        MovementAnimator moveInAnimator1 = new MovementAnimator(-POPUP_HEIGHT, 10, 1, Interpolation.DECELERATE_INTERPOLATOR);
        moveInAnimator1.setControllingY(true);
        MovementAnimator moveOutAnimator1 = new MovementAnimator(10, -POPUP_HEIGHT, 1, Interpolation.ANTICIPATE_INTERPOLATOR);
        moveOutAnimator1.setControllingY(true);
        settingsMenu.addIncomingAnimator(moveInAnimator1);
        settingsMenu.addOutgoingAnimator(moveOutAnimator1);

        volumeSlider = new PopupSlider(Assets.textFieldBackground, Assets.textCursor, 5, POPUP_HEIGHT-15,
                POPUP_WIDTH-10, 5, 3, 10);

        PopupButton cancelButton = new PopupButton(Assets.cancelButtonUp, Assets.cancelButtonDown, 5,
                5, POPUP_WIDTH - 10, (POPUP_WIDTH-10)/3);

        PopupButton saveButton = new PopupButton(Assets.saveButtonUp, Assets.saveButtonDown, 5,
                cancelButton.getY() + cancelButton.getHeight() + 5, POPUP_WIDTH - 10, (POPUP_WIDTH-10)/3);

        PopupButton exitButton = new PopupButton(Assets.exitButtonUp, Assets.exitButtonDown, 5,
                saveButton.getY() + saveButton.getHeight() + 5, POPUP_WIDTH - 10, (POPUP_WIDTH-10)/3);

        settingsMenu.addPopupWidget(volumeSlider);
        settingsMenu.addPopupWidget(cancelButton);
        settingsMenu.addPopupWidget(saveButton);
        settingsMenu.addPopupWidget(exitButton);


        cancelButton.setButtonListener(new PopupButtonListener() {
            @Override
            public void onClick() {
                settingsMenu.moveOut();
                state = RUNNING;
            }
        });

        saveButton.setButtonListener(new PopupButtonListener() {
            @Override
            public void onClick() {
                settingsMenu.moveOut();
                state = RUNNING;
            }
        });

        exitButton.setButtonListener(new PopupButtonListener() {
            @Override
            public void onClick() {
                //Exit Game
            }
        });
    }


    public PopupMenu getSettingsMenu(){
        return settingsMenu;
    }



    public void pauseGame(){
        settingsMenu.moveIn();
        //player.pause();
        //TODO Add Pause Enemies Here!
        state = PAUSED;
    }
}
