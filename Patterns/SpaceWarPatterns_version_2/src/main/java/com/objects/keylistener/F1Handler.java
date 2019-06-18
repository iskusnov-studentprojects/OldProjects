package com.objects.keylistener;

import com.enums.FormMessages;
import com.visual.window.MainForm;
import com.visual.window.Model;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 27.01.2016.
 */
public class F1Handler extends KeyHandler{

    public F1Handler(MainForm form) {
        super(form);
    }

    public F1Handler(KeyHandler nextHandler, MainForm form) {
        super(nextHandler, form);
    }

    @Override
    public void processKey(KeyEvent value) {
        if(value.getKeyCode() == KeyEvent.VK_F1){
            form.processMessage(FormMessages.F1Press);
        }
        else {
            if(nextHandler!=null)
                nextHandler.processKey(value);
        }
    }
}
