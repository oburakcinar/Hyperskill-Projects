package budget;


import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Scanner scan;
    private User user;


    public UserInterface(Scanner scan) throws IOException {
        this.scan = scan;
        this.user = new User();
    }

    public void start() throws IOException {
        while (true) {
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze(Sort)");
            System.out.println("0) Exit");

            String command = scan.next();
            System.out.println();
            switch (command) {
                case "1":
                    System.out.println("Enter income:");
                    user.addIncome(scan.nextDouble());
                    System.out.println("Income was added!\n");
                    break;
                case "2":
                    purchaseLoop:
                    while (true) {
                        System.out.println("Choose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) Back");
                        String purchaseCommand = scan.next(); //
                        System.out.println(); //
                        switch (purchaseCommand) {
                            case "1":
                                purchase(Item.FOOD);
                                break;
                            case "2":
                                purchase(Item.CLOTHES);
                                break;
                            case "3":
                                purchase(Item.ENTERTAINMENT);
                                break;
                            case "4":
                                purchase(Item.OTHER);
                                break;
                            case "5":
                                break purchaseLoop;
                        }
                    }
                    break;
                case "3":
                    showPurchaseLoop:
                    while (true) {
                        System.out.println("Choose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) All");
                        System.out.println("6) Back");
                        String showPurchaseCommand = scan.next();
                        System.out.println();
                        switch (showPurchaseCommand) {
                            case "1":
                                user.showPurchasesByCategory(Item.FOOD);
                                break;
                            case "2":
                                user.showPurchasesByCategory(Item.CLOTHES);
                                break;
                            case "3":
                                user.showPurchasesByCategory(Item.ENTERTAINMENT);
                                break;
                            case "4":
                                user.showPurchasesByCategory(Item.OTHER);
                                break;
                            case "5":
                                user.showAllPurchases();
                                break;
                            case "6":
                                break showPurchaseLoop;
                        }
                    }
                    break;
                case "4":
                    user.showBalance();
                    System.out.println();
                    break;
                case "5":
                    user.save();
                    break;
                case "6":
                    user.load();
                    break;
                case "7":
                    sortLoop:
                    while (true) {
                        System.out.println("How do you want to sort?");
                        System.out.println("1) Sort all purchases");
                        System.out.println("2) Sort by type");
                        System.out.println("3) Sort certain type");
                        System.out.println("4) Back");
                        String sortCommand = scan.next();
                        System.out.println();
                        switch (sortCommand) {
                            case "1":
                                user.showSortedAllPurchases();
                                break;
                            case "2":
                                user.showSortedTypes();
                                break;
                            case "3":
                                System.out.println("Choose the type of purchase");
                                System.out.println("1) Food");
                                System.out.println("2) Clothes");
                                System.out.println("3) Entertainment");
                                System.out.println("4) Other");
                                String sortTypeCommand = scan.next();
                                System.out.println();
                                switch (sortTypeCommand) {
                                    case "1":
                                        user.showSortedPurchasesByCategory(Item.FOOD);
                                        break;
                                    case "2":
                                        user.showSortedPurchasesByCategory(Item.CLOTHES);
                                        break;
                                    case "3":
                                        user.showSortedPurchasesByCategory(Item.ENTERTAINMENT);
                                        break;
                                    case "4":
                                        user.showSortedPurchasesByCategory(Item.OTHER);
                                        break;
                                }
                                break;
                            case "4":
                                break sortLoop;
                        }
                    }
                    break;
                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
            }
        }
    }

    private void purchase(Item type) {
        System.out.println("Enter purchase name:");
        String purchaseName = scan.nextLine();
        purchaseName = scan.nextLine();
        System.out.println("Enter its price:");
        String itemPrice = scan.nextLine();
        user.makePurchase(type, purchaseName, Double.parseDouble(itemPrice));
        System.out.println("Purchase was added!\n");
    }

}
