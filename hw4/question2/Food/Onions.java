package Food;

public class Onions extends AddOnDecorator {
    public Onions(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public double getPrice() {
        return foodItem.getPrice() + 0.30;
    }

    @Override
    public String getItemName() {
        return foodItem.getItemName() + ", Onions";
    }

}
