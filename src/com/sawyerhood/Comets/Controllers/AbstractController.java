
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * AbstractController.java
 * 
 * An abstract controller for our MVC game. The controller is responsible for
 * updating the model.
 */


package com.sawyerhood.Comets.Controllers;

import com.sawyerhood.Comets.Screens.GameScreen;

public abstract class AbstractController {

	private GameScreen screen;
	public AbstractController(GameScreen screen)
	{
		this.screen = screen;
	}
	public GameScreen getScreen() {
		return screen;
	}
	public void setScreen(GameScreen screen) {
		this.screen = screen;
	}
	public abstract void update(float delta);
	
		
	
	
}
