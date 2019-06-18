package com.visual.window.command;

import com.objects.queue.MyQueue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

/**
 * Created by Sergey on 31.01.2016.
 */
public class Invoker {
    private static Invoker instance;
    private Queue<ControllerCommand> commands;
    private Timer timer;

    private Invoker() {
        commands = new MyQueue<ControllerCommand>();
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!commands.isEmpty())
                    commands.poll().execute();
            }
        });
        timer.start();
    }

    public static Invoker getInstance(){
        if(instance == null){
            instance = new Invoker();
        }
        return instance;
    }

    public void addCommand(ControllerCommand command){
        commands.add(command);
    }
}
