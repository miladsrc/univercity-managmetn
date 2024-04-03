package repository;

import base.repsitory.BaseRepositoryImpl;
import domain.Student;
import org.hibernate.SessionFactory;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Integer, Student> implements StudentRepository{
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        super ( sessionFactory );
    }

}
