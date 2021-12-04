package com.emergentes.dao;

import com.emergentes.modelo.Comentario;
import java.util.List;

public interface ComentarioDAO {
    public void insert(Comentario comentario) throws Exception;
    public void update(Comentario comentario) throws Exception;
    public void delete(int id) throws Exception;
    public Comentario getById(int id) throws Exception;
    public List<Comentario> getAll() throws Exception;
}