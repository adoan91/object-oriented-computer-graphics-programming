package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HealCommand extends Command{
    private static GameWorld game;

    public HealCommand(){
        super("Heal");
    }

    public static void setTarget(GameWorld gw){
        if(game == null){
            game = gw;
        }
    }

    public void actionPerformed(ActionEvent e){
        game.heal();
    }
}

