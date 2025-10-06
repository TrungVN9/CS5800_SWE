package hw3;

public abstract class MacronutrientFactory {
    abstract Carbs createCarbs();
    abstract Protein createProtein();
    abstract Fats createFats();
    
    public abstract String getPlanName();
}
