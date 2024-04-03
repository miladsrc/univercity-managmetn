package project.util;

import connection.utill.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.*;
import service.*;

public class ApplicationContext {

    //SESSION FACTORY
    private static final SessionFactory sessionFactory;
    //REPOSITORY
    private static final PersonRepository PERSON_REPOSITORY;
    private static final TeacherRepository TEACHER_REPOSITORY;
    private static final StudentRepository STUDENT_REPOSITORY;
    //SERVICE
    private static final PersonService PERSON_SERVICE;
    private static final TeacherService TEACHER_SERVICE;
    private static final StudentService STUDENT_SERVICE;

    static {
        sessionFactory = SessionFactorySingleton.getInstance ();
        //REPOSITORY
        PERSON_REPOSITORY = new PersonRepositoryImpl ( sessionFactory );
        TEACHER_REPOSITORY = new TeacherRepositoryImpl ( sessionFactory );
        STUDENT_REPOSITORY = new StudentRepositoryImpl ( sessionFactory );
        //SERVICE
        PERSON_SERVICE = new PersonServiceImpl ( PERSON_REPOSITORY );
        TEACHER_SERVICE = new TeacherServiceImpl ( TEACHER_REPOSITORY );
        STUDENT_SERVICE = new StudentServiceImpl ( STUDENT_REPOSITORY );
    }

    //GETTER
    public static PersonService getPersonServicee() {
        return PERSON_SERVICE;
    }
    public static TeacherService getTeacherService() {
        return TEACHER_SERVICE;
    }
    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

}


