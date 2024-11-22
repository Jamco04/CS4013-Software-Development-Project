package com.mycompany.payrollsystem.system;


import java.util.Scanner;

/**
 A Command Line Interface for the payroll system.
 */

public class CLI
{
    private Scanner in;

    public CLI()
    {
        in = new Scanner(System.in);
    }

    /**
     Runs the system.
     */

    public void run()
    {
        System.out.println("Welcome to the Payroll System!");
        System.out.println("Please enter your role (Admin/HR/Employee):");
        String role = in.nextLine().toLowerCase();

        boolean more = false;
        while (more)
        switch (role) {
            case "admin":
                runAdminCLI();
                break;
            case "hr":
                runHRCLI();
                break;
            case "employee":
                runEmployeeCLI();
                break;
            default:
                System.out.println("Please enter a VALID role (Admin/HR/Employee):");
                more = true;
        }
    }

        boolean more = true;
        StaffContainer staff = new StaffContainer(); // holds the appointments

        while (more)
        {
            System.out.println("A)dd  C)ancel  S)how  Q)uit");
            String command = in.nextLine().toUpperCase();

            if (command.equals("A"))
            {
                System.out.println("Appointment: date start_time end_time description");
                String line = in.nextLine();
                Appointment a = new Appointment(line);  // creates the new appointment and adds it to calendar
                if (calendar.checkCollision(a)){
                    System.out.println("Appointment collides with existing appointment.");
                }
                else {
                    staff.add(a);
                }
            }
            else if (command.equals("C"))
            {
                System.out.println("Enter Appointment Date");
                String line = in.nextLine();
                AppointmentDate day = new AppointmentDate(line);    // holds the date
                Appointment a = getChoice(calendar.getAppointmentsForDay(day)); // lets user select specific appointment on that date
                if (a != null)  // cancel that appointment
                    calendar.cancel(a);
            }
            else if (command.equals("S"))
            {
                System.out.println("Date");
                String line = in.nextLine();
                AppointmentDate day = new AppointmentDate(line);
                for (Appointment appt : calendar.getAppointmentsForDay(day))    // shows all appointments on that date
                    System.out.println(appt.format());
            }
            else if (command.equals("Q"))
            {
                more = false;   // stops the while loop
            }
        }
    }

    private Appointment getChoice(ArrayList<Appointment> choices)
    {
        if (choices.size() == 0) return null;
        while (true)
        {
            char c = 'A';
            for (Appointment choice : choices)
            {
                System.out.println(c + ") " + choice.format());
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.size())
                return choices.get(n);
        }
    }
}
