package com.interfaces.invisibility;

/**
 * Created by Sergey on 29.12.2015.
 */
public class MakeVisible implements IVisible {

    public MakeVisible(boolean value) {
        this.value = value;
    }

    private boolean value;

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean makeVisible() {
        return value;
    }
}
