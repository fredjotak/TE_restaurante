package com.emergentes.dao;

import com.emergentes.modelo.Servicio;
import java.util.List;

public interface ServicioDAO {
    public int insert(Servicio servicio) throws Exception;
    public void update(Servicio servicio) throws Exception;
    public void delete(int id) throws Exception;
    public Servicio getById(int id) throws Exception;
    public List<Servicio> getAll() throws Exception;
}