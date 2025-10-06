package hw3;
/**
 *
 * @author trungvong
 */
public class Question2 {
    public static void main(String[] args) {
<<<<<<< HEAD
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
=======
        MacronutrientFactory noRestrictionOption = NoRestrictionFactory.getInstance();
        Customer cherry = new Customer("Cherry", noRestrictionOption);
        Customer peter = new Customer("Peter", noRestrictionOption);
        
        cherry.selectDietPlan();
        peter.selectDietPlan();
    
        MacronutrientFactory paleoOption = PaleoFactory.getInstance();
>>>>>>> refs/remotes/origin/main
        Customer victor = new Customer("Victor", paleoOption);
        
        victor.selectDietPlan();
        
<<<<<<< HEAD
=======
        MacronutrientFactory veganOption = VeganFactory.getInstance();
        Customer john = new Customer("John", veganOption);
        
        john.selectDietPlan();
        
        MacronutrientFactory nutAllergyOption = NutAllergyFactory.getInstance();
        Customer tom = new Customer("Tom", nutAllergyOption);
        Customer hana = new Customer("Hana", nutAllergyOption);
        
        tom.selectDietPlan();
        hana.selectDietPlan();
        
>>>>>>> refs/remotes/origin/main
    }
}
