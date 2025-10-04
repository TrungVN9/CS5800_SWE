package hw3;

public class Customer {
    private String customerName;
    private MacronutrientFactory dietPlan;

    public Customer(String customerName, MacronutrientFactory dietPlan) {
        this.customerName = customerName;
        this.dietPlan = dietPlan;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setDietPlan(MacronutrientFactory dietPlan) {
        this.dietPlan = dietPlan;
    }

    public MacronutrientFactory getDietPlan() {
        return dietPlan;
    }
    
    public void selectDietPlan() {
        if (dietPlan == null) {
            System.out.println("Should choose a meal plan");
        }

        Carbs c = dietPlan.createCarbs();
        Protein p = dietPlan.createProtein();
        Fats f = dietPlan.createFats();
        
        System.out.println("========= " + dietPlan.getPlanName() + " for " + customerName + " =========");

        c.selectCarbs();
        p.selectProtein();
        f.selectFats();
    }
}
