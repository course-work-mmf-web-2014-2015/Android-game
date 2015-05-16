package com.bsu.mmf.web.course_work.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bsu.mmf.web.course_work.OurGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Ice Age: Squirrel";
		config.width = 400;
        config.height = 710;
		new LwjglApplication(new OurGame(), config);
	}
}
