package hw3.question1;

public class Question1{
    public static void main(String[] args) {
        
        // 3 topping pizza
        Pizza pizzaHutSmall = new Pizza.Builder()
                .setPizzaHut()
                .setSizeSmall()
                .addBacon()
                .addBeef()
                .addHam()
                .build();

        pizzaHutSmall.eat();
        
        // 6 toppings pizza
        Pizza pizzaHutMedium = new Pizza.Builder()
                .setPizzaHut()
                .setSizeMedium()
                .addBacon()
                .addChicken()
                .addHam()
                .addExtraCheese()
                .addMushrooms()
                .addOnions()
                .build();
        pizzaHutMedium.eat();

        // 9 toppings pizza
        Pizza pizzaHutLarge = new Pizza.Builder()
                .setPizzaHut()
                .setSizeLarge()
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
        
        Pizza largePizzaHut = new Pizza.Builder()
                                .setPizzaHut()
                                .setSizeLarge()
                                .addOnions()
                                .addPesto()
                                .addOlives()
                                .build();
        largePizzaHut.eat();

        Pizza smallPizzaHut = new Pizza.Builder()
                                .setPizzaHut()
                                .setSizeSmall()
                                .addOnions()
                                .addPesto()
                                .build();
        smallPizzaHut.eat();

        Pizza littleCaesarMedium = new Pizza.Builder()
                                .setLittleCaesars()
                                .setSizeMedium()
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

        Pizza littleCaesarSmall = new Pizza.Builder()
                                .setLittleCaesars()
                                .setSizeSmall()
                                .addBacon()
                                .addChicken()
                                .addHam()
                                .addExtraCheese()
                                .addMushrooms()
                                .addOnions()
                                .build();

        littleCaesarSmall.eat();

        Pizza dominoPizzaSmall = new Pizza.Builder()
                                .setDominos()
                                .setSizeSmall()
                                .addBacon()
                                .build();
        
        dominoPizzaSmall.eat();
        
        Pizza dominoPizzaLarge = new Pizza.Builder()
                                .setDominos()
                                .setSizeLarge()
                                .addExtraCheese()
                                .addMushrooms()
                                .addOnions()
                                .build();
            
        dominoPizzaLarge.eat();      
    }
}