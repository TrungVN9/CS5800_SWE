import Food.Cheese;
import Food.FoodItem;
import Food.HotDogs;
import Food.Ketchup;
import Food.Burger;
import Food.Onions;
import Food.Fries;

import LoyaltyStatues.GoldStatus;
import LoyaltyStatues.PlatinumStatus;

public class RestaurantOrder {

    public static void main(String[] args) {
        Customer john = new Customer("John", new GoldStatus());
        CustomerOrder order1 = new CustomerOrder(john);
        
        FoodItem burger = new Burger();
        burger = new Cheese(burger);

        order1.addItem(burger);
        
        FoodItem hotdog = new HotDogs();
        hotdog = new Onions(hotdog);
        hotdog = new Ketchup(hotdog);

        order1.addItem(hotdog);
        order1.printReceipt();
        
        System.out.println("==============================");

        Customer jane = new Customer("Jane", new PlatinumStatus());
        CustomerOrder order2 = new CustomerOrder(jane);

        FoodItem hotdog2 = new HotDogs();
        hotdog2 = new Ketchup(hotdog2);

        order2.addItem(hotdog2);

        FoodItem fries = new Fries();
        fries = new Onions(fries);
        fries = new Cheese(fries);

        order2.addItem(fries);
        order2.printReceipt();

        System.out.println("==============================");

        Customer Tom = new Customer("Tom", new GoldStatus());
        CustomerOrder order3 = new CustomerOrder(Tom);

        FoodItem hotdog3 = new HotDogs();
        hotdog3 = new Ketchup(hotdog3);

        order3.addItem(hotdog3);

        FoodItem fries1 = new Fries();
        fries1 = new Onions(fries1);
        fries1 = new Cheese(fries1);

        order3.addItem(fries1);
        order3.printReceipt();
    }   

}
