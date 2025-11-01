package view;

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
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    studentService.viewStudents();
                    break;
                case 3:
                    studentService.updateStudent();
                    break;
                case 4:
                    studentService.deleteStudent();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

}
