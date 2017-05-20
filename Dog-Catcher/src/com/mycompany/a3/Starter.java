package com.mycompany.a3;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class Starter {
	
	private Form current;
	@SuppressWarnings("unused")
	private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    
    
//other methods    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        /*Game g = */new Game();
    }
//other methods
    
    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
    
}
