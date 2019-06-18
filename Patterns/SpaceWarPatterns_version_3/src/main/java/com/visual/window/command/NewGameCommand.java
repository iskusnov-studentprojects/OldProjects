package com.visual.window.command;

import com.Controller;

/**
 * Created by Sergey on 31.01.2016.
 */
public class NewGameCommand extends ControllerCommand {
    public NewGameCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.newGame();
    }
}
