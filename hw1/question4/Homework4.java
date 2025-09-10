/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package question4;

/**
 *
 * @author trungvong
 */
public class Homework4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Textbook cleanCode = new Textbook("Clean Code", "Robert Cecil Martin", "Pearson");
        Instructor SWEInstructor = new Instructor("Nima", "Davarpanah", "3-2636");
        
        Course sweCourse = new Course("Advanced Software Engineer", SWEInstructor, cleanCode);

        sweCourse.print();

        // Create 2 instructors and 2 textbooks
        Instructor dbInstructor = new Instructor("John", "Doe", "1-1234");
        Instructor algoInstructor = new Instructor("Jane", "Smith", "2-5678");
        Textbook dbTextbook = new Textbook("Database System Concepts", "Abraham Silberschatz", "McGraw-Hill");
        Textbook algoTextbook = new Textbook("Introduction to Algorithms", "Thomas H. Cormen", "MIT Press");
        // Create 2 courses using the above instructors and textbooks
        Course dbCourse = new Course("Database Systems", dbInstructor, dbTextbook);
        Course algoCourse = new Course("Algorithms", algoInstructor, algoTextbook);
        // Print the details of each course
        System.out.println();
        dbCourse.print();
        System.out.println();
        algoCourse.print();
    }

}
