package connection.utill;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import domain.*;

public class SessionFactorySingleton {
    private SessionFactorySingleton() {
    }

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder ()
                    .configure ()
                    .build ();
            INSTANCE = new MetadataSources ( registry )
                    .addAnnotatedClass ( Person.class )
                    .addAnnotatedClass ( Teacher.class )
                    .addAnnotatedClass ( Student.class )
                    .buildMetadata ()
                    .buildSessionFactory ();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance ();
    }
}