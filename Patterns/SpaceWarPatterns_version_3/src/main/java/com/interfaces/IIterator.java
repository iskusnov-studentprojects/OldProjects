package com.interfaces;

import java.util.*;

/**
 * 
 */
public interface IIterator<T> {

	/**
	 * @return
	 */
	public void next();

	public boolean hasNext();

	public T get();

	public void remove();
}