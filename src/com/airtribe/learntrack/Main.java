package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addStudent(scanner);
                    case 2 -> viewStudents();
                    case 3 -> deactivateStudent(scanner);
                    case 4 -> addCourse(scanner);
                    case 5 -> viewCourses();
                    case 6 -> deactivateCourse(scanner);
                    case 7 -> enrollStudent(scanner);
                    case 8 -> viewEnrollments(scanner);
                    case 9 -> updateEnrollment(scanner);
                    case 0 -> {
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }

            } catch (InvalidInputException e) {
                System.out.println("Input Error: " + e.getMessage());
            }
            catch (EntityNotFoundException e) {
                System.out.println("Not Found: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Unexpected error occurred.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== LearnTrack Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Deactivate Student");
        System.out.println("4. Add Course");
        System.out.println("5. View Courses");
        System.out.println("6. Deactivate Course");
        System.out.println("7. Enroll Student in Course");
        System.out.println("8. View Enrollments by Student");
        System.out.println("9. Update Enrollment Status");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added: " + student.getDisplayName());
    }

    private static void viewStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        students.forEach(System.out::println);
    }

    private static void deactivateStudent(Scanner scanner) {
        System.out.print("Enter Student ID to deactivate: ");
        int id = Integer.parseInt(scanner.nextLine());
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Duration (weeks): ");
        int duration = Integer.parseInt(scanner.nextLine());

        Course course = courseService.addCourse(name, description, duration);
        System.out.println("Course added: " + course);
    }

    private static void viewCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        courses.forEach(System.out::println);
    }

    private static void deactivateCourse(Scanner scanner) {
        System.out.print("Enter Course ID to deactivate: ");
        int id = Integer.parseInt(scanner.nextLine());
        courseService.deactivateCourse(id);
        System.out.println("Course deactivated.");
    }

    private static void enrollStudent(Scanner scanner) {
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrollment successful: " + enrollment);
    }

    private static void viewEnrollments(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        enrollments.forEach(System.out::println);
    }

    private static void updateEnrollment(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments to update.");
            return;
        }

        Enrollment enrollment = enrollments.get(0); // simple approach for now

        System.out.print("Enter new status (COMPLETED / CANCELLED): ");
        EnrollmentStatus status = EnrollmentStatus.valueOf(scanner.nextLine().toUpperCase());

        enrollmentService.updateEnrollmentStatus(enrollment, status);
        System.out.println("Enrollment updated.");
    }
}
