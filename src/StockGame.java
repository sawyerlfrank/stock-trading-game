import java.util.Random;
import java.util.Scanner;

public class StockGame {

    // Instance variables
    private static final int STARTING_BALANCE = 1000;
    private static final int STARTING_PRICE = 50;
    private static final int MAX_DAYS = 20;

    // Applying the constant values
    private int balance = STARTING_BALANCE;
    private int stockPrice = STARTING_PRICE;
    private int stockOwned = 0;
    private int day = 1;
    private int stockTrend = 0;
    private int trendDirection = 0;

    // Sets up Random
    private final Random rand = new Random();

    public void play() {
        // Setting up Scanner
        Scanner scanner = new Scanner(System.in);


        // Initial variables
        boolean endProgram = false;



        // Getting username from input
            System.out.println("Enter username: ");
        String username = scanner.next();

        // Beginning of main game loop
        System.out.println("Welcome to the Stock Trade " + username + "!");
        while (!endProgram && day <= MAX_DAYS) {

            displayStatus();

            // Updates dayFinished to make sure it asks what the player wants to do today
            boolean dayFinished = false;


            // Current day loop
            while (!dayFinished && !endProgram) {

                System.out.println("What would you like to do? (skip, buy, sell, exit, help): ");
                String command = scanner.next();

                if (command.equalsIgnoreCase("buy")) {
                    // Stock buying call
                    buyShares(scanner);
                } else if (command.equalsIgnoreCase("sell")) {
                    // Stock selling call
                    sellShares(scanner);
                } else if (command.equalsIgnoreCase("exit")) {
                    // Game exit option, also important for ending the while loop
                    System.out.println("Exit game? (Y or N): ");
                    String tempEnd = scanner.next();

                    if (tempEnd.equalsIgnoreCase("y")) {
                        System.out.println("Thanks for playing!");
                        endProgram = true;
                        dayFinished = true;
                    }
                } else if (command.equalsIgnoreCase("help")) {
                    displayHelp();
                } else if (command.equalsIgnoreCase("skip")) {
                    dayFinished = true;
                } else {
                    System.out.println("Unknown command. Type 'help' to see available commands.");
                }
            }

            // Ends the game without going past the final day
            if (endProgram || day == MAX_DAYS) {
                break;
            }

            // Increments to the next day and updates the stock price
            day++;
            updateStockPrice();
        }
        displayFinalSummary();
    }

    // Method to give the player basic info
    private void displayStatus() {

        // Info printed for the player to see
        System.out.println("Balance: " + balance);
        System.out.println("Stock owned: " + stockOwned);
        System.out.println("Stock value: " + stockOwned * stockPrice);
        System.out.println("Stock Price on day " + day + ": " + stockPrice);

    }

    // Method to change the stock price each day
    private void updateStockPrice() {

        // Stock price randomizer, from -5 up to +5 each day
        stockPrice += rand.nextInt(11) - 5;

        // Random stock trend simulation
        if (stockTrend == 0) {
            trendDirection = rand.nextInt(-1, 2);

            if (trendDirection != 0) {
                stockTrend = rand.nextInt(1, 5);
            }
        }

        // Adjust stock price based on trend
        stockPrice += trendDirection * rand.nextInt(0, 11);
        if (stockTrend > 0) {
            stockTrend--;
        }
        if (stockTrend == 0) {
            trendDirection = 0;
        }

        stockPrice = Math.max(1, stockPrice);


    }

    private void buyShares(Scanner scanner) {

        System.out.println("Number of stock to buy: ");
        int stockBuy = scanner.nextInt();
        if (stockBuy > 0 && balance >= stockPrice * stockBuy) {
            stockOwned += stockBuy;
            balance -= stockBuy * stockPrice;
        }
    }

    private void sellShares(Scanner scanner) {

        System.out.println("Number of stock to sell: ");
        int stockSell = scanner.nextInt();
        if (stockSell > 0 && stockOwned >= stockSell) {
            stockOwned -= stockSell;
            balance += stockSell * stockPrice;
        }
    }

    private int calculateNetWorth() {
        // Calculates the total portfolio value
        return balance + (stockOwned * stockPrice);
    }

    private void displayFinalSummary() {
        // Displays final performance
        System.out.println("Your final portfolio worth is: $" + calculateNetWorth());
    }

    private void displayHelp() {
        // Explains the game's commands
        System.out.println("""
            Current Commands: buy, sell, skip, exit, help
            
            Buy: prompts you to buy stocks, and asks for the amount
            Sell: prompts you to sell stocks, and asks for the amount
            Skip: skips to the next trading day
            Exit: completes the game and exits it
            Help: brings up this menu
            """);

    }
}
