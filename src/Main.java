import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // Setting up Scanner and Random
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Initial variables
        boolean endProgram = false;
        int balance = 1000;
        int stockPrice = 50;
        int stockOwned = 0;
        int day = 1;
        boolean skip = false;

        // Getting username from input
        System.out.println("Enter username: ");
        String username = scanner.next();

        //Beginning of main game loop
        System.out.println("Welcome to the Stock Trade " + username + "!");
        while (!endProgram) {
            //Stock price randomizer, from -10 up to +10 each day
            stockPrice += rand.nextInt(-10,11);

            //Basic info printed for the player to see
            System.out.println("Balance: " + balance);
            System.out.println("Stock owned: " + stockOwned);
            System.out.println("Stock value: " + stockOwned * stockPrice);
            System.out.println("Stock Price on day " + day + ": " + stockPrice);

            //Command loop
            while (!skip) {
                System.out.println("What would you like to do? (skip, buy, sell, exit, help): ");
                String command = scanner.next();

                if (Objects.equals(command, "buy")) {
                    //Stock buying call
                    System.out.println("Number of stock to buy: ");
                    int stockBuy = scanner.nextInt();
                    if (stockBuy > 0 && balance >= stockPrice * stockBuy) {
                        stockOwned += stockBuy;
                        balance -= stockBuy * stockPrice;
                    }
                } else if (Objects.equals(command, "sell")) {
                    //Stock selling call
                    System.out.println("Number of stock to sell: ");
                    int stockSell = scanner.nextInt();
                    if (stockOwned >= stockSell) {
                        stockOwned -= stockSell;
                        balance += stockSell * stockPrice;
                    }
                } else if (Objects.equals(command, "exit")) {
                    //Game exit option, also important for ending the while loop
                    System.out.println("Exit game? (Y or N): ");
                    String tempEnd = scanner.next();
                    if (tempEnd.equalsIgnoreCase("y")) {
                        System.out.println("Thanks for playing!");
                        endProgram = true;
                        skip = true;
                    }
                } else if (Objects.equals(command, "help")) {
                    System.out.println("Current Commands: buy, sell, exit, help, skip");
                } else if (Objects.equals(command, "skip")) {
                    skip = true;
                }
            }

            //Updates skip to make sure it asks what the player wants to do on the next day
            skip = false;
            //Increments the day at the end of the loop to be ready for the next iteration
            day += 1;
        }
    }
}