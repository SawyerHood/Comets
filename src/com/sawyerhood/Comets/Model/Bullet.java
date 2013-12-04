

/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * Bullet.java
 * 
 * Fires out of the ship
 */

package com.sawyerhood.Comets.Model;

import com.badlogic.gdx.math.Circle;

public class Bullet extends SpaceObject {

	private float timeAlive;
	public static final float expiration = 3; //How many seconds the bullets should live on the screen.
	private float radius;
	
	public Bullet(float x, float y, float xVel, float yVel) {
		super(x, y, xVel, yVel);
		timeAlive = 0;
		radius = 5;
		
	}
	
	public void updateLifeTime(float delta)
	{
		timeAlive += delta;
	}
	
	public boolean isAlive()
	{
		return timeAlive < expiration;
	}

	public float getRadius() {
		return radius;
	}
	
	public Circle getHitArea()
	{
		return new Circle(this.getX(), this.getY(), radius);
	}

}
