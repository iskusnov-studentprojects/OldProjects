package com.objects.keylistener;

import com.visual.window.MainForm;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 27.01.2016.
 */
public abstract class KeyHandler {

    protected KeyHandler nextHandler;
    protected MainForm form;

    public KeyHandler(MainForm form) {
        this.form = form;
        nextHandler = null;
    }

    public KeyHandler(KeyHandler nextHandler, MainForm form) {
        this.nextHandler = nextHandler;
        this.form = form;
    }

    public abstract void processKey(KeyEvent value);
}
