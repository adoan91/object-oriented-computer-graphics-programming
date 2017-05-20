package com.mycompany.a3;

import java.util.ArrayList;
//import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Dog extends Animals implements IDrawable, ISelectable{
	private int scratch; //max changed from 5 to 4
	private ArrayList<ICollider> List = new ArrayList<ICollider>(); //recent collision
	private int count = 0; // touched by same cat counter
	private boolean selected;

	public Dog() {
		super.setSpeed(4); //changed from 5 to 4
		super.setColor(ColorUtil.rgb(165, 42, 42)); //brown
		selected = false;
	}
	
	public void setSize(int size) {
		if(size > 60 || size < 40) {
			System.out.println("Dog size must be between 40 and 60");
		} else {
			super.setSize(size);
		}
	}
	
	public int getScratch() {
		return scratch;
	}
	
	public void setScratch(int scratch) {
		this.scratch = scratch;
	}
	
	public String toString() {
		return ("Dog: loc = " + getLocationX() + ", " + getLocationY() + 
				" color: " + "[" + ColorUtil.red(getColor()) + ","
				+ ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" + " size = " + getSize() + 
				" speed = " + getSpeed() + " dir = " + getDirection() + 
				" scratches = " + getScratch());
	}

	public void draw(Graphics g, Point pCmpRelPrnt){ 	//polymorphic drawing capability
		int xLoc = pCmpRelPrnt.getX() + (int)getLocationX() - getSize() / 2;
		int yLoc = pCmpRelPrnt.getY() + (int)getLocationY() - getSize() / 2;
		if(isSelected()){ 								//unfilled circle when selected
			g.drawArc(xLoc, yLoc, getSize(), getSize(), 0, 360);
		} else { 										//Dogs are circles
			g.setColor(super.getColor()); 				//size indicates the diameter of the circle
        	g.fillArc(xLoc, yLoc, getSize(), getSize(), 0, 360);
		}
    }

    @Override
    public boolean collidesWith(ICollider otherObject) {
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
    public void handleCollision(ICollider otherObject, GameWorld gw){
    	Sound dogCollisionSound = new Sound("woof.wav");
    	if(this.scratch < 4){
	    	if(otherObject instanceof Cat){ //when a cat collides with a dog
	    		if((List.contains(otherObject)) == false){
	    			List.clear();
	    			
			        int speed = getSpeed();
			        speed--;
			        int scratch = getScratch();
			        scratch++;
			        
			        if(speed >= 0 && scratch <= 5) {
			            setSpeed(speed);
			            setScratch(scratch);
			            
			            //rainbow alternative
			            //setColor(ColorUtil.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			            
			            //redder as assignment says
			            setColor(ColorUtil.rgb(ColorUtil.red(getColor()) + 22, ColorUtil.green(getColor()), ColorUtil.blue(getColor())));
			       		List.add(otherObject);
			            if(gw.checkSound()){
			        		dogCollisionSound.play(); //if sound is on play the sound (dog bark)
			        	}
			        }
	    		} else {
		    		count++;
		    		if(count == 50){
		    			count = 0;
		    			List.clear();
		    		}
	    		}
	    	}
    	}
    }

	@Override
	public void setSelected(boolean select) {
		System.out.println(select);
		selected = select;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent's origin
		int xLoc = /*pCmpRelPrnt.getX() + */(int)getLocationX()/* - getSize() / 2*/; //pCmpRelPrnt.getX() + iShapeX; // shape location relative
		int yLoc = /*pCmpRelPrnt.getY() + */(int)getLocationY()/* - getSize() / 2*/; //pCmpRelPrnt.getY() + iShapeY; // to parent's origin
		if ((px >= xLoc - getSize() / 2) && (px <= xLoc + getSize() / 2)
				&& (py <= yLoc + getSize() / 2) && (py >= yLoc - getSize())
				/*&& (py >= yLoc + getSize() / 2) && (py <= yLoc - getSize())*/ ) {
			//System.out.print("TRUE pointer x= " + px + " y= " + py);
			return true;
		} else {
			System.out.print("xLoc = " + xLoc + " yLoc = "+yLoc);
			System.out.print("FALSE pointer x= " + px + " y= " + py);
			return false;
		}
	}
}