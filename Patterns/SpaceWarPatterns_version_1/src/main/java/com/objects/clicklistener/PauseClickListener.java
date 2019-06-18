package com.objects.clicklistener;

import com.enums.FormMessages;
import com.objects.Space;
import com.visual.window.MainForm;

import java.awt.event.MouseEvent;

/**
 * Created by Sergey on 31.01.2016.
 */
public class PauseClickListener implements IMouseClickListener{

    MainForm form;

    public PauseClickListener(MainForm form) {
        this.form = form;
    }

    @Override
    public void process(MouseEvent event) {
        int x = event.getX(),
                y = event.getY();
        double w = form.getWidth()/ Space.SPACEWIDTH,
                h = form.getHeight()/Space.SPACEHEIGHT;
        //continue click
        if(x >= w*20 && x <= w*80 && y >= h*10 && y <= h*30)
            form.processMessage(FormMessages.ContinueClick);
        //new game click
        if(x >= w*20 && x <= w*80 && y >= h*40 && y <= h*60)
            form.processMessage(FormMessages.NewGameClick);
        //exit click
        if(x >= w*20 && x <= w*80 && y >= h*70 && y <= h*90)
            form.processMessage(FormMessages.ExitClick);
    }
}
