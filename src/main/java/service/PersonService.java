package service;

import base.service.BaseService;
import domain.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService extends BaseService<Integer, Person> {
    boolean contains(Integer id) throws SQLException;
    List<Person> findAll();
}
