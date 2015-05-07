package com.desitum.wallDefender.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.wallDefender.WallDefender;
import com.desitum.wallDefender.data.Assets;
import com.desitum.wallDefender.data.Settings;
import com.desitum.wallDefender.libraries.animation.MovementAnimator;
import com.desitum.wallDefender.libraries.interpolation.Interpolation;
import com.desitum.wallDefender.objects.menu.GUI.PopupButton;
import com.desitum.wallDefender.objects.menu.GUI.PopupButtonListener;
import com.desitum.wallDefender.objects.menu.GUI.PopupMenu;
import com.desitum.wallDefender.objects.menu.GUI.PopupSlider;
import com.desitum.wallDefender.objects.menu.GUI.PopupSliderListener;
import com.desitum.wallDefender.world.MenuInterface;
import com.desitum.wallDefender.world.MenuRenderer;
import com.desitum.wallDefender.world.MenuWorld;

/**
 * Created by Zmyth97 on 4/3/2015.
 */
public class MenuScreen implements Screen, MenuInterface {

    public static String PLAY = "play";
    public static String SETTINGS = "settings";

    public static final float FRUSTUM_WIDTH = 150;
    public static final float FRUSTUM_HEIGHT = 100;

    public int state;

    public static final int MAIN_MENU = 0;
    public static final int SETTINGS_MENU = 1;

    private WallDefender wallDefender;

    private Viewport viewport;
    private OrthographicCamera cam;

    private MenuWorld menuWorld;
    private MenuRenderer menuRenderer;

    private Vector3 touchPoint;
    private float lastClick;

    SpriteBatch batch;

    private PopupMenu popupMenu;

    private MovementAnimator myAnimator = new MovementAnimator(15, 5, 2, Interpolation.LINEAR_INTERPOLATOR);

    public MenuScreen (WallDefender sl){
        wallDefender = sl;

        batch = new SpriteBatch();

        menuWorld = new MenuWorld(this);
        menuRenderer = new MenuRenderer(batch, menuWorld);

        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH/2, FRUSTUM_HEIGHT/2, 0);
        viewport = new FitViewport(FRUSTUM_WIDTH, FRUSTUM_HEIGHT, cam);

        touchPoint = new Vector3(0, 0, 0);

        lastClick = 0;

        popupMenu = new PopupMenu(Assets.textFieldBackground, 10, -130, 130, 80);
        MovementAnimator yAnimator = new MovementAnimator(-130, 10, 1, Interpolation.DECELERATE_INTERPOLATOR);
        yAnimator.setControllingY(true);
        popupMenu.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(10, -130, 1, Interpolation.ANTICIPATE_INTERPOLATOR);
        yAnimator2.setControllingY(true);
        popupMenu.addOutgoingAnimator(yAnimator2);

        PopupButton cancelButton = new PopupButton(Assets.cancelButtonUp, Assets.cancelButtonDown, 5, 5, 57.5f, 15);
        cancelButton.setButtonListener(new PopupButtonListener() {
            @Override
            public void onClick() {
                popupMenu.moveOut();
                state = MAIN_MENU;

            }
        });
        popupMenu.addPopupWidget(cancelButton);

        final PopupSlider volumeSlider = new PopupSlider(Assets.textFieldBackground, Assets.textCursor, 5, 60, 120, 5, 3, 10);
        volumeSlider.setSliderListener(new PopupSliderListener() {
            @Override
            public void onChange(float pos) {
                System.out.println(pos);
            }
        });
        popupMenu.addPopupWidget(volumeSlider);

        PopupButton okButton = new PopupButton(Assets.okButtonUp, Assets.okButtonDown, 67.5f, 5, 57.5f, 15);
        okButton.setButtonListener(new PopupButtonListener() {
            @Override
            public void onClick() {
                Settings.setVolume(volumeSlider.getPosition());
                popupMenu.moveOut();
                state = MAIN_MENU;

            }
        });
        popupMenu.addPopupWidget(okButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateInput();
        update(delta);
        draw();
    }

    private void updateInput(){
        if (lastClick > 0){
            return;
        }
        if (Gdx.input.isTouched()) {
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            lastClick = 0.2f;
        }

        if (state == SETTINGS_MENU) {
            popupMenu.updateTouchInput(touchPoint, Gdx.input.isTouched());
        } else if (state == MAIN_MENU){
            menuWorld.updateTouchInput(touchPoint, Gdx.input.isTouched());
        }
    }

    private void update(float delta){
        menuWorld.update(delta);
        popupMenu.update(delta);
        lastClick -= delta;
    }

    private void draw(){
        Gdx.gl.glClearColor(0, 0, .196f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(Assets.menuBackground, 0, 0, this.FRUSTUM_WIDTH, this.FRUSTUM_HEIGHT);
        menuRenderer.draw();
        popupMenu.draw(batch);
        batch.end();

    }

    @Override
    public void playGame() {
        //GameScreen myGameScreen = new GameScreen(wallDefender);
        wallDefender.setScreen(new GameScreen(wallDefender));
    }

    @Override
    public void settings() {
        popupMenu.moveIn();
        state = SETTINGS_MENU;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
