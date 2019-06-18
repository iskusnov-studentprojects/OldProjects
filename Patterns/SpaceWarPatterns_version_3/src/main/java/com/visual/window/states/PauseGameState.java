package com.visual.window.states;

import com.enums.StateTypes;
import com.visual.window.Model;

/**
 * 
 */
public class PauseGameState extends State {

	public PauseGameState(Model model) {
		super(model);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		model.stop();
	}

	@Override
	public StateTypes getType(){
		return StateTypes.PauseGameState;
	}
}