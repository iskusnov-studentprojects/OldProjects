package com.visual.window.states;

import com.enums.StateTypes;
import com.visual.window.LogicWindow;
import com.visual.window.MainForm;

import javax.swing.*;
import java.util.*;

/**
 * 
 */
public class State {

	protected LogicWindow logicWindow;
	/**
	 * Default constructor
	 */
	public State(LogicWindow logicWindow) {
		this.logicWindow = logicWindow;
	}

	/**
	 * @return
	 */
	public void execute() {

	}

	public StateTypes getType(){
		return StateTypes.State;
	}
}