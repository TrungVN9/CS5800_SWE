package Food;

public class HotDogs implements FoodItem{
    @Override
    public String getItemName() {
        return "Hot Dogs";
    }

    @Override
    public double getPrice() {
        return 3.49;
    }
}
