package com.objects.planet;

import com.enums.PlayerColor;

import java.util.Stack;

/**
 * Created by Sergey on 31.01.2016.
 */
public class PlanetPool {
    private Stack<Planet> memory;
    private Stack<PlanetMemento> saves;
    private static PlanetPool instance;

    private PlanetPool() {
        memory = new Stack<Planet>();
        saves = new Stack<PlanetMemento>();
    }

    public static PlanetPool getInstance(){
        if(instance == null)
            instance = new PlanetPool();
        return instance;
    }

    public Planet getObject(){
        Planet planet;
        if(memory.empty()){
            planet = new Planet(0, 0, PlayerColor.None);
        }
        else {
            planet = memory.pop();
        }

        saves.push(planet.createMemento());
        return planet;
    }

    public void releaseObject(Planet planet){
        if(planet == null)
            return;
        planet.resetState(saves.pop());
        memory.push(planet);
    }
}
