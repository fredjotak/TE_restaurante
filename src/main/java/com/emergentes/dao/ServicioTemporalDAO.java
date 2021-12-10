package com.emergentes.dao;

import com.emergentes.modelo.ServicioTemporal;
import java.util.List;

public interface ServicioTemporalDAO {
    public void insert(ServicioTemporal servicioTemporal) throws Exception;
    public void update(ServicioTemporal servicioTemporal) throws Exception;
    public void delete(int id) throws Exception;
    public ServicioTemporal getById(int id) throws Exception;
    public List<ServicioTemporal> getAll() throws Exception;
    public List<ServicioTemporal> getFilterIdMesa(int idMesa) throws Exception;
}