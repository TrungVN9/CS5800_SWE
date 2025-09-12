package question4;

import java.util.ArrayList;

/**
 *
 * @author trungvong
 */
public class Course {
    private String name;
    private Instructor instructor;
    private Textbook textbook;

    private ArrayList<Instructor> instructors; // for multiple instructors
    private ArrayList<Textbook> textbooks; // for multiple textbooks
    
    public Course(String name, Instructor instructor, Textbook textbook) {
        this.name = name;
        this.instructor = instructor;
        this.textbook = textbook;
    }
    
    // Constructor for multiple instructors and textbooks
    public Course(String name, ArrayList<Instructor> instructors, ArrayList<Textbook> textbooks) {
        this.name = name;
        this.instructors = instructors;
        this.textbooks = textbooks;
    }


    // Print function to display course details
    public void print() {
        System.out.println("Course Name: " + name);
        if (instructor != null) {
            System.out.println("Instructor: " + instructor.getFirstName() + " " + instructor.getLastName()
                    + ", Office: " + instructor.getOfficeNumber());
        }
        // Handle a list of instructors 
        else if (instructors != null) {
            System.out.println("Instructors:");
            for (Instructor inst : instructors) {
                System.out.println(
                        " - " + inst.getFirstName() + " " + inst.getLastName() + ", Office: " + inst.getOfficeNumber());
            }
        }
        if (textbook != null) {
            System.out.println("Textbook: " + textbook.getTitle() + " by " + textbook.getAuthor() + ", Publisher: "
                    + textbook.getPublisher());
        }
        // Handle a list of textbooks
        else if (textbooks != null) {
            System.out.println("Textbooks:");
            for (Textbook tb : textbooks) {
                System.out
                        .println(" - " + tb.getTitle() + " by " + tb.getAuthor() + ", Publisher: " + tb.getPublisher());
            }
        }
    }

}
