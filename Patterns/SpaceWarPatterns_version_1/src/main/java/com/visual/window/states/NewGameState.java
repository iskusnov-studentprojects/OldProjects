package com.visual.window.states;

import com.enums.PlayerColor;
import com.enums.RaceType;
import com.enums.StateTypes;
import com.objects.planet.Planet;
import com.objects.Space;
import com.objects.planet.PlanetPool;
import com.sql.SingletonSQLQuerys;
import com.visual.window.Model;

import java.util.*;

/**
 * 
 */
public class NewGameState extends State {

	public NewGameState(Model model) {
		super(model);
	}

	/**
	 * @return
	 */
	@Override
	public void execute() {
		Planet p = null, race1Planet = null, race2Planet=null;
		Space.getInstance().clear();
		Random rand = new Random();
		int planetNumber = SingletonSQLQuerys.getInstance().getNumberPlanets();
		for(int i = 0; i < planetNumber; i++)
		{
			int x, y;
			do{
				x = rand.nextInt(Space.SPACEWIDTH) + 1;
				y = rand.nextInt(Space.SPACEHEIGHT) + 1;
			}while(Space.getInstance().get(x,y) != null);
			p = PlanetPool.getInstance().getObject();
			p.setLocation(x, y);
			Space.getInstance().set(p,x,y);
			if(race1Planet == null)
				race1Planet = p;
			else if(race2Planet == null)
				race2Planet = p;
		}
		model.refreshRaces();
		race1Planet.setPlayerColor(PlayerColor.Green);
		race2Planet.setPlayerColor(PlayerColor.Blue);
		model.setPlanets(race1Planet, race2Planet);
		model.changeState(new RunningGameState(model));
	}

	@Override
	public StateTypes getType(){
		return StateTypes.BeginGameState;
	}
}