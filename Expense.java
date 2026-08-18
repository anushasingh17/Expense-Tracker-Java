package com.tracker.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private int id;
    private double amount;
    private String category;
    private LocalDate date;
    private String description;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public Expense(int id, double amount, String category, LocalDate date, String description) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Convert Expense to CSV line
    public String toCsv() {
        // Replace commas in category and description to prevent CSV parsing issues
        String safeCategory = category.replace(",", ";");
        String safeDescription = description.replace(",", ";");
        return id + "," + amount + "," + safeCategory + "," + date.format(DATE_FORMATTER) + "," + safeDescription;
    }

    // Create Expense from CSV line
    public static Expense fromCsv(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid CSV line format: " + csvLine);
        }
        int id = Integer.parseInt(parts[0]);
        double amount = Double.parseDouble(parts[1]);
        String category = parts[2].replace(";", ",");
        LocalDate date = LocalDate.parse(parts[3], DATE_FORMATTER);
        String description = parts[4].replace(";", ",");
        return new Expense(id, amount, category, date, description);
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Date: %s | Amount: $%-8.2f | Category: %-12s | Description: %s",
                id, date.format(DATE_FORMATTER), amount, category, description);
    }
}
