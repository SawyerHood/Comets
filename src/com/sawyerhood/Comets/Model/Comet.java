
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * Comet.java
 * 
 * The main 'enemy' of the game.
 */

package com.sawyerhood.Comets.Model;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Circle;

public class Comet extends SpaceObject {
	
	private float radius;
	private static final float mpa = (float) .5; //The mass per area of the comet. Used for collisions. 
	

	public Comet(float x, float y, float xVel, float yVel) {
		this(x, y, xVel, yVel, 30);
	}
	
	public Comet(float x, float y, float xVel, float yVel, float radius)
	{
		super(x, y, xVel, yVel);
		this.radius = radius;
		
	}
	
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public float getMass()
	{
		return (float) (mpa * Math.PI * Math.pow(radius, 1));
	}
	
	//Spawns children depending on the size of the comet.
	public ArrayList<Comet> spawnChildren()
	{
		Random r = new Random();
		ArrayList<Comet> children = new ArrayList<Comet>();
		int childNum = 0;
		if(radius >= 70)
			childNum = 4;
		else if(radius >= 40)
			childNum = 3;
		else if(childNum >= 10)
			childNum = 2;
		
		for(int i = 0; i < childNum; i++)
		{
			children.add(new Comet(this.getX(), this.getY(), r.nextFloat() *100, r.nextFloat()*100, radius/2));
		}
		
		return children;
		
	}
	
	public Circle getHitArea()
	{
		return new Circle(getX(), getY(), getRadius());
	}
	
	//Formula for how many points the comet is worth.
	public int getScore()
	{
		return (int) (Math.pow(200-radius, 1.5));
		
	}

}
