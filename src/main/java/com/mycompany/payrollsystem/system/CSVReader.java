package com.mycompany.payrollsystem.system;

import java.io.*;
import java.util.ArrayList;


public class CSVReader{

    /*
    public void readGradeBounds(String filePath, double[] bounds, String[] grades) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int index = 0;

        reader.readLine();  // (skip header)

        while ((line = reader.readLine()) != null) {    //until end
            String[] parts = line.split(",");   //split with comma
            bounds[index] = Double.parseDouble(parts[0]); //populate array (double, String)
            grades[index] = parts[1];
            index++;
        }
        reader.close();

    }




    public ArrayList<StudentResult> readGradeBook(String filePath, int numOfTests) throws IOException {

        ArrayList<StudentResult> results = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        reader.readLine();  // (skip header)

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String id = parts[0];   //1st id, the rest is scores

            StudentResult student = new StudentResult(id, numOfTests);

            for (int i = 1; i <= numOfTests; i++) {
                student.addResult(i - 1, Double.parseDouble(parts[i])); //add testResult to student
            }
            results.add(student); //add to arraylist
        }
        reader.close();
        return results;
    }


*/

}