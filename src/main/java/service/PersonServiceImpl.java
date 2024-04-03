package service;

import base.service.BaseServiceImpl;
import domain.Person;
import repository.PersonRepository;

import java.sql.SQLException;
import java.util.List;

public class PersonServiceImpl extends BaseServiceImpl<Integer, Person, PersonRepository> implements PersonService{
    public PersonServiceImpl(PersonRepository repository) {
        super ( repository );
    }

    @Override
    public void save(Person entity) throws SQLException {
        super.save ( entity );
    }

    @Override
    public Person findBYId(Class<Person> T, Integer integer) throws SQLException {
        return repository.findById(T, integer);
    }

    @Override
    public void update(Person entity) throws SQLException {
        super.update ( entity );
    }

    @Override
    public void delete(Integer id) throws SQLException {
        super.delete ( Person.class,id );
    }

    @Override
    public boolean contains(Integer id) throws SQLException {
        return super.contains( Person.class, id );
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll(Person.class);
    }

}
