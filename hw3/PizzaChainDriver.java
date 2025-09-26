package hw3;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Pizza {
    private String chainName;
    private List<String> toppings;
    private String size;

    public void setTopping(List<String> toppings) {
        this.toppings = toppings;
    }

    public List<String> getTopping() {
        return toppings;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getChainName() {
        return chainName;
    }

    private Pizza(Builder builder){
        this.chainName = builder.chainName;
        this.size = builder.size;
        this.toppings = builder.toppings;
    }

    public void eat(){
        System.out.println(chainName + ": " + size + " pizza with " + toppings.size() + " toppings " + toppings);
    }

    //Builder
    public static class Builder{
        private final String chainName;
        private final String size;
        private List<String> toppings = new ArrayList<>();

        public Builder(String chainName, String size){
            this.chainName = chainName;
            this.size = size;
        }

        public Builder addTopping(String toppingName){

            List<String> listToppings = Arrays.asList(
                "Pepperoni", "Sausage", "Mushrooms", "Bacon",
                "Onions", "Extra Cheese", "Peppers", "Chicken",
                "Olives", "Spinach", "Tomato and Basil", "Beef",
                "Ham", "Pesto", "Spicy Pork", "Ham and Pineapple"
            );
            if (!listToppings.contains(toppingName)){
                throw new IllegalArgumentException(toppingName + " not available!");
            }            
            toppings.add(toppingName);
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }
    }
}

public class PizzaChainDriver{
    public static void main(String[] args){
        //Pizza hut
        Pizza pizzaHutSmall = new Pizza.Builder("Pizza Hut", "Small")
            .addTopping("Pepperoni")
            .addTopping("Mushrooms")
            .addTopping("Olives")
            .build();

        pizzaHutSmall.eat();
        
        Pizza pizzaHutMedium = new Pizza.Builder("Pizza Hut", "Medium")
            .addTopping("Pepperoni")
            .addTopping("Mushrooms")
            .addTopping("Olives")
            .addTopping("Beef")
            .addTopping("Bacon")
            .addTopping("Sausage")
            .build();

        pizzaHutMedium.eat();

        Pizza pizzaHutLarge = new Pizza.Builder("Pizza Hut", "Large")
            .addTopping("Pepperoni")
            .addTopping("Mushrooms")
            .addTopping("Olives")
            .addTopping("Beef")
            .addTopping("Bacon")
            .addTopping("Chicken")
            .addTopping("Extra Cheese")
            .addTopping("Ham")
            .addTopping("Sausage")
            .build();

        pizzaHutLarge.eat();


        // Part 2 --- Purchasing another 2 pizza chains
        Pizza largePizzaHut = new Pizza.Builder("Pizza Hut", "Large")
                            .addTopping("Extra Cheese")
                            .addTopping("Ham")
                            .addTopping("Sausage")
                            .build();
        largePizzaHut.eat();

        Pizza smallPizzaHut = new Pizza.Builder("Pizza Hut", "Small")
                            .addTopping("Extra Cheese")
                            .addTopping("Ham")
                            .build();
        smallPizzaHut.eat();

        Pizza littleCaesarMedium = new Pizza.Builder("Little Caesars", "Medium")
            .addTopping("Mushrooms")
            .addTopping("Olives")
            .addTopping("Beef")
            .addTopping("Bacon")
            .addTopping("Chicken")
            .addTopping("Extra Cheese")
            .addTopping("Ham")
            .addTopping("Sausage")
            .build();

        littleCaesarMedium.eat();

        Pizza littleCaesarSmall = new Pizza.Builder("Little Caesars", "Small")
            .addTopping("Mushrooms")
            .addTopping("Olives")
            .addTopping("Beef")
            .addTopping("Extra Cheese")
            .addTopping("Ham")
            .addTopping("Sausage")
            .build();

        littleCaesarSmall.eat();

        Pizza dominoPizzaSmall = new Pizza.Builder("Dominos", "Small")
            .addTopping("Beef")
            .build();
        
        dominoPizzaSmall.eat();
        
        Pizza dominoPizzaLarge = new Pizza.Builder("Dominos", "Large")
            .addTopping("Extra Cheese")
            .addTopping("Ham")
            .addTopping("Sausage")
            .build();
            
        dominoPizzaLarge.eat();      
    }
}