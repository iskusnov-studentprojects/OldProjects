package com.objects;

import com.interfaces.IIterator;

import java.util.List;

/**
 * Created by Sergey on 29.12.2015.
 */
public class TemplateIterator<T> implements IIterator {
    private List<T> memory;

    private int number;

    public TemplateIterator(List<T> memory){
        this.memory = memory;
        number = 0;
    }

    @Override
    public void next() {
        if(number >= memory.size())
            number = -1;
        else
            number++;
    }

    @Override
    public boolean isEnd() {
        if(number < 0 || memory.isEmpty() || number >= memory.size())
            return true;
        else
            return false;
    }

    @Override
    public T get() {
        if(isEnd()){
            number = -1;
            return null;
        }
        return memory.get(number);
    }
}
