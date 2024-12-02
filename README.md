# CS4013 - Software Development Project

## Payroll System HelpFile

This HelpFile provides an overview of the Payroll System functionalities.

**Note:** For testing, ensure you adjust the conditions (`October`, `25`, `isAfter`).

---

## **Overview**

This application provides a **Command-Line Interface (CLI)** for performing payroll operations based on different roles: **Admin**, **HR**, and **Employee**.

---

## **Getting Started**

1. **Run the Application**
   - Execute the `RunCLI` class to start the application.

2. **Select Your Role**
   - Choose your role: `"Admin"`, `"HR"`, or `"Employee"`.

---

## **Roles and Functionalities**

### **Admin Role**

1. **Admin Login**
   - Enter the Admin password: `admin123`.
   - You have **3 login attempts**.

2. **Admin Menu Options**
   - **1) Add Staff**  
     Add new employees to the payroll system.
   - **2) View Staff**  
     Display a list of all employees.  
     A CSV file of the employee list will also be generated in the `output` folder.
   - **3) Generate Payslips**  
     - Generate payslips for **full-time employees** based on their salary.  
     - Generate payslips for **part-time employees** with valid pay claims, based on their hourly rate and hours worked.
   - **4) Logout**  
     Log out of the Admin account and exit the menu.
   - **5) Load Sample Employees**  
     Populate the system with sample employees for testing purposes.

---

### **HR Role**

1. **HR Login**
   - Enter the HR password: `hr123`.
   - You have **3 login attempts**.

2. **HR Menu Options**
   - **1) Perform Annual Promotion (Full-Time)**  
     Promote eligible full-time employees annually in **October**.
   - **2) Promote to New Salary Scale (Full-Time)**  
     - Promote full-time employees to a new salary scale (with a new title).  
     - The new scale point is based on the number of years spent at the highest scale point of the previous scale.
   - **3) Promote to Next Scale Point (Part-Time)**  
     Promote part-time employees to their next scale point.
   - **4) Logout**  
     Log out of the HR account and exit the menu.

---

### **Employee Role**

1. **Employee Menu Options**
   - **1) View Details**  
     Display the personal details of the logged-in employee.
   - **2) View Payslips**  
     View payslips associated with the logged-in employee.
   - **3) Submit Pay Claim**  
     Part-time employees can submit pay claims by specifying hours worked.
   - **4) Logout**  
     Log out of the Employee account and exit the menu.

---

## **Additional Notes**

- **GUI Implementation**  
  The GUI is currently under development.  
  To load the initial scene (assuming correct JavaFX configuration), run the `GUI` class.

---


Enjoy using the Payroll System!
