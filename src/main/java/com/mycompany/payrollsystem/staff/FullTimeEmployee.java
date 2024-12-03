package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.ScaleLoader;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

/**
 *
 */

//FullTimeEmployee
//(Adam+James)

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary;      //only FullTimeEmployee has annual salary
    private LocalDateTime topScaleStartTime; //Checks time that employee has been at the top scalePoint



    /**
     * Constructor for full time employees
     * Takes the below parameters and creates a fullTimeEmployee object
     * This is done by calling the default constructor specified in the staff class with
     * name, id, title, scalePoint and password, then  with category and salary after as they are not in the super constructor
     * @param name employees name
     * @param id employees id
     * @param category employees current category
     * @param title employees current title
     * @param scalePoint employees current scalePoint
     * @param password employees current password
     */

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint, String password) {
        super(name, id, title, scalePoint, password);
        this.category = category;
        this.salary = getSalary();
        checkTopScale();
    }


    /**
     * A method with no added parameters
     * This method checks whether an employee's scale point has reached the maximum for their job title
     * If the employee reaches their top scale point, the date and time at which this occurred is logged
     * This time since the employee has been at the max scalePoint is stored in topScaleStartTime
     * It then resets the topScaleStartTime to 0 when an employee is no longer at the top of their scalePoint scale
     */

    public void checkTopScale(){
        int maxScalePoints = ScaleLoader.getMaxScalePoints(title);
        if (scalePoint == maxScalePoints){
            if (topScaleStartTime == null) {
                topScaleStartTime = LocalDateTime.now();
            }
        }
        else {
            topScaleStartTime = null;
        }
    }

    /**
     * A method with no parameters
     * This method advances an employees current scalePoint by one-
     * provided their current scalePoint is not the maximum scale point within their title
     * Also automatically adjusts the employee's salary after each scale point update.
     * @return false if at the top of the scalePoint, true if not
     */

    public boolean updateScalePoint() {
        int maxScalePoints = ScaleLoader.getMaxScalePoints(title);
        if (scalePoint < maxScalePoints) {
            checkTopScale();
            scalePoint++;
            salary = getSalary();
            return true;
        } else {
            return false; // Already at top
        }
    }

    /**
     * This method is used to determine how long (in full years) an employee has been at their maximum scale point.
     * @return how many full years the employee has been at the top of their payscale
     */

    public long getYearsAtTop() {
        if (topScaleStartTime == null) return 0;
        return ChronoUnit.YEARS.between(topScaleStartTime, LocalDateTime.now());
    }

    /**
     * A method used for promoting an employee to a new title
     * Takes the new title the employee will be promoted to as the only parameter
     * Employees default datafield "title" is updated to the new one which is passed in
     * Determines how many years the employee has been at the top scale point for their previous title.
     * Assigns new scalePoint within new title based off that
     * Employees salary is recalculated to reflect their new position
     * @param newTitle The employees new title which they are being promoted to
     */

    public void promoteToNewTitle(String newTitle) {
        title = newTitle;
        int yearsAtTop = (int) getYearsAtTop();
        topScaleStartTime = null; // Reset top scale timer
        int maxScalePoints = ScaleLoader.getMaxScalePoints(newTitle);


        scalePoint = Math.min(yearsAtTop, ScaleLoader.getMaxScalePoints(newTitle));   //new scalepoint will be years at top (limit is the maximum scale point)
        if (scalePoint == 0) {
            scalePoint = 1; } //years could be 0, in which case make scalePoint 1

        salary = getSalary();
        checkTopScale();

    }

    /**
     * Calls the method getPay from the ScaleLoader class
     * Calls this method passing category, title, and the string value of the integer scalePoint
     * @return the employee's current salary based on their category, title, and scalePoint
     */

    public double getSalary() {
        return ScaleLoader.getPay(category, title, String.valueOf(scalePoint));
    }

    /**
     * A simple getter method for getting an enployees category
     * @return Employees current category
     */

    public String getCategory() {
        return category;
    }

    /**
     * toString method for displaying a full time employees details
     * @return displays a fullTime Employees current name, id, category, title, scalePoint, and salary
     */

    @Override
    public String toString() {
        return String.format("Full-Time Employee: name: %s | id: %d | category: %s | title: %s | scalePoint: %d | salary: %.2f",
                name, id, category, title, scalePoint, salary);
    }
}