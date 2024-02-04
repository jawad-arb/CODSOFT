package org.example.task1;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        playNumberGuessingGame();
    }

    public static void playNumberGuessingGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int limit1 = 1;
        int limit2 = 100;
        int generatedNumber = random.nextInt(limit2 - limit1 + 1) + limit1;

        int maxAttempts = 10;
        int roundsPlayed = 0;
        int totalAttempts = 0;

        boolean playAgain = true;

        while (playAgain) {
            System.out.println("\nRound " + (roundsPlayed + 1));
            System.out.println("Guess the number between " + limit1 + " and " + limit2);

            int attempts = 0;
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + (attempts + 1) + " attempts.");
                    totalAttempts += attempts + 1;
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                attempts++;
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + generatedNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            if (!playAgainInput.equals("yes")) {
                playAgain = false;
            } else {
                roundsPlayed++;
                generatedNumber = random.nextInt(limit2 - limit1 + 1) + limit1;
            }
        }

        System.out.println("\nGame Over");
        System.out.println("Total Rounds Played: " + roundsPlayed);
        System.out.println("Total Attempts: " + totalAttempts);
    }
}
