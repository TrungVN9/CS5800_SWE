package Food;

public class Fries implements FoodItem {
    @Override
    public String getItemName() {
        return "Fries";
    }

    @Override
    public double getPrice() {
        return 2.49;
    }
}
