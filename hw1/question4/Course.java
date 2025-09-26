/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package question4;

/**
 *
 * @author trungvong
 */
public class Course {
    private String name;
    private Instructor instructor;
    private Textbook textbook;

    private Instructor[] instructors; // for multiple instructors
    private Textbook[] textbooks; // for multiple textbooks
    
    public Course(String name, Instructor instructor, Textbook textbook) {
        this.name = name;
        this.instructor = instructor;
        this.textbook = textbook;
    }
    
    // Constructor for multiple instructors and textbooks
    public Course(String name, Instructor[] instructors, Textbook[] textbooks) {
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
        } else if (instructors != null) {
            System.out.println("Instructors:");
            for (Instructor inst : instructors) {
                System.out.println(
                        " - " + inst.getFirstName() + " " + inst.getLastName() + ", Office: " + inst.getOfficeNumber());
            }
        }
        if (textbook != null) {
            System.out.println("Textbook: " + textbook.getTitle() + " by " + textbook.getAuthor() + ", Publisher: "
                    + textbook.getPublisher());
        } else if (textbooks != null) {
            System.out.println("Textbooks:");
            for (Textbook tb : textbooks) {
                System.out
                        .println(" - " + tb.getTitle() + " by " + tb.getAuthor() + ", Publisher: " + tb.getPublisher());
            }
        }
    }

}
