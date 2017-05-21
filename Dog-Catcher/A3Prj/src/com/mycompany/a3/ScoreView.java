package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	private Label totalScore, dogsCaptured, catsCaptured, dogsRemaining, catsRemaining, sound;
	
	ScoreView() {
		
		this.setLayout(new FlowLayout(Component.CENTER));
		totalScore = new Label();
		totalScore.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		totalScore.getAllStyles().setPadding(RIGHT, 5);
		this.add(totalScore);
		dogsCaptured = new Label();
		dogsCaptured.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		dogsCaptured.getAllStyles().setPadding(RIGHT, 5);
		this.add(dogsCaptured);
		catsCaptured = new Label();
		catsCaptured.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		catsCaptured.getAllStyles().setPadding(RIGHT, 5);
		this.add(catsCaptured);
		dogsRemaining = new Label();
		dogsRemaining.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		dogsRemaining.getAllStyles().setPadding(RIGHT, 5);
		this.add(dogsRemaining);
		catsRemaining = new Label();
		catsRemaining.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		catsRemaining.getAllStyles().setPadding(RIGHT, 5);
		this.add(catsRemaining);
		sound = new Label();
		sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		sound.getAllStyles().setPadding(RIGHT, 5);
		this.add(sound);
		
		this.setVisible(true);
	}
	
	public void update(Observable o, Object arg) {
		//code here to update labels from game state data in GameWorld (Observable)
		totalScore.setText("Total Score: " + ((GameWorld) o).getTotalScore());
		dogsCaptured.setText("Dogs Captured: " + ((GameWorld) o).getDogsCaptured());
		catsCaptured.setText("Cats Captured: " + ((GameWorld) o).getCatsCaptured());
		dogsRemaining.setText("Dogs Remaining: " + ((GameWorld) o).getDogsRemaining());
		catsRemaining.setText("Cats Remaining: " + ((GameWorld) o).getCatsRemaining());
		
		if(((GameWorld) o).checkSound()) {
			sound.setText	("Sound: ON");
		} else {
			sound.setText	("Sound: OFF");
		}
		repaint();
		setVisible(true);
	}
}
