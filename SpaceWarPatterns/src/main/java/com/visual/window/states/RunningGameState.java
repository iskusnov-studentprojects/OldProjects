package com.visual.window.states;

import com.enums.StateTypes;
import com.interfaces.drawing.RunningGameDrawing;
import com.visual.window.LogicWindow;
import com.visual.window.MainForm;

import java.util.*;

/**
 * 
 */
public class RunningGameState extends State {

	/**
	 * Default constructor
	 *
	 * @param logicWindow
	 */
	public RunningGameState(LogicWindow logicWindow) {
		super(logicWindow);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		logicWindow.getForm().setDrawing(new RunningGameDrawing());
		logicWindow.start();
	}

	@Override
	public StateTypes getType(){
		return StateTypes.RunningGameState;
	}
}