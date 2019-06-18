package com.visual.window;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.Controller;
import com.visual.window.command.InvokerForController;
import com.visual.window.command.RefreshStatisticCommand;

import java.awt.event.*;

/**
 *
 * @author Sergey
 */
public class StatisticForm extends javax.swing.JFrame {
    javax.swing.Timer timer;
    Controller controller;
    
    /**
     * Creates new form StatisticForm
     */
    public StatisticForm(Controller _controller) {
        initComponents();
        controller = _controller;
        timer = new javax.swing.Timer(200, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                InvokerForController.getInstance().addCommand(new RefreshStatisticCommand(controller));
            }
        });
        start();
    }
    
    public void refreshInformation(String[] race1, String[] race2){
        int index;
        index = race1InfoList.getSelectedIndex();
        race1InfoList.setListData(race1);
        race1InfoList.setSelectedIndex(index);
        index = race2InfoList.getSelectedIndex();
        race2InfoList.setListData(race2);
        race2InfoList.setSelectedIndex(index);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        race1TextPane1 = new javax.swing.JScrollPane();
        race1InfoList = new javax.swing.JList<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        race2InfoList = new javax.swing.JList<String>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Statistic");
        setResizable(false);

        jLabel1.setText("Race1");

        race1TextPane1.setViewportView(race1InfoList);

        jScrollPane1.setViewportView(race2InfoList);

        jLabel2.setText("Race2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(race1TextPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 258, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addComponent(race1TextPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> race1InfoList;
    private javax.swing.JScrollPane race1TextPane1;
    private javax.swing.JList<String> race2InfoList;
    // End of variables declaration//GEN-END:variables
}
