
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * SpaceObject.java
 * 
 * This is the abstract class that all spaceobjects inherit from.
 */



package com.sawyerhood.Comets.Model;

import com.badlogic.gdx.math.Circle;

public abstract class SpaceObject {
	
	private float x;
	private float y;
	private float xVel;
	private float yVel;
	
	
	
	public SpaceObject(float x, float y, float xVel, float yVel)
	{
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}



	public float getxVel() {
		return xVel;
	}


	public void setxVel(float xVel) {
		this.xVel = xVel;
	}


	public float getyVel() {
		return yVel;
	}


	public void setyVel(float yVel) {
		this.yVel = yVel;
	}
	
	public abstract Circle getHitArea(); //Responsible for collision detection.




	
	

}
