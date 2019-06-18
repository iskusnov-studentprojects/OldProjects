package com.objects.clicklistener;

import com.enums.FormMessages;
import com.objects.Space;
import com.visual.window.MainForm;

import java.awt.event.MouseEvent;

/**
 * Created by Sergey on 31.01.2016.
 */
public class MenuClickListener implements IMouseClickListener{

    MainForm form;

    public MenuClickListener(MainForm form) {
        this.form = form;
    }

    @Override
    public void process(MouseEvent event) {
        int x = event.getX(),
                y = event.getY();
        double w = form.getWidth()/Space.SPACEWIDTH,
                h = form.getHeight()/Space.SPACEHEIGHT;
        //new game click
        if(x >= w*20 && x <= w*80 && y >= h*20 && y <= h*40)
            form.processMessage(FormMessages.NewGameClick);
        //exit click
        if(x >= w*20 && x <= w*80 && y >= h*60 && y <= h*80)
            form.processMessage(FormMessages.ExitClick);
    }
}
