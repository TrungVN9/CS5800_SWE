package hw3;

public class PizzaChainDriver{
    public static void main(String[] args) {
        
        // 3 topping pizza
        Pizza pizzaHutSmall = new Pizza.Builder("Pizza Hut", "Small")
                .addBacon()
                .addBeef()
                .addHam()
                .build();

        pizzaHutSmall.eat();
        
        // 6 toppings pizza
        Pizza pizzaHutMedium = new Pizza.Builder("Pizza Hut", "Medium")
                .addBacon()
                .addChicken()
                .addHam()
                .addExtraCheese()
                .addMushrooms()
                .addOnions()
                .build();
        pizzaHutMedium.eat();

        // 9 toppings pizza
        Pizza pizzaHutLarge = new Pizza.Builder("Pizza Hut", "Large")
                .addSpinach()
                .addSpicyPork()
                .addHam()
                .addBacon()
                .addBeef()
                .addOnions()
                .addPesto()
                .addOlives()
                .addPesto()
                .build();

        pizzaHutLarge.eat();

        System.out.println("======== PART 2 ===========");
        // Part 2 --- Purchasing another 2 pizza chains
        Pizza largePizzaHut = new Pizza.Builder("Pizza Hut", "Large")
                                .addOnions()
                                .addPesto()
                                .addOlives()
                                .build();
        largePizzaHut.eat();

        Pizza smallPizzaHut = new Pizza.Builder("Pizza Hut", "Small")
                                .addOnions()
                                .addPesto()
                                .build();
        smallPizzaHut.eat();

        Pizza littleCaesarMedium = new Pizza.Builder("Little Caesars", "Medium")
                                    .addSpicyPork()
                                    .addHam()
                                    .addBacon()
                                    .addBeef()
                                    .addOnions()
                                    .addPesto()
                                    .addOlives()
                                    .addPesto()
                                    .build();

        littleCaesarMedium.eat();

        Pizza littleCaesarSmall = new Pizza.Builder("Little Caesars", "Small")
                                    .addBacon()
                                    .addChicken()
                                    .addHam()
                                    .addExtraCheese()
                                    .addMushrooms()
                                    .addOnions()
                                    .build();

        littleCaesarSmall.eat();

        Pizza dominoPizzaSmall = new Pizza.Builder("Dominos", "Small")
                                    .addBacon()
                                    .build();
        
        dominoPizzaSmall.eat();
        
        Pizza dominoPizzaLarge = new Pizza.Builder("Dominos", "Large")
                                    .addExtraCheese()
                                    .addMushrooms()
                                    .addOnions()
                                    .build();
            
        dominoPizzaLarge.eat();      
    }
}