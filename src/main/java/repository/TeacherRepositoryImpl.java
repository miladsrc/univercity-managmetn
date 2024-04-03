package repository;

import base.repsitory.BaseRepositoryImpl;
import domain.Teacher;
import org.hibernate.SessionFactory;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Integer, Teacher> implements TeacherRepository{
    public TeacherRepositoryImpl(SessionFactory sessionFactory) {
        super ( sessionFactory );
    }

}
