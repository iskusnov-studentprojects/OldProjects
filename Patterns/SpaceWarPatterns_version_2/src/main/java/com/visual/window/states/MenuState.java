package com.visual.window.states;

import com.enums.StateTypes;
import com.visual.window.Model;

/**
 * 
 */
public class MenuState extends State {

	public MenuState(Model model) {
		super(model);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {

	}

	@Override
	public StateTypes getType(){
		return StateTypes.MenuState;
	}
}