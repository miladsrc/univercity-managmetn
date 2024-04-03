package service;

import base.service.BaseServiceImpl;
import domain.Student;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl extends BaseServiceImpl<Integer, Student, StudentRepository> implements StudentService{

    public StudentServiceImpl(StudentRepository repository) {
        super ( repository );
    }


    @Override
    public void save(Student entity) throws SQLException {
        super.save ( entity );
    }

    @Override
    public Student findBYId(Integer integer) throws SQLException {
        return repository.findById(Student.class, integer);
    }

    @Override
    public void update(Student entity) throws SQLException {
        super.update ( entity );
    }

    @Override
    public void delete(Integer id) throws SQLException {
        super.delete ( Student.class,id );
    }

    @Override
    public boolean contains(Integer id) throws SQLException {
        return super.contains( Student.class, id );
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll(Student.class);
    }
}
