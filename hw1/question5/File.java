/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package question5;

/**
 *
 * @author trungvong
 */
public class File {
    private String name;
    private double size; // in KB

    // package-private constructor: only classes in question5 can instantiate
    File(String name, double size) {
        this.name = name;
        this.size = size;
    }

    // Method to create a File instance with validation on size and folder existence
    static File createFile(Folder folder, String name, double size) {
        if (folder == null) {
            System.out.println("Cannot create a File without an existing Folder.");
            return null;
        }
        if (size <= 0) {
            System.out.println("File size must be positive.");
            return null;
        }
        return new File(name, size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    // print with indentation
    public void print(String indent) {
        System.out.println(indent + "File: " + name + " (" + size + " KB)");
    }
}
