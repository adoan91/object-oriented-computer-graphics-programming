package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

/* 
 * GameWorld holds a collection of game objects and
 * other state variables, and a set of methods to accept
 * and execute user commands.
*/
public class GameWorld extends Observable {
	private BGSound bgSound = new BGSound("bg.wav");
	private int initDog, initCat, totalScore, dogsCaptured, catsCaptured,
				dogsRemaining, catsRemaining, clockTicks;
	private Net net;
	private Dog dog;
	private Cat cat;
	private int removedScratches = 0;
	private boolean sound = false;
	private GameObjectCollection objects;
	private int count = 0;
	private int GW_WIDTH; 
	private int GW_HEIGHT; 
	private int mvabsolx;
	private int mvabsoly;
	private int svgety;
	private Random rand = new Random(); //to generate initial random sizes and locations of animals
	
	public GameWorld() {
		objects = new GameObjectCollection();
	}
	
	public void init(){ //NOTE game starts with sound OFF
		initDog = 3;
		initCat = 2;
		totalScore = 0;
		dogsCaptured = 0; 
		catsCaptured = 0; 
		dogsRemaining = 0; 
		catsRemaining = 0;
		/*
		 * All objects of the same class have the same initial color
		 * (chosen by you), assigned when the object is created (e.g,
		 * cats could be yellow, dogs could be brown).
		 */
		net = new Net(rand.nextInt(GW_WIDTH - (2 * 100)) + mvabsolx + 100, rand.nextInt(GW_HEIGHT - mvabsoly - svgety  - (2 * 100)) + mvabsoly + svgety + 100); //net size is 100
		objects.add(net);
		createDog();
		createCat();
		setChanged();
	
		
		
		
	}
	
	//deprecated
	/*public void createNet() {
		net = new Net(rand.nextInt(GW_WIDTH), rand.nextInt(GW_HEIGHT));
		net.setSize(100); //set to 100 when the object is created
		net.setLocation(rand.nextInt(GW_WIDTH), rand.nextInt(GW_HEIGHT));
		net.setColor(ColorUtil.BLACK);
		objects.add(net);
	}	*/
	
	public void createDog() {
		for (int i = 0; i < initDog; i++) {
			dog = new Dog();
			dog.setSize(rand.nextInt(60-40) + 40); //assignment 3 says to change from 20-50 to 40-60
			dog.setLocation(rand.nextInt(GW_WIDTH - (2 * dog.getSize())) + mvabsolx + dog.getSize(), rand.nextInt(GW_HEIGHT - mvabsoly - svgety - (2 * dog.getSize())) + mvabsoly + svgety + dog.getSize());
			dog.setDirection(rand.nextInt(359)); // 0 - 359
			objects.add(dog);
			dogsRemaining ++;	
		}
	}	
	
	public void createCat() {
		for (int i = 0; i < initCat; i++) {
			cat = new Cat();
			cat.setSize(rand.nextInt(60-40) + 40); //assignment 3 says to change from 20-50 to 40-60
			cat.setLocation(rand.nextInt(GW_WIDTH - (2 * cat.getSize())) + mvabsolx + cat.getSize(), rand.nextInt(GW_HEIGHT - mvabsoly - svgety  - (2 * cat.getSize())) + mvabsoly + svgety + cat.getSize());
			cat.setDirection(rand.nextInt(359));
			objects.add(cat);
			catsRemaining ++;
		}
	}

	/*
	 * The size attribute of the net is constrained to be a 
	 * positive integer between 50 and 1000 (inclusive)
	 */
	/*
	 * expand the size of the net (the center location of the net
	 * shouldn't change).
	 */
	public void expand() { //making the net bigger
		int x = net.getSize();
		if(x < 1000) {
			net.setSize(x + 1);
			System.out.println("Net expanded to " + net.getSize() + ".");
		} else {
			System.out.println("Cannot expand net higher than 1000.");
		}
	}
	
	/*
	 * contract (decrease) the size of the net (the center 
	 * location shouldn't change).
	 */
	public void contract() {
		int x = net.getSize();
		if(x > 50) {
			net.setSize(x - 1);
			System.out.println("Net contracted to " + net.getSize() + ".");
		} else {
			System.out.println("Cannot contract net lower than 50.");
		}
	}

	/*
	 * scoop up all the animals that are in the net. This causes
	 * all of the animals whose centers are within the boundaries
	 * of the bounding square of the net to be removed from the
	 * game world, and the score to be updated according to the
	 * rules of play.
	 */
	public void scoop() {
		Sound scoopSound = new Sound("scoop.wav");
		if(sound){
			scoopSound.play();
		}
		IIterator iterator = objects.getIterator();
		iterator.scoopSetUp();
		Object currObj;
		while(iterator.scoopHasNext()) {
			currObj = iterator.scoopGetNext();
			if(currObj instanceof Cat) {
				if(	(((Cat) currObj).getLocationX() >= net.getLocationX() - (net.getSize() / 2)) &&  
					(((Cat) currObj).getLocationX() <= net.getLocationX() + (net.getSize() / 2)) && 
					(((Cat) currObj).getLocationY() <= net.getLocationY() + (net.getSize() / 2)) && 
					(((Cat) currObj).getLocationY() >= net.getLocationY() - (net.getSize() / 2)) 	) 
				{
					iterator.remove();
					catsCaptured ++;
					catsRemaining --;
					totalScore = totalScore - 10;
					System.out.println("Cat scooped");
				}
			}
			else if(currObj instanceof Dog) {
				if((((Dog) currObj).getLocationX() >= net.getLocationX() - (net.getSize() / 2)) &&  
					(((Dog) currObj).getLocationX() <= net.getLocationX() + (net.getSize() / 2)) && 
					(((Dog) currObj).getLocationY() <= net.getLocationY() + (net.getSize() / 2)) && 
					(((Dog) currObj).getLocationY() >= net.getLocationY() - (net.getSize() / 2)) 	)
				{
					//I don't want to lose the scratch count of a dog i've removed
					if((((Dog) currObj)).getScratch() > 0) { 
						removedScratches += (((Dog) currObj)).getScratch();
					}
					iterator.remove();
					dogsCaptured ++;
					dogsRemaining --;
					totalScore = totalScore + 10 - removedScratches;
					removedScratches = 0;
					System.out.println("Dog scooped");
					if(dogsRemaining == 0){	
						setChanged();
						notifyObservers();
						printPoints();
						Boolean bOk = Dialog.show("YOU WIN!", "Final Score = " + totalScore, "Ok", "DON'T CLICK THIS");
				    	if(bOk) {
				    		Display.getInstance().exitApplication();	
				    	}else{
				    		Display.getInstance().exitApplication();
				    	}
					}
				}
			}
		}
		setChanged();
		notifyObservers();
	}
	
	//guiding the net
	//move the net to the right
	public void moveRight() {
		net.moveRight(mvabsolx + GW_WIDTH);
	}
	
	//move the net to the left
	public void moveLeft() {
		net.moveLeft(mvabsolx);
	}
	
	//move the net up
	public void moveUp() {
		net.moveUp(GW_HEIGHT);
	}
	
	//move the net down
	public void moveDown() {
		net.moveDown(mvabsoly + svgety);
	}
	
	/*
	 * transferring net to a location of a randomly chosen
	 * dog
	 */
	public void jumpToDog() {
		ArrayList<Dog> dogList = new ArrayList<Dog>();
		IIterator iterator = objects.getIterator();

		while(iterator.hasNext()) {
			if(iterator.getNext() instanceof Dog){
				dogList.add((Dog) iterator.objectAt(iterator.getIndex()));
			}
		}
		int temp = rand.nextInt(dogList.size());
		float x = dogList.get(temp).getLocationX();
		float y = dogList.get(temp).getLocationY();
		net.jumpToDog(x, y);
		setChanged();
		notifyObservers();
	}
	
	/*
	 * transfer the net to a location of a randomly selected cat.
	 * If there are no cats, print an error message instead.
	 */
	public void jumpToCat() {
		if(catsRemaining == 0) {
			System.out.println("Error message: No cat to jump to.");
		} else {
			ArrayList<Cat> catList = new ArrayList<Cat>();
			IIterator iterator = objects.getIterator();
	
			while(iterator.hasNext()) {
				if(iterator.getNext() instanceof Cat){
					catList.add((Cat) iterator.objectAt(iterator.getIndex()));
				}
			}
			int temp = rand.nextInt(catList.size());
			
			float x = catList.get(temp).getLocationX();
			float y = catList.get(temp).getLocationY();
			net.jumpToCat(x, y);
			setChanged();
			notifyObservers();
		}
	}
	
	/*
	 * If two cats run into each other, a kitten is produced, and
	 * a new cat object is created in a nearby location to one of
	 * its parents.
	 */
	/*
	 * pretend that a collision occurred between two cats.
	 * if the player specifies the 'k' command, the program first
	 * checks if there are at least two cats. If so, it randomly
	 * picks a cat and produces a kitten in a location that is
	 * close to the chosen cat. If the number of cats in the
	 * world is less than two, print an error message without 
	 * producing a kitten.
	 */
	public void kitten(Cat cat1, Cat cat2) {
			float cat1x = cat1.getLocationX();
			float cat1y = cat1.getLocationY();
			float cat2x = cat2.getLocationX();
			float cat2y = cat2.getLocationY();
			int temp = rand.nextInt(1) + 1;
			cat = new Cat();
			cat.setKitten();
			cat.setSize(rand.nextInt(60 - 40) + 40); //assignment 3 says to change from 20-50 to 40-60			
			if(temp == 1){
				cat.setLocation(cat1x + rand.nextFloat() * 150, cat1y + rand.nextFloat() * 150);
			}
			else if(temp == 2){
				cat.setLocation(cat2x + rand.nextFloat() * 150, cat2y + rand.nextFloat() * 150);
			}
			cat.setDirection(rand.nextInt(359));
			objects.add(cat);
			catsRemaining ++;
			System.out.println("A kitten has been born nearby one of it's parents!");
			setChanged();
			notifyObservers();	
	}
	
	/*
	 * If a cat and a dog run into each other, the cat scratches
	 * the dog. This causes the dog's scratch attribute to be
	 * incremented, and it's color to change (such as becoming
	 * more red). It also causes the dog's speed to reduce by 1.
	 * The cat's speed would not change. A dog with 5 scratches
	 * stops moving.
	 */
	/*
	 * pretend that a fight occurred between a cat and a dog (a 
	 * cat and a dog run into each other). The program randomly
	 * picks a dog and scratches it once, changes its color, and
	 * reduces its speed by 1. If there are no cats, print an 
	 * error message instead.
	 */
	public void fight() {
		if(catsRemaining == 0){
			System.out.println("Error message: No cats availible to fight a dog.");
		} else {
			ArrayList<Dog> dogList = new ArrayList<Dog>();
			IIterator iterator = objects.getIterator();

			while(iterator.hasNext()) {
				if(iterator.getNext() instanceof Dog){
					dogList.add((Dog) iterator.objectAt(iterator.getIndex()));
				}
			}
			int temp = rand.nextInt(dogList.size());
			
			if(((Dog) dogList.get(temp)).getScratch() < 5) { 
				//int redder = ColorUtil.red(dogList.get(temp).getColor()); 
				//																		gets redder by 1
				dogList.get(temp).setColor(ColorUtil.rgb(ColorUtil.red(dogList.get(temp).getColor()) + 1, 
						ColorUtil.green(dogList.get(temp).getColor()), ColorUtil.blue(dogList.get(temp).getColor())));
				//int speed = ((Dog) dogList.get(temp)).getSpeed();
				((Dog) dogList.get(temp)).setSpeed(((Dog) dogList.get(temp)).getSpeed() - 1); // decrement speed
				((Dog) dogList.get(temp)).setScratch(((Dog) dogList.get(temp)).getScratch() + 1); // increment scratch count
				System.out.println("A fight has occurred the (random) dog now has " + ((Dog) dogList.get(temp)).getScratch() + " scratches.");
			} else {
				System.out.println("A fight has occurred but the (random) dog that was scratched already had 5 scratches so it remains immobile.");
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/*
	 * tell the game world that the "game clock" has ticked. All
	 * moving objects are told to update their positions
	 * according to their current direction and speed.
	 */
	public void tick() {
		clockTicks = clockTicks + 1;
		IIterator iterator = objects.getIterator();
		Object currObj;
		//mapview boundaries
		int top = mvabsoly + svgety;
		int left = mvabsolx;
		int right = mvabsolx + GW_WIDTH;
		int bottom = /*mvabsoly - svgety*/ + GW_HEIGHT;
		
		while(iterator.hasNext()) {
			currObj = iterator.getNext();
			if(currObj instanceof Animals){
				((Animals) currObj).move(top, left, right, bottom, 20);
			}
		}
		//check if moving caused any collisions
		iterator = objects.getIterator();
		while(iterator.hasNext()){
			ICollider curObj = (ICollider)iterator.getNext(); //get a collidable object
			//check if this object collides with any OTHER object
			IIterator iter2 = objects.getIterator();
			while(iter2.hasNext()){
				ICollider otherObj = (ICollider) iter2.getNext(); //get a collidable object and check for collision
				if(otherObj != curObj){// make sure it's not the SAME object
					if(curObj.collidesWith(otherObj)){	
						if(curObj instanceof Cat && otherObj instanceof Cat){ //if two cats collide it will cause two collisions
							count++;
							if(count == 2){ //so when count is 2 handleCollision
								curObj.handleCollision(otherObj, this);
								count = 0;
							}		
						}
						else if(curObj instanceof Dog && otherObj instanceof Cat){
							curObj.handleCollision(otherObj, this);
						}
					}
				}
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/*
	 * print the points of the game state values: current score,
	 * number of dogs/cats captured, and number of dogs/cats left
	 * in the world. Output should be appropriately labeled in
	 * easily readable format.
	 */
	public void printPoints() {	
		System.out.println("Current score: " + totalScore);
		System.out.println("Number of Dogs Captured: " + dogsCaptured);
		System.out.println("Number of Cats Captured: " + catsCaptured);
		System.out.println("Number of Dogs: " + dogsRemaining);
		System.out.println("Number of Cats: " + catsRemaining);
		System.out.println("Total clock ticks: " + clockTicks);
	}
	
	/*
	 * print a map showing the current world state.
	 */
	public void printMap() {
		IIterator iterator = objects.getIterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.getNext().toString());
		}
	}
	
	/*
	 * quit, by calling the method System.exit(0) to terminate
	 * the program. Your program should confirm the user's intent
	 * to quit before actually exiting.
	 */
	/*public void quit() {
		Display.getInstance().exitApplication();	
	}*/
	
	public int getTotalScore() {
		return totalScore;
	}

	public void toggleSound() {		
		if(sound){
			sound = false;
			bgSound.pause(); //no background sound if sound is off
		} else {
			sound = true;
			bgSound.play(); //background sound only when sound is on
		}
		setChanged();
		notifyObservers();
	}
	
	public boolean checkSound() {	
		return sound;
	}

	public int getDogsCaptured() {
		return dogsCaptured;
	}

	public int getCatsCaptured() {
		return catsCaptured;
	}

	public int getDogsRemaining() {
		return dogsRemaining;
	}

	public int getCatsRemaining() {
		return catsRemaining;
	}
	
	public void setGameWorldSize(int mvwidth, int mvheight, int mvabsx, int mvabsy, int svy){
		GW_WIDTH = mvwidth;
		GW_HEIGHT = mvheight;
		mvabsolx = mvabsx;
		mvabsoly = mvabsy;
		svgety = svy;
	}

	public GameObjectCollection getGameObjectCollection() {
		return objects;
	}
	
	public void heal() {
		IIterator iterator = objects.getIterator();
		while(iterator.hasNext()){
			Object curObj = iterator.getNext();
			if(curObj instanceof Dog && ((Dog)curObj).isSelected()){
				((Dog) curObj).setSelected(false);
				((Dog) curObj).setScratch(0); //cured of scratches
				((Dog) curObj).setSpeed(4); //revert speed
				((Dog) curObj).setColor(ColorUtil.rgb(165, 42, 42)); //revert to brown
				setChanged();
				notifyObservers();
			}
		}
	}
}
