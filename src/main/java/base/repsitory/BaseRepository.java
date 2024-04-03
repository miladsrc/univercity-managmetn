package base.repsitory;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository <ID extends Serializable,TYPE extends BaseEntity<ID>> {

    <T> void save(T entity);

    <T> void update(T entity);

    <T> T findById(Class<T> entityClass, Integer id);

    boolean deleteById(Class<TYPE> entityType, Integer id);

    boolean contains(Class<TYPE> entityClass, Integer id);

    List<TYPE> findAll(Class<TYPE> entityClass);
}