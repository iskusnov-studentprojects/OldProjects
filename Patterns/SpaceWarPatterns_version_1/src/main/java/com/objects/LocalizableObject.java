package com.objects;

import com.enums.PlayerColor;
import com.interfaces.action.IAction;
import com.interfaces.cannon.ICannon;
import com.interfaces.drawing.IDrawing;
import com.interfaces.invisibility.IInvisibility;
import com.interfaces.invisibility.IVisible;
import com.interfaces.move.IMovable;
import com.objects.visitor.CollectorOfInformation;

import java.awt.*;

/**
 * 
 */
public class LocalizableObject {

	protected boolean immortal;
	protected IMovable movable;
	protected IAction action;
	protected IDrawing drawing;
	public Location location;
	protected PlayerColor playerColor;
	protected IInvisibility invisibility;
	protected ICannon cannon;
	protected IVisible iVisible;
	protected Race race;

	public IInvisibility getInvisibility() {
		return invisibility;
	}

	public void setInvisibility(IInvisibility invisibility) {
		this.invisibility = invisibility;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(int x, int y) {
		if(location == null)
			location = new Location(x, y);
		location.X = x;
		location.Y = y;
	}

	public IMovable getMovable() {
		return movable;
	}

	public IAction getAction() {
		return action;
	}

	public IDrawing getDrawing() {
		return drawing;
	}

	public void setDrawing(IDrawing drawing) {
		this.drawing = drawing;
	}

	public ICannon getCannon() {
		return cannon;
	}

	public void setCannon(ICannon cannon) {
		this.cannon = cannon;
	}

	public IVisible getiVisible() {
		return iVisible;
	}

	public void setiVisible(IVisible iVisible) {
		this.iVisible = iVisible;
	}

	public boolean isImmortal() {
		return immortal;
	}

	public void doAction() {
		action.action();
	}

	public void getDamage(int value) {
		throw new UnsupportedOperationException();
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}

	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}

	public void setMovable(IMovable movable) {
		this.movable = movable;
	}

	public void setAction(IAction action) {
		this.action = action;
	}

	public boolean isVisible(){
		return invisibility == null ? true : invisibility.isVisible();
	}

	public void attack(LocalizableObject ship){
		getCannon().fire(ship);
	}

	public void accept(CollectorOfInformation visitor){
		throw new UnsupportedOperationException();
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
}