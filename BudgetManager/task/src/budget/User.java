package budget;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class User {
    private double balance;
    private LinkedHashMap<String, Double> foodList;
    private LinkedHashMap<String, Double> clothesList;
    private LinkedHashMap<String, Double> entertainmentList;
    private LinkedHashMap<String, Double> otherList;
    private File file;


    public User() throws IOException{
        balance = 0.0;
        foodList = new LinkedHashMap<>();
        clothesList = new LinkedHashMap<>();
        entertainmentList = new LinkedHashMap<>();
        otherList = new LinkedHashMap<>();
        this.file = new File("purchases.txt");
        file.createNewFile();
    }


    public void addIncome(double income) {
        balance += income;
    }

    public void makePurchase(Item type, String itemName, double price) {
        balance -= price;
        if (balance < 0) balance = 0;
        switch (type) {
            case FOOD:
                foodList.put(itemName, price);
                break;
            case CLOTHES:
                clothesList.put(itemName, price);
                break;
            case ENTERTAINMENT:
                entertainmentList.put(itemName, price);
                break;
            case OTHER:
                otherList.put(itemName, price);
                break;
        }
    }

    public void showBalance() {
        System.out.printf("Balance: $" + "%.2f", balance);
        System.out.println();
    }

    public void showPurchasesByCategory(Item category) {
        switch (category) {
            case FOOD:
                System.out.println("Food:");
                printPurchasesByCategory(foodList);
                break;
            case CLOTHES:
                System.out.println("Clothes:");
                printPurchasesByCategory(clothesList);
                break;
            case ENTERTAINMENT:
                System.out.println("Entertainment:");
                printPurchasesByCategory(entertainmentList);
                break;
            case OTHER:
                System.out.println("Other:");
                printPurchasesByCategory(otherList);
                break;
        }
    }

    public void showSortedPurchasesByCategory(Item category) {
        switch (category) {
            case FOOD:
                if (foodList.isEmpty()) {
                    System.out.println("The purchase list is empty!\n");
                } else {
                    System.out.println("Food:");
                    printSortedPurchasesByCategory(valueSort(foodList)); //
                }
                break;
            case CLOTHES:
                if (foodList.isEmpty()) {
                    System.out.println("The purchase list is empty!\n");
                } else {
                    System.out.println("Clothes:");
                    printSortedPurchasesByCategory(valueSort(clothesList)); //
                }
                break;
            case ENTERTAINMENT:
                if (foodList.isEmpty()) {
                    System.out.println("The purchase list is empty!\n");
                } else {
                    System.out.println("Entertainment:");
                    printSortedPurchasesByCategory(valueSort(entertainmentList));
                }
                break;
            case OTHER:
                if (foodList.isEmpty()) {
                    System.out.println("The purchase list is empty!\n");
                } else {
                    System.out.println("Other:");
                    printSortedPurchasesByCategory(valueSort(otherList));
                }

                break;
        }
    }

    public void showAllPurchases() {
        double total = 0.0;
        System.out.println("All:");
        for (var entry : foodList.entrySet()) {
            System.out.printf(entry.getKey() + " $" + "%.2f\n", entry.getValue());
            total += entry.getValue();
        }
        for (var entry : clothesList.entrySet()) {
            System.out.printf(entry.getKey() + " $" + "%.2f\n", entry.getValue());
            total += entry.getValue();
        }
        for (var entry : entertainmentList.entrySet()) {
            System.out.printf(entry.getKey() + " $" + "%.2f\n", entry.getValue());
            total += entry.getValue();
        }
        for (var entry : otherList.entrySet()) {
            System.out.printf(entry.getKey() + " $" + "%.2f\n", entry.getValue());
            total += entry.getValue();
        }

        if (total != 0) {
            System.out.printf("Total sum: $" + "%.2f\n\n", total);
        } else {
            System.out.printf("The purchase list is empty!\n\n");
        }

    }

    public void showSortedAllPurchases() {
        TreeMap<String, Double> allItems = new TreeMap<>();
        allItems.putAll(foodList);
        allItems.putAll(clothesList);
        allItems.putAll(entertainmentList);
        allItems.putAll(otherList);
        if (allItems.isEmpty()) {
            System.out.println("The purchase list is empty!\n");
        } else {
            System.out.println("All:");
            printPurchasesByCategory(valueSort(allItems)); //
        }

    }

    private void printPurchasesByCategory(Map<String, Double> map) {
        double sum = 0.0;
        if (map.size() == 0) {
            System.out.println("The purchase list is empty!");
        } else {
            for (var entry : map.entrySet()) {
                System.out.printf(entry.getKey() + " $" + "%.2f\n", entry.getValue());
                sum += entry.getValue();
            }
            System.out.printf("Total sum: $" + "%.2f\n\n", sum);
        }
    }


    public void printSortedPurchasesByCategory(Map<String, Double> map) {
        TreeMap<String, Double> sorted = new TreeMap<>();
        sorted.putAll(map);
        printPurchasesByCategory(valueSort(sorted));
    }

    private double getCategoryTotal(Map<String, Double> map) {
        double total = 0.0;
        for (Double price : map.values()) {
            total += price;
        }
        return total;
    }

    public void showSortedTypes() {
        TreeMap<String, Double> categoryMap = new TreeMap<>();
        categoryMap.put("Food - $", getCategoryTotal(foodList));
        categoryMap.put("Clothes - $", getCategoryTotal(clothesList));
        categoryMap.put("Entertainment - $", getCategoryTotal(entertainmentList));
        categoryMap.put("Other - $", getCategoryTotal(otherList));
        TreeMap<String, Double> sortedCategoryMap = new TreeMap<>();
        sortedCategoryMap = valueSort(categoryMap);
        System.out.println("Types:");
        double total = 0.0;
        for (var entry : sortedCategoryMap.entrySet()) {
            System.out.printf(entry.getKey() + "%.2f\n", entry.getValue());
            total += entry.getValue();
        }
        System.out.printf("Total sum: $%.2f\n\n", total);
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(file, false);
        writer.write(balance + "\n");
        if (!foodList.isEmpty()) {
            writer.write("food list\n");
            for (var entry : foodList.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
        if (!clothesList.isEmpty()) {
            writer.write("clothes list\n");
            for (var entry : clothesList.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
        if (!entertainmentList.isEmpty()) {
            writer.write("entertainment list\n");
            for (var entry : entertainmentList.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
        if (!otherList.isEmpty()) {
            writer.write("other list\n");
            for (var entry : otherList.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
        System.out.println("Purchases were saved!\n");
        writer.close();
    }

    public void load() throws IOException {
        Scanner fileReader = new Scanner(file);
        this.balance = Double.parseDouble(fileReader.nextLine()); //first line of the file is balance
        LinkedHashMap<String, Double> currentMap = null;
        while (fileReader.hasNextLine()) {
            String str = fileReader.nextLine();
            if (str.equals("food list")) {
                currentMap = foodList;
            } else if (str.equals("clothes list")) {
                currentMap = clothesList;
            } else if (str.equals("entertainment list")) {
                currentMap = entertainmentList;
            } else if (str.equals("other list")) {
                currentMap = otherList;
            } else {
                String[] line = str.split(",");
                currentMap.put(line[0], Double.valueOf(line[1]));
            }
        }
        System.out.println("Purchases were loaded!\n");
    }

    //treemap sort by values instead of keys
    public static <K, V extends Comparable<V> > TreeMap<K, V> valueSort(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int comp = map.get(k2).compareTo( //sorting descending order
                        map.get(k1));
                if (comp == 0)
                    return -1;
                else
                    return comp;
            }
        };
        TreeMap<K, V> sorted = new TreeMap<K, V>(valueComparator);
        sorted.putAll(map);

        return sorted;
    }

}
