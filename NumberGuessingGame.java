import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int attempts = 0;
        int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        int maxAttempts = 5;
        int score = 100;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + minRange + " and " + maxRange + ". Can you guess it?");

        while (attempts < maxAttempts) {
            System.out.println("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number.");
                System.out.println("Your score: " + score);
                break;
            } else if (guess < randomNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            score -= 20; // Deducting score for each attempt
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you couldn't guess the number. The correct number was " + randomNumber);
            System.out.println("Better luck next time!");
        }

        scanner.close();
    }
}
