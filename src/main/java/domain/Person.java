package domain;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Person.TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity<Integer> {

    public static final String TABLE = "person";
    public static final String ID = "person_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String BIRTH_DATE = "birth_date";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Person.ID)
    Long id;

    @Column(name = Person.FIRST_NAME)
    String firstName;

    @Column(name = Person.LAST_NAME)
    String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = Person.BIRTH_DATE)
    Date birthDate;

    public Person(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer ().getPersistentClass () : o.getClass ();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer ().getPersistentClass () : this.getClass ();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Person person = (Person) o;
        return getId () != null && Objects.equals ( getId (), person.getId () );
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer ().getPersistentClass ().hashCode () : getClass ().hashCode ();
    }
}

