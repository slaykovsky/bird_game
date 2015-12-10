package com.slaykovsky.bird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.slaykovsky.bird.BirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bird is a word";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new BirdGame(), config);
	}
}
