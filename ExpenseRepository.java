package com.tracker.repository;

import com.tracker.model.Expense;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private final String expensesFilePath;
    private final String budgetFilePath;

    public ExpenseRepository(String expensesFilePath, String budgetFilePath) {
        this.expensesFilePath = expensesFilePath;
        this.budgetFilePath = budgetFilePath;
    }

    // Load expenses from CSV file
    public List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        File file = new File(expensesFilePath);
        
        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip header if it exists
            String header = reader.readLine();
            if (header != null && !header.startsWith("id,")) {
                // If it's not a header, parse it as an expense (fallback)
                try {
                    expenses.add(Expense.fromCsv(header));
                } catch (Exception e) {
                    // Ignore header parsing errors
                }
            }

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    expenses.add(Expense.fromCsv(line));
                } catch (IllegalArgumentException e) {
                    System.err.println("Error parsing expense line: " + line + ". " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading expenses file: " + e.getMessage());
        }

        return expenses;
    }

    // Save expenses to CSV file
    public void saveExpenses(List<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expensesFilePath))) {
            writer.write("id,amount,category,date,description\n");
            for (Expense expense : expenses) {
                writer.write(expense.toCsv() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving expenses file: " + e.getMessage());
        }
    }

    // Load monthly budget limit from file
    public double loadBudget() {
        File file = new File(budgetFilePath);
        if (!file.exists()) {
            return 0.0; // Default to 0 (no budget set)
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                return Double.parseDouble(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading budget file: " + e.getMessage());
        }
        return 0.0;
    }

    // Save monthly budget limit to file
    public void saveBudget(double budget) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(budgetFilePath))) {
            writer.write(String.valueOf(budget));
        } catch (IOException e) {
            System.err.println("Error saving budget file: " + e.getMessage());
        }
    }
}
