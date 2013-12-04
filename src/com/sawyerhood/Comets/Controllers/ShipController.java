
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * ShipController.java
 * 
 * This handles the control of one ship
 * multiple can be added to make the game multiplayer.
 */


package com.sawyerhood.Comets.Controllers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.sawyerhood.Comets.Model.Bullet;
import com.sawyerhood.Comets.Model.Ship;
import com.sawyerhood.Comets.Model.SpaceObject;
import com.sawyerhood.Comets.Screens.GameScreen;


public class ShipController extends AbstractController {

	private Ship ship;
	private ArrayList<SpaceObject> objects;
	private int forward;
	private int left;
	private int right;
	private int fire;
	private final float coolDownTime = (float) .3;
	private float lastShot;
	private int backwards;
	

	public ShipController(GameScreen screen, Ship ship, ArrayList<SpaceObject> objects, int forward, int turnLeft, int turnRight, int fire, int backwards )
	{
		super(screen);
		this.ship = ship;
		this.forward = forward;
		this.left = turnLeft;
		this.right = turnRight;
		this.fire = fire;
		this.objects = objects;
		this.lastShot = 0;
		this.backwards = backwards;
		
	}
	
	@Override
	public void update(float delta) {
		
		
		ship.tickDeathTimer(delta); //Tick the death timer.
		ship.tickInvincibleTimer(delta); //Tick the invis timer.
		lastShot -= delta; //Remove the time since the last shot.
		if(ship.isAlive())
		{
		
		
		if(Gdx.input.isKeyPressed(left))
		{
			ship.left(delta);
		}
		
		if(Gdx.input.isKeyPressed(right))
		{
			ship.right(delta);
		}
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			if(lastShot <= 0)
			{
				Bullet b = ship.fire();
				objects.add(b);
				lastShot = coolDownTime;
			}
		}
		
		if(Gdx.input.isKeyPressed(forward))
		{
			ship.forward(delta);
		}
		
		else if(Gdx.input.isKeyPressed(backwards))
		{
			ship.backwards(delta);
		}
		//If the ship is not being accelerated, slow it down a tad, like in the original.
		else
		{
			ship.slowDown(delta);
		}
	
		Vector2 v = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
		v = v.sub(ship.getX(), ship.getY());
		v = v.nor();
		ship.setAngle(v.angle());
		screen.setAngle(v.angle());
		
		}
		

	}

}
