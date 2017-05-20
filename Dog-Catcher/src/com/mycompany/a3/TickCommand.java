package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command {
    private static GameWorld game;
	
	public TickCommand(){
        super("Tick");
    }

	public static void setTarget(GameWorld gw){
        if(game == null){
            game = gw;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        System.out.println("Tick");
        game.tick();
    }
}
