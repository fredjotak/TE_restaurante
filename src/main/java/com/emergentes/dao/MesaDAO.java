package com.emergentes.dao;

import com.emergentes.modelo.Mesa;
import java.util.List;

public interface MesaDAO {
    public void insert(Mesa mesa) throws Exception;
    public void update(Mesa mesa) throws Exception;
    public void delete(int id) throws Exception;
    public void ocupar(int id) throws Exception;
    public void desocupar(int id) throws Exception;
    public Mesa getById(int id) throws Exception;
    public List<Mesa> getAll() throws Exception;
    public List<Mesa> getFilterNombre(String texto) throws Exception;
}