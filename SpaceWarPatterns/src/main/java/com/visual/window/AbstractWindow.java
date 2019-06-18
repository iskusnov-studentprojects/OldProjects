package com.visual.window;

import java.util.*;
import java.awt.Window;

/**
 * 
 */
public abstract class AbstractWindow {

	/**
	 * Default constructor
	 */
	public AbstractWindow() {
	}

	/**
	 * 
	 */
	protected Window window;



	/**
	 * 
	 */
	public void invalidate() {
		// TODO implement here
		window.invalidate();
	}

}