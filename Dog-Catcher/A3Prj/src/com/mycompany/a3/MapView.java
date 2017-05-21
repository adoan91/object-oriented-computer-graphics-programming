package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
    private GameObjectCollection goc;
    private Point origin; //upper left corner
    private boolean pauseStatus;

	MapView() {
		getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLUE));
		origin = new Point(getX(), getY()); 	//relative to parent container's origin
		pauseStatus = false; 					//Game is initially not paused
		setVisible(true);
	}
	
	public void p(boolean pause){ 	//need to know whether Game is paused or not
		pauseStatus = pause;
	}
	
	public void update(Observable o, Object arg) {
		if(o instanceof GameWorld) { 			//contents of the game
			((GameWorld) o).printMap(); 		//displayed in text form on the console
		}
		gw = (GameWorld) o; 					//updated GameWorld
        goc = gw.getGameObjectCollection(); 	//updated GameObjectCollection
        repaint();
	}

	@Override
	public void paint(Graphics g) {
        super.paint(g);
        IIterator iter = goc.getIterator();
        while(iter.hasNext()){ 			//iterate through collection of game objects
            GameObject n = (GameObject) iter.getNext();
            if(n instanceof IDrawable){ 	//draw everything that is drawable
            	((IDrawable) n).draw(g, origin);
            }
        }
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y); 					//where the mouse was clicked
		Point pCmpRelPrnt =  new Point(getX(), getY());
		IIterator iter = goc.getIterator();
		if(pauseStatus == true){ 								//only when the Game is in paused mode
	        while (iter.hasNext()){
	            GameObject n = (GameObject) iter.getNext();
	            if(n instanceof ISelectable){ 					//only ISelectable objects
	            	if(((ISelectable) n).contains(pPtrRelPrnt, pCmpRelPrnt)){
	            		((ISelectable) n).setSelected(true);
	            	} else { 									//iterates through all ISelectables on each click
	            		((ISelectable) n).setSelected(false); 	//so only one object can be selected at a time
	            	} 											//or none will be selected if you click an empty area
	            	repaint(); 							//repaint incase an object is selected to draw selected object correctly
	            }
	        }
		}
	}
	
	public void unpause(){ 						//Game will call this method whenever it goes from paused to play mode
		IIterator iter = goc.getIterator(); 	//so that any selected objects are unselected when resuming the game
        while (iter.hasNext()){
            GameObject n = (GameObject) iter.getNext();
            if(n instanceof ISelectable){
            	((ISelectable) n).setSelected(false);
            	repaint(); 						//revert selected object images to unselected
            }
        }
	}
}
