/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.vista;

import javax.swing.SwingUtilities;

/**
 *
 * @author martdominguez
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Principal().build();
            }
        });
    }
        
}
