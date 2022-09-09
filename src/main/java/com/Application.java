package com;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.VendingMachine.*;

public class Application {
    public static void main(String[] args) throws InterruptedException, IOException {
        var vendingMachine = new VendingMachine();

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
                    vendingMachine.setInputCategoryName(console.nextLine());

                    System.out.println(">> Enter the Category price... (ex. \"23.75\", \"12.2\", \"15\")");

                    try {
                        vendingMachine.setInputCategoryPrice(console.nextDouble());
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect value. Please try again.");
                        break;
                    }

                    if (vendingMachine.getInputCategoryPrice() <= 0) {
                        System.out.println("The price must be higher then \"0\".");
                        System.out.println("Enter the right price.");
                        break;
                    }

                    System.out.println(">> Enter the Category item quantity... (ex. \"1\", \"12\", \"100\")");
                    System.out.println(">> You may enter \"0\" if you don't want to specify the item quantity.");

                    try {
                        vendingMachine.setInputItemQuantity(console.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect value. Please try again.");
                        break;
                    }

                    if (vendingMachine.getInputItemQuantity() < 0) {
                        System.out.println("Quantity of the items must be higher then \"0\".");
                        System.out.println("Enter the right quantity.");
                    } else if (vendingMachine.getInputItemQuantity() == 0) {
                        addCategory(vendingMachine.getInputCategoryName(), vendingMachine.getInputCategoryPrice());
                    } else {
                        addCategory(vendingMachine.getInputCategoryName(), vendingMachine.getInputCategoryPrice(),
                                vendingMachine.getInputItemQuantity());
                    }
                }
                case 2 -> {
                    System.out.println(">> Adding items to Vending Machine:");
                    System.out.println(">> Enter the Category Name... (ex. \"Chocolate bar\", \"Donut\")");

                    Scanner console = new Scanner(System.in);
                    vendingMachine.setInputCategoryName(console.nextLine());

                    System.out.println(">> Enter the number of items you want to add... ");

                    try {
                        vendingMachine.setInputItemQuantity(console.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect value. Please try again.");
                        break;
                    }

                    if (vendingMachine.getInputItemQuantity() == 0) {
                        System.out.println("Quantity of adding items must be higher then \"0\".");
                    } else if (vendingMachine.getInputItemQuantity() < 0) {
                        System.out.println("For adding items you must enter a positive number.");
                    } else {
                        addItem(vendingMachine.getInputCategoryName(), vendingMachine.getInputItemQuantity());
                    }

                    System.out.println();
                }
                case 3 -> {
                    System.out.println(">> Purchasing Snack:");
                    System.out.println(">> Enter the Category Name... (ex. \"Chocolate bar\", \"Donut\")");

                    Scanner console = new Scanner(System.in);
                    vendingMachine.setInputCategoryName(console.nextLine());

                    System.out.println(">> Enter the date of purchase in format \"yyyy-MM-dd\"... (ex. 2017-12-01)");

                    vendingMachine.setInputPurchaseDate(console.nextLine());

                    purchase(vendingMachine.getInputCategoryName(), vendingMachine.getInputPurchaseDate());

                    System.out.println();
                }
                case 4 -> {
                    System.out.println(">> Showing the list of served Categories");
                    System.out.println("   in Vending Machine:");

                    list();

                    System.out.println();
                }
                case 5 -> {
                    clear();

                    System.out.println(">> Serving of all Snack Categories that");
                    System.out.println("   don’t have items for sale has been stopped.");
                    System.out.println();
                }
                case 6 -> {
                    System.out.println(">> Showing earnings in specified Month:");
                    System.out.println(">> Enter the Month oh the Year in format \"yyyy-MM\"... (ex. 2020-04)");

                    Scanner console = new Scanner(System.in);
                    vendingMachine.setInputPurchaseDate(console.nextLine());

                    System.out.println(">> Showing earnings and total price in " + vendingMachine.getInputPurchaseDate() + ".");

                    reportMonth(vendingMachine.getInputPurchaseDate());

                    System.out.println();
                }
                case 7 -> {
                    System.out.println(">> Showing earnings by Category gained since");
                    System.out.println("   provided date till now:");
                    System.out.println(">> Enter the provided date in format \"yyyy-MM-dd\"... (ex. 2020-04-07)");

                    Scanner console = new Scanner(System.in);
                    vendingMachine.setInputPurchaseDate(console.nextLine());

                    System.out.println(">> Showing earnings gained since " + vendingMachine.getInputPurchaseDate());
                    System.out.println("   till now with total price.");

                    reportFromDate(vendingMachine.getInputPurchaseDate());

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
