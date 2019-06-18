package com.visual.window.states;

import com.enums.StateTypes;
import com.visual.window.Model;

/**
 * 
 */
public abstract class State {

	protected Model model;

	public State(Model model) {
		this.model = model;
	}

	/**
	 * @return
	 */
	public abstract void execute();

	public StateTypes getType(){
		return StateTypes.State;
	}
}