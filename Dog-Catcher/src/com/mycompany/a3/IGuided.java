package com.mycompany.a3;

/*
 * Some game objects are guided, meaning that they provide an
 * interface that allows other objects to tell them to
 * move left, right, up, down or to jump to a location of a
 * randomly selected dog or cat.
 */
public interface IGuided {
	/*
	 * Hence, the interface must declare six separate methods 
	 * (i.e., moveLeft, moveRight, moveUp, moveDown, jumpToDog,
	 * and jumpToCat).
	 */
	void moveLeft(int left);
	void moveRight(int right);
	void moveUp(int top);
	void moveDown(int bottom);
	void jumpToDog(float x, float y);
	void jumpToCat(float x, float y);
}
