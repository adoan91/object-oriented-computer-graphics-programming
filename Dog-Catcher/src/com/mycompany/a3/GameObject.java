package com.mycompany.a3;

import java.util.Vector;


/*
 * There are two kinds of abstract game objects: "animals" and 
 * "catchers".
 * The game objects have attributes (fields) and behaviors 
 * (methods).
 */
public abstract class GameObject {
	private Vector<Float> location;
	/*
	 * All game objects have a color, defined by an int value 
	 * (use static rgb() methods of CN1's ColorUtil to generate
	 * colors).
	 */
	private int color;
	/*
	 * All game objects have an integer attribute size.
	 */
	private int size;
	
	public GameObject() {
		location = new Vector<>(); 
		location.setSize(2);
	}
	
	/*
	 * All game objects provide the ability for external code to 
	 * obtain their color.
	 */
	public int getColor() {
		return color;
	}
	
	/*
	 * By default, game objects provide the ability to have their
	 * color changed, unless it is explicitly stated that a 
	 * certain type of game object has a color which cannot be
	 * changed once it is created.
	 * The color of a dog may change during the game.
	 * The color of a cat never changes.
	 * The color of a net never changes.
	 */
	public void setColor(int color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}
	
	/*
	 * By default, game objects provide the ability to have their
	 * size changed, unless it is explicitly stated that a 
	 * certain type of game object has a size which cannot be 
	 * changed once it is created.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/*
	 * All game objects provide the ability for external code to
	 * obtain their location
	 */
	public float getLocationX() {
		return location.get(0);
	}
	
	public float getLocationY() {
		return location.get(1);
	}
	
	//The point (X,Y) is the center of the object.
	public void setLocation(float x, float y) { //change their location
		x = (float) (Math.round(x * 10) / 10.0);
		y = (float) (Math.round(y * 10) / 10.0);
		location.set(0, x);						
		location.set(1, y);
	}
}
