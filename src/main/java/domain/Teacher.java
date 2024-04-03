package domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Teacher.COLUMN_NAME)
public class Teacher extends Person {

    public static final String COLUMN_NAME = "teacher";
    public static final String TEACHER_ID = "teacher_id";
    public static final String TEACHER_DEGREE = "teacher_degree";
    public static final String TEACHER_RANK = "teacher_rank";
    public static final String TEACHER_SALARY = "teacher_salary";

    public Teacher( String firstName, String lastName, Date birthDate, Long teacherId, String degree, Rank rank, double monthlySalary) {
        super ( firstName, lastName, birthDate );
        this.teacherId = teacherId;
        this.degree = degree;
        this.rank = rank;
        this.monthlySalary = monthlySalary;
    }

    @Column(name = Teacher.TEACHER_ID)
    private Long teacherId;

    @Column(name = Teacher.TEACHER_DEGREE)
    private String degree;

    @Enumerated( EnumType.STRING )
    @Column(name = Teacher.TEACHER_RANK)
    private Rank rank;

    @Column(name = Teacher.TEACHER_SALARY)
    private double monthlySalary;


}