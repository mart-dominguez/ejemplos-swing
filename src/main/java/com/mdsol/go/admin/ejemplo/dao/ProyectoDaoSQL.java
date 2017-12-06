/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsol.go.admin.ejemplo.dao;

import com.mdsol.go.admin.ejemplo.entidades.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdominguez
 */
public class ProyectoDaoSQL implements ProyectoDao {

    private static final String SQL_INSERT = "INSERT INTO PROYECTO (NOMBRE,PRESUPUESTO) VALUES (?,?)";
    private static final String SQL_DELETE = "DELETE FROM PROYECTO WHERE ID = ?";
    private static final String SQL_UPDATE = "UPDATE PROYECTO SET NOMBRE=?,PRESUPUESTO=? WHERE ID = ?";
    private static final String SQL_FIND_ALL = "SELECT ID,NOMBRE,PRESUPUESTO FROM PROYECTO ";
    private static final String SQL_FIND_BY_ID = "SELECT ID,NOMBRE,PRESUPUESTO FROM PROYECTO WHERE ID = ?";

    @Override
    public void crear(Proyecto t) {
        Connection conn;
        PreparedStatement pstm;
        try {
            conn = DBConnection.getInstance().getConnection();
            pstm = conn.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, t.getNombre());
            pstm.setDouble(2, t.getPresupuesto());

            int filasAfectadas = pstm.executeUpdate();

            // el ID unico lo genera la base de datos por lo tanto lo tenemos que recupear con el siguiente codigo
            // y luego asignarselo al producto
            if (filasAfectadas >= 0) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {

                        t.setId(generatedKeys.getInt(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProyectoDaoSQL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Proyecto t) {
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE)) {
            pstm.setInt(3, t.getId());
            pstm.setString(1, t.getNombre());
            pstm.setDouble(2, t.getPresupuesto());

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDaoSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void borrar(Proyecto t) {
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE)) {
            pstm.setInt(1, t.getId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Proyecto buscar(Integer id) {
        Connection conn = DBConnection.getInstance().getConnection();
        Proyecto p = null;
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BY_ID)) {
            pstm.setInt(1, id);
            try (ResultSet resultado = pstm.executeQuery()) {
                if (resultado.next()) {
                    p = new Proyecto();
                    p.setId(resultado.getInt(1));
                    p.setNombre(resultado.getString(2));
                    p.setPresupuesto(resultado.getDouble(3));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<Proyecto> buscar() {
        Connection conn = DBConnection.getInstance().getConnection();
        List<Proyecto> lista = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_ALL)) {
            try (ResultSet resultado = pstm.executeQuery()) {
                while (resultado.next()) {
                    Proyecto p = new Proyecto();
                    p.setId(resultado.getInt(1));
                    p.setNombre(resultado.getString(2));
                    p.setPresupuesto(resultado.getDouble(3));
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
