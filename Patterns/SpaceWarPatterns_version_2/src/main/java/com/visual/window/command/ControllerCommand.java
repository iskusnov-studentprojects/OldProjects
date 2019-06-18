package com.visual.window.command;

import com.Controller;

/**
 * Created by Sergey on 31.01.2016.
 */
public abstract class ControllerCommand {
    protected Controller controller;

    public ControllerCommand(Controller controller) {
        this.controller = controller;
    }

    public abstract void execute();
}
