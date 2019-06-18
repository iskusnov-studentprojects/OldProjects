package com.visual.window.states;

import com.enums.StateTypes;
import com.visual.window.Model;

/**
 * 
 */
public class EndGameState extends State {

	public EndGameState(Model model) {
		super(model);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		model.stop();
		model.changeState(new MenuState(model));
	}

	@Override
	public StateTypes getType(){
		return StateTypes.EndGameState;
	}
}