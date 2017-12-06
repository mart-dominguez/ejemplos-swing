/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.dao;

import com.mdsol.go.admin.ejemplo.entidades.Proyecto;
import com.mdsol.go.admin.ejemplo.entidades.Tarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdominguez
 */
public class TareaDaoSQL implements TareaDao {

    private static final String SQL_INSERT = "INSERT INTO TAREA (DESCRIPCION,DURACION,COSTO_HORA,ID_PROYECTO) VALUES (?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM TAREA WHERE ID = ?";
    private static final String SQL_UPDATE = "UPDATE TAREA SET DESCRIPCION =?,DURACION=?,COSTO_HORA=?,ID_PROYECTO=? WHERE ID = ?";
    private static final String SQL_FIND_ALL = "SELECT ID,DESCRIPCION,DURACION,COSTO_HORA,ID_PROYECTO FROM TAREA ";
    private static final String SQL_FIND_BY_ID = "SELECT ID,DESCRIPCION,DURACION,COSTO_HORA,ID_PROYECTO FROM TAREA WHERE ID = ?";

    @Override
    public void crear(Tarea t) {
        Connection conn;
        PreparedStatement pstm;
        try {
            conn = DBConnection.getInstance().getConnection();
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, t.getDescripcion());
            pstm.setInt(2, t.getDuracion());
            pstm.setDouble(3, t.getCostoHora());
            if (t.getProyecto() != null) {
                pstm.setInt(4, t.getProyecto().getId());
            } else {
                pstm.setNull(4, Types.NULL);
            }

            int filasAfectadas = pstm.executeUpdate();

            // el ID unico lo genera la base de datos por lo tanto lo tenemos que recupear con el siguiente codigo
            // y luego asignarselo al producto
            if (filasAfectadas >= 0) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {

                        t.setId(generatedKeys.getInt(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TareaDaoSQL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TareaDaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Tarea t) {
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE)) {
            pstm.setInt(5, t.getId());
            pstm.setString(1, t.getDescripcion());
            pstm.setInt(2, t.getDuracion());
            pstm.setDouble(3, t.getCostoHora());
            if (t.getProyecto() != null) {
                pstm.setInt(4, t.getProyecto().getId());
            } else {
                pstm.setNull(4, Types.NULL);
            }
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TareaDaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void borrar(Tarea t) {
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE)) {
            pstm.setInt(1, t.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TareaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Tarea buscar(Integer id) {
        ProyectoDao proyectoDao = new ProyectoDaoSQL();

        Connection conn = DBConnection.getInstance().getConnection();
        Tarea t = null;
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BY_ID)) {
            pstm.setInt(1, id);
            try (ResultSet resultado = pstm.executeQuery()) {
                if (resultado.next()) {
                    t = new Tarea();
                    t.setId(resultado.getInt(1));
                    t.setDescripcion(resultado.getString(2));
                    t.setDuracion(resultado.getInt(3));
                    t.setCostoHora(resultado.getDouble(4));
                    t.setProyecto(proyectoDao.buscar(resultado.getInt(5)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public List<Tarea> buscar() {
        ProyectoDao proyectoDao = new ProyectoDaoSQL();
        Connection conn = DBConnection.getInstance().getConnection();
        List<Tarea> lista = null;
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_ALL)) {
            try (ResultSet resultado = pstm.executeQuery()) {
                while (resultado.next()) {
                    Tarea p = new Tarea();
                    p.setId(resultado.getInt(1));
                    p.setDescripcion(resultado.getString(2));
                    p.setDuracion(resultado.getInt(3));
                    p.setCostoHora(resultado.getDouble(4));
                    p.setProyecto(proyectoDao.buscar(resultado.getInt(5)));
                    lista.add(p);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
