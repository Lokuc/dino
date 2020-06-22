package com.severgames.dino.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.severgames.dino.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1200;
		config.height=675;
		config.vSyncEnabled=true;
		config.title="SeverGames";
		config.samples=8;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
