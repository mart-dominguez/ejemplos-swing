/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.dao;

import com.mdsol.go.admin.ejemplo.entidades.Tarea;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface TareaDao {
    public void crear(Tarea t);
    public void actualizar(Tarea t);
    public void borrar(Tarea t);
    public Tarea buscar(Integer id);
    public List<Tarea> buscar();
}
