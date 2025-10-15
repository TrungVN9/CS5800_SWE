package Food;

public class Burger implements FoodItem {
    @Override
    public String getItemName() {
        return "Burger";
    }

    @Override
    public double getPrice() {
        return 5.99;
    }
}
