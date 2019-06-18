package com.objects.keylistener;

import com.enums.FormMessages;
import com.visual.window.MainForm;
import com.visual.window.Model;
import com.visual.window.states.PauseGameState;
import com.visual.window.states.RunningGameState;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 27.01.2016.
 */
public class SpaceHandler extends KeyHandler{
    public SpaceHandler(MainForm form) {
        super(form);
    }

    public SpaceHandler(KeyHandler nextHandler, MainForm form) {
        super(nextHandler, form);
    }

    @Override
    public void processKey(KeyEvent value) {
        if(value.getKeyCode() == KeyEvent.VK_SPACE){
            form.processMessage(FormMessages.SpacePress);
        }
        else {
            if(nextHandler!=null)
                nextHandler.processKey(value);
        }
    }
}
