package hw3;
/**
 *
 * @author trungvong
 */
public class Question2 {
    public static void main(String[] args) {
        MacronutrientFactory noRestrictionOption = NoRestrictionFactory.getInstance();
        MacronutrientFactory paleoOption = PaleoFactory.getInstance();
        MacronutrientFactory veganOption = VeganFactory.getInstance();
        MacronutrientFactory nutAllergyOption = NutAllergyFactory.getInstance();

        Customer tom = new Customer("Tom", nutAllergyOption);
        Customer john = new Customer("John", veganOption);
        Customer cherry = new Customer("Cherry", noRestrictionOption);
        Customer victor = new Customer("Victor", paleoOption);
        Customer hana = new Customer("Hana", nutAllergyOption);
        Customer peter = new Customer("Peter", noRestrictionOption);
        
        tom.selectDietPlan();
        john.selectDietPlan();
        cherry.selectDietPlan();
        victor.selectDietPlan();
        hana.selectDietPlan();
        peter.selectDietPlan();
    }
}
