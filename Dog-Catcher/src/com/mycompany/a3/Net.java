package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Net extends Catchers implements IDrawable, ICollider{
	
	public Net(float width, float height) {
		super.setSize(100);
		super.setLocation(width, height);
		super.setColor(ColorUtil.BLACK);
	}
	
	public void setColor(int color) {
		
	}
	
	@Override
	public String toString() {
		
		return ("Net: loc = " + getLocationX() + 
				", " + getLocationY() + " color: " + "[" + ColorUtil.red(getColor()) + ","
				+ ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" +
				" size = " + getSize());
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(getColor());
        g.drawRect(pCmpRelPrnt.getX() + (int)getLocationX() - getSize() / 2,  	//net is square
        		pCmpRelPrnt.getY() + (int)getLocationY() - getSize() / 2, 
        		getSize(), getSize()); 											//size indicates the length of equal sides of
    } 																			//the square

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
    public void handleCollision(ICollider otherObject,GameWorld gw) {
    	//nothing
    }
}
