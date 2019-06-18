package com.visual.window.command;

import com.Controller;

/**
 * Created by Sergey on 31.01.2016.
 */
public class ContinueCommand extends ControllerCommand {
    public ContinueCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.countinueGame();
    }
}
