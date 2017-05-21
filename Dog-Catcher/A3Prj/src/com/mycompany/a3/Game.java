package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

/*
 * Game class manages the flow of control in the game.
 * This class accepts input in the form of keyboard commands from
 * the human player and invokes appropriate methods in the
 * GameWorld class to perform the requested commands - that is, 
 * to manipulate data in the game model.
 * Game class is also responsible for displaying information
 * about the game objects and state variables.
 */
public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer = new UITimer(this);
	private boolean isPause = false;
	private Button bPause = new Button();
	private int ppsflag = 0; //if sound was ON before pausing ppsflag will be 1
	
	//	side menu check box
	private CheckBox soundCheck = new CheckBox();
	
	//	left buttons
	private Button bExpand = new Button();//"Expand"
	private Button bUp = new Button();
	private Button bLeft = new Button();
	private Button bJumpToADog = new Button();
	
	//	right buttons
	private Button bContract = new Button();
	private Button bDown = new Button();
	private Button bRight = new Button();
	private Button bJumpToACat = new Button();
	private Button bScoop = new Button();
	
	//	command
	private ExpandCommand myExpandCommand = new ExpandCommand();
	private ContractCommand myContractCommand = new ContractCommand();
	private ScoopCommand myScoopCommand = new ScoopCommand();
	private RightCommand myRightCommand = new RightCommand();
	private LeftCommand myLeftCommand = new LeftCommand();
	private UpCommand myUpCommand = new UpCommand();
	private DownCommand myDownCommand = new DownCommand();
	private JumpToADogCommand myJumpToADogCommand = new JumpToADogCommand();
	private JumpToACatCommand myJumpToACatCommand = new JumpToACatCommand();
	private ExitCommand myExitCommand = new ExitCommand();
	private SoundCommand mySoundCommand = new SoundCommand();
	private AboutCommand myAboutCommand = new AboutCommand();
	private HelpCommand myHelpCommand = new HelpCommand();
	private PauseCommand myPauseCommand = new PauseCommand();
	
	private Button bHeal = new Button();
	private HealCommand myHealCommand = new HealCommand();
	

	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		timer.schedule(20, true, this); //start UITimer, each 20 milliseconds call run()
		
		
		//KittenCommand myKittenCommand = new KittenCommand();
		//FightCommand myFightCommand = new FightCommand();
		
		HealCommand.setTarget(gw);
		PauseCommand.setTarget(this);

		//	set target
		SoundCommand.setTarget(gw);
		ExpandCommand.setTarget(gw);
		UpCommand.setTarget(gw);
		LeftCommand.setTarget(gw);
		JumpToADogCommand.setTarget(gw);
		ContractCommand.setTarget(gw);
		DownCommand.setTarget(gw);
		RightCommand.setTarget(gw);
		JumpToACatCommand.setTarget(gw);
		ScoopCommand.setTarget(gw);
		//KittenCommand.setTarget(gw);
		//FightCommand.setTarget(gw);
		

		//Button pauseButton;
		
		
		
		//	bottom buttons
		//Button bKitten = new Button();
		//Button bFight = new Button();
		//Button bTick = new Button();
		
		//	side menu check box styles
		//soundCheck.getAllStyles().setBgTransparency(255);
		//soundCheck.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCheck.getUnselectedStyle().setBgTransparency(255); //.getAllStyles()
		soundCheck.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		
		//	left button styles
		bExpand.getUnselectedStyle().setBgTransparency(255);
		bExpand.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bExpand.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bUp.getUnselectedStyle().setBgTransparency(255);
		bUp.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bUp.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bLeft.getUnselectedStyle().setBgTransparency(255);
		bLeft.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bLeft.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bJumpToADog.getUnselectedStyle().setBgTransparency(255);
		bJumpToADog.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bJumpToADog.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		//	right button styles
		bContract.getUnselectedStyle().setBgTransparency(255);
		bContract.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bContract.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bDown.getUnselectedStyle().setBgTransparency(255);
		bDown.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bDown.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bRight.getUnselectedStyle().setBgTransparency(255);
		bRight.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bRight.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bJumpToACat.getUnselectedStyle().setBgTransparency(255);
		bJumpToACat.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bJumpToACat.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bScoop.getUnselectedStyle().setBgTransparency(255);
		bScoop.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bScoop.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		//	bottom button styles
		/*bKitten.getUnselectedStyle().setBgTransparency(255);
		bKitten.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bKitten.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bFight.getUnselectedStyle().setBgTransparency(255);
		bFight.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bFight.getUnselectedStyle().setFgColor(ColorUtil.WHITE);*/
		/*bTick.getUnselectedStyle().setBgTransparency(255);
		bTick.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bTick.getUnselectedStyle().setFgColor(ColorUtil.WHITE);*/
		bHeal.getUnselectedStyle().setBgTransparency(255);
		bHeal.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bHeal.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		bPause.getUnselectedStyle().setBgTransparency(255);
		bPause.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		bPause.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		soundCheck.setCommand(mySoundCommand);
		mySoundCommand.putClientProperty("SideComponent", soundCheck);
		
		//	title bar area and side menu
		Toolbar myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		this.setTitle("Dog Catcher Game");
		myToolbar.addCommandToSideMenu(myScoopCommand);
		myToolbar.addCommandToSideMenu(mySoundCommand);
		myToolbar.addCommandToSideMenu(myAboutCommand);
		myToolbar.addCommandToSideMenu(myExitCommand);
		myToolbar.addCommandToRightBar(myHelpCommand);
		
		this.setLayout(new BorderLayout());
		
		add(BorderLayout.NORTH, sv);

		Container leftControlContainer = new Container(new GridLayout(4, 1));
		
		leftControlContainer.getAllStyles().setPadding(Component.TOP, 100);
		leftControlContainer.getAllStyles().setPadding(Component.BOTTOM, 100);
		leftControlContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLUE));
		
		//	set left button commands
		bExpand.setCommand(myExpandCommand);
		bUp.setCommand(myUpCommand);
		bLeft.setCommand(myLeftCommand);
		bJumpToADog.setCommand(myJumpToADogCommand);
		
		leftControlContainer.add(bExpand);
		leftControlContainer.add(bUp);
		leftControlContainer.add(bLeft);
		leftControlContainer.add(bJumpToADog);
		
		add(BorderLayout.WEST, leftControlContainer);

		Container rightControlContainer = new Container(new GridLayout(5, 1));
		
		rightControlContainer.getAllStyles().setPadding(Component.TOP, 100);
		rightControlContainer.getAllStyles().setPadding(Component.BOTTOM, 100);
		rightControlContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLUE));
		
		//	set right button commands
		bContract.setCommand(myContractCommand);
		bDown.setCommand(myDownCommand);
		bRight.setCommand(myRightCommand);
		bJumpToACat.setCommand(myJumpToACatCommand);
		bScoop.setCommand(myScoopCommand);

		rightControlContainer.add(bContract);
		rightControlContainer.add(bDown);
		rightControlContainer.add(bRight);
		rightControlContainer.add(bJumpToACat);
		rightControlContainer.add(bScoop);
		add(BorderLayout.EAST, rightControlContainer);

		add(BorderLayout.CENTER, mv);

		Container bottomControlContainer = new Container(new FlowLayout(Component.CENTER));
		
		bottomControlContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLUE));
		
		// set bottom button commands
		/*bKitten.setCommand(myKittenCommand);
		bFight.setCommand(myFightCommand);*/
		//bTick.setCommand(myTickCommand);
		bPause.setCommand(myPauseCommand);
		bHeal.setCommand(myHealCommand);

		/*bottomControlContainer.add(bKitten);
		bottomControlContainer.add(bFight);*/
		//bottomControlContainer.add(bTick);
		bottomControlContainer.add(bHeal);
		bottomControlContainer.add(bPause);
		
		add(BorderLayout.SOUTH, bottomControlContainer);
		
		bHeal.setEnabled(false);
		myHealCommand.setEnabled(false);
		//this.addKeyListener('h', myHealCommand);
		
		//	key binding
		this.addKeyListener('e', myExpandCommand);
		this.addKeyListener('c', myContractCommand);
		this.addKeyListener('s', myScoopCommand);
		this.addKeyListener('r', myRightCommand);
		this.addKeyListener('l', myLeftCommand);
		this.addKeyListener('u', myUpCommand);
		this.addKeyListener('d', myDownCommand);
		this.addKeyListener('o', myJumpToADogCommand);
		this.addKeyListener('a', myJumpToACatCommand);
		this.addKeyListener('x', myExitCommand);
		//this.addKeyListener('p', myPauseCommand); //not specified in assignment but added just cuz
		/*this.addKeyListener('k', myKittenCommand);
		this.addKeyListener('f', myFightCommand);*/
		//this.addKeyListener('t', myTickCommand);
		
		
		show();
		gw.setGameWorldSize(mv.getWidth(), mv.getHeight(), mv.getAbsoluteX(), mv.getAbsoluteY(), sv.getY());
		gw.init();	//	initialize world
		gw.addObserver(mv);	//	register the map observer
		gw.addObserver(sv);	//	register the score observer
		gw.notifyObservers();
	}

	@Override
	public void run() {
		gw.tick();		
	}

	public boolean isPaused(){
		return isPause;
	}

	public void pauseGame(){ 	//pausing the game will always turn off the sounds including background sounds but will restore
		if(isPause == false){ 	//the sounds to what it was set to before pausing
			timer.cancel(); //stop UITimer and stop animation
			isPause = true;
			mv.p(true);
			bPause.setText("Play"); //change label on the button to "Play"
			
			bHeal.setEnabled(true);
			myHealCommand.setEnabled(true);
			addKeyListener('h', myHealCommand);
			
			bExpand.setEnabled(false);
			bUp.setEnabled(false);
			bLeft.setEnabled(false);
			bJumpToADog.setEnabled(false);
			bContract.setEnabled(false);
			bDown.setEnabled(false);
			bRight.setEnabled(false);
			bJumpToACat.setEnabled(false);
			bScoop.setEnabled(false);
			
			myExpandCommand.setEnabled(false);
			myUpCommand.setEnabled(false);
			myLeftCommand.setEnabled(false);
			myJumpToADogCommand.setEnabled(false);
			myContractCommand.setEnabled(false);
			myDownCommand.setEnabled(false);
			myRightCommand.setEnabled(false);
			myJumpToACatCommand.setEnabled(false);
			myScoopCommand.setEnabled(false);
			
			removeKeyListener('e', myExpandCommand);
			removeKeyListener('c', myContractCommand);
			removeKeyListener('s', myScoopCommand);
			removeKeyListener('r', myRightCommand);
			removeKeyListener('l', myLeftCommand);
			removeKeyListener('u', myUpCommand);
			removeKeyListener('d', myDownCommand);
			removeKeyListener('o', myJumpToADogCommand);
			removeKeyListener('a', myJumpToACatCommand);

			if(gw.checkSound()){ //if sound was ON before pausing ppsflag will be 1
				ppsflag = 1;
				gw.toggleSound(); //turns off sounds including background sound
			} else {
				ppsflag = 0;
			}
		}
	}

	public void resumeGame(){		
		if(isPause == true){
			
			if(ppsflag == 1){
				if(gw.checkSound() == false){
					gw.toggleSound(); //turn sound back ON
				}
				ppsflag = 0;
			}//else keep sound OFF
			
			timer.schedule(20, true, this); //restart UITimer
			isPause = false;
			mv.p(false); 
			mv.unpause(); //unpause deselect all dogs
			bPause.setText("Pause"); //set label back to "Pause"
			
			bHeal.setEnabled(false);
			myHealCommand.setEnabled(false);
			removeKeyListener('h', myHealCommand);
			
			bExpand.setEnabled(true);
			bUp.setEnabled(true);
			bLeft.setEnabled(true);
			bJumpToADog.setEnabled(true);
			bContract.setEnabled(true);
			bDown.setEnabled(true);
			bRight.setEnabled(true);
			bJumpToACat.setEnabled(true);
			bScoop.setEnabled(true);
			
			myExpandCommand.setEnabled(true);
			myUpCommand.setEnabled(true);
			myLeftCommand.setEnabled(true);
			myJumpToADogCommand.setEnabled(true);
			myContractCommand.setEnabled(true);
			myDownCommand.setEnabled(true);
			myRightCommand.setEnabled(true);
			myJumpToACatCommand.setEnabled(true);
			myScoopCommand.setEnabled(true);
			
			addKeyListener('e', myExpandCommand);
			addKeyListener('c', myContractCommand);
			addKeyListener('s', myScoopCommand);
			addKeyListener('r', myRightCommand);
			addKeyListener('l', myLeftCommand);
			addKeyListener('u', myUpCommand);
			addKeyListener('d', myDownCommand);
			addKeyListener('o', myJumpToADogCommand);
			addKeyListener('a', myJumpToACatCommand);
		}
	}
}
