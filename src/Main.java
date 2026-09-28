import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        boolean endProgram = false;

        int balance = 1000;
        int stockPrice = 50;
        int stockOwned = 0;
        int day = 1;

        System.out.println("Enter username: ");
        String username = scanner.next();

        System.out.println("Welcome to the Stock Trade " + username + "!");
        while (!endProgram) {
            stockPrice += rand.nextInt(-10,11);

            System.out.println("Balance: " + balance);
            System.out.println("Stock owned: " + stockOwned);
            System.out.println("Stock value: " + stockOwned * stockPrice);
            System.out.println("Stock Price on day " + day + ": " + stockPrice);

            System.out.println("Number of stock to buy: ");
            int stockBuy = scanner.nextInt();
            if (balance >= stockPrice * stockBuy){
                stockOwned += stockBuy;
                balance -= stockBuy * stockPrice;
            }

            System.out.println("Number of stock to sell: ");
            int stockSell = scanner.nextInt();
            if (stockOwned >= stockSell){
                stockOwned -= stockSell;
                balance += stockSell * stockPrice;
            }

            System.out.println("Exit game? (Y or N): ");
            String tempEnd = scanner.next();
            if (tempEnd.equalsIgnoreCase("y")){
                System.out.println("Thanks for playing!");
                endProgram = true;
            }
            day += 1;
        }
    }
}