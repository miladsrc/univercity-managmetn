import domain.Person;
import domain.Rank;
import domain.Student;
import domain.Teacher;
import project.util.ApplicationContext;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner ( System.in );
    static SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner ( System.in );
        int option;
        do {
            System.out.println ( "Welcome to the School Management System" );
            System.out.println ( "1. Manage Teachers" );
            System.out.println ( "2. Manage Students" );
            System.out.println ( "3. Exit" );
            System.out.print ( "Enter your choice: " );
            option = scanner.nextInt ();

            switch (option) {
                case 1 -> manageTeachers ();
                case 2 -> manageStudents ();
                case 3 -> System.out.println ( "Exiting the system..." );
                default -> System.out.println ( "Invalid option! Please try again." );
            }
        } while (option != 3);
    }

    private static void manageTeachers() throws SQLException {
        Scanner scanner = new Scanner ( System.in );
        int choice;
        do {
            System.out.println ( "Teacher Management" );
            System.out.println ( "1. Add Teacher" );
            System.out.println ( "2. View Teacher" );
            System.out.println ( "3. Update Teacher" );
            System.out.println ( "4. Delete Teacher" );
            System.out.println ( "5. Check if Teacher Exists (contains)" );
            System.out.println ( "6. Load All Teachers (loadAll)" );
            System.out.println ( "7. Return to Main Menu" );
            System.out.print ( "Enter your choice: " );
            choice = scanner.nextInt ();

            switch (choice) {
                case 1 -> addTeacher ();
                case 2 -> findTeacher ();
                case 3 -> updateTeacher ();
                case 4 -> deleteTeacher ();
                case 5 -> containTeacher ();
                case 6 -> fetchAllTeachers ();
                case 7 -> {
                    main ( null );
                }
                default -> System.out.println ( "Invalid choice! Please try again." );
            }
        } while (true);
    }

    private static void fetchAllTeachers() {
        try {
            System.out.println ( "Fetching all teachers..." );
            List<Teacher> teachers = ApplicationContext.getTeacherService ().findAll ( Teacher.class );
            if (teachers.isEmpty ()) {
                System.out.println ( "There are no teachers to display." );
            } else {
                for (Teacher teacher : teachers) {
                    System.out.println ( teacher );
                }
            }
        } catch (Exception e) {
            System.out.println ( "An error occurred while fetching teachers: " + e.getMessage () );
        }
    }

    private static void containTeacher() {
        try {
            System.out.print ( "Please enter the teacher ID to check existence: " );
            Integer id = scanner.nextInt ();
            scanner.nextLine ();

            boolean exists = ApplicationContext.getTeacherService ().contains ( Teacher.class, id );
            if (exists) {
                System.out.println ( "A teacher with ID " + id + " exists." );
            } else {
                System.out.println ( "No teacher found with ID " + id + "." );
            }
        } catch (InputMismatchException ime) {
            System.out.println ( "Invalid input. Please enter a valid numeric ID." );
            scanner.nextLine (); // to consume the incorrect input
        } catch (Exception e) {
            System.out.println ( "An error occurred while checking for teacher existence: " + e.getMessage () );
        }
    }


    private static void deleteTeacher() {
        try {
            System.out.print ( "Please enter the teacher ID to delete: " );
            int id = scanner.nextInt ();
            scanner.nextLine ();
            ApplicationContext.getTeacherService ().delete ( Teacher.class, id );
            System.out.println ( "Teacher with ID " + id + " was successfully deleted." );
        } catch (InputMismatchException ime) {
            System.out.println ( "Invalid input. Please enter a valid numeric ID." );
            scanner.nextLine (); // to consume the incorrect input
        } catch (SQLException sqle) {
            System.out.println ( "A SQL error occurred: " + sqle.getMessage () );
        } catch (Exception e) {
            System.out.println ( "An error occurred: " + e.getMessage () );
        }
    }

    private static void findTeacher() {
        try {
            System.out.print ( "Please enter the teacher ID to find: " );
            Long id = scanner.nextLong ();
            scanner.nextLine ();
            Teacher teacher = ApplicationContext.getTeacherService ().findBYId ( Teacher.class, Math.toIntExact ( id ) );
            if (teacher != null) {
                System.out.println ( "Teacher found: " + teacher.toString () );
            } else {
                System.out.println ( "Teacher with ID " + id + " not found." );
            }
        } catch (InputMismatchException ime) {
            System.out.println ( "Invalid input. Please enter a valid numeric ID." );
            scanner.nextLine (); // to consume the incorrect input
        } catch (ArithmeticException ae) {
            System.out.println ( "Numeric overflow. The ID is too large." );
        } catch (SQLException sqle) {
            System.out.println ( "A SQL error occurred: " + sqle.getMessage () );
        } catch (Exception e) {
            System.out.println ( "An error occurred: " + e.getMessage () );
        }
    }

    private static void updateTeacher() throws SQLException {
        System.out.print ( "Please enter the teacher ID to update: " );
        int id = scanner.nextInt ();
        scanner.nextLine ();

        Teacher teacher = ApplicationContext.getTeacherService ().findBYId ( Teacher.class, id );
        if (teacher != null) {
            System.out.println ( "Teacher found: " + teacher.toString () );
            System.out.print ( "Enter new first name or press enter to skip: " );
            String firstName = scanner.nextLine ();

            if (!firstName.isEmpty ()) {
                teacher.setFirstName ( firstName );
            }

            System.out.print ( "Enter new last name or press enter to skip: " );
            String lastName = scanner.nextLine ();
            if (!lastName.isEmpty ()) {
                teacher.setLastName ( lastName );
            }

            System.out.print ( "Enter birth date (YYYY-MM-DD): " );
            String birthDateString = scanner.nextLine ();
            Date birthDate = null;

            while (birthDate == null) {
                try {
                    java.util.Date parsedDate = dateFormat.parse ( birthDateString );
                    birthDate = new Date ( parsedDate.getTime () );
                } catch (ParseException e) {
                    System.out.println ( "Invalid date format! Please enter the date in YYYY-MM-DD format." );
                    birthDateString = scanner.nextLine ();
                }
            }

            System.out.print ( "Enter new degree or press enter to skip: " );
            String degree = scanner.nextLine ();
            if (!degree.isEmpty ()) {
                teacher.setDegree ( degree );
            }

            System.out.println ( "Enter teacher's rank (ASSISTANT, ASSOCIATE, FULL):" );
            String rankString = scanner.nextLine ();
            Rank rank = null;
            switch (rankString.toUpperCase ()) {
                case "ASSISTANT" -> rank = Rank.ASSISTANT;
                case "ASSOCIATE" -> rank = Rank.ASSOCIATE;
                case "FULL" -> rank = Rank.FULL;
                default -> {
                    System.out.println ( "Invalid rank! Please enter ASSISTANT, ASSOCIATE, or FULL." );
                    return;
                }
            }

            System.out.print ( "Enter new monthly salary or press enter to skip: " );
            String salaryString = scanner.nextLine ();
            if (!salaryString.isEmpty ()) {
                double monthlySalary = Double.parseDouble ( salaryString );
                teacher.setMonthlySalary ( monthlySalary );
            }

            ApplicationContext.getTeacherService ().update ( teacher );
            System.out.println ( "Teacher updated successfully!" );
        } else {
            System.out.println ( "Teacher with ID " + id + " not found." );
        }
    }

    private static void addTeacher() {

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );

        System.out.println ( "Enter teacher's first name:" );
        String firstName = scanner.nextLine ();

        System.out.println ( "Enter teacher's last name:" );
        String lastName = scanner.nextLine ();

        System.out.print ( "Enter birth date (YYYY-MM-DD): " );
        String birthDateString = scanner.nextLine ();
        Date birthDate = null;

        while (birthDate == null) {
            try {
                java.util.Date parsedDate = dateFormat.parse ( birthDateString );
                birthDate = new Date ( parsedDate.getTime () );
            } catch (ParseException e) {
                System.out.println ( "Invalid date format! Please enter the date in YYYY-MM-DD format." );
                birthDateString = scanner.nextLine ();
            }
        }

        System.out.println ( "Enter teacher's ID:" );
        Long teacherId = scanner.nextLong ();
        scanner.nextLine ();

        System.out.println ( "Enter teacher's degree:" );
        String degree = scanner.nextLine ();

        System.out.println ( "Enter teacher's rank (ASSISTANT, ASSOCIATE, FULL):" );
        String rankString = scanner.nextLine ();
        Rank rank = null;

        switch (rankString.toUpperCase ()) {
            case "ASSISTANT" -> rank = Rank.ASSISTANT;
            case "ASSOCIATE" -> rank = Rank.ASSOCIATE;
            case "FULL" -> rank = Rank.FULL;
            default -> {
                System.out.println ( "Invalid rank! Please enter ASSISTANT, ASSOCIATE, or FULL." );
                return;
            }
        }

        System.out.println ( "Enter teacher's monthly salary:" );
        double monthlySalary = scanner.nextDouble ();
        scanner.nextLine ();

        Teacher teacher = new Teacher ( teacherId, firstName, lastName, birthDate, teacherId, degree, rank, monthlySalary );
        try {
            ApplicationContext.getTeacherService ().save ( teacher );
            System.out.println ( "Teacher added successfully!" );
        } catch (Exception e) {
            System.out.println ( "An error occurred while adding the teacher: " + e.getMessage () );
        }
    }

    private static void manageStudents() throws SQLException {
        Scanner scanner = new Scanner ( System.in );
        int choice;
        do {
            System.out.println ( "Student Management" );
            System.out.println ( "1. Add Student" );
            System.out.println ( "2. View Student" );
            System.out.println ( "3. Update Student" );
            System.out.println ( "4. Delete Student" );
            System.out.println ( "5. Check if Student Exists (contains)" );
            System.out.println ( "6. Load All Students (loadAll)" );
            System.out.println ( "7. Return to Main Menu" );
            System.out.print ( "Enter your choice: " );
            choice = scanner.nextInt ();

            switch (choice) {
                case 1:
                    addStudent ();
                    break;
                case 2:
                    viewStudent ();
                    break;
                case 3:
                    updateStudent ();
                    break;
                case 4:
                    deleteStudent ();
                    break;
                case 5:
                    contain ();
                    break;
                case 6:
                    fetchAllStudent ();
                    break;
                case 7:
                    main ( null );
                default:
                    System.out.println ( "Invalid choice! Please try again." );
            }
        } while (choice != 7);
    }


    //---------------------------STUDENT MANAGEMENT-------------------------------------

    private static void fetchAllStudent() {
        try {
            System.out.println ( "Fetching all students..." );
            List<Student> students = ApplicationContext.getStudentService ().findAll ( Student.class );
            if (students.isEmpty ()) {
                System.out.println ( "There are no students to display." );
            } else {
                for (Student student : students) {
                    System.out.println ( student );
                }
            }
        } catch (Exception e) {
            System.out.println ( "An unexpected error occurred: " + e.getMessage () );
        }
    }

    private static void contain() {
        try {
            System.out.print ( "Please enter the student ID to check existence: " );
            Integer id = scanner.nextInt ();
            scanner.nextLine ();

            boolean exists = ApplicationContext.getStudentService ().contains ( id );
            if (exists) {
                System.out.println ( "A student with ID " + id + " exists." );
            } else {
                System.out.println ( "No student found with ID " + id + "." );
            }
        } catch (InputMismatchException ime) {
            System.out.println ( "Invalid input. Please enter a valid numeric ID." );
            scanner.nextLine (); // to consume the incorrect input
        } catch (SQLException sqle) {
            System.out.println ( "A SQL error occurred while checking for student existence: " + sqle.getMessage () );
        } catch (Exception e) {
            System.out.println ( "An unexpected error occurred: " + e.getMessage () );
        }
    }


    private static void deleteStudent() {
        Scanner scanner = new Scanner ( System.in );
        try {
            System.out.print ( "Please enter the person ID to delete: " );
            Integer id = scanner.nextInt ();
            scanner.nextLine ();

            ApplicationContext.getPersonServicee ().delete ( Person.class, id );

            System.out.println ( "Person with ID " + id + " was successfully deleted." );
        } catch (InputMismatchException ime) {
            System.out.println ( "Invalid input. Please enter a valid numeric ID." );
            scanner.nextLine ();
        } catch (SQLException sqle) {
            System.out.println ( "A SQL error occurred: " + sqle.getMessage () );
        } catch (Exception e) {
            System.out.println ( "An unexpected error occurred: " + e.getMessage () );
        } finally {
            scanner.close ();
        }
    }


    private static void updateStudent() throws SQLException {

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
        System.out.print ( "Please enter the person ID to update: " );
        Long id = scanner.nextLong ();
        scanner.nextLine ();

        Student student = ApplicationContext.getStudentService ().findBYId ( Student.class, Math.toIntExact ( id ) );

        System.out.print ( "Enter new field of study or press enter to skip: " );
        String fieldOfStudy = scanner.nextLine ();
        if (!fieldOfStudy.isEmpty ()) {
            student.setFieldOfStudy ( fieldOfStudy );
        }

        System.out.print ( "Enter new entry year or press enter to skip: " );
        String entryYearString = scanner.nextLine ();
        if (!entryYearString.isEmpty ()) {
            int entryYear = Integer.parseInt ( entryYearString );
            student.setEntryYear ( entryYear );
        }
        ApplicationContext.getStudentService ().update ( student );
        System.out.println ( "Student updated successfully!" );
    }


    private static void viewStudent() throws SQLException {
        System.out.print ( "Please enter the student ID to view: " );
        Long id = scanner.nextLong ();
        scanner.nextLine ();

        Student student = ApplicationContext.getStudentService ().findBYId ( Student.class, Math.toIntExact ( id ) );
        if (student != null) {
            System.out.println ( "Student found: " + student.toString () );
        } else {
            System.out.println ( "Student with ID " + id + " not found." );
        }
    }

    private static void addStudent() {
        Scanner scanner = new Scanner ( System.in );
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
            System.out.println ( "Welcome to the Student Creation Menu" );
            System.out.print ( "Enter first name: " );
            String firstName = scanner.nextLine ();

            System.out.print ( "Enter last name: " );
            String lastName = scanner.nextLine ();

            System.out.print ( "Enter birth date (YYYY-MM-DD): " );
            String birthDateString = scanner.nextLine ();
            Date birthDate = null;

            while (birthDate == null) {
                try {
                    java.util.Date parsedDate = dateFormat.parse ( birthDateString );
                    birthDate = new Date ( parsedDate.getTime () );
                } catch (ParseException e) {
                    System.out.println ( "Invalid date format! Please enter the date in YYYY-MM-DD format." );
                    birthDateString = scanner.nextLine ();
                }
            }

            System.out.print ( "Enter student code: " );
            Long studentCode = scanner.nextLong ();
            scanner.nextLine ();

            System.out.print ( "Enter field of study: " );
            String fieldOfStudy = scanner.nextLine ();

            System.out.print ( "Enter entry year: " );
            int entryYear = scanner.nextInt ();

            Student student = new Student ( firstName, lastName, birthDate, studentCode, fieldOfStudy, entryYear );
            ApplicationContext.getStudentService ().save ( student );
            System.out.println ( "Student created successfully!" );
        } catch (InputMismatchException ime) {
            System.out.println ( "Invalid input. Please enter a valid numeric ID or year." );
            scanner.nextLine ();
        } catch (SQLException sqle) {
            System.out.println ( "A SQL error occurred: " + sqle.getMessage () );
        } catch (Exception e) {
            System.out.println ( "An unexpected error occurred: " + e.getMessage () );
        } finally {
            scanner.close ();
        }
    }
}






