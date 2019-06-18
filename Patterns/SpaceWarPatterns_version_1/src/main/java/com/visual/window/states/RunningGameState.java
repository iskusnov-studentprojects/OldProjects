package com.visual.window.states;

import com.enums.StateTypes;
import com.interfaces.drawing.RunningGameDrawing;
import com.visual.window.Model;

/**
 * 
 */
public class RunningGameState extends State {

	public RunningGameState(Model model) {
		super(model);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		model.start();
	}

	@Override
	public StateTypes getType(){
		return StateTypes.RunningGameState;
	}
}