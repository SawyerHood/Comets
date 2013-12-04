
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * CollisionController.java
 * 
 * By far the most important controller. The collision controller 
 * handles all collisions between objects on the screen and handles them
 * accordingly.
 */


package com.sawyerhood.Comets.Controllers;
import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.sawyerhood.Comets.Model.*;
import com.sawyerhood.Comets.Screens.GameScreen;
import com.sawyerhood.Comets.Util.Collision2D;


public class CollisionController extends AbstractController {

	private ArrayList<SpaceObject> objects;
	private ArrayList<SpaceObject> toDelete;
	
	public CollisionController(GameScreen screen, ArrayList<SpaceObject> objects) {
		super(screen);
		this.objects = objects;
		this.toDelete = new ArrayList<SpaceObject>();
	}

	@Override
	public void update(float delta) {
		
		
		for(int i = 0; i < objects.size(); i++)
			for(int j = i + 1; j < objects.size(); j++)
			{
				
				//The objects are overlapping. Then check for the appropriate situation.
				if(Intersector.overlaps(objects.get(i).getHitArea(), objects.get(j).getHitArea()))
				{
					if(objects.get(i) instanceof Comet && objects.get(j) instanceof Comet)
						cometHandler((Comet) objects.get(i), (Comet) objects.get(j));
					if(objects.get(i) instanceof Comet && objects.get(j) instanceof Bullet)
						cometStruck((Bullet)objects.get(j), (Comet)objects.get(i));
					if(objects.get(j) instanceof Comet && objects.get(i) instanceof Bullet)
						cometStruck((Bullet)objects.get(i), (Comet)objects.get(j));
					if(objects.get(i) instanceof Ship && objects.get(j) instanceof Comet)
						shipStruck((Ship)objects.get(i), (Comet)objects.get(j));
					if(objects.get(j) instanceof Ship && objects.get(i) instanceof Comet)
						shipStruck((Ship)objects.get(j), (Comet)objects.get(i));
					
				}
				
				
				
			}
		
		deleteDestroyed();
		

	}
	
	//Handles is a bullet hits a comet.
	private void cometStruck(Bullet bullet, Comet comet) {
		
			ArrayList<Comet> toAdd = comet.spawnChildren();
			getScreen().addScore(comet.getScore()); //Adds score to the game.
			markForDelete(bullet); //Deletes the bullet.
			markForDelete(comet); //Deletes the comet.
			for(Comet c : toAdd)
			{
				objects.add(c); //Add child comets.
			}
			getScreen().addComets(toAdd.size());
			getScreen().removeComets(1);
		
		
	}
	
	//Ship is hit by the comet.
	private void shipStruck(Ship s, Comet c)
	{
		if(s.isAlive() && !s.isInvincible())
		{
				s.kill();
				getScreen().looseLife();
		}
			
	}
	
	
	//Two comets hit each other.
	private void cometHandler(Comet comet1, Comet comet2)
	{
		
			//We use the formula for perfectly elastic collisions. Physics 1 was useful.
			float vx1f, vy1f, vx2f, vy2f;
			vx1f = Collision2D.collide(comet1.getxVel(), comet2.getxVel(), comet1.getMass(), comet2.getMass());
			vy1f = Collision2D.collide(comet1.getyVel(), comet2.getyVel(), comet1.getMass(), comet2.getMass());
			vx2f = Collision2D.collide(comet2.getxVel(), comet1.getxVel(), comet2.getMass(), comet1.getMass());
			vy2f = Collision2D.collide(comet2.getyVel(), comet1.getyVel(), comet2.getMass(), comet1.getMass());
			
			//Set the new velocities.
			comet1.setxVel(vx1f);
			comet1.setyVel(vy1f);
			comet2.setxVel(vx2f);
			comet2.setyVel(vy2f);
			
			//Find a unit vector between the two colliding comets and move one comet out of the way.
			Vector2 v = new Vector2(comet2.getX(), comet2.getY());
			v = v.sub(comet1.getX(), comet1.getY());
			v = v.nor();
			v = v.scl(comet2.getRadius() + comet1.getRadius());
			comet2.setX(comet1.getX()+v.x);
			comet2.setY(comet1.getY()+v.y);
		
	}
	
	private void markForDelete(SpaceObject s)
	{
		toDelete.add(s);
	}
	
	private void deleteDestroyed()
	{
		
		for(SpaceObject s : toDelete)
		{
			objects.remove(s);
		}
		
		toDelete.clear();
		
	}

}
