package com.mycompany.a3;

import java.util.Random;



/*
 * two concrete kinds of animals: dogs and cats
 * All animals are moving and they all move the same way.
 */
public abstract class Animals extends GameObject implements IMoving, ICollider {
										/*
										 * All animals have integer attributes speed and direction,
										 * which are used to define how they move through the world
										 * when told to do so.
										 */
	private int speed;
										/*
										 * The direction of dogs and cats indicates heading specified
										 * by a compass angle in degrees: 0 means heading north 
										 * (upwards in the world), 90 means heading east (rightward
										 * in the world), etc.
										 */
	private int direction;
	private Random rand = new Random();

	public Animals() {

	}
	
	@Override
	public void setSize(int size) {
		super.setSize(size);
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	/*
	 * add (or subtract) small random values (e.g., 5 degrees) to
	 * their direction while they move so as to not run in a 
	 * straight line. If an animal's center hits a side of the
	 * world, it changes direction and does not run out of bounds
	 */
	public void move(int top, int left, int right, int bottom, int elapsedTime) {	
		if(this instanceof Cat){
			if(((Cat) this).checkKitten()){
				((Cat) this).kittenGrow();
			}
		}
		//rate = getSpeed()
		//time = elapsedMilliSecs / 1000; adjusted 1000 to 15 so its not super slow
		//deltaX = cos(Theta) * (rate * time)
		//deltaY = sin(Theta) * (rate * time)
		float deltaX = (float) Math.cos(90 - getDirection()) * getSpeed() * ((float) elapsedTime / 15);
		float deltaY = (float) Math.sin(90 - getDirection()) * getSpeed() * ((float) elapsedTime / 15);
		float newLocationX = (getLocationX() + deltaX);
		float newLocationY = (getLocationY() + deltaY);
		
		if(		newLocationX < right - (getSize() / 2) 
				&& newLocationX > left + (getSize() / 2)
				&& newLocationY < bottom - (getSize() / 2)
				&& newLocationY > top/* - (getSize() / 2)*/
		){
			setLocation(newLocationX, newLocationY);
		} else {
			setDirection(rand.nextInt(360));
		}
	}
}
