package base.repsitory;

import base.entity.BaseEntity;
import connection.utill.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<ID extends Serializable, TYPE extends BaseEntity<ID>>
        implements BaseRepository<ID, TYPE> {

    protected SessionFactory sessionFactory = (SessionFactory) SessionFactorySingleton.getInstance ();

    public BaseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public <T> void save(T entity) {
        Transaction tx = null;
        T merge = null;
        try (Session session = sessionFactory.openSession ()) {
            tx = session.beginTransaction ();
            merge = (T) session.merge ( entity );
            tx.commit ();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback ();
            }
            e.printStackTrace ();
        }
    }

    @Override
    public <T> void update(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession ()) {
            tx = session.beginTransaction ();
            session.merge ( entity );
            tx.commit ();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback ();
            }
            e.printStackTrace ();
        }
    }

    @Override
    public <T> T findById(Class<T> entityClass, Integer id) {
        Session session = null;
        T entity = null;
        try {
            session = sessionFactory.openSession ();
            entity = session.find ( entityClass, id );
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            if (session != null) {
                session.close ();
            }
        }
        return entity;
    }

    @Override
    public boolean deleteById(Class<TYPE> entityType, Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        boolean isSuccess = false;
        try {
            TYPE entity = session.get(entityType, id);
            if (entity != null) {
                session.remove(entity);
                tx.commit();
                isSuccess = true;
            } else {
                tx.rollback();
                System.out.println("Entity with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isSuccess;
    }

    @Override
    public boolean contains(Class<TYPE> entityClass, Integer id) {
        Session session = sessionFactory.openSession();
        TYPE entity = session.find(entityClass, id);
        session.close();
        return entity != null;
    }

    @Override
    public List<TYPE> findAll(Class<TYPE> entityClass) {
        Session session = sessionFactory.openSession();
        Query<TYPE> query = session.createQuery("FROM " + entityClass.getName(), entityClass);
        List<TYPE> entities = (List<TYPE>) ((Query<?>) query).list();
        session.close();
        return entities;
    }

}