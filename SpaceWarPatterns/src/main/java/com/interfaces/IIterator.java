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

	public boolean isEnd();

	public T get();

}