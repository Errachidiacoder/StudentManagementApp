package service;

import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Ajouter un étudiant
    public void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // éviter bug scanner
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Student student = new Student(id, name, email);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // Afficher tous les étudiants
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Mettre à jour un étudiant
    public void updateStudent() {
        System.out.print("Enter ID of student to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                s.setName(scanner.nextLine());
                System.out.print("Enter new email: ");
                s.setEmail(scanner.nextLine());
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Supprimer un étudiant
    public void deleteStudent() {
        System.out.print("Enter ID of student to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        students.removeIf(s -> s.getId() == id);
        System.out.println("Student deleted (if existed).");
    }
}
