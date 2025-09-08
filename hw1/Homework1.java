package hw1;

public class Homework1 {
    public static void main(String[] args) {
        // Create a SalariedEmployee object
        SalariedEmployee salariedEmployee = new SalariedEmployee("Joe", "Jones", "111-11-1111", 2500);

        // Print SalariedEmployee details
        System.out.println("Salaried Employee:");
        System.out.println("Name: " + salariedEmployee.getFirstName() + " " + salariedEmployee.getLastName() + "\nSSN: " + salariedEmployee.getSsn());
        System.out.println("Weekly Salary: $" + salariedEmployee.getWeeklySalary());

        // Create an HourlyEmployee object
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Stephanie", "Smith", "222-22-2222", 25, 32);
        
        // Print HourlyEmployee details
        System.out.println("\nHourly Employee:");
        System.out.println("Name: " + hourlyEmployee.getFirstName() + " " + hourlyEmployee.getLastName() + "\nSSN: " + hourlyEmployee.getSsn());
        System.out.println("Wage: $" + hourlyEmployee.getWage());
        System.out.println("Worked Hours: " + hourlyEmployee.getHoursWorked());


        // Create a Hourly Employee 
        HourlyEmployee hourlyEmployee2 = new HourlyEmployee("Marry", "Quinn", "333-33-3333", 19, 47);

         // Print HourlyEmployee details
        System.out.println("\nHourly Employee:");
        System.out.println("Name: " + hourlyEmployee2.getFirstName() + " " + hourlyEmployee2.getLastName() + "\nSSN: " + hourlyEmployee2.getSsn());
        System.out.println("Wage: $" + hourlyEmployee2.getWage());
        System.out.println("Worked Hours: " + hourlyEmployee2.getHoursWorked());

        // Create a CommissionEmployee Object
        CommissionEmployee commissionEmployee = new CommissionEmployee("Nicole", "Dior", "444-44-4444", 0.15, 50000.0);

        // Print CommissionEmployee details
        System.out.println("\nCommission Employee:");
        System.out.println("Name: " + commissionEmployee.getFirstName() + " " + commissionEmployee.getLastName() + "\nSSN: " + commissionEmployee.getSsn());
        System.out.println("Commission Rate: " + commissionEmployee.getCommissionRate());
        System.out.println("Gross Sales: $" + commissionEmployee.getGrossSales());
        
        // Create a SalariedEmployee object
        SalariedEmployee salariedEmployee2 = new SalariedEmployee("Renwa", "Chanel", "555-55-5555", 1700);
        // Print SalariedEmployee details
        System.out.println("Salaried Employee:");
        System.out.println("Name: " + salariedEmployee2.getFirstName() + " " + salariedEmployee2.getLastName() + "\nSSN: " + salariedEmployee2.getSsn());
        System.out.println("Weekly Salary: $" + salariedEmployee2.getWeeklySalary());

        // Create BaseSalaried Employee Object
        BaseEmployee baseEmployee = new BaseEmployee("Mike", "Davenport", "666-66-6666", 95000.0);

        // Print BaseEmployee details
        System.out.println("\nBase Employee:");
        System.out.println("Name: " + baseEmployee.getFirstName() + " " + baseEmployee.getLastName() + "\nSSN: " + baseEmployee.getSsn());
        System.out.println("Base Salary: $" + baseEmployee.getBaseSalary());

        // Create Commission Employee Object 
        CommissionEmployee commissionEmployee2 = new CommissionEmployee("Mahnaz", "Vaziri", "777-77-7777", 0.22, 40000.0);

        // Print CommissionEmployee details
        System.out.println("\nCommission Employee:");
        System.out.println("Name: " + commissionEmployee2.getFirstName() + " " + commissionEmployee2.getLastName() + "\nSSN: " + commissionEmployee2.getSsn());
        System.out.println("Commission Rate: " + commissionEmployee2.getCommissionRate());
        System.out.println("Gross Sales: $" + commissionEmployee2.getGrossSales());
        
    }
}
