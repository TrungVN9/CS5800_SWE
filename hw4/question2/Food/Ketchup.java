package Food;

public class Ketchup extends AddOnDecorator {
    public Ketchup(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public String getItemName() {
        return foodItem.getItemName() + " + Ketchup";
    }

    @Override
    public double getPrice() {
        return foodItem.getPrice() + 0.25;
    }

}
