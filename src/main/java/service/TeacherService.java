package service;

import base.service.BaseService;
import domain.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService extends BaseService<Integer, Teacher> {
    boolean contains(Integer id) throws SQLException;
    List<Teacher> findAll();
}
