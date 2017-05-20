package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	private static GameWorld game;
	
	public SoundCommand(){
        super("Sound");
    }
	
	public static void setTarget(GameWorld gw/*, Game g*/){
        if(game == null){
            game = gw;
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent evt){
        game.toggleSound(); 
        SideMenuBar.closeCurrentMenu();

    }

}
