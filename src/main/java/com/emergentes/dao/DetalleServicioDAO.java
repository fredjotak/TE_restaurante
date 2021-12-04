package com.emergentes.dao;

import com.emergentes.modelo.DetalleServicio;
import java.util.List;

public interface DetalleServicioDAO {
    public void insert(DetalleServicio detalleServicio) throws Exception;
    public void update(DetalleServicio detalleServicio) throws Exception;
    public void delete(int id) throws Exception;
    public DetalleServicio getById(int id) throws Exception;
    public List<DetalleServicio> getAll() throws Exception;
    public List<DetalleServicio> getListIdServicio(int idServicio) throws Exception;
}