package dev.fernando.proyecto.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void delete(T entity);
}
