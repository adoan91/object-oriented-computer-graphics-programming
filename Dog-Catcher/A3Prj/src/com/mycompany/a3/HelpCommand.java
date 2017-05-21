package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {

    public HelpCommand(){
        super("Help?");
    }

    @Override
    public void actionPerformed(ActionEvent ev){
    	String Message = "Key's binded: e, c, s, r, l, u, d, o, a, k, f, t, x";
        Dialog.show("Help?", Message, "Ok", null);
    }

}
