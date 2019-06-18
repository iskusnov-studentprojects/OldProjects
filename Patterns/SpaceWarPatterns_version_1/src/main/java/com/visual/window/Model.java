package com.visual.window;

import com.Controller;
import com.enums.PlayerColor;
import com.enums.RaceType;
import com.enums.StateTypes;
import com.objects.planet.Planet;
import com.objects.Race;
import com.visual.window.command.EndGameCommand;
import com.visual.window.command.Invoker;
import com.visual.window.states.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class Model {

	/**
	 * Default constructor
	 */
	public Model(final Controller controller) {
		this.controller = controller;
		changeState(new MenuState(this));
		timer = new javax.swing.Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				race1.turn();
				race2.turn();
				if(!race1.isAlive()){
					Invoker.getInstance().addCommand(new EndGameCommand(controller));
					javax.swing.JOptionPane.showMessageDialog(null, "Winner Race2!");
				}
				if(!race2.isAlive()){
					Invoker.getInstance().addCommand(new EndGameCommand(controller));
					javax.swing.JOptionPane.showMessageDialog(null, "Winner Race1!");
				}
			}
		});
	}

	/**
	 * 
	 */
	protected State state;
	protected Race race1;
	protected Race race2;
	protected Timer timer;
	Controller controller;

	public void changeState(State state){
		this.state = state;
		state.execute();
	}

	public void start(){
		timer.start();
	}

	public void stop(){
		timer.stop();
	}

	public void refreshRaces(){
		race1 = new Race(RaceType.RegenerationRace, PlayerColor.Green);
		race2 = new Race(RaceType.StealthRace, PlayerColor.Blue);
	}

	public boolean isRunning(){
		if(state.getType() == StateTypes.RunningGameState)
			return true;
		else
			return false;
	}

	public String[][] collectInformation(){
		String[][] res = new String[2][];
		res[0] = race1.getInformation();
		res[1] = race2.getInformation();
		return res;
	}

	public void setPlanets(Planet race1Planet, Planet race2Planet){
		race1.getPlanets().add(race1Planet);
		race2.getPlanets().add(race2Planet);
		race1Planet.setRace(race1);
		race2Planet.setRace(race2);
	}
}