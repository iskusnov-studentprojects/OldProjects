package com.objects.keylistener;

import com.visual.window.MainForm;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 22.01.2016.
 */
public class MenuKeyListener implements IKeyListener {

    private MainForm form;
    private KeyHandler handler;

    public MenuKeyListener(MainForm window) {
        this.form = window;
        handler = new NHandler(form);
    }

    @Override
    public void process(KeyEvent value) {
        handler.processKey(value);
    }
}
