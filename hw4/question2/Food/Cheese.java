package Food;

public class Cheese extends AddOnDecorator {
    public Cheese(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public double getPrice() {
        return foodItem.getPrice() + 1.00;
    }

    @Override
    public String getItemName() {
        return foodItem.getItemName() + " + Cheese";
    }

}
