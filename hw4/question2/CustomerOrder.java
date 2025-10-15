import java.util.ArrayList;
import java.util.List;

import Food.FoodItem;

public class CustomerOrder {
    private Customer customerName;
    private List<FoodItem> items;

    public CustomerOrder(Customer customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public double calculateSubTotal() {
        double totalCost = 0.0;
        for (FoodItem item : items) {
            totalCost += item.getPrice();
        }
        return totalCost;
    }

    public double calculateTotal() {
        double subTotal = calculateSubTotal();
        return customerName.getLoyaltyStatus().applyDiscount(subTotal);
    }

    public void printReceipt() {
        System.out.println("\nReceipt for " + customerName.getCustomerName());
        System.out.println("Customer Loyalty Status: " + customerName.getLoyaltyStatus().getStatusName());
        System.out.println("");
        for (FoodItem item : items) {
            System.out.println(item.getItemName() + ": $" + String.format("%.2f", item.getPrice()));
        }
        System.out.println("Subtotal: $" + String.format("%.2f", calculateSubTotal()));
        System.out.println("Discount: -$" + String.format("%.2f", calculateSubTotal() - calculateTotal()));
        System.out.println("Total cost: $" + String.format("%.2f", calculateTotal()));
    }
}
