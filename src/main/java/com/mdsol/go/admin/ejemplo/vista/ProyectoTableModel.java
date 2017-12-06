/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import com.mdsol.go.admin.ejemplo.entidades.Proyecto;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author mdominguez
 */
public class ProyectoTableModel implements TableModel {
    
        private List<Proyecto> lista;
        private String[] columnas ={"ID","Descripcion","Presupuesto"};
        
        public ProyectoTableModel(List<Proyecto> lista){
            this.lista = lista;
        }
        
        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return this.columnas.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return this.columnas[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return this.columnas[columnIndex].getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0:
                    return lista.get(rowIndex).getId();
                case 1:
                    return lista.get(rowIndex).getNombre();
                case 2:
                    return lista.get(rowIndex).getPresupuesto();
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

        @Override
        public void addTableModelListener(TableModelListener l) {        
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
        }
    }

