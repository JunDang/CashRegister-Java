import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

public class CashRegister {

    static int MANAGER_KEY = 5678;
    int amountPurchased = 0;
    public ArrayList<PurchaseRecord> purchaseRecords;
    public String menuDescription;
    public ArrayList<Item> items;

    public CashRegister(ArrayList<Item> items) {
        this.items = items;
        this.purchaseRecords = new ArrayList<>();
    }

    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();

        Item item1 = new Item("pants", 100f, 10);
        Item item2 = new Item("shoes", 400f, 40);
        Item item3 = new Item("Shirts", 99f, 50);

        items.add(item1);
        items.add(item2);
        items.add(item3);

        CashRegister cashRegister = new CashRegister(items);
        cashRegister.updateMenu();

        do
        {
            Scanner s = new Scanner(System.in);
            int number = s.nextInt();

            if (1 <= number && number <= items.size())
            {
                Item item = items.get(number - 1);
                cashRegister.purchaseItemandUpdate(item, s);

            } else if (number == MANAGER_KEY) {

                cashRegister.managerOperation(s);

            } else if (number == -1){

                System.out.print("you are leaving this system");
                break;

            } else {

                System.out.println("Your selection is not within the range. Please select again");

            }

        } while (true);
    }

    void purchaseItemandUpdate(Item item, Scanner s) {
        System.out.println("how many " + item.getDescription() + " would you like: ");
        amountPurchased = s.nextInt();

        while (amountPurchased > item.getQuantity())
        {
            System.out.println("There is only " + item.getQuantity() + " in the stock, please input another amount: ");
            amountPurchased = s.nextInt();
        }

        int itemQuantityLeft = item.getQuantity() - amountPurchased;
        item.setQuantity(itemQuantityLeft);
        Float totalCost = amountPurchased * item.getPrice();
        Date currentTime = new Date();

        PurchaseRecord purchase = new PurchaseRecord(currentTime, item.getDescription(), amountPurchased, totalCost);
        purchaseRecords.add(purchase);
        System.out.println("your total cost is " + totalCost);
        System.out.println();

        updateMenu();
    }

    void managerOperation(Scanner scanner)
    {
        System.out.println();
        System.out.println("you are entering manager mode.");

        while (true) {
            System.out.println();
            System.out.println(
                    "input 0 to take a look at the stock and purchase records.\nor item id to restock.\nor input -3 to exit the manager mode.");
            int num = scanner.nextInt();
            if (num == 0) {
                for (Item item : items
                ) {
                    System.out.println(item.toString());

                }

                for (PurchaseRecord purchase : purchaseRecords
                ) {
                    System.out.println(purchase.description + purchase.toString() + "with a total cost of " + purchase.price);

                }
            } else if (num > 0 && num <= items.size()) {
                Item item = items.get(num - 1);
                System.out.println("how many " + item.getDescription() + " would you like to add to your stock?");
                int quantity = scanner.nextInt();
                int restockedAmount = item.getQuantity() + quantity;
                item.setQuantity(restockedAmount);
                System.out.println("you have added " + quantity + " " + item.getDescription() + ", now you have " + item.getQuantity() + " " + item.getDescription() + " in the stock.");
            } else if (num == -3){
                System.out.println("Leaving the manager mode.\n");
                break;
            } else {
                System.out.println("It is wrong input, please input the number listed in the options.");
            }
        }
        System.out.println();
        updateMenu();
    }

    void updateMenu()
    {
        menuDescription = "There are ";
        for (Item item: items) {
            menuDescription = menuDescription + item.toString() + "\n";
        }
        String choice = "\nIf you are a buyer, Please enter:\nnumber 1 for buying pants\nnumber 2 for buying shoes\nnumber 3 for buying shirts\ninput number -1 to leave the system\nIf you are a manager, please input the manger key." ;
        menuDescription = menuDescription + choice;
        System.out.println(menuDescription);
        System.out.println();
        String option = "Which would you like to purchase?";
        System.out.println(option);
    }
}
