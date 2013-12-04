
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * SpaceObjectController.java
 * 
 * This controller effects all space objects in the game. It is responsible for moving them
 * across the screen.
 */


package com.sawyerhood.Comets.Controllers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.sawyerhood.Comets.Model.SpaceObject;
import com.sawyerhood.Comets.Screens.GameScreen;

public class SpaceObjectController extends AbstractController {

	private ArrayList<SpaceObject> objects;
	
	
	public SpaceObjectController(GameScreen screen, ArrayList<SpaceObject> objects )
	{
		super(screen);
		this.objects = objects;
		
	}
	
	@Override
	public void update(float delta) {
		for(SpaceObject m : objects)
			move(m, delta);
	}	
	
	//Move the objects and wrap around if out of bounds.
	private void move(SpaceObject g, float delta)
	{
		g.setX(g.getX() + delta*g.getxVel());
		g.setY(g.getY() + delta*g.getyVel());
		float y = Gdx.graphics.getHeight() + 10;
		float x = Gdx.graphics.getWidth() + 10;
		
		if(g.getX() < -10)
			g.setX(x);
		if(g.getY() < -10)
			g.setY(y);
		if(g.getY() > y)
			g.setY(0);
		if(g.getX() > x)
			g.setX(0);
	}

}
