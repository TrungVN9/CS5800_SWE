package question4;

import java.util.ArrayList;

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
        System.out.println();

        System.out.println("----- Creating another course with multiple instructors and textbooks -----");
        // Create 2 instructors and 2 textbooks for one course 
        Instructor sweInstructor1 = new Instructor("John", "Doe", "1-1234");
        Instructor sweInstructor2 = new Instructor("Jane", "Smith", "2-5678");
        Textbook sweTextBook1 = new Textbook("Design Pattern", "Erich Gamma", "Addison-Wesley");
        Textbook sweTextBook2 = new Textbook("Automate Boring Stuff", "Al Sweigart", "No Starch Press");
        
        ArrayList<Instructor> instructors = new ArrayList<>();
        instructors.add(sweInstructor1);
        instructors.add(sweInstructor2);
        
        ArrayList<Textbook> textbooks = new ArrayList<>();
        textbooks.add(sweTextBook1);
        textbooks.add(sweTextBook2);

        Course sweCourse2 = new Course("Software Engineering", instructors, textbooks);
        sweCourse2.print();
        System.out.println();
    }

}
