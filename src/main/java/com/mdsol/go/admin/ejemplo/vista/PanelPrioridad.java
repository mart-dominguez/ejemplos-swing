/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author martdominguez
 */
public class PanelPrioridad {
    private JPanel panel;
    private JLabel lblNombre;
    private JTextField txtNombre;
    
    
    public JPanel build(){
        panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitulo = new JLabel("Prioridades");
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.anchor= GridBagConstraints.NORTH;
        panel.add(lblTitulo,gbc);
        
        
        lblNombre = new JLabel("Nombre: ");
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.anchor= GridBagConstraints.LINE_START;
        panel.add(lblNombre,gbc);
        
        txtNombre = new JTextField(20);
        gbc.gridx=1;
        panel.add(txtNombre,gbc);
        
        
        return panel;
    }
}
