package com.severgames.dino;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGdxGame extends Game {

	public static int W;
	public static int H;


	@Override
	public void dispose() {

	}

	@Override
	public void create() {
		W= Gdx.graphics.getWidth();
		H= Gdx.graphics.getHeight();
		setScreen(new Menu(this));
	}



}
