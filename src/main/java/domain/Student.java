package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Student.COLUM_LABEL)
public class Student extends Person {

    public static final String COLUM_LABEL = "student";
    private static final String TABLE_NAME = "student_id";
    private static final String FIELD_NAME = "field_of_study";
    private static final String ENTERY_DATE = "entery_year";


    @Column(name = Student.TABLE_NAME)
    private Long studentCode;

    @Column(name = Student.FIELD_NAME)
    private String fieldOfStudy;

    @Column(name = Student.ENTERY_DATE)
    private int entryYear;


    public Student( String firstName, String lastName, java.util.Date birthDate, Long studentCode, String fieldOfStudy, int entryYear) {
        super (firstName, lastName, birthDate );
        this.studentCode = studentCode;
        this.fieldOfStudy = fieldOfStudy;
        this.entryYear = entryYear;
    }

    public Student(String fieldOfStudy, int entryYear) {
        this.fieldOfStudy = fieldOfStudy;
        this.entryYear = entryYear;
    }
}
