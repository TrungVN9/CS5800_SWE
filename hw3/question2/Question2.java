package hw3.question2;
/**
 *
 * @author trungvong
 */
public class Question2 {
    public static void main(String[] args) {
        MacronutrientFactory noRestrictionOption = NoRestrictionFactory.getInstance();
        Customer cherry = new Customer("Cherry", noRestrictionOption);
        Customer peter = new Customer("Peter", noRestrictionOption);

        cherry.selectDietPlan();
        peter.selectDietPlan();

        MacronutrientFactory nutAllergyOption = NutAllergyFactory.getInstance();
        Customer tom = new Customer("Tom", nutAllergyOption);
        Customer hana = new Customer("Hana", nutAllergyOption);

        tom.selectDietPlan();
        hana.selectDietPlan();

        MacronutrientFactory veganOption = VeganFactory.getInstance();
        Customer john = new Customer("John", veganOption);
        
        john.selectDietPlan();

        MacronutrientFactory paleoOption = PaleoFactory.getInstance();
        Customer victor = new Customer("Victor", paleoOption);
        
        victor.selectDietPlan();
        
    }
}
