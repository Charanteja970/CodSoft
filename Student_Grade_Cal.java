import java.util.Scanner;

public class Student_Grade_Cal {

    static Scanner sc = new Scanner(System.in);
    static int[][] arr = new int[20][6]; // Assuming 20 records with 6 subjects each
    static int cur = 0;

    private static int getIntInput() {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                if (input < 0 || input > 100) {
                    System.out.println("Marks should be between 0 and 100. Please enter again:");
                } else {
                    return input;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Results Calculator");
        System.out.println("Please Enter ");
        System.out.println("1. To Enter new Record ");
        System.out.println("2. EXIT");
        int op1 = getIntInput();
        if (op1 == 1) {
            newRecord();
            recursion();
        } else {
            return;
        }
    }

    private static void recursion() {
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("1. Enter new Record ");
            System.out.println("2. Get the complete details of Current Record");
            System.out.println("3. To get Complete details of ALL RECORDS");
            System.out.println("4. EXIT");
            int op2 = getIntInput();
            if (op2 == 4)
                return;
            else if (op2 == 3)
                getRecord(cur);
            else if (op2 == 2)
                getRecord(1);
            else
                newRecord();
        }
    }

    private static void getRecord(int i) {
        int dummy = cur;
        dummy--;
        while (i > 0) {
            System.out.println();
            System.out.println();
            System.out.println("Record No. : " + dummy);
            System.out.println("Subject\tMarks\tPoints\tGrade");
            int totalMarks = 0; // Variable to calculate total marks
            totalMarks += arr[dummy][0];
            totalMarks += arr[dummy][1];
            totalMarks += arr[dummy][2];
            totalMarks += arr[dummy][3];
            totalMarks += arr[dummy][4];
            totalMarks += arr[dummy][5];
            System.out.println("Telugu\t" + arr[dummy][0] + "\t" + getPoints(arr[dummy][0]));
            System.out.println("Hindi\t" + arr[dummy][1] + "\t" + getPoints(arr[dummy][1]));
            System.out.println("English\t" + arr[dummy][2] + "\t" + getPoints(arr[dummy][2]));
            System.out.println("Maths\t" + arr[dummy][3] + "\t" + getPoints(arr[dummy][3]));
            System.out.println("Science\t" + arr[dummy][4] + "\t" + getPoints(arr[dummy][4]));
            System.out.println("Social\t" + arr[dummy][5] + "\t" + getPoints(arr[dummy][5]));
            System.out.println("Total\t" + totalMarks); // Display total marks
            dummy--;
            i--;
        }
    }

    private static String getPoints(int i) {
        if (i <= 100 && i >= 91) {
            return ("10     A+");
        } else if (i <= 90 && i >= 81) {
            return ("9     A");
        } else if (i <= 80 && i >= 71) {
            return ("8     B+");
        } else if (i <= 70 && i >= 61) {
            return ("7     B");
        } else if (i <= 60 && i >= 51) {
            return ("6     C");
        } else if (i <= 50 && i >= 45) {
            return ("5     D");
        } else {
            return ("-     F");
        }
    }

    private static void newRecord() {
        System.out.println();
        System.out.println();
        System.out.println("Record : New Record");
        System.out.println("Enter the marks of Students below : ");
        System.out.print("Telugu : ");
        arr[cur][0] = getIntInput();
        System.out.print("Hindi : ");
        arr[cur][1] = getIntInput();
        System.out.print("English : ");
        arr[cur][2] = getIntInput();
        System.out.print("Maths : ");
        arr[cur][3] = getIntInput();
        System.out.print("Science : ");
        arr[cur][4] = getIntInput();
        System.out.print("Social :  ");
        arr[cur][5] = getIntInput();
        cur++;
    }
}
