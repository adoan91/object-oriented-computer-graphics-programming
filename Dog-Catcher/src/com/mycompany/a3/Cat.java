package com.mycompany.a3;


import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Cat extends Animals implements IDrawable, ICollider{
	private ArrayList<ICollider> List = new ArrayList<ICollider>(); //recent collision
	private boolean kitten;
	private int count = 0; 
	private int kount = 0; 

	public Cat() {
		super.setColor(ColorUtil.YELLOW);
		super.setSpeed(4); //changed from 5 to 4
	}

	public void setSize(int size) {
		if(size > 60 || size < 40) {
			System.out.println("Cat size must be between 40 and 60");
		} else {
			super.setSize(size);
		}
	}
	
	@Override
	public void setColor(int color) {
		
	}
	
	@Override
	public void setSpeed(int speed) {
		
	}
	
	public String toString() {
		return ("Cat: loc = " + getLocationX() + ", " + getLocationY() +
				" color: " + "[" + ColorUtil.red(getColor()) + ","
				+ ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" + " size = " + getSize() + 
				" speed = " + getSpeed() + 
				" dir = " + getDirection());
	}

	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt){ //cats are isosceles triangles
		g.setColor(getColor());
		
		Point top = new Point(pCmpRelPrnt.getX() + (int)getLocationX(),  		//size indicates the length
				pCmpRelPrnt.getY() + (int)getLocationY() + (getSize() / 2)); 	//of the base of the isosceles triangle
		Point bottomLeft = new Point(pCmpRelPrnt.getX() + (int)getLocationX() - (getSize() / 2), 
				pCmpRelPrnt.getY() + (int)getLocationY() - (getSize() / 2));
		Point bottomRight = new Point(pCmpRelPrnt.getX() + (int)getLocationX() + (getSize() / 2), 
				pCmpRelPrnt.getY() + (int)getLocationY() - (getSize() / 2));
		int [] xPts = new int [] {top.getX(), bottomLeft.getX(), bottomRight.getX()};
		int [] yPts = new int [] {top.getY(), bottomLeft.getY(), bottomRight.getY()};

		g.fillPolygon(xPts, yPts, 3);
	}
	
	
	@Override
	public boolean collidesWith(ICollider otherObject){
		
		boolean result = false;
        float thisCenterX = this.getLocationX() + (getSize() / 2); // find centers
        float thisCenterY = this.getLocationY() + (getSize() / 2);
        float otherCenterX = ((GameObject) otherObject).getLocationX() + (((GameObject) otherObject).getSize() / 2);
        float otherCenterY = ((GameObject) otherObject).getLocationY() + (((GameObject) otherObject).getSize() / 2);
        // find dist between centers (use square, to avoid taking roots)
        float dx = thisCenterX - otherCenterX;
        float dy = thisCenterY - otherCenterY;
        float distBetweenCentersSqr = (dx * dx + dy * dy);
        // find square of sum of radii
        int thisRadius = getSize() / 2;
        int otherRadius = ((GameObject) otherObject).getSize() / 2;
        int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
        if (distBetweenCentersSqr <= radiiSqr) {
            result = true;
        }
        return result;
	}
	
	
	@Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
		Sound catCollisionSound = new Sound("meow.wav");
		
		if(otherObject instanceof Cat){
			if(gw.getCatsRemaining() < 30){
				if(kitten == true || ((Cat)otherObject).kitten == true){
					
				}else{
					if((List.contains(otherObject)) == false){
						List.clear();
						gw.kitten(this, (Cat)otherObject);
						List.add(otherObject);
						if(gw.checkSound()){ //when two cats collide and produce a kitten (meow)
							catCollisionSound.play();
						}
					}else{
						count++;
						if(count == 50){ 
			    			count = 0;
			    			List.clear();
			    		}
					}
				}
			}
		}
    }
	
	public void kittenGrow(){
		kount++;
		if (kount > 1000){ //20 seconds to grow into a normal cat
			kitten = false;
			kount = 0;
		}
	}
	public boolean checkKitten(){
		return kitten;
	}
	
	public void setKitten() {
		kitten = true;
	}
    
}
