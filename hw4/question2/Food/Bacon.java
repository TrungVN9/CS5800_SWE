package Food;

public class Bacon extends AddOnDecorator {
    public Bacon(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public double getPrice() {
        return foodItem.getPrice() + 1.50;
    }

    @Override
    public String getItemName() {
        return foodItem.getItemName() + " + Bacon";
    }
}
