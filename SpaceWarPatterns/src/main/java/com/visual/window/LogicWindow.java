package com.visual.window;

import com.Constants;
import com.enums.PlayerColor;
import com.enums.RaceType;
import com.enums.StateTypes;
import com.interfaces.KeyListener;
import com.interfaces.drawing.MenuDrawing;
import com.objects.Planet;
import com.objects.Race;
import com.visual.window.states.*;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class LogicWindow extends AbstractWindow{

	/**
	 * Default constructor
	 */
	public LogicWindow() {
		window = new MainForm(new MenuDrawing(), new KeyListener(this));
		window.show();
		final State state = new EndGameState(this);
		changeState(new StartState(this));
		timer = new javax.swing.Timer(200, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				race1.turn();
				race2.turn();
				window.repaint();
				if(!raceIsAlive(race1)){
					changeState(state);
					javax.swing.JOptionPane.showMessageDialog(null, "Победил? Race2!");
				}
				if(!raceIsAlive(race2)){
					changeState(state);
					javax.swing.JOptionPane.showMessageDialog(null, "Победил? Race1!");
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
	protected StatisticForm statistic;


	/**
	 * 
	 */
	public void invalidate() {
		window.repaint();
	}

	public void changeState(State state){
		this.state = state;
		state.execute();
	}

	public void showStatistic(){
		statistic = new StatisticForm(race1, race2);
		statistic.setVisible(true);
	}

	public void closeStatistic(){
		if(statistic!=null){
			statistic.dispose();
			statistic = null;
		}
	}

	public void start(){
		timer.start();
	}

	public void stop(){
		timer.stop();
	}

	public boolean raceIsAlive(Race race){
		int allPlanets = 0, poorPlanets = 0;
		for(Planet i: Constants.PLANETS)
			if(i.getPlayerColor() == race.getPlayerColor()){
				allPlanets++;
				if(i.getResources() <= 200)
					poorPlanets++;
			}
		if(allPlanets == poorPlanets && race.getShips().isEmpty())
			return false;
		else
			return true;
	}

	public static void main(String args[]) {
        /* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LogicWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LogicWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LogicWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LogicWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

        /* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LogicWindow();
			}
		});
	}

	public void refreshRaces(){
		race1 = new Race(RaceType.RegenerationRace, PlayerColor.Green);
		race1.getPlanets().add(Constants.PLANETS.get(0));
		race2 = new Race(RaceType.StealthRace, PlayerColor.Red);
		race2.getPlanets().add(Constants.PLANETS.get(1));
	}

	public MainForm getForm(){
		return (MainForm)window;
	}

	public boolean isRunning(){
		if(state.getType() == StateTypes.RunningGameState)
			return true;
		else
			return false;
	}
}