package repository;


import base.repsitory.BaseRepository;
import base.repsitory.BaseRepositoryImpl;
import domain.Person;
import org.hibernate.SessionFactory;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Integer, Person> implements PersonRepository {
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        super ( sessionFactory );
    }

}
