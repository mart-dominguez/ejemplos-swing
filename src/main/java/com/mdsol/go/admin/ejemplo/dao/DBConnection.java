/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author martdominguez
 */
public class DBConnection {
    
    private static DBConnection _instance;       
    
    private Connection myConn;
    
    public static DBConnection getInstance(){
        if(_instance==null) _instance = new DBConnection();
        return _instance;
    }
    
    private DBConnection(){
    }
      
    
    public Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofa2", "root", "root");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }


}

