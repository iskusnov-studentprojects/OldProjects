package com.visual.window.states;

import com.enums.StateTypes;
import com.interfaces.drawing.MenuDrawing;
import com.visual.window.LogicWindow;
import com.visual.window.MainForm;

import java.awt.event.ActionListener;
import java.util.*;

/**
 * 
 */
public class EndGameState extends State {

	public EndGameState(LogicWindow logicWindow) {
		super(logicWindow);
	}



	/**
	 * @return
	 */
	@Override
	public void execute() {
		logicWindow.stop();
		logicWindow.closeStatistic();
	}

	@Override
	public StateTypes getType(){
		return StateTypes.EndGameState;
	}
}