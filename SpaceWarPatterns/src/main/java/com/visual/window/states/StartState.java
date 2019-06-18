package com.visual.window.states;

import com.enums.StateTypes;
import com.interfaces.drawing.MenuDrawing;
import com.visual.window.LogicWindow;
import com.visual.window.MainForm;

import javax.swing.*;
import java.util.*;

/**
 * 
 */
public class StartState extends State {
	/**
	 * Default constructor
	 *
	 * @param logicWindow
	 */
	public StartState(LogicWindow logicWindow) {
		super(logicWindow);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		logicWindow.invalidate();
	}

	@Override
	public StateTypes getType(){
		return StateTypes.MenuState;
	}
}