package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import java.util.List;

public interface ProductoDAO {
    public void insert(Producto producto) throws Exception;
    public void update(Producto producto) throws Exception;
    public void delete(String id) throws Exception;
    public Producto getById(String id) throws Exception;
    public List<Producto> getAll() throws Exception;
}