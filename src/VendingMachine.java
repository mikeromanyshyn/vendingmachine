package src;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.util.*;


public class VendingMachine {

    public static ArrayList<Snack> snacks = new ArrayList<>();
    private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");



    //ADDING NEW CATEGORY OF SNACKS
    public static void addCategory(String categoryName, double itemPrice, int itemQuantity) {
        var newSnack = new Snack(categoryName, itemPrice, itemQuantity);

        if (snacks.isEmpty()) {
            snacks.add(newSnack);
            System.out.println("New Category \"" + newSnack.getCategoryName() + "\" was added:");
            System.out.println(newSnack.getCategoryName() + " "
                    + decimalFormat.format(newSnack.getItemPrice()) + " " + newSnack.getItemQuantity());
            System.out.println();
            return;
        }

        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getCategoryName().equalsIgnoreCase(newSnack.getCategoryName())) {
                System.out.println("The Category \"" + newSnack.getCategoryName() + "\" already exist:");
                System.out.println(snacks.get(i).getCategoryName() + " "
                        + decimalFormat.format(snacks.get(i).getItemPrice()) + " " + snacks.get(i).getItemQuantity());
                System.out.println();
            } else {
                snacks.add(i, newSnack);
                System.out.println("New Category \"" + newSnack.getCategoryName() + "\" was added:");
                System.out.println(newSnack.getCategoryName() + " "
                        + decimalFormat.format(newSnack.getItemPrice()) + " " + newSnack.getItemQuantity());
                System.out.println();
            }
            break;
        }
    }

    //ADDING NEW CATEGORY OF SNACKS WITHOUT QUANTITY
    public static void addCategory(String categoryName, double itemPrice) {
        var newSnack = new Snack(categoryName, itemPrice, 0);

        if (snacks.isEmpty()) {
            snacks.add(newSnack);
            System.out.println("New Category \"" + newSnack.getCategoryName() + "\" was added:");
            System.out.println(newSnack.getCategoryName() + " "
                    + decimalFormat.format(newSnack.getItemPrice()) + " " + newSnack.getItemQuantity());
            System.out.println();
            return;
        }

        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getCategoryName().equalsIgnoreCase(newSnack.getCategoryName())) {
                System.out.println("The Category \"" + newSnack.getCategoryName() + "\" already exist:");
                System.out.println(snacks.get(i).getCategoryName() + " "
                        + decimalFormat.format(snacks.get(i).getItemPrice()) + " " + snacks.get(i).getItemQuantity());
                System.out.println();
            } else {
                snacks.add(i, newSnack);
                System.out.println("New Category \"" + newSnack.getCategoryName() + "\" was added:");
                System.out.println(newSnack.getCategoryName() + " "
                        + decimalFormat.format(newSnack.getItemPrice()) + " " + newSnack.getItemQuantity());
                System.out.println();
            }
            break;
        }
    }

    //ADDING SOME AMOUNT (QUANTITY) OF ITEMS (SNACKS)
    public static void addItem(String categoryName, int itemQuantity) {
        Snack snackByCategory = findSnackByCategory(categoryName);
        int sumQuantity;

        if (snacks.isEmpty()) {
            System.out.println("There are no Categories served at the moment.");
            System.out.println("You must add new Category first.");
            System.out.println();
            return;
        }

        if (snackByCategory == null) {
            System.out.println("There are no \"" + categoryName + "\" Category.");
            System.out.println("You must add new \"" + categoryName + "\" Category first.");
            System.out.println();
            return;
        } else {
            sumQuantity = snackByCategory.getItemQuantity() + itemQuantity;
            snackByCategory.setItemQuantity(sumQuantity);
            System.out.println("The \"" + snackByCategory.getCategoryName()
                    + "\" was added in amount of " + itemQuantity + ".");
            System.out.println(snackByCategory.getCategoryName() + " "
                    + decimalFormat.format(snackByCategory.getItemPrice()) + " " + snackByCategory.getItemQuantity());
            System.out.println();
        }
    }

    //PURCHASING A SINGLE SNACK ITEM (DECREASING QUANTITY)
    public static void purchase(String categoryName, String purchaseDateString) {
        if (snacks.isEmpty()) {
            System.out.println("There are no Items to purchase at the moment.");
            System.out.println("You must add new Item first.");
            System.out.println();
            return;
        }

        Snack snackByCategory = findSnackByCategory(categoryName);

        if (snackByCategory == null){
            System.out.println("You can not buy \"" + categoryName + "\".");
            System.out.println("There are no \"" + categoryName + "\" in Vending Machine.");
            System.out.println("You must add \"" + categoryName + "\" first.");
            System.out.println();
            return;
        }

        if (snackByCategory.getItemQuantity() == 0) {
            System.out.println("You can not buy \"" + snackByCategory.getCategoryName() + "\".");
            System.out.println("You must add few items of \"" + snackByCategory.getCategoryName() + "\" before buying.");
            System.out.println();
            return;
        }

        LocalDate purchaseDate = null;

        try {
            purchaseDate = LocalDate.parse(purchaseDateString);
        } catch (DateTimeParseException e) {
            System.out.println();
            System.out.println("Incorrect Date. Please try again.");
            return;
        }

        snackByCategory.addPurchase(purchaseDate);

        int sumQuantity = snackByCategory.getItemQuantity() - 1;
        snackByCategory.setItemQuantity(sumQuantity);

        System.out.println("You bought \"" + snackByCategory.getCategoryName() + "\" for "
                + decimalFormat.format(snackByCategory.getItemPrice()) + " on " + purchaseDate);
        System.out.println("There are " + snackByCategory.getItemQuantity() + " Items of \""
                + snackByCategory.getCategoryName() + "\" left.");
        System.out.println();
    }

    //SORTING LIST OF SNACKS BY ITEMS QUANTITY (FROM BIGGEST AMOUNT)
    public static void list() {
        snacks.sort(Comparator.comparingInt(Snack::getItemQuantity).reversed());

        for (Snack snack: snacks) {
            if (snack.isServed()) {
                System.out.println(snack.getCategoryName() + " "
                        + decimalFormat.format(snack.getItemPrice()) + " " + snack.getItemQuantity());
            }
        }
        System.out.println();
    }

    //NOT SERVING ITEMS WITH ZERO QUANTITY
    public static void clear() {
        if (snacks.isEmpty()) {
            System.out.println("Nothing to clear.");
            System.out.println("There are no Categories in Vending Machine.");
            System.out.println();
        } else {
            for (int i = 0; i < snacks.size(); i++) {
                if (snacks.get(i).getItemQuantity() == 0) {
                    snacks.get(i).setServed(false);
                }
            }
        }
    }

    //SHOW EARNINGS BY CATEGORY IN SPECIFIED MONTH (CALCULATING TOTAL PRICE)
    public static void reportMonth(String purchasedMonth) {
        DateTimeFormatter formatterFirstDayMonth = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        LocalDate purchaseDate = null;

        try {
            purchaseDate = LocalDate.parse(purchasedMonth, formatterFirstDayMonth);
        } catch (DateTimeParseException e) {
            System.out.println();
            System.out.println("Incorrect Date. Please try again.");
            return;
        }

        DateTimeFormatter formatterLastDayMonth = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, purchaseDate.lengthOfMonth())
                .toFormatter();

        LocalDate startDate = LocalDate.parse(purchasedMonth, formatterFirstDayMonth);
        LocalDate endDate = LocalDate.parse(purchasedMonth, formatterLastDayMonth);


        double totalPrice = 0;

        for (Snack snack: snacks) {
            int purchasedItemQuantity = 0;

            for (LocalDate purchase: snack.getPurchases()) {
                if (isDateInRange(purchase, startDate, endDate)) {
                    purchasedItemQuantity += 1;
                }
            }

            double multiplePrice = snack.getItemPrice() * purchasedItemQuantity;
            if (purchasedItemQuantity > 0) {
                System.out.println(snack.getCategoryName() + " "
                        + decimalFormat.format(multiplePrice) + " " + purchasedItemQuantity);
                totalPrice += multiplePrice;
            }
        }

        System.out.println(">Total " + decimalFormat.format(totalPrice));
        System.out.println();
    }

    //SHOW EARNINGS BY CATEGORY GAINED SINCE PROVIDED DATE TILL NOW SORTED BY CATEGORY NAME (CALCULATING TOTAL PRICE)
    public static void reportFromDate(String purchasedDay) throws IOException {
        LocalDate startDate = null;

        try {
            startDate = LocalDate.parse(purchasedDay);
        } catch (DateTimeParseException e) {
            System.out.println();
            System.out.println("Incorrect Date. Please try again.");
            return;
        }

        LocalDate endDate = LocalDate.now();

        double totalPrice = 0;

        snacks.sort(Comparator.comparing(Snack::getCategoryName));

        for (Snack snack: snacks) {
            int purchasedItemQuantity = 0;

            for (LocalDate purchase: snack.getPurchases()) {
                if (startDate.isBefore(endDate)) {
                    purchasedItemQuantity += 1;
                }
            }

            double multiplePrice = snack.getItemPrice() * purchasedItemQuantity;
            if (purchasedItemQuantity > 0) {
                System.out.println(snack.getCategoryName() + " "
                        + decimalFormat.format(multiplePrice) + " " + purchasedItemQuantity);
                totalPrice += multiplePrice;
            }
        }

        System.out.println(">Total " + decimalFormat.format(totalPrice));
        System.out.println();
    }



    private static Snack findSnackByCategory(String categoryName) {
        for (Snack snack: snacks) {
            if (snack.getCategoryName().equalsIgnoreCase(categoryName)) {
                return snack;
            }
        }
        
        return null;
    }

    private static boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        return dateToCheck.isAfter(startDate) && dateToCheck.isBefore(endDate);
    }

}
