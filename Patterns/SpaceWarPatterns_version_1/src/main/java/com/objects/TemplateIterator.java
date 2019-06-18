package com.objects;

import com.interfaces.IIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sergey on 29.12.2015.
 */
public class TemplateIterator<T> implements IIterator {
    private ArrayList<T> memory;

    private int number;

    public TemplateIterator(Collection<T> memory){
        this.memory = new ArrayList<T>(memory);
        number = 0;
    }

    @Override
    public void next() {
        if(number < memory.size())
            number++;
    }

    @Override
    public boolean hasNext() {
        if(memory.isEmpty() || number >= memory.size())
            return false;
        else
            return true;
    }

    @Override
    public T get() {
        if(memory.isEmpty())
            return null;
        return memory.get(number);
    }

    @Override
    public void remove() {
        memory.remove(number);
        if(number == memory.size())
            number--;
    }


}
