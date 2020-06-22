package com.severgames.dino;



import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	public static MyGdxGame myGdxGame;
	private static Frame frame;
	private static Menu menu;



	@Override
	public void dispose() {

	}

	@Override
	public void create() {
		myGdxGame=this;
		BackgroundManager bg = new BackgroundManager();
		bg.load();
		frame=new Frame(bg);
		menu = new Menu();
		setScreen(new loadScreen());
	}


	public void setFrame() {
		setScreen(frame);
	}

	public void setMenu(){
		setScreen(menu);
	}
}
