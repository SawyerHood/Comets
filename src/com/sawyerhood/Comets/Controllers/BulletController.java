

/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * BulletController.java
 * 
 * 
 * This class is responsible for handling when bullets run out of time and
 * handles removing them from the world.
 */


package com.sawyerhood.Comets.Controllers;

import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import java.util.Iterator;

import com.sawyerhood.Comets.Model.*;
import com.sawyerhood.Comets.Screens.GameScreen;



public class BulletController extends AbstractController {

	private ArrayList<SpaceObject> toDelete;
	private ArrayList<SpaceObject> objects;

	public BulletController(GameScreen screen, ArrayList<SpaceObject> objects) {
		super(screen);
		this.objects = objects;
		toDelete = new ArrayList<SpaceObject>();
	}

	@Override
	public void update(float delta) {
		for(SpaceObject s : objects)
		{
			
			if(s instanceof Bullet)
			{
				((Bullet)s).updateLifeTime(delta);
				if(!((Bullet)s).isAlive()) //The bullet has been alive for too long.
				{
					markForDelete(s);//Remove it.
					
					
				}
			}
		}
		deleteDestroyed(); //Clears out the bullets that have been destroyed.
		

	}
	
	private void markForDelete(SpaceObject s)
	{
		toDelete.add(s);
	}
	
	//The purpose of this is to make sure that their are no concurrent access exceptions.
	private void deleteDestroyed()
	{
		
		for(SpaceObject s : toDelete)
		{
			objects.remove(s);
		}
		
		toDelete.clear();
		
	}

}
