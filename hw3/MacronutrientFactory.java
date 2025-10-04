package hw3;

/*
 *
 * @author trungvong
 */

// Implement the Abstract Factory pattern 
public abstract class MacronutrientFactory {
    abstract Carbs createCarbs();
    abstract Protein createProtein();
    abstract Fats createFats();
    public abstract String getPlanName();
}
