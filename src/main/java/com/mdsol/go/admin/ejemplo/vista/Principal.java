/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author martdominguez
 */
public class Principal {        

    private JFrame jframe;
    private JPanel cards;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemCategorias;
    private JMenuItem menuItemProyectos;
    private JMenuItem menuItemTareas;
    private JMenuItem menuItemSalir;

        private enum Comandos {
        CATEGORIAS,TAREAS,PROYECTOS,SALIR
    }
    
    public JFrame build() {    
        jframe = new JFrame();
        menuBar = new JMenuBar();

        menu = new JMenu("Opciones");
        menu.setMnemonic(KeyEvent.VK_O);

        menuBar.add(menu);


        menuItemCategorias = new JMenuItem("Categorias",
                KeyEvent.VK_C);
        menuItemCategorias.setActionCommand(Comandos.CATEGORIAS.name());
        menuItemCategorias.addActionListener(this.al);
        menu.add(menuItemCategorias);

        menuItemProyectos = new JMenuItem("Proyectos",
                KeyEvent.VK_C);
        menuItemProyectos.setActionCommand(Comandos.PROYECTOS.name());
        menuItemProyectos.addActionListener(this.al);
        menu.add(menuItemProyectos);

        menuItemTareas = new JMenuItem("Tareas",
                KeyEvent.VK_C);
        menuItemTareas.setActionCommand(Comandos.TAREAS.name());        
        menuItemTareas.addActionListener(this.al);
        menu.add(menuItemTareas);

        menu.addSeparator();

        menuItemSalir = new JMenuItem("Salir", KeyEvent.VK_C);
        menuItemSalir.setActionCommand(Comandos.SALIR.name());        
        menuItemSalir.addActionListener(this.al);
        menu.add(menuItemSalir);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(new PanelProyectos().build(), Comandos.PROYECTOS.toString());
        cards.add(new PanelPrioridad().build(), Comandos.CATEGORIAS.toString());
        cards.add(new PanelTareas().build(), Comandos.TAREAS.toString());
        jframe.setContentPane(cards);
        jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
        jframe.setJMenuBar(menuBar);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        return jframe; 
    }
    
    private ActionListener al = (e ) -> {
        switch(Comandos.valueOf(e.getActionCommand())){
            case CATEGORIAS:
                ((CardLayout) cards.getLayout()).show(cards, Comandos.CATEGORIAS.toString());
                break;
             case PROYECTOS:
                ((CardLayout) cards.getLayout()).show(cards, Comandos.PROYECTOS.toString());
                break;
             case TAREAS:
                ((CardLayout) cards.getLayout()).show(cards, Comandos.TAREAS.toString());
                break;
             case SALIR:
                 System.exit(1);
                 
        }
    };
    
    

}
