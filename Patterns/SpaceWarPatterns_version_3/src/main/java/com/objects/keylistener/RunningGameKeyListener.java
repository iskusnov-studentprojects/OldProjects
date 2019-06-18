package com.objects.keylistener;

import com.visual.window.MainForm;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 03.02.2016.
 */
public class RunningGameKeyListener implements IKeyListener {
    private MainForm form;
    KeyHandler handler;

    public RunningGameKeyListener(MainForm window) {
        this.form = window;
        handler = new SpaceHandler(new F1Handler(new NHandler(form), form), form);
    }

    @Override
    public void process(KeyEvent value) {
        handler.processKey(value);
    }
}
