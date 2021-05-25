package src;

import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.*;

public class VendingApplication {
    public static String getInputCategoryName() {
        return inputCategoryName;
    }

    public static void setInputCategoryName(String inputCategoryName) {
        VendingApplication.inputCategoryName = inputCategoryName;
    }

    public static double getInputCategoryPrice() {
        return inputCategoryPrice;
    }

    public static void setInputCategoryPrice(double inputCategoryPrice) {
        VendingApplication.inputCategoryPrice = inputCategoryPrice;
    }

    public static int getInputItemQuantity() {
        return inputItemQuantity;
    }

    public static void setInputItemQuantity(int inputItemQuantity) {
        VendingApplication.inputItemQuantity = inputItemQuantity;
    }

    public static String getInputPurchaseDate() {
        return inputPurchaseDate;
    }

    public static void setInputPurchaseDate(String inputPurchaseDate) {
        VendingApplication.inputPurchaseDate = inputPurchaseDate;
    }

    static String inputCategoryName;
    static String inputPurchaseDate;
    static double inputCategoryPrice;
    static int inputItemQuantity;



    public static void main(String[] args) throws InterruptedException, IOException {

        //WELCOME TEXT
        System.out.println();
        System.out.println();
        System.out.println("---------- Snack Vending Machine ----------");
        System.out.println("-------------- basic version --------------");
        System.out.println();
        System.out.println("---------- by Michael Romanyshyn ----------");
        System.out.println();

        //"HOW TO USE" TEXT
        System.out.println("You may use the Vending Machine by entering");
        System.out.println("the command buttons in Main Menu.");
        System.out.println();

        //PAUSE
        System.out.print("Loading ");

        for (int i = 0; i < 15; i++) {
            Thread.sleep(285);
            System.out.print(". ");
        }

        Thread.sleep(500);
        System.out.print("Done!");
        Thread.sleep(1000);
        System.out.println();

        //START. MAIN MENU LIST
        Scanner key = new Scanner(System.in);

        int userChoice = 0;

        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("MAIN MENU:");
            System.out.println();
            System.out.println("1 - add new Snack Category;");
            System.out.println("2 - add few items to Vending Machine;");
            System.out.println("3 - purchase Snack;");
            System.out.println("4 - show list of served Categories;");
            System.out.println("5 - stop serving all Snack Categories");
            System.out.println("    that don’t have items for sale;");
            System.out.println("6 - show earnings by Category");
            System.out.println("    in specified Month;");
            System.out.println("7 - show earnings by Category gained since");
            System.out.println("    provided date till now.");
            System.out.println("0 - exit Vending Machine");
            System.out.println();

            //USER'S CHOICE
            System.out.print("Choose the option: ");
            System.out.println();


            try {
                userChoice = key.nextInt();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Incorrect value. Please try again.");
                break;
            }

            switch (userChoice) {
                case 1 -> {
                    System.out.println(">> Adding new Snack Category:");
                    System.out.println(">> Enter the Category Name... (ex. \"Chocolate bar\", \"Donut\")");

                    Scanner console = new Scanner(System.in);
                    setInputCategoryName(console.nextLine());

                    System.out.println(">> Enter the Category price... (ex. \"23.75\", \"12.2\", \"15\")");

                    try {
                        setInputCategoryPrice(console.nextDouble());
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect value. Please try again.");
                        break;
                    }

                    if (getInputCategoryPrice() <= 0) {
                        System.out.println("The price must be higher then \"0\".");
                        System.out.println("Enter the right price.");
                        break;
                    }

                    System.out.println(">> Enter the Category item quantity... (ex. \"1\", \"12\", \"100\")");
                    System.out.println(">> You may enter \"0\" if you don't want to specify the item quantity.");

                    try {
                        setInputItemQuantity(console.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect value. Please try again.");
                        break;
                    }

                    if (getInputItemQuantity() < 0) {
                        System.out.println("Quantity of the items must be higher then \"0\".");
                        System.out.println("Enter the right quantity.");
                    } else if (getInputItemQuantity() == 0) {
                        VendingMachine.addCategory(getInputCategoryName(), getInputCategoryPrice());
                    } else {
                        VendingMachine.addCategory(getInputCategoryName(), getInputCategoryPrice(), getInputItemQuantity());
                    }
                }
                case 2 -> {
                    System.out.println(">> Adding items to Vending Machine:");
                    System.out.println(">> Enter the Category Name... (ex. \"Chocolate bar\", \"Donut\")");

                    Scanner console = new Scanner(System.in);
                    setInputCategoryName(console.nextLine());

                    System.out.println(">> Enter the number of items you want to add... ");

                    try {
                        setInputItemQuantity(console.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect value. Please try again.");
                        break;
                    }

                    if (getInputItemQuantity() == 0) {
                        System.out.println("Quantity of adding items must be higher then \"0\".");
                    } else if (getInputItemQuantity() < 0) {
                        System.out.println("For adding items you must enter a positive number.");
                    } else {
                        VendingMachine.addItem(getInputCategoryName(), getInputItemQuantity());
                    }

                    System.out.println();
                }
                case 3 -> {
                    System.out.println(">> Purchasing Snack:");
                    System.out.println(">> Enter the Category Name... (ex. \"Chocolate bar\", \"Donut\")");

                    Scanner console = new Scanner(System.in);
                    setInputCategoryName(console.nextLine());

                    System.out.println(">> Enter the date of purchase in format \"yyyy-MM-dd\"... (ex. 2017-12-01)");

                    setInputPurchaseDate(console.nextLine());

                    VendingMachine.purchase(getInputCategoryName(), getInputPurchaseDate());

                    System.out.println();
                }
                case 4 -> {
                    System.out.println(">> Showing the list of served Categories");
                    System.out.println("   in Vending Machine:");

                    VendingMachine.list();

                    System.out.println();
                }
                case 5 -> {
                    VendingMachine.clear();

                    System.out.println(">> Serving of all Snack Categories that");
                    System.out.println("   don’t have items for sale has been stopped.");
                    System.out.println();
                }
                case 6 -> {
                    System.out.println(">> Showing earnings in specified Month:");
                    System.out.println(">> Enter the Month oh the Year in format \"yyyy-MM\"... (ex. 2020-04)");

                    Scanner console = new Scanner(System.in);
                    setInputPurchaseDate(console.nextLine());

                    System.out.println(">> Showing earnings and total price in " + getInputPurchaseDate() + ".");

                    VendingMachine.reportMonth(getInputPurchaseDate());

                    System.out.println();
                }
                case 7 -> {
                    System.out.println(">> Showing earnings by Category gained since");
                    System.out.println("   provided date till now:");
                    System.out.println(">> Enter the provided date in format \"yyyy-MM-dd\"... (ex. 2020-04-07)");

                    Scanner console = new Scanner(System.in);
                    setInputPurchaseDate(console.nextLine());

                    System.out.println(">> Showing earnings gained since " + getInputPurchaseDate());
                    System.out.println("   till now with total price.");

                    VendingMachine.reportFromDate(getInputPurchaseDate());

                    System.out.println();
                }
                case 0 -> {
                    System.out.println("Thank you for using Vending Machine!");
                    System.out.println("See you next time.");
                    System.out.println();
                    return;
                }
                default -> {
                    System.out.println("Please enter only integers from 1 till 7.");
                    System.out.println();
                }
            }
        }
    }
}
