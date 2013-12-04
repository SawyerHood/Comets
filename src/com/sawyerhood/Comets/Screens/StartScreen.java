
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * StartScreen.java
 * 
 * This is the entry screen of the game. Simply displays the title, author, and what to do.
 */


package com.sawyerhood.Comets.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.sawyerhood.Comets.CometsGame;

public class StartScreen extends AbstractScreen {

	private BitmapFont title;
	private BitmapFont author;
	private BitmapFont instruction;
	private float alpha = 0;
	
	private Color color = new Color(1,1,1,0);
	
	public StartScreen(CometsGame game) {
		super(game);
		title = new BitmapFont();
		author = new BitmapFont();
		instruction = new BitmapFont();
		
		title.setScale(5);
		author.setScale(2);
		instruction.setScale((float) 1.5);
		instruction.setColor(color);
	}
	
	
	public void render(float delta)
	{
		super.render(delta);
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		batch.begin();
		TextBounds titleBounds = title.getBounds("Comets!!!");
		TextBounds authorBounds = author.getBounds("By Sawyer Hood.");
		TextBounds instructionBounds = instruction.getBounds("Press any key to continue...");
		title.draw(batch, "Comets!!!", (width-titleBounds.width)/2, 2*(height + titleBounds.height)/3);
		author.draw(batch, "By Sawyer Hood", (width-authorBounds.width)/2, (height + authorBounds.height)/2);
	
		color.a += delta/2;
		color.a %= 1;
		
		instruction.setColor(color);
		instruction.draw(batch, "Press any key to continue...", (width-instructionBounds.width)/2, 3*instructionBounds.height);
		batch.end();	
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
		{
			
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
		
		
	}
	
	
	@Override
	public void dispose()
	{
		super.dispose();
		title.dispose();
		author.dispose();
		instruction.dispose();
	}

}
