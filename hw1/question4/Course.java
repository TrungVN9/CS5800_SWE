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

    public Course(String name, Instructor instructor, Textbook textbook) {
        this.name = name;
        this.instructor = instructor;
        this.textbook = textbook;
    }
    
    public void print(){
        System.out.println("Course name: " + name);
        System.out.println("Instructor name: " + instructor.getFirstName() + " " + instructor.getLastName());
        System.out.println("Textbook title: " + textbook.getTitle() + "\nauthor: " + textbook.getAuthor() + "\npublisher: " + textbook.getPublisher());
    }

}
