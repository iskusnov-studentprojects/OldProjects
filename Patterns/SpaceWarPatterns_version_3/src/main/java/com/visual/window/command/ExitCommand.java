package com.visual.window.command;

import com.Controller;

/**
 * Created by Sergey on 31.01.2016.
 */
public class ExitCommand extends ControllerCommand {
    public ExitCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.exit();
    }
}
