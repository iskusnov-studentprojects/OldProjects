package com.visual.window.states;

import com.enums.StateTypes;
import com.visual.window.LogicWindow;
import com.visual.window.MainForm;

import java.util.*;

/**
 * 
 */
public class PauseGameState extends State {

	/**
	 * Default constructor
	 *
	 * @param logicWindow
	 */
	public PauseGameState(LogicWindow logicWindow) {
		super(logicWindow);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {

	}

	@Override
	public StateTypes getType(){
		return StateTypes.PauseGameState;
	}
}