package hw3.question1;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
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

    public static class Builder{
        private final String chainName;
        private final String size;
        private List<String> toppings = new ArrayList<>();

        public Builder(String chainName, String size){
            this.chainName = chainName;
            if (!size.equals("Small") && !size.equals("Medium") && !size.equals("Large")) {
                throw new IllegalArgumentException("Invalid size. Must be Small, Medium, or Large.");
            }
            this.size = size;
        }
        
        public Builder addPepperoni() {
            toppings.add("Pepperoni");
            return this;
        }

        public Builder addSausage() {
            toppings.add("Sausage");
            return this;
        }

        public Builder addMushrooms() {
            toppings.add("Mushrooms");
            return this;
        }

        public Builder addBacon() {
            toppings.add("Bacon");
            return this;
        }

        public Builder addOnions() {
            toppings.add("Onions");
            return this;
        }
        public Builder addExtraCheese() {
            toppings.add("Extra Cheese");
            return this;
        }

        public Builder addPeppers() {
            toppings.add("Peppers");
            return this;
        }

        public Builder addChicken() {
            toppings.add("Chicken");
            return this;
        }

        public Builder addOlives() {
            toppings.add("Olives");
            return this;
        }

        public Builder addSpinach() {
            toppings.add("Spinach");
            return this;
        }

        public Builder addTomatoAndBasil() {
            toppings.add("Tomato and Basil");
            return this;
        }

        public Builder addBeef() {
            toppings.add("Beef");
            return this;
        }
        
        public Builder addHam() {
            toppings.add("Ham");
            return this;
        }

        public Builder addPesto() {
            toppings.add("Pesto");
            return this;
        }

        public Builder addSpicyPork() {
            toppings.add("Spicy Pork");
            return this;
        }

        public Builder addHamAndPineapple() {
            toppings.add("Ham and Pineapple");
            return this;
        }
        
        public Pizza build(){
            return new Pizza(this);
        }
    }
}
    
