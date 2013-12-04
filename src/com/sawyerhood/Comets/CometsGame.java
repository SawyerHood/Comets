
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * CometsGame.java
 * 
 * 
 * This is the main entry point of a libGDX game. It acts as the game state manager.
 * It contains a screen object that represents the current state of the game.
 */


package com.sawyerhood.Comets;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sawyerhood.Comets.Screens.GameScreen;
import com.sawyerhood.Comets.Screens.StartScreen;

public class CometsGame extends Game  {
	
	
	//This is run when a new Game object is instantiated. 
	
	@Override
	public void create() {
	setScreen(new StartScreen(this));
	}

	//Frees memory. In this case we are not loading any resources that need
	//to be freed.
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {	
		super.render();
		
	}
	

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
