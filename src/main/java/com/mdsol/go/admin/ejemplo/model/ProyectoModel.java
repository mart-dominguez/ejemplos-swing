/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.model;

import com.mdsol.go.admin.ejemplo.dao.ProyectoDao;
import com.mdsol.go.admin.ejemplo.dao.ProyectoDaoSQL;
import com.mdsol.go.admin.ejemplo.entidades.Proyecto;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public class ProyectoModel {
    
    private ProyectoDao dao;
    
    public ProyectoModel(){
        dao = new ProyectoDaoSQL();
    }
    
    public Proyecto crearProyecto(String nombre,Double presupuesto){
        Proyecto p = new Proyecto(null,nombre,presupuesto);        
        this.dao.crear(p);
        System.out.println(p);
        return p;
    }

    public List<Proyecto> listarProyectos(){
        return this.dao.buscar();
    }

    
}
