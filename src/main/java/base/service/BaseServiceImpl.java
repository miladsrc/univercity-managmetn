package base.service;

import base.entity.BaseEntity;
import base.repsitory.BaseRepository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseServiceImpl<ID extends Serializable, TYPE extends BaseEntity<ID>, R extends BaseRepository<ID, TYPE>>
        implements BaseService<ID,TYPE> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(TYPE entity) throws SQLException {
        repository.save ( entity );
    }

    @Override
    public TYPE findBYId(Class<TYPE> entity, ID id) throws SQLException {
        return repository.findById ( entity, (Integer) id );
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        repository.update ( entity );
    }

    @Override
    public void delete(Class<TYPE> T, Integer id) throws SQLException {
        repository.deleteById (T,  id );
    }

    @Override
    public boolean contains(Class<TYPE> entityClass, Integer id) {
        return repository.contains ( entityClass, id );
    }

    @Override
    public List<TYPE> findAll(Class<TYPE> entityClass) {
        return repository.findAll ( entityClass );
    }

    public abstract void delete(Integer id) throws SQLException;
}
