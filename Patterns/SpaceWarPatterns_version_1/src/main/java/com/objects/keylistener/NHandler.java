package com.objects.keylistener;

import com.enums.FormMessages;
import com.visual.window.MainForm;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 27.01.2016.
 */
public class NHandler extends KeyHandler{
    public NHandler(MainForm form) {
        super(form);
    }

    public NHandler(KeyHandler nextHandler, MainForm form) {
        super(nextHandler, form);
    }

    @Override
    public void processKey(KeyEvent value) {
        if(value.getKeyCode() == KeyEvent.VK_N){
            form.processMessage(FormMessages.NPress);
        }
        else {
            if(nextHandler!=null)
                nextHandler.processKey(value);
        }
    }
}
