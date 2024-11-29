package com.mycompany.payrollsystem.system.user;
import java.util.Arrays;
import java.util.List;

public class Titles {
    private static final List<String> validTitles = Arrays.asList(
            "Full Professor","Professor","Associate Professor A","Associate Professor B",
            "Assistant Professor","Teaching Assistant","Senior Administrative Officer III",
            "Senior Administrative Officer II","Senior Administrative Officer I",
            "Senior Executive Administrator", "Executive Administrator","Senior Administrator",
            "Administrator","EPS Portfolio Manager", "EPS Category Manager",
            "EPS Category Specialist Higher", "EPS Category Specialist","Sub Librarian",
            "Assistant Librarian II","Assistant Librarian I","Senior Library Assistant",
            "Library Assistant","Analyst Programmer III","Analyst Programmer II",
            "Analyst Programmer I","Senior Computer Operator","Computer Operator",
            "Print Operator II","Print Operator I","Computer Lab Attendant",
            "Temporary Computer Assistant","Chief Technical Officer","Technical Officer",
            "Senior Technical Officer","Senior Lab Attendant","Laboratory Attendant",
            "Sen Porter/Attendant","Porter/Attendant", "Grounds Supervisor","Groundsworkperson",
            "Senior Aide","Groundsworkperson Machine Attendant","Service Staff","Service Staff Shift",
            "Plant Maintenance Aide","Grounds Foreperson","Teaching Fellow","University Teacher",
            "Associate Teacher", "Therapies Regional Supervisors Regional Placement Facilitator",
            "Clinical Tutor","Clinical Fellow","Assistant Senior Instructor", "Lead Instructor",
            "Multi Activity Instructor (Grade I)","Multi Activity Instructor (Grade II)",
            "Multi Activity Instructor (Grade III)","Assistant Instructor","CO-OP STUDENTS"
    );

    public static List<String> getTitles() {
        return validTitles;
    }
}
