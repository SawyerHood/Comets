/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * Ship.java
 * 
 * This is the player controlled ship.
 */



package com.sawyerhood.Comets.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;


public class Ship extends SpaceObject {
	
	
	private Color color;
	private float acc;
	private float turnVelocity;
	private float angle;
	private static final float bulletSpeed = 400;
	private float deathTimer;
	private boolean alive;
	private boolean invincible;
	private float invincibleTimer;
	

	
	public Ship(float x, float y)
	{
		this(x,y,0, 0, 270,0);
		
	}
	
	public Ship(float x, float y, float xVel, float yVel, float turnVelocity, float angle)
	{
		super(x,y,xVel, yVel);
		this.color = Color.RED;
		this.acc = 400;
		this.turnVelocity = turnVelocity;
		this.angle = angle;
		this.alive = true;
		this.deathTimer = (float) 3.0;
		this.invincible = true;
		this.invincibleTimer = (float) 3.0;
		
		
	}

	public Color getColor() {
		return color;
	}
	
	public void toggleColor()
	{
		if(getColor() == Color.GREEN)
			setColor(Color.RED);
		else
			setColor(Color.GREEN);
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void accelerate(float delta)
	{
		acc(delta, acc);
	}
	
	public void deccelerate(float delta)
	{
		acc(delta, -acc);
	}
	
	public void forward(float delta)
	{
		this.setY(this.getY() + 200*delta);
	}
	
	public void backwards(float delta)
	{
		this.setY(this.getY() - 200*delta);
	}
	
	public void left(float delta)
	{
		this.setX(this.getX() - 200*delta);
	}
	
	public void right(float delta)
	{
		this.setX(this.getX() + 200*delta);
	}
	private void acc(float delta, float rate)
	{
		this.setxVel(rate*delta*(float)Math.cos(Math.toRadians(this.getAngle())) + this.getxVel());
		this.setyVel(rate*delta*(float)Math.sin(Math.toRadians(this.getAngle())) + this.getyVel());
		if(this.getxVel() > 200)
		{
			this.setxVel(200);
		}
		if(this.getxVel() < -200)
		{
			this.setxVel(-200);
		}
		if(this.getyVel() > 200)
		{
			this.setyVel(200);
		}
		if(this.getyVel() < -200)
		{
			this.setyVel(-200);
		}
	}
	
	
	
	public void turnLeft(float delta)
	{
		this.setAngle(this.getAngle()+delta*this.getTurnVelocity());
	}
	
	public void turnRight(float delta)
	{
		this.setAngle(this.getAngle()-delta*this.getTurnVelocity());
	}
	
	public float getTurnVelocity() {
		return turnVelocity;
	}


	public void setTurnVelocity(float turnVelocity) {
		this.turnVelocity = turnVelocity;
	}


	public float getAngle() {
		return angle;
	}


	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Bullet fire() {
		return new Bullet(this.getX(), this.getY(), bulletSpeed*(float)Math.cos(Math.toRadians(this.getAngle())), bulletSpeed*(float)Math.sin(Math.toRadians(this.getAngle())));
	}
	
	public Circle getHitArea()
	{
		return new Circle(getX(), getY(), 20);
	}
	
	//Ticks the timer if the ship is dead and resurrects it if the time is up.
	public void tickDeathTimer(float delta)
	{
		if(!isAlive())
		{
			deathTimer -= delta;
			if (deathTimer < 0)
			{
				alive = true;
				this.setX(Gdx.graphics.getWidth()/2);
				this.setY(Gdx.graphics.getHeight()/2);
				this.setAngle(90);
				this.setxVel(0);
				this.setyVel(0);
				this.invincible = true;
				this.invincibleTimer = (float) 3.0;
				toggleColor();
			}
		}
	}
	
	//The ship is invincible for a short time after respawn, this keeps track of that.
	public void tickInvincibleTimer(float delta)
	{
		if(invincible)
		{
			invincibleTimer-= delta;
			if(invincibleTimer < 0)
			{
				invincible = false;
				toggleColor();
			}
		}
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public boolean isInvincible()
	{
		
		return invincible;
	}
	
	public void setInvincible(boolean b)
	{
		invincible = b;
	}
	
	public void kill()
	{
		alive = false;
		deathTimer = (float) 3.0;
	}
	
	public void slowDown(float delta)
	{
		if(this.getxVel() != 0)
			this.setxVel(this.getxVel() - this.getxVel() * .3f * delta);
		if(this.getyVel() != 0)
			this.setyVel(this.getyVel() - this.getyVel() * .3f * delta);
	}


	
	

}
