package com.severgames.dino;



import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {



	@Override
	public void dispose() {

	}

	@Override
	public void create() {
		setScreen(new loadScreen(this));
	}



}
