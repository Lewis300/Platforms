package com.dataproject.platforms;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.ProababilityTools;

// LEWIS' ROOT : "D:\\LibGDXProjects\\Platforms\\core\\assets"

// LUKA'S ROOT : "C:\\Users\\lukam\\IdeaProjects\\Platforms\\core\\assets"

public class Platforms extends Game
{
	private static Batch sb;
	public static GameScreen gameScreen;

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;

	public final static String ROOT = "C:\\Users\\lukam\\IdeaProjects\\Platforms\\core\\assets";

	public Platforms(int height, int width)
	{
		super();

		SCREEN_HEIGHT = height;
		SCREEN_WIDTH = width;
	}

	@Override
	public void create ()
	{
		MiscTools.loadFonts();
		sb = new SpriteBatch();
		System.out.print(SCREEN_HEIGHT+", "+SCREEN_WIDTH);
//		Gdx.graphics.setDisplayMode(SCREEN_HEIGHT, SCREEN_HEIGHT, true);
		gameScreen = new GameScreen(sb);
		setScreen(gameScreen);

	}

	@Override
	public void render()
	{
		super.render();
	}

	@Override
	public void dispose()
	{
		gameScreen.dispose();
		sb.dispose();
	}

}
