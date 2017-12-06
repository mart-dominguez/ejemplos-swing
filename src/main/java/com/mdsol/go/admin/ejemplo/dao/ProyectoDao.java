/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.dao;

import com.mdsol.go.admin.ejemplo.entidades.Proyecto;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface ProyectoDao {
    public void crear(Proyecto t);
    public void actualizar(Proyecto t);
    public void borrar(Proyecto t);
    public Proyecto buscar(Integer id);
    public List<Proyecto> buscar();
}
