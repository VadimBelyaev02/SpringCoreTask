package com.vadim.springtask.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, K> {
    List<T> findAll();

    Optional<T> findById(K id);

    T save(T t);

    T update(T t);

    void deleteById(K id);
}
