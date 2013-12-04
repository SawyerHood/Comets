
/*
 * By Sawyer Hood
 * 11/20/2013
 * First Example Using LibGDX
 * Collision2d.java
 */

package com.sawyerhood.Comets.Util;

public class Collision2D {
	
	public static float collide(float v1, float v2, float m1, float m2)
	{
		return (v1*(m1-m2) + 2 * m2 * v2) / (m1 + m2);
	}

}
