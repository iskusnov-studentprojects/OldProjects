package com.objects.pool;

import com.interfaces.IPrototype;
import com.objects.ships.prototypes.*;

import java.util.Stack;

/**
 * Created by Sergey on 29.12.2015.
 */
public class PrototypesPool {
    Stack<IPrototype> memory;

    protected PrototypesPool(){
        memory = new Stack<IPrototype>();
        memory.push(RadarShip.createPrototype());
        memory.push(RegenShip.createPrototype());
        memory.push(StealthShip.createPrototype());
        memory.push(TransportShip.createPrototype());
        memory.push(WarShip.createPrototype());
    }

    protected static PrototypesPool pool;

    public static PrototypesPool getInstance(){
        if(pool == null)
            pool = new PrototypesPool();
        return pool;
    }

    public IPrototype acquireObject() {
        return memory.pop();
    }

    public void releaseObject(IPrototype value) {
        memory.push(value);
    }
}
