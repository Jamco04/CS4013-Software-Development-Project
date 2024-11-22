package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.Staff;

import java.util.HashMap;
import java.util.Map;

public class StaffContainer {
    private Map<Integer, Staff> staffMap = new HashMap<>();

    public void add(Staff staff) {
        staffMap.put(staff.getId(), staff);
    }

    public Staff getStaffById(int id) {
        return staffMap.get(id);
    }

    public void listAllStaff() {
        for (Staff staff : staffMap.values()) {
            System.out.println(staff);
        }
    }
}

