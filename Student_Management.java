import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Student_Management
 */
class Student {
    String name;
    int roll;
    String Grade;
    int Class;
    String phone;
    static Set<String> usedPhoneNumbers = new HashSet<>();
    static Set<Integer> completedroll = new HashSet<>();

    public Student(String name, int roll, String Grade, int Class, String phone) {

        this.name = name;
        this.Grade = Grade;
        this.Class = Class;

        if (!usedPhoneNumbers.contains(phone)) {
            this.phone = phone;
            usedPhoneNumbers.add(phone);
        } else {
            throw new IllegalArgumentException("Phone number must be unique.");
        }

        if (!completedroll.contains(roll)) {
            this.roll = roll;
            completedroll.add(roll);
        } else {
            throw new IllegalArgumentException("Roll number must be unique.");
        }
    }

    public String getname() {
        return name;
    }

    public int getroll() {
        return roll;
    }

    public String getGrade() {
        return Grade;
    }

    public int getclass() {
        return Class;
    }

    public String getnumber() {
        return phone;
    }

    public String complete() {
        return name + "\t" + roll + "\t" + Grade + "\t" + Class + "\t" + phone + "\t";
    }

}

class StudentDB {
    private List<Student> StDb = new ArrayList<>();
    private final String dataFile = "student_data.txt";

    public void addStudent(Student student) {
        StDb.add(student);
    }

    public void removeStudent(int rollNumber) {
        StDb.removeIf(student -> student.getroll() == rollNumber);
    }

    public Student findStudent(int roll) {
        for (Student std : StDb) {
            if (std.getroll() == roll) {
                return std;
            }
        }
        return null;
    }

    public List<Student> getall() {
        return StDb;
    }

    public void SaveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dataFile))) {
            for (Student student : StDb) {
                writer.println(student.complete());
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving data to file: " + e.getMessage());
        }
    }

    public void LoadFile() {
        try (Scanner scanner = new Scanner(new File(dataFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                if (data.length == 5) {
                    try {
                        String name = data[0];
                        int roll = Integer.parseInt(data[1]);
                        String grade = data[2];
                        int Class = Integer.parseInt(data[3]);
                        String phone = data[4];
                        Student student = new Student(name, roll, grade, Class, phone);
                        StDb.add(student);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing data from file: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error creating student from file data: " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid data format in file: " + line);
                }
            }
            System.out.println("Data loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + dataFile);
        } catch (@SuppressWarnings("hiding") IOException e) {
            System.out.println("Error occurred while reading data from file: " + e.getMessage());
        }
    }
}

public class Student_Management {
    public static void main(String[] args) {
        StudentDB sms = new StudentDB();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Class: ");
                    int Class = scanner.nextInt();
                    System.out.print("Enter Phone: ");
                    scanner.nextLine();
                    String phone = scanner.nextLine();
                    System.out.println();
                    try {
                        Student student = new Student(name, rollNumber, grade, Class, phone);
                        sms.addStudent(student);
                    } catch (IllegalArgumentException e) {
                        if (e.getMessage().equals("Phone number must be unique.")) {
                            System.out.println("Error: Phone number " + phone + " is already in use.");
                        } else if (e.getMessage().equals("Roll number must be unique.")) {
                            System.out.println(
                                    "Error: Roll number " + rollNumber + " is already assigned to another student.");
                        } else {
                            System.out.println("Unknown error occurred while creating student.");
                        }
                        System.out.println("Please recheck and re-enter the data.");
                    }

                    break;
                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    sms.removeStudent(rollToRemove);
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Student foundStudent = sms.findStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent.complete());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    List<Student> allStudents = sms.getall();
                    if (allStudents.isEmpty()) {
                        System.out.println("No students in the system.");
                    } else {
                        System.out.println("Name\tRoll\tGrade\tClass\tPhone");
                        for (Student s : allStudents) {
                            System.out.println(s.complete());
                        }
                    }
                    break;
                case 5:
                    sms.SaveToFile();
                    System.out.println("Data saved to file.");
                    break;
                case 6:
                    sms.LoadFile();
                    System.out.println("Data loaded from file.");
                    break;
                case 7:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}