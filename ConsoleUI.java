package com.tracker.ui;

import com.tracker.model.Expense;
import com.tracker.service.ExpenseService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    private final ExpenseService service;
    private final Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    
    // Predefined list of categories for ease of use
    private static final String[] PREDEFINED_CATEGORIES = {
        "Food", "Rent/Bills", "Transport", "Entertainment", "Shopping", "Health", "Education", "Other"
    };

    public ConsoleUI(ExpenseService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            checkAndAlertBudget();
            printMainMenu();
            int choice = readIntegerInput("Enter choice (1-7): ");
            System.out.println();
            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewAllExpenses();
                    break;
                case 3:
                    editExpense();
                    break;
                case 4:
                    deleteExpense();
                    break;
                case 5:
                    showReportsMenu();
                    break;
                case 6:
                    showBudgetMenu();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Thank you for using Expense Tracker! Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
            System.out.println();
        }
    }

    private void printMainMenu() {
        System.out.println("=========================================");
        System.out.println("          EXPENSE TRACKER SYSTEM         ");
        System.out.println("=========================================");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. Edit Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. Spending Reports & Summaries");
        System.out.println("6. Budget Settings");
        System.out.println("7. Exit");
        System.out.println("=========================================");
    }

    private void checkAndAlertBudget() {
        double budget = service.getMonthlyBudget();
        if (budget > 0) {
            double currentSpent = service.getCurrentMonthTotal();
            if (currentSpent > budget) {
                System.out.println("\n*** WARNING: Monthly budget exceeded! Budget: $" 
                        + String.format("%.2f", budget) + " | Spent: $" + String.format("%.2f", currentSpent) + " ***");
            } else {
                System.out.println("\nBudget status: Spent $" + String.format("%.2f", currentSpent) 
                        + " of $" + String.format("%.2f", budget) + " (Remaining: $" 
                        + String.format("%.2f", service.getRemainingBudget()) + ")");
            }
        }
    }

    private void addExpense() {
        System.out.println("--- Add New Expense ---");
        double amount = readDoubleInput("Enter amount: ");
        String category = selectCategory();
        LocalDate date = readDateInput("Enter date (YYYY-MM-DD) [Press Enter for Today]: ");
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        Expense expense = service.addExpense(amount, category, date, description);
        System.out.println("\nExpense added successfully!");
        System.out.println(expense);
    }

    private void viewAllExpenses() {
        System.out.println("--- All Expenses ---");
        List<Expense> expenses = service.getExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        expenses.forEach(System.out::println);
    }

    private void editExpense() {
        System.out.println("--- Edit Expense ---");
        int id = readIntegerInput("Enter the ID of the expense to edit: ");
        Expense expense = service.getExpenseById(id);
        if (expense == null) {
            System.out.println("Expense not found with ID: " + id);
            return;
        }

        System.out.println("Current expense details: " + expense);
        System.out.println("Enter new details (press Enter to keep current value):");

        // Read Amount
        double amount = expense.getAmount();
        System.out.print("Enter amount [Current: " + amount + "]: ");
        String amountStr = scanner.nextLine().trim();
        if (!amountStr.isEmpty()) {
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Keeping current amount.");
            }
        }

        // Read Category
        String category = expense.getCategory();
        System.out.println("Select category [Current: " + category + "]:");
        System.out.println("1. Choose predefined category");
        System.out.println("2. Custom category");
        System.out.print("Enter choice (or press Enter to keep current): ");
        String catChoice = scanner.nextLine().trim();
        if (!catChoice.isEmpty()) {
            if ("1".equals(catChoice)) {
                category = selectCategory();
            } else if ("2".equals(catChoice)) {
                System.out.print("Enter custom category: ");
                category = scanner.nextLine().trim();
            }
        }

        // Read Date
        LocalDate date = expense.getDate();
        System.out.print("Enter date (YYYY-MM-DD) [Current: " + date + "]: ");
        String dateStr = scanner.nextLine().trim();
        if (!dateStr.isEmpty()) {
            try {
                date = LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Keeping current date.");
            }
        }

        // Read Description
        System.out.print("Enter description [Current: " + expense.getDescription() + "]: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = expense.getDescription();
        }

        boolean success = service.editExpense(id, amount, category, date, description);
        if (success) {
            System.out.println("\nExpense updated successfully!");
            System.out.println(service.getExpenseById(id));
        } else {
            System.out.println("Failed to update expense.");
        }
    }

    private void deleteExpense() {
        System.out.println("--- Delete Expense ---");
        int id = readIntegerInput("Enter the ID of the expense to delete: ");
        boolean deleted = service.deleteExpense(id);
        if (deleted) {
            System.out.println("Expense with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Expense not found with ID: " + id);
        }
    }

    private void showReportsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Spending Reports & Summaries ---");
            System.out.println("1. Overall Total Spending");
            System.out.println("2. Filter Expenses by Category");
            System.out.println("3. Filter Expenses by Month (YYYY-MM)");
            System.out.println("4. View Category-wise Breakdown (Summary)");
            System.out.println("5. Back to Main Menu");
            int choice = readIntegerInput("Enter choice (1-5): ");
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Overall Total Expenses: $" + String.format("%.2f", service.getTotalExpenses()));
                    break;
                case 2:
                    filterByCategory();
                    break;
                case 3:
                    filterByMonth();
                    break;
                case 4:
                    viewCategoryBreakdown();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private void filterByCategory() {
        System.out.print("Enter category to filter: ");
        String category = scanner.nextLine().trim();
        List<Expense> filtered = service.getExpensesByCategory(category);
        if (filtered.isEmpty()) {
            System.out.println("No expenses found in category: " + category);
        } else {
            System.out.println("Expenses in category '" + category + "':");
            filtered.forEach(System.out::println);
            double categoryTotal = filtered.stream().mapToDouble(Expense::getAmount).sum();
            System.out.println("Category Total: $" + String.format("%.2f", categoryTotal));
        }
    }

    private void filterByMonth() {
        System.out.print("Enter month (YYYY-MM): ");
        String monthStr = scanner.nextLine().trim();
        try {
            YearMonth yearMonth = YearMonth.parse(monthStr);
            List<Expense> filtered = service.getExpensesByMonth(yearMonth);
            if (filtered.isEmpty()) {
                System.out.println("No expenses found for month: " + monthStr);
            } else {
                System.out.println("Expenses in " + yearMonth.getMonth() + " " + yearMonth.getYear() + ":");
                filtered.forEach(System.out::println);
                double monthTotal = filtered.stream().mapToDouble(Expense::getAmount).sum();
                System.out.println("Month Total: $" + String.format("%.2f", monthTotal));
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid month format. Please use YYYY-MM.");
        }
    }

    private void viewCategoryBreakdown() {
        Map<String, Double> breakdown = service.getCategorySummaries();
        if (breakdown.isEmpty()) {
            System.out.println("No expenses recorded to summarize.");
            return;
        }
        System.out.println("Category-wise Spending breakdown:");
        double total = service.getTotalExpenses();
        for (Map.Entry<String, Double> entry : breakdown.entrySet()) {
            double percent = (entry.getValue() / total) * 100;
            System.out.format("- %-15s : $%-8.2f (%.1f%%)%n", entry.getKey(), entry.getValue(), percent);
        }
    }

    private void showBudgetMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Budget Settings ---");
            System.out.println("1. View Current Budget");
            System.out.println("2. Set/Update Monthly Budget");
            System.out.println("3. Back to Main Menu");
            int choice = readIntegerInput("Enter choice (1-3): ");
            System.out.println();
            switch (choice) {
                case 1:
                    double budget = service.getMonthlyBudget();
                    if (budget <= 0) {
                        System.out.println("No monthly budget is currently set.");
                    } else {
                        System.out.println("Monthly Budget: $" + String.format("%.2f", budget));
                        System.out.println("Spent this month: $" + String.format("%.2f", service.getCurrentMonthTotal()));
                        System.out.println("Remaining Budget: $" + String.format("%.2f", service.getRemainingBudget()));
                    }
                    break;
                case 2:
                    double newBudget = readDoubleInput("Enter monthly budget limit (0 to clear): ");
                    service.setMonthlyBudget(newBudget);
                    System.out.println("Monthly budget updated to: $" + String.format("%.2f", newBudget));
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    // Category Selector helper
    private String selectCategory() {
        System.out.println("Select a category:");
        for (int i = 0; i < PREDEFINED_CATEGORIES.length; i++) {
            System.out.println((i + 1) + ". " + PREDEFINED_CATEGORIES[i]);
        }
        System.out.println((PREDEFINED_CATEGORIES.length + 1) + ". Custom Category");

        while (true) {
            int choice = readIntegerInput("Choice (1-" + (PREDEFINED_CATEGORIES.length + 1) + "): ");
            if (choice >= 1 && choice <= PREDEFINED_CATEGORIES.length) {
                return PREDEFINED_CATEGORIES[choice - 1];
            } else if (choice == PREDEFINED_CATEGORIES.length + 1) {
                System.out.print("Enter custom category: ");
                String customCat = scanner.nextLine().trim();
                if (!customCat.isEmpty()) {
                    return customCat;
                }
                System.out.println("Category name cannot be empty.");
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // Input Reader Helpers to handle invalid formats robustly
    private int readIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    private double readDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val < 0) {
                    System.out.println("Amount cannot be negative.");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid decimal number.");
            }
        }
    }

    private LocalDate readDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
}
