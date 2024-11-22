package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.Staff;
import java.util.ArrayList;

public class StaffContainer {
    private ArrayList<Staff> staffArrayList;

    public void add (Staff staff){
        staffArrayList.add(staff);   // add an employee
    }
}
