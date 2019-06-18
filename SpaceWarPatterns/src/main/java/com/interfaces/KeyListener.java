package com.interfaces;

import com.visual.window.LogicWindow;
import com.visual.window.states.BeginGameState;
import com.visual.window.states.PauseGameState;
import com.visual.window.states.RunningGameState;

import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 22.01.2016.
 */
public class KeyListener implements IKeyListener {

    private LogicWindow window;

    public KeyListener(LogicWindow window) {
        this.window = window;
    }

    @Override
    public void process(KeyEvent value) {
        switch(value.getKeyCode()){
            case KeyEvent.VK_F1:
                window.showStatistic();
                break;
            case KeyEvent.VK_SPACE: if(window.isRunning())
                window.changeState(new PauseGameState(window));
            else
                window.changeState(new RunningGameState(window));
                break;
            case KeyEvent.VK_N:
                window.changeState(new BeginGameState(window));
                break;
        }
    }
}
