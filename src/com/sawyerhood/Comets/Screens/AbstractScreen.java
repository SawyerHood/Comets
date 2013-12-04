/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * AbstractScreen.java
 * 
 * Abstract screen, is the base for an in game screen.
 */




package com.sawyerhood.Comets.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sawyerhood.Comets.CometsGame;

public abstract class AbstractScreen implements Screen {
	
	protected final CometsGame game; //This is the game.
	protected final SpriteBatch batch; //This is used to render sprites from files, though not used in this game.
	protected final ShapeRenderer s; //Renders shapes.
	
	public AbstractScreen(CometsGame game)
	{
		this.game = game;
		batch = new SpriteBatch();
		s = new ShapeRenderer();
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1); //Paints the screen black.
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		s.dispose();

	}

}
