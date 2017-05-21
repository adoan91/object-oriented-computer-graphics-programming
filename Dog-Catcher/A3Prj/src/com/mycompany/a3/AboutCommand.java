package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	public AboutCommand(){
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        String Message = "Anthony Doan, CSC133, Assignment 3";
        Dialog.show("About", Message, "Ok", null);
    }
}
