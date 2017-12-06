/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
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
public class PanelTareas {
    private JPanel panel;
    private JLabel lblNombre;
    private JLabel lblCosto;
    private JLabel lblDuracion;
    private JTextField txtNombre;
    private JTextField txtCosto;
    private JTextField txtDuracion;
    private JButton botonAgregar;
    private JTable jtable;
    
    
    public JPanel build(){
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Tareas"));

        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitulo = new JLabel("Información de TAREAS");
        lblTitulo.setBackground(Color.RED);
        lblTitulo.setOpaque(true);
        gbc.weightx=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor= GridBagConstraints.CENTER;
        
        panel.add(lblTitulo,gbc);
        
        
        lblNombre = new JLabel("Descripcion: ");
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.anchor= GridBagConstraints.FIRST_LINE_START;
        panel.add(lblNombre,gbc);
        
        txtNombre = new JTextField(20);
        gbc.gridx=1;
        panel.add(txtNombre,gbc);
        
        lblCosto = new JLabel("Costo Hora: ");
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(lblCosto,gbc);
        
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.00);
        formatter.setMaximum(99999.99);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(false);
        txtCosto = new JFormattedTextField(formatter);
        gbc.gridx=1;
        panel.add(txtCosto,gbc);
        
        
        lblDuracion = new JLabel("Duracion: ");
        gbc.gridx=0;
        gbc.gridy=3;
        panel.add(lblDuracion,gbc);
        
        NumberFormat format2 = NumberFormat.getInstance();
        NumberFormatter formatter2 = new NumberFormatter(format2);
        formatter2.setValueClass(Integer.class);
        formatter2.setMinimum(1);
        formatter2.setMaximum(999);
        formatter2.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(false);
        txtDuracion = new JFormattedTextField(formatter2);
        gbc.gridx=1;
        panel.add(txtDuracion,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        gbc.weighty=1;
        gbc.insets = new Insets(20,0,0,0);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill=GridBagConstraints.NONE;

        botonAgregar = new JButton("Guardar");
        panel.add(botonAgregar,gbc);
        return panel;
    }
}
