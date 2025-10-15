package Food;

public abstract class AddOnDecorator implements FoodItem {
    protected FoodItem foodItem;

    public AddOnDecorator(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public double getPrice() {
        return foodItem.getPrice();
    }

    @Override
    public String getItemName() {
        return foodItem.getItemName();
    }

}
