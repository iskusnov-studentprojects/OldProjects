package com;

import com.interfaces.drawing.MenuDrawing;
import com.interfaces.drawing.PauseDrawing;
import com.interfaces.drawing.RunningGameDrawing;
import com.objects.Space;
import com.objects.clicklistener.MenuClickListener;
import com.objects.clicklistener.PauseClickListener;
import com.objects.keylistener.MenuKeyListener;
import com.objects.keylistener.RunningGameKeyListener;
import com.visual.window.MainForm;
import com.visual.window.Model;
import com.visual.window.StatisticForm;
import com.visual.window.states.EndGameState;
import com.visual.window.states.NewGameState;
import com.visual.window.states.PauseGameState;
import com.visual.window.states.RunningGameState;

/**
 * Created by Sergey on 29.01.2016.
 */
public class Controller {
    private Model model;
    private MainForm view;
    private StatisticForm statictic;

    public Controller() {
        model = new Model(this);
        view = new MainForm(this);
        view.setVisible(true);
        Space.getInstance().addObserver(view);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller();
            }
        });
    }

    public void showStatistic(){
        if(statictic == null) {
            statictic = new StatisticForm(this);
        }
        statictic.setVisible(true);
    }

    public void processSpace(){
        if(model.isRunning())
            pauseGame();
        else
            countinueGame();
    }

    public void refreshInformation(){
        if(statictic == null)
            return;
        String[][] info = model.collectInformation();
        statictic.refreshInformation(info[0], info[1]);
    }

    public void newGame(){
        closeStatistic();
        view.setDrawing(new RunningGameDrawing());
        view.setClickListener(null);
        view.setKeyListener(new RunningGameKeyListener(view));
        model.changeState(new NewGameState(model));
    }

    public void endGame(){
        view.setDrawing(new MenuDrawing());
        view.setClickListener(new MenuClickListener(view));
        view.setKeyListener(new MenuKeyListener(view));
        closeStatistic();
        model.changeState(new EndGameState(model));
    }

    public void pauseGame(){
        view.setDrawing(new PauseDrawing());
        view.setClickListener(new PauseClickListener(view));
        model.changeState(new PauseGameState(model));
    }

    public void countinueGame(){
        view.setDrawing(new RunningGameDrawing());
        view.setClickListener(null);
        model.changeState(new RunningGameState(model));
    }

    public void exit(){
        closeMainForm();
        closeStatistic();
    }

    private void closeStatistic(){
        if(statictic != null) {
            statictic.setVisible(false);
            statictic.dispose();
            statictic = null;
        }
    }

    private void closeMainForm(){
        if(view != null){
            view.setVisible(false);
            view.dispose();
            view = null;
        }
    }
}
