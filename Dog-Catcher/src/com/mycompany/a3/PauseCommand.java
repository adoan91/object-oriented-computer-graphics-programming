package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command{
    private static Game game;

    public PauseCommand(){
        super("Pause");
    }
    
    public static void setTarget(Game g){ //target is Game this time
        if(game == null){
            game = g;
        }
    }

    public void actionPerformed(ActionEvent ev){
        if(game.isPaused()){
            game.resumeGame();
        }
        else if(!game.isPaused()){
        	game.pauseGame();
        }
    }
}
