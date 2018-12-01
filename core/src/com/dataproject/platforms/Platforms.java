package com.dataproject.platforms;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platforms extends Game
{
	SpriteBatch sb;
	Screen gameScreen;

	public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	
	@Override
	public void create ()
	{
		sb = new SpriteBatch();
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
