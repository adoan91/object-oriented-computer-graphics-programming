package com.mycompany.a3;

/*
 * Some game objects are moving, meaning that they provide an
 * interface that allows other objects to tell them to move.
 */
public interface IMoving {
	
	/*
	 * Telling a moving object to move() causes the object to
	 * update its location.
	 */
	void move(int width, int mvabsx, int height, int mvabsy, int milliseconds);

}
