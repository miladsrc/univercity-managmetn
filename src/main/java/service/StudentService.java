package service;

import base.service.BaseService;
import domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService extends BaseService<Integer, Student> {
    Student findBYId(Integer integer) throws SQLException;
    boolean contains(Integer id) throws SQLException;
    List<Student> findAll();
}
