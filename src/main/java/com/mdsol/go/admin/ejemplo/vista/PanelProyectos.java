/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import com.mdsol.go.admin.ejemplo.model.ProyectoModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author martdominguez
 */
public class PanelProyectos {
    private JPanel panel;
    private JLabel lblNombre;
    private JLabel lblPresupuesto;
    private JTextField txtNombre;
    private JTextField txtPresupuesto;
    private JButton botonAddProyecto;
    private JTable tablaProyectos;
    private ProyectoModel modelo;
    
    public PanelProyectos(){
        modelo = new ProyectoModel();
    }
    
    public JPanel build(){
        panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitulo = new JLabel("Información de proyectos");
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.anchor= GridBagConstraints.NORTH;
        panel.add(lblTitulo,gbc);
        
        
        lblNombre = new JLabel("Nombre de Proyecto: ");
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.anchor= GridBagConstraints.LINE_START;
        panel.add(lblNombre,gbc);
        
        txtNombre = new JTextField(20);
        gbc.gridx=1;
        panel.add(txtNombre,gbc);
        
        lblPresupuesto = new JLabel("Nombre de Proyecto: ");
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(lblPresupuesto,gbc);
                
        txtPresupuesto = new JTextField(8);
        gbc.gridx=1;
        panel.add(txtPresupuesto,gbc);        

        gbc.gridy=3;
        gbc.gridwidth=2;
        botonAddProyecto = new JButton();
        botonAddProyecto.addActionListener((e) ->{
            modelo.crearProyecto(txtNombre.getText(),Double.valueOf(txtPresupuesto.getText()) );            
        });
        panel.add(botonAddProyecto,gbc);        

        gbc.gridy=4;
        gbc.gridwidth=2;
        tablaProyectos = new JTable(new ProyectoTableModel(modelo.listarProyectos()));
        panel.add(tablaProyectos);
        return panel;
    }
}
