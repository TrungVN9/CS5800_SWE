package hw3;
/**
 *
 * @author trungvong
 */
public class Question2 {
    public static void main(String[] args) {
        MacronutrientFactory noRestrictionOption = new NoRestrictionFactory();
        Customer cherry = new Customer("Cherry", noRestrictionOption);
        Customer peter = new Customer("Peter", noRestrictionOption);

        cherry.selectDietPlan();
        peter.selectDietPlan();

        MacronutrientFactory nutAllergyOption = new NutAllergyFactory();
        Customer tom = new Customer("Tom", nutAllergyOption);
        Customer hana = new Customer("Hana", nutAllergyOption);

        tom.selectDietPlan();
        hana.selectDietPlan();

        MacronutrientFactory veganOption = new VeganFactory();
        Customer john = new Customer("John", veganOption);
        
        john.selectDietPlan();

        MacronutrientFactory paleoOption = new PaleoFactory();
        Customer victor = new Customer("Victor", paleoOption);
        
        victor.selectDietPlan();
        
    }
}
