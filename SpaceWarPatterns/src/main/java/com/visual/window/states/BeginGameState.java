package com.visual.window.states;

import com.Constants;
import com.enums.PlayerColor;
import com.enums.RaceType;
import com.enums.StateTypes;
import com.objects.Planet;
import com.objects.Race;
import com.objects.Space;
import com.visual.window.LogicWindow;
import com.visual.window.MainForm;

import javax.swing.*;
import java.util.*;

/**
 * 
 */
public class BeginGameState extends State {

	public BeginGameState(LogicWindow logicWindow) {
		super(logicWindow);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		Space.getInstance().clear();
		Constants.PLANETS.clear();
		Random rand = new Random();
		for(int i = 0; i < Constants.PLANETNUMBER; i++)
		{
			int x, y;
			do{
				x = rand.nextInt(Constants.SPACEWIDTH) + 1;
				y = rand.nextInt(Constants.SPACEHEIGHT) + 1;
			}while(Space.getInstance().field[x][y] != null);
			Planet p = new Planet(x,y, PlayerColor.None, RaceType.RegenerationRace);
			Space.getInstance().field[x][y] = p;
			Constants.PLANETS.add(p);

		}
		logicWindow.refreshRaces();
		Constants.PLANETS.get(0).setPlayerColor(PlayerColor.Green);
		Constants.PLANETS.get(1).setPlayerColor(PlayerColor.Red);
		logicWindow.invalidate();
		logicWindow.changeState(new RunningGameState(logicWindow));
	}

	@Override
	public StateTypes getType(){
		return StateTypes.BeginGameState;
	}
}