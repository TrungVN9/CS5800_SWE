import static org.junit.jupiter.api.Assertions.*;

import Food.*;
import LoyaltyStatues.GoldStatus;
import LoyaltyStatues.PlatinumStatus;
import org.junit.jupiter.api.Test;

public class testQuestion2 {
    @Test
    public void testLoyaltyStatues() {
        // BUILD
        GoldStatus gold = new GoldStatus();
        PlatinumStatus platinum = new PlatinumStatus();

        // OPERATE
        String goldStatus = gold.getStatusName();
        String platinumStatus = platinum.getStatusName();

        double goldDiscount = gold.applyDiscount(100);
        double platinumDiscount = platinum.applyDiscount(100);

        // CHECK
        assertNotEquals(goldStatus, platinumStatus);
        assertNotNull(goldStatus);
        assertNotNull(platinumStatus);
        assertTrue(goldStatus.contains("Gold"));
        assertTrue(platinumStatus.contains("Platinum"));

        assertNotEquals(goldDiscount, platinumDiscount);
        assertTrue(goldDiscount > platinumDiscount);
    }

    @Test
    public void testFoodItems(){
        // BUILD
        FoodItem burger = new Burger();
        FoodItem cheeseBurger = new Cheese(burger);
        FoodItem OnionCheeseBurger = new Onions(cheeseBurger);

        FoodItem fries = new Fries();
        FoodItem friesCheese = new Cheese(fries);

        // OPERATE
        String burgerName = burger.getItemName();
        double burgerPrice = burger.getPrice();

        String cheeseBurgerName = cheeseBurger.getItemName();
        double cheeseBurgerPrice = cheeseBurger.getPrice();

        String OnionCheeseBurgerName = OnionCheeseBurger.getItemName();
        double OnionCheeseBurgerPrice = OnionCheeseBurger.getPrice();

        String friesName = fries.getItemName();
        String friesCheeseName = friesCheese.getItemName();
        double friesPrice = fries.getPrice();
        double friesCheesePrice =  friesCheese.getPrice();

        // CHECK
        assertNotNull(burgerName);
        assertTrue(burgerPrice > 0.0);
        assertTrue(burgerName.contains("Burger"));

        assertTrue(cheeseBurgerPrice > burgerPrice);
        assertNotNull(cheeseBurgerName);
        assertTrue(cheeseBurgerName.contains("Cheese"));

        assertTrue(OnionCheeseBurgerPrice > cheeseBurgerPrice);
        assertNotNull(OnionCheeseBurgerName);
        assertTrue(OnionCheeseBurgerName.contains("Cheese, Onions"));

        assertTrue(friesCheesePrice > friesPrice);
        assertTrue(friesName.contains("Fries"));
        assertTrue(friesCheeseName.contains("Cheese"));

    }

    @Test
    public void testCustomerOrder(){
        // BUILD
        Customer john = new Customer("John", new GoldStatus());
        CustomerOrder order = new CustomerOrder(john);

        Customer tom = new Customer("Tom", new PlatinumStatus());
        CustomerOrder order1= new CustomerOrder(tom);

        Customer cherry = new Customer("Cheery", new GoldStatus());
        CustomerOrder order2 = new CustomerOrder(cherry);

        FoodItem burger =  new Burger();
        FoodItem hotDogs = new HotDogs();
        FoodItem fries = new Fries();
        FoodItem cheeseBurger = new Cheese(burger);

        // OPERATE
        order.addItem(burger);
        order.addItem(hotDogs);

        order1.addItem(burger);
        order1.addItem(hotDogs);

        order2.addItem(hotDogs);
        order2.addItem(fries);
        order2.addItem(cheeseBurger);
        // CHECK
        assertTrue(order.calculateSubTotal() > 0.0);
        assertTrue(order.calculateSubTotal() > order.calculateTotal());

        assertTrue(order1.calculateSubTotal() > 0.0);
        assertTrue(order1.calculateSubTotal() > order1.calculateTotal());

        assertTrue(order.calculateTotal() > order1.calculateTotal());
        assertTrue(order2.calculateSubTotal() > 0.0);
        assertTrue(order2.calculateSubTotal() > order2.calculateTotal());
        assertTrue(order2.calculateSubTotal() > order1.calculateSubTotal());
    }
}