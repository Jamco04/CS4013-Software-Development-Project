package com.mycompany.payrollsystem.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PayLoader {
    // Data structure:
    //      Key: category, role, tier
    //      Value: pay
    private static final HashMap<String, Double> payGrades = new HashMap<>();
    private static final HashMap<String, Integer> titleScalePoints = new HashMap<>();//title - scalePoint
    private static final HashMap<String, String> titleCategory = new HashMap<>();

    public static void loadPay(String csvFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String category = data[0];
                String title = data[1];
                String tier = data[2];
                double pay = Double.parseDouble(data[3]);
                String key = generateKey(category, title, tier);
                payGrades.put(key, pay);

                int currentTier = Integer.parseInt(tier);
                titleScalePoints.put(title, Math.max(titleScalePoints.getOrDefault(title, 0), currentTier));
            }
        }
    }


    //just an edited version of the above method to map catagory and the title together
    public static void loadTitleCategoryMap(String csvFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip header if exists
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String category = data[0];
                String title = data[1];

                // Store the mapping in the map
                titleCategory.put(title, category);
            }
        }
    }


    public int getMaxScalePoints(String title) {
        return titleScalePoints.getOrDefault(title, 0);
    }

    public double getPay(String category, String title, String tier) {   // returns value
        String key = generateKey(category, title, tier);
        return payGrades.getOrDefault(key, -1.0);   //-1 for testing purposes
    }
    public static String getCategoryFromTitle(String title) {
        return titleCategory.getOrDefault(title, "Unknown Category");
    }

    private static String generateKey(String category, String role, String tier) { // returns key
        return category + "-" + role + "-" + tier;
    }
}
