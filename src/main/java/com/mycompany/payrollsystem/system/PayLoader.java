package com.mycompany.payrollsystem.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PayLoader {
    // Data structure:
    //      Key: category, role, tier
    //      Value: pay
    private Map<String, Double> payGrades = new HashMap<>();

    public void loadPay(String csvFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String category = data[0];
                String role = data[1];
                String tier = data[2];
                double pay = Double.parseDouble(data[3]);
                String key = generateKey(category, role, tier);
                payGrades.put(key, pay);
            }
        }
    }

    public double getPay(String category, String role, String tier) {   // returns value
        String key = generateKey(category, role, tier);
        System.out.println("They key is: " + key);    //testing purposes
        return payGrades.getOrDefault(key, 0.0);
    }

    private String generateKey(String category, String role, String tier) { // returns key
        return category + "-" + role + "-" + tier;
    }
}
