import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static Scanner sc = new Scanner(System.in);

    private static int[] ask(int[] values) {
        System.out.printf("MIN: %d    max: %d%n", values[0], values[1]);
        System.out.print("Do you want to continue with your min and max? (y/n): ");
        sc.nextLine(); // Consume the newline character
        String input = sc.nextLine();

        while (true) {
            if (input.length() > 0) {
                char o = input.charAt(0);

                if (o == 'y' || o == 'Y') {
                    System.out.println("Continuing...");
                    break;
                } else if (o == 'n' || o == 'N') {
                    System.out.println("Enter your MIN:");
                    values[0] = getIntInput();
                    System.out.println("Enter your MAX:");
                    values[1] = getIntInput();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        return values;
    }

    private static int getIntInput() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    private static boolean level1(int[] values) {
        values = ask(values);
        Random random = new Random();
        int range = values[1] - values[0] + 1;
        int randomValue = random.nextInt(range) + values[0];

        int guess = -1;
        while (guess != randomValue) {
            System.out.println("Enter your guess : ");
            guess = getIntInput();
            if (guess > randomValue) {
                System.out.println("You have guessed HIGH");
            } else if (guess < randomValue) {
                System.out.println("You have guessed LOW");
            } else {
                System.out.println("Congrats! You have guessed Correct");
                break;
            }
        }
        return true;
    }

    private static boolean level2_3(int[] values, int chances) {
        values = ask(values);
        Random random = new Random();
        int range = values[1] - values[0] + 1;
        int randomValue = random.nextInt(range) + values[0];

        while (chances > 0) {
            System.out.println();
            System.out.println("Lifes remaining : " + chances);
            System.out.println("ENter your guess : ");
            int guess = getIntInput();
            if (guess > randomValue) {
                System.out.println("You have guessed HIGH");
            } else if (guess < randomValue) {
                System.out.println("You have guessed LOW");
            } else {
                System.out.println("Congrats! You have guessed Correct");
                break;
            }
            chances--;
        }
        return chances > 0; // Return true if the player won
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Game...");
        System.out.println("This is a number guessing game");
        System.out.println("You have to guess a random number that is generated");
        System.out.print("Enter the number of rounds you want to play the game: ");
        int rounds = getIntInput();
        int[] values = new int[2];
        int wins = 0;
        int completed = 0;

        while (completed++ < rounds) {
            System.out.println();
            System.out.println("ROUND -  " + completed);
            System.out.println();
            System.out.println("Enter the mode");
            System.out.println("1. EASY - no limit on your guesses");
            System.out.println("2. MEDIUM - 10 guesses");
            System.out.println("3. HARD - 5 guesses");
            int level = getIntInput();
            if (completed == 1) {
                System.out.print("Enter your MIN: ");
                values[0] = getIntInput();
                System.out.print("Enter your MAX: ");
                values[1] = getIntInput();
            }

            if (level == 1) {
                if (level1(values)) {
                    wins++;
                }
            } else {
                boolean won = level2_3(values, (level == 2) ? 10 : 5);
                if (won) {
                    wins++;
                }
            }

            System.out.printf("Your Winning percentage is: %.2f%%%n", ((double) wins / completed) * 100);
        }

        sc.close();
    }
}
