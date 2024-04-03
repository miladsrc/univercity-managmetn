package service;

import base.service.BaseServiceImpl;
import domain.Teacher;
import repository.TeacherRepository;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl extends BaseServiceImpl<Integer, Teacher, TeacherRepository> implements TeacherService {
    public TeacherServiceImpl(TeacherRepository repository) {
        super ( repository );
    }

    @Override
    public void save(Teacher entity) throws SQLException {
        super.save ( entity );
    }

    @Override
    public Teacher findBYId(Class<Teacher> T, Integer integer) throws SQLException {
        return repository.findById ( Teacher.class,integer);
    }

    @Override
    public void update(Teacher entity) throws SQLException {
        super.update ( entity );
    }

    @Override
    public void delete(Integer id) throws SQLException {
        super.delete ( Teacher.class, id );
    }

    @Override
    public boolean contains(Integer id) throws SQLException {
        return super.contains( Teacher.class, id );
    }

    @Override
    public List<Teacher> findAll() {
        return repository.findAll( Teacher.class);
    }
}
