package com.forezp.api.DB.service.able;

import java.util.List;

public interface PublicService<T> {

    T add(T t);

    void delete(String id);

    T update(T t);

    T findById(String id);

    List<T> findAll();

}