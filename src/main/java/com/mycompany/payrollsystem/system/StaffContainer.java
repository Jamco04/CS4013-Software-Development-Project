package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.Staff;

import java.util.Collection;
import java.util.HashMap;

/**
 * A class to manage a collection of staff using a HashMap for fast lookup by staff ID
 */
public class StaffContainer {
    private static HashMap<Integer, Staff> staffMap = new HashMap<>();  // static as it refers to the class

    /**
     * Adds a staff member to the collection
     * @param staff The staff member to be added
     * @return True if the staff member was added successfully, false if the ID already exists
     */
    public static boolean add(Staff staff) {    // called in a constructor, boolean for testing purposes
        if (staffMap.containsKey(staff.getId())) {
            System.out.println("Error: Staff ID " + staff.getId() + " already exists.");
            return false;
        }
        staffMap.put(staff.getId(), staff);
        return true;
    }

    /**
     * Retrieves a staff member by their ID
     * @param id The ID of the staff member to retrieve
     * @return The staff member with the given ID, or null if not found
     */
    public static Staff getStaffById(int id) {
        return staffMap.get(id);
    }

    /**
     * Checks if the staff collection is empty
     * @return True if the staff collection is empty, false otherwise
     */
    public static boolean isEmpty() {
        return staffMap.isEmpty();
    }

    /**
     * Lists all staff members in the collection
     */
    public static void listAllStaff() {
        for (Staff staff : staffMap.values()) {
            System.out.println(staff.toString());
        }
    }

    /**
     * Retrieves all staff members in the collection
     * @return A collection of all staff members
     */
    public static Collection<Staff> getAllStaff() {
        return staffMap.values();
    }

    /**
     * Clears all staff members from the collection
     */
    public static void clearAllStaff() {
        staffMap.clear();
    }
}
