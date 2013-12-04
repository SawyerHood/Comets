
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * GameOverScreen.java
 * 
 * Presented when the game is over.
 */

package com.sawyerhood.Comets.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.sawyerhood.Comets.CometsGame;

public class GameOverScreen extends AbstractScreen {

	private float upTime = 0;
	private BitmapFont font;
	private BitmapFont scoreFont;
	private int score;
	
	
	public GameOverScreen(CometsGame game, int score) {
		super(game);
		
		font = new BitmapFont();
		scoreFont = new BitmapFont();
		
		this.score = score;
		font.scale(5);
		scoreFont.scale(3);
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		batch.begin();
		
		TextBounds fontBounds = font.getBounds("Game Over!");
		font.draw(batch, "Game Over!", (Gdx.graphics.getWidth() - fontBounds.width)/2, (Gdx.graphics.getHeight() + fontBounds.height)/2);
		
		TextBounds scoreFontBounds = scoreFont.getBounds("Score: " + score);
		scoreFont.draw(batch, "Score: " + score, (Gdx.graphics.getWidth() - scoreFontBounds.width)/2, (Gdx.graphics.getHeight() - 4*scoreFontBounds.height)/2);
		batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY) && upTime > 3)
		{
			this.dispose();
			game.setScreen(new GameScreen(game));
		}
		
		upTime += delta;
		
	}
	
	//Remove fonts.
	@Override 
	public void dispose()
	{
		font.dispose();
		scoreFont.dispose();
	}

}
