package com.dataproject.platforms.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dataproject.platforms.Platforms;

public class DesktopLauncher {
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 571;
		config.width = (int)(500*1.6180339);
//		config.fullscreen = true;
		

		config.addIcon("Players\\Player1Texture.png", Files.FileType.Internal);
		new LwjglApplication(new Platforms(config.height, config.width), config);

	}
}
