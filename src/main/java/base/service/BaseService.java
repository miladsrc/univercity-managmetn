package base.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<ID,TYPE>{
    void save(TYPE entity) throws SQLException;
    TYPE findBYId(Class<TYPE> T,ID id) throws SQLException;
    void update(TYPE entity) throws SQLException;
    void delete(Class<TYPE> T, Integer id) throws SQLException;
    boolean contains(Class<TYPE> entityClass, Integer id);
    List<TYPE> findAll(Class<TYPE> entityClass);
}