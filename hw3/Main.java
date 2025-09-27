package hw3;
/**
 *
 * @author trungvong
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== No Restriction Plan ===");
        MacronutrientFactory factory1 = new NoRestrictionFactory();
        Carbs carb1 = factory1.createCarbs();
        Protein protein1 = factory1.createProtein();
        Fats fats1 = factory1.createFats();
        carb1.selectCarbs();
        protein1.selectProtein();
        fats1.selectFats();

        System.out.println("=== Paleo Plan ===");
        MacronutrientFactory factory2 = new PaleoFactory();
        Carbs carb2 = factory2.createCarbs();
        Protein protein2 = factory2.createProtein();
        Fats fats2 = factory2.createFats();

        carb2.selectCarbs();
        protein2.selectProtein();
        fats2.selectFats();

        System.out.println("=== Vegan Plan ===");
        MacronutrientFactory factory3 = new VeganFactory();
        Carbs carb3 = factory3.createCarbs();
        Protein protein3 = factory3.createProtein();
        Fats fats3 = factory3.createFats();
        carb3.selectCarbs();
        protein3.selectProtein();
        fats3.selectFats();

        System.out.println("=== Nut Allergy Plan ===");
        MacronutrientFactory factory4 = new NutAllergyFactory();
        Carbs carb4 = factory4.createCarbs();
        Protein protein4 = factory4.createProtein();
        Fats fats4 = factory4.createFats();
        carb4.selectCarbs();
        protein4.selectProtein();
        fats4.selectFats();
        
    }
}
