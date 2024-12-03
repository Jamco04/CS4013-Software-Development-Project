package com.mycompany.payrollsystem.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//ScaleLoader
//James - getPay, generateKey
//Enda - getMaxScalePoints, getCategoryFromTitle
//Andrei - validTitle, validCategory
//Adam - loadScales

/**
 * A class to load and manage scale data for employee titles, categories, and pay grades
 */
public class ScaleLoader {
    // Data structure:
    //      Key: category, role, tier
    //      Value: pay
    private static final HashMap<String, Double> payGrades = new HashMap<>();   //for generating pay
    private static final HashMap<String, Integer> titleScalePoints = new HashMap<>();   //for max scalepoint
    private static final ArrayList<String> titles = new ArrayList<>();  //title validation
    private static final ArrayList<String> categories = new ArrayList<>();  //title validation
    private static final HashMap<String, String> titleCategory = new HashMap<>();

    /**
     * Loads the scale data from a CSV file into memory
     * @param csvFile The path to the CSV file containing scale data
     * @throws IOException for if there is an issue reading the file
     */
    public static void loadScales(String csvFile) throws IOException {
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

                titles.add(title);  // will contain duplicates but will work
                categories.add(category);   // same case

                titleCategory.put(title, category);

                int currentTier = Integer.parseInt(tier);
                titleScalePoints.put(title, Math.max(titleScalePoints.getOrDefault(title, 0), currentTier));
            }
        }
    }

    /**
     * Returns the maximum scale points for a given title
     * @param title The title to check
     * @return The maximum scale points for the title
     */
    public static int getMaxScalePoints(String title) {
        return titleScalePoints.getOrDefault(title, 0);
    }

    /**
     * Returns the pay for a given category, title, and tier
     * @param category The category of the employee
     * @param title The title of the employee
     * @param tier The tier of the employee
     * @return The pay for the category, title, and tier
     */
    public static double getPay(String category, String title, String tier) {   // returns value
        String key = generateKey(category, title, tier);
        return payGrades.getOrDefault(key, -1.0);   // -1 for testing purposes
    }

    /**
     * Checks if the title is valid
     * @param title The title to check
     * @return True if the title is valid, false otherwise
     */
    public static boolean validTitle(String title) {
        return titles.contains(title);
    }

    /**
     * Checks if the category is valid
     * @param category The category to check
     * @return True if the category is valid, false otherwise
     */
    public static boolean validCategory(String category) {
        return categories.contains(category);
    }

    /**
     * Generates a unique key based on the category, role, and tier
     * @param category The category
     * @param role The role
     * @param tier The tier
     * @return A unique key based on the input parameters
     */
    private static String generateKey(String category, String role, String tier) { // returns key
        return category + "-" + role + "-" + tier;
    }

    /**
     * Retrieves the category for a given title
     * @param title The title to check
     * @return The category associated with the title, or "Unknown Category" if not found
     */
    public static String getCategoryFromTitle(String title) {
        return titleCategory.getOrDefault(title, "Unknown Category");
    }
}
