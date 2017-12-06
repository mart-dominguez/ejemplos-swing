/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import com.mdsol.go.admin.ejemplo.entidades.Proyecto;
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
import javax.swing.ListSelectionModel;
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
    private ProyectoTableModel modeloTabla;
    
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
        
        gbc.gridy=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        botonAddProyecto = new JButton(" AGREGAR ");
        botonAddProyecto.addActionListener((e) ->{
            modelo.crearProyecto(txtNombre.getText(),Double.valueOf(txtPresupuesto.getText()) );      
            //fireTableRowsInserted
            int ultimaFila = modelo.listarProyectos().size()-1;
            modeloTabla.fireTableRowsInserted(ultimaFila,ultimaFila );
        });
        panel.add(botonAddProyecto,gbc);        

        
        

        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=2;
        modeloTabla = new ProyectoTableModel(modelo.listarProyectos());
        tablaProyectos = new JTable(modeloTabla);
        panel.add(tablaProyectos);
        ListSelectionModel selectionModel = tablaProyectos.getSelectionModel();
        //tablaProyectos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //tablaProyectos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tablaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener((e) -> {
            int filaIndice = ((ListSelectionModel)e.getSource()).getMinSelectionIndex();
            Proyecto p = modeloTabla.getFila(filaIndice);
            txtNombre.setText(p.getNombre());
            txtPresupuesto.setText(p.getPresupuesto().toString());
            
        });
        
        return panel;
    }
}
