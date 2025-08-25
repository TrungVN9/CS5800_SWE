package hw1;

public class TestHomework1 {
    public static void main(String[] args) {
        // Create a SalariedEmployee object
        SalariedEmployee salariedEmployee = new SalariedEmployee();
        salariedEmployee.setFirstName("Joe");
        salariedEmployee.setLastName("Jones");
        salariedEmployee.setSsn("111-11-1111");
        salariedEmployee.setWeeklySalary(2500.0);

        // Print SalariedEmployee details
        System.out.println("Salaried Employee:");
        System.out.println("Name: " + salariedEmployee.getFirstName() + " " + salariedEmployee.getLastName() + "\nSSN: " + salariedEmployee.getSsn());
        System.out.println("Weekly Salary: $" + salariedEmployee.getWeeklySalary());

        // Create an HourlyEmployee object
        HourlyEmployee hourlyEmployee = new HourlyEmployee();
        hourlyEmployee.setFirstName("Stephanie");
        hourlyEmployee.setLastName("Smith");
        hourlyEmployee.setSsn("222-22-2222");
        hourlyEmployee.setWage(25.0);
        hourlyEmployee.setWorkedHours(32.0);

        // Print HourlyEmployee details
        System.out.println("\nHourly Employee:");
        System.out.println("Name: " + hourlyEmployee.getFirstName() + " " + hourlyEmployee.getLastName() + "\nSSN: " + hourlyEmployee.getSsn());
        System.out.println("Wage: $" + hourlyEmployee.getWage());
        System.out.println("Worked Hours: " + hourlyEmployee.getWorkedHours());


        // Create a Hourly Employee 
        HourlyEmployee hourlyEmployee2 = new HourlyEmployee();
        hourlyEmployee2.setFirstName("Marry");
        hourlyEmployee2.setLastName("Quinn");
        hourlyEmployee2.setSsn("333-33-3333");
        hourlyEmployee2.setWage(19.0);
        hourlyEmployee2.setWorkedHours(47.0);

         // Print HourlyEmployee details
        System.out.println("\nHourly Employee:");
        System.out.println("Name: " + hourlyEmployee2.getFirstName() + " " + hourlyEmployee2.getLastName() + "\nSSN: " + hourlyEmployee2.getSsn());
        System.out.println("Wage: $" + hourlyEmployee2.getWage());
        System.out.println("Worked Hours: " + hourlyEmployee2.getWorkedHours());

        // Create a CommissionEmployee Object
        CommissionEmployee commissionEmployee = new CommissionEmployee();
        commissionEmployee.setFirstName("Nicole");
        commissionEmployee.setLastName("Dior");
        commissionEmployee.setSsn("444-44-4444");
        commissionEmployee.setCommissionRate(0.15);
        commissionEmployee.setGrossSales(50000.0);

        // Print CommissionEmployee details
        System.out.println("\nCommission Employee:");
        System.out.println("Name: " + commissionEmployee.getFirstName() + " " + commissionEmployee.getLastName() + "\nSSN: " + commissionEmployee.getSsn());
        System.out.println("Commission Rate: " + commissionEmployee.getCommissionRate());
        System.out.println("Gross Sales: $" + commissionEmployee.getGrossSales());
        
        // Create a SalariedEmployee object
        SalariedEmployee salariedEmployee2 = new SalariedEmployee();
        salariedEmployee2.setFirstName("Renwa");
        salariedEmployee2.setLastName("Chanel");
        salariedEmployee2.setSsn("555-55-5555");
        salariedEmployee2.setWeeklySalary(1700.0);

        // Print SalariedEmployee details
        System.out.println("Salaried Employee:");
        System.out.println("Name: " + salariedEmployee2.getFirstName() + " " + salariedEmployee2.getLastName() + "\nSSN: " + salariedEmployee2.getSsn());
        System.out.println("Weekly Salary: $" + salariedEmployee2.getWeeklySalary());

        // Create BaseSalaried Employee Object
        BaseEmployee baseEmployee = new BaseEmployee();
        baseEmployee.setFirstName("Mike");
        baseEmployee.setLastName("Davenport");
        baseEmployee.setSsn("666-66-6666");
        baseEmployee.setBaseSalary(95000.0);

        // Print BaseEmployee details
        System.out.println("\nBase Employee:");
        System.out.println("Name: " + baseEmployee.getFirstName() + " " + baseEmployee.getLastName() + "\nSSN: " + baseEmployee.getSsn());
        System.out.println("Base Salary: $" + baseEmployee.getBaseSalary());

        // Create Commission Employee Object 
        CommissionEmployee commissionEmployee2 = new CommissionEmployee();
        commissionEmployee2.setFirstName("Mahnaz");
        commissionEmployee2.setLastName("Vaziri");
        commissionEmployee2.setSsn("777-77-7777");
        commissionEmployee2.setCommissionRate(0.22);
        commissionEmployee2.setGrossSales(40000.0);

        // Print CommissionEmployee details
        System.out.println("\nCommission Employee:");
        System.out.println("Name: " + commissionEmployee2.getFirstName() + " " + commissionEmployee2.getLastName() + "\nSSN: " + commissionEmployee2.getSsn());
        System.out.println("Commission Rate: " + commissionEmployee2.getCommissionRate());
        System.out.println("Gross Sales: $" + commissionEmployee2.getGrossSales());
        
    }
}
