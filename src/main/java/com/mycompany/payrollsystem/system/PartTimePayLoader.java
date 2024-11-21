package com.mycompany.payrollsystem.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PartTimePayLoader {
    private Map<String, Double> PartTimePayLoader = new HashMap<>();

    public void loadPartTimePayRates(String csvFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String title = data[0];
                String tier = data[1];
                double payRate = Double.parseDouble(data[2]);

                String key = generateKey(title, tier);
                PartTimePayLoader.put(key, payRate);
            }
        }
    }

    public double getPayRate(String title, String tier) {
        String key = generateKey(title, tier);
        return PartTimePayLoader.getOrDefault(key, 0.0);
    }

    private String generateKey(String title, String tier) {
        return title + "-" + tier;
    }
}

