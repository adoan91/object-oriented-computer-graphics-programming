package com.mycompany.a3;

/*
 * one concrete kind of catcher: nets
 * All catchers are guided and they are all guided the same way.
 */
public abstract class Catchers extends GameObject implements IGuided {
	
	public void moveLeft(int left) {
		float x = getLocationX() - 10;
		float y = getLocationY();
		if(x > left){
			setLocation(x, y);
			System.out.println("Moved left 1 unit.");
		} else {
			System.out.println("You've hit the west edge of the world, don't fall off.");
			setLocation(left + 1, y);
		}
	}
	
	public void moveRight(int right) {
		float x = getLocationX() + 10;
		float y = getLocationY();
		if(x < right){
			setLocation(x, y);
			System.out.println("Moved right.");
		} else {
			System.out.println("You've hit the east edge of the world, don't fall off.");
			setLocation(right - 1, y);
		}
	}
	
	public void moveUp(int top) { // actually bottom
		float x = getLocationX();
		float y = getLocationY() + 10; //made this move 10 units instead of 1 like in last assignments because its so slow at 1
		if(y < top/*y > top*/){
			setLocation(x, y);
			System.out.println("Moved up.");
		} else {
			System.out.println("You've hit the north edge of the world, don't fall off.");
			setLocation(x, top - 1/*top + 1*/);
		}

	}
	
	public void moveDown(int bottom) { //actually top
		float x = getLocationX();
		float y = getLocationY() - 10; //made this move 10 units instead of 1 like in last assignments because its so slow at 1
		if(y > bottom/*y < bottom*/){ //x >= 0 && x <= width && y > 0 && y <= height
			setLocation(x, y);
			System.out.println("Moved down.");
		} else {
			System.out.println("You've hit the south edge of the world, don't fall off.");
			setLocation(x, bottom + 1/*bottom - 1*/);
		}
	}
	
	
	public void jumpToDog(float x, float y) {
		setLocation(x, y);
		System.out.println("Net set location to a random dog.");
	}
	
	public void jumpToCat(float x, float y){
		setLocation(x, y);
		System.out.println("Net set location to a random cat.");
	}
	
	

}
