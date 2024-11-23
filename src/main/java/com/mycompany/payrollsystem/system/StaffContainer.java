package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.Staff;

import java.util.HashMap;
import java.util.Map;

//holds the staff (instead of a list I use hash map for easier look up)
//  Key: ID
//  Value: Staff

public class StaffContainer {
    private static Map<Integer, Staff> staffMap = new HashMap<>();  //static as it refers to the class

    public static boolean add(Staff staff) {    //called in a construcotr
        if (staffMap.containsKey(staff.getId())) {
            System.out.println("Error: Staff ID " + staff.getId() + " already exists.");
            return false;
        }
        staffMap.put(staff.getId(), staff);
        return true;
    }


    public static Staff getStaffById(int id) {
        return staffMap.get(id);
    }

    public static boolean isEmpty() {
        return staffMap.isEmpty();
    }

    public static void listAllStaff() {
        for (Staff staff : staffMap.values()) {
            System.out.println(staff.toString());
        }
    }
}

