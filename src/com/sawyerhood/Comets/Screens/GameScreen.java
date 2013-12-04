/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * GameScreen.java
 * 
 * This is the screen that handles the gameplay. It functions as the Master as well as the view.
 */

package com.sawyerhood.Comets.Screens;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.sawyerhood.Comets.CometsGame;
import com.sawyerhood.Comets.Controllers.AbstractController;
import com.sawyerhood.Comets.Controllers.BulletController;
import com.sawyerhood.Comets.Controllers.CollisionController;
import com.sawyerhood.Comets.Controllers.ShipController;
import com.sawyerhood.Comets.Controllers.SpaceObjectController;
import com.sawyerhood.Comets.Model.Bullet;
import com.sawyerhood.Comets.Model.Comet;
import com.sawyerhood.Comets.Model.SpaceObject;
import com.sawyerhood.Comets.Model.Ship;

public class GameScreen extends AbstractScreen {
	
	
	private ArrayList<AbstractController> controllers; // Holds all of the controllers.
	private ArrayList<SpaceObject> objects; //Holds all of the objects on the screen.
	private boolean gameOver = false;
	private BitmapFont font;
	private int score; 
	private int lives = 3; //Starting lives.
	private int cometsAlive = 0;
	private int wave = 0;
	private final int cometOffset = 2;
	private int rrange = 100;
	private float angle = 0;
	
	
	public GameScreen(CometsGame game) {
		
		
		super(game);
		
		
		Ship ship = new Ship(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		objects = new ArrayList<SpaceObject>();
		objects.add(ship); //Add out player;
		
		spawnRandomComets();
		
		controllers = new ArrayList<AbstractController>();
		controllers.add(new SpaceObjectController(this, objects));
		controllers.add(new ShipController(this, ship,this.objects, Keys.W, Keys.A, Keys.D, Keys.SPACE, Keys.S));
		//controllers.add(new ShipController(this, ship2, Keys.W, Keys.A, Keys.D));
		controllers.add(new CollisionController(this, objects));
		controllers.add(new BulletController(this, objects));
		
		
		font = new BitmapFont();
		score = 0;
	}
	
	@Override 
	public void render(float delta)
	{
		
		update(delta); //Update all of the objects.
		
		//Begin drawing objects.
		super.render(delta); 
		s.begin(ShapeType.Line);
		s.setColor(new Color(Color.BLACK));
		renderObjects();
		renderUI();
		s.end();
		
		if(isGameOver())
		{
			this.dispose();
			game.setScreen(new GameOverScreen(game, score));
		}
	}
	
	//Draw all of the text on the screen.
	private void renderUI() {
		batch.begin();
		TextBounds bounds = font.getBounds("Score: " + score);
		font.draw(batch, "Score: " + score, bounds.width, 2*bounds.height);
		bounds = font.getBounds("Livese: " + lives);
		font.draw(batch, "Lives: " + lives , bounds.width, Gdx.graphics.getHeight() - 2*bounds.height);
		batch.end();
		
	}

	//Update all of the controllers
	public void update(float delta)
	{
		for(AbstractController c: controllers)
			c.update(delta);
		if(lives < 0)
			gameOver = true;
		
		//spawn comets if there are none on the screen.
		if(cometsAlive <= 0)
		{
			spawnRandomComets();
			lives+=1;
		}
			
	}
	
	public void renderObjects()
	{
		for(SpaceObject g: objects)
		{
			if(g instanceof Comet)
			{
				s.setColor(Color.BLUE);
				s.circle(g.getX(), g.getY(), ((Comet) g).getRadius());
			}
			if(g instanceof Ship)
			{
				drawShip((Ship)g);
			}
			if(g instanceof Bullet)
			{
				drawBullet((Bullet)g);
			}
		}
	}
	
	public void drawShip(Ship ship)
	{
		if(ship.isAlive())
		{
			float verticies[] = new float[8];
			verticies[0] = ship.getX()+25*(float)Math.cos(Math.toRadians(ship.getAngle()));
			verticies[1] = ship.getY()+25*(float)Math.sin(Math.toRadians(ship.getAngle())); 
			verticies[2] = ship.getX()+15*(float)Math.cos(Math.toRadians(120+ship.getAngle()));
			verticies[3] = ship.getY()+15*(float)Math.sin(Math.toRadians(120+ship.getAngle())); 
			verticies[4] = ship.getX()+10*(float)Math.cos(Math.toRadians(180+ship.getAngle()));
			verticies[5] = ship.getY()+10*(float)Math.sin(Math.toRadians(180+ship.getAngle())); 
			verticies[6] = ship.getX()+15*(float)Math.cos(Math.toRadians(-120+ship.getAngle()));
			verticies[7] = ship.getY()+15*(float)Math.sin(Math.toRadians(-120+ship.getAngle()));
		
			s.setColor(ship.getColor());
			s.triangle(verticies[0],verticies[1],verticies[2],verticies[3],verticies[6],verticies[7]);
		}
		
	}
	
	public void spawnRandomComets()
	{
		Random r = new Random();
		Vector2 startingPos[] = new Vector2[4];
		startingPos[0] = new Vector2(0,0);
		startingPos[1] = new Vector2(0,Gdx.graphics.getHeight());
		startingPos[2] = new Vector2(Gdx.graphics.getWidth(),0);
		startingPos[3] = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		for(int i = 0; i < cometOffset + wave; i++)
		{
			Vector2 v = startingPos[r.nextInt(4)];
			float x = v.x;
			float y = v.y;
			float xVel = -100 + r.nextFloat() * 200;
			float yVel = -100 + r.nextFloat() * 200;
			float radius = 0 + r.nextFloat() * rrange;
			objects.add(new Comet(x,y,xVel,yVel,radius));
		}
		cometsAlive += cometOffset + wave;
		wave += 1;
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		font.dispose();
	}
	
	public void drawBullet(Bullet b)
	{
		s.setColor(Color.YELLOW);
		s.circle(b.getX(), b.getY(), b.getRadius());
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void addScore(int toAdd)
	{
		score += toAdd;
	}

	public void looseLife() {
		lives -= 1;
		
	}
	
	public void addComets(int toAdd)
	{
		cometsAlive += toAdd;
	}
	
	public void removeComets(int toRemove)
	{
		cometsAlive -= toRemove;
	}
	
	public void setAngle(float angle)
	{
		this.angle = angle;
	}

	
	
	


	
	

}
