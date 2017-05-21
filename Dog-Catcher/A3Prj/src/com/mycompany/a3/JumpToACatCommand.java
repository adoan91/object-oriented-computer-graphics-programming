package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class JumpToACatCommand extends Command {
	private static GameWorld game;
	
	public JumpToACatCommand(){
        super("JumpToACat");
    }

	public static void setTarget(GameWorld gw){
        if(game == null){
            game = gw;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        System.out.println("JumpToACat");
        game.jumpToCat();
    }
}
