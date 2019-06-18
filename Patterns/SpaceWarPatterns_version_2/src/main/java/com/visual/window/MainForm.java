package com.visual.window;

import com.Controller;
import com.enums.FormMessages;
import com.interfaces.Observer;
import com.interfaces.drawing.IDrawing;
import com.interfaces.drawing.MenuDrawing;
import com.objects.clicklistener.IMouseClickListener;
import com.objects.clicklistener.MenuClickListener;
import com.objects.keylistener.IKeyListener;
import com.objects.keylistener.MenuKeyListener;
import com.visual.window.command.*;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author ��??
 */
public class MainForm extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form MainForm
     */
    private IDrawing drawing;
    private IKeyListener keyListener;
    private IMouseClickListener clickListener;
    private Controller controller;

    public MainForm(Controller controller) {
        initComponents();
        Dimension dimension = new Dimension(500, 500);
        setMinimumSize(dimension);
        setSize(500, 500);
        setDrawing(new MenuDrawing());
        setKeyListener(new MenuKeyListener(this));
        setClickListener(new MenuClickListener(this));
        this.controller = controller;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (keyListener != null)
                    keyListener.process(e);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickListener != null)
                    clickListener.process(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Space War");
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    @Override
    public void paint(Graphics g){
        g.drawImage(getFrame(), 0, 0, null);
    }

    public void setDrawing(IDrawing drawing) {
        this.drawing = drawing;
        repaint();
    }

    public void setClickListener(IMouseClickListener listener){
        this.clickListener = listener;
    }

    public void setKeyListener(IKeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void processMessage(FormMessages event){
        switch (event){

            case NewGameClick:
                InvokerForController.getInstance().addCommand(new NewGameCommand(controller));
                break;
            case ExitClick:
                InvokerForController.getInstance().addCommand(new ExitCommand(controller));
                break;
            case ContinueClick:
                InvokerForController.getInstance().addCommand(new ContinueCommand(controller));
                break;
            case SpacePress:
                InvokerForController.getInstance().addCommand(new SpacePressCommand(controller));
                break;
            case F1Press:
                InvokerForController.getInstance().addCommand(new ShowStatisticCommand(controller));
                break;
            case NPress:
                InvokerForController.getInstance().addCommand(new NewGameCommand(controller));
                break;
        }
    }

    public Image getFrame(){
        Image buffer = createImage(this.getWidth(), this.getHeight());
        drawing.draw(buffer.getGraphics(), this.getWidth(), this.getHeight());
        return buffer;
    }

    @Override
    public void update() {
        repaint();
    }
}