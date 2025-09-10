/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package question4;

/**
 *
 * @author trungvong
 */
public class Instructor {
    private String firstName;
    private String lastName;
    private String officeNumber;

    public Instructor(String firstName, String lastName, String officeNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeNumber = officeNumber;
    }
    
    public void setFirstName(String fn) {
        this.firstName = fn;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public String getLastName() {
        return lastName;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
    
    public String getOfficeNumber() {
        return officeNumber;
    }
    
}
