package com.desitum.wallDefender;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.desitum.wallDefender.screens.MenuScreen;
import com.desitum.wallDefender.screens.SplashScreen;

public class WallDefender extends Game {

	@Override
	public void create () {

		Screen splashScreen = new SplashScreen(this);
		this.setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	public void exitScreen(){
		Screen MenuScreen = new MenuScreen(this);
		this.setScreen(MenuScreen);
	}

}
