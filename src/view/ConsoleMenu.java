package view;

import exception.InvalidCourseException;
import model.Course;
import model.Student;
import service.StudentService;

import java.util.Scanner;

public class ConsoleMenu {

    private StudentService studentService = new StudentService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Manage Courses for a Student");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consommer le \n

            switch (choice) {
                case 1 -> studentService.addStudent();
                case 2 -> studentService.viewStudents();
                case 3 -> studentService.updateStudent();
                case 4 -> studentService.deleteStudent();
                case 5 -> manageCoursesMenu();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void manageCoursesMenu() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        Student student = studentService.getStudentById(studentId);
        if(student == null) {
            System.out.println("Student not found.");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Manage Courses for " + student.getName() + " ---");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Remove Course");
            System.out.println("4. View Courses");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Course name: ");
                    String name = scanner.nextLine();
                    System.out.print("Course note (0-20): ");
                    float note = scanner.nextFloat();
                    scanner.nextLine();
                    try {
                        Course course = new Course.CourseBuilder().withName(name).withNote(note).build();
                        student.addCourse(course);
                    } catch (InvalidCourseException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Course name to update: ");
                    String name = scanner.nextLine();
                    System.out.print("New note: ");
                    float newNote = scanner.nextFloat();
                    scanner.nextLine();
                    student.updateCourse(name, newNote);
                }
                case 3 -> {
                    System.out.print("Course name to remove: ");
                    String name = scanner.nextLine();
                    student.removeCourse(name);
                }
                case 4 -> {
                    System.out.println("Courses for " + student.getName() + ":");
                    for(Course c : student.getCourses()) {
                        System.out.println(c);
                    }
                }
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
