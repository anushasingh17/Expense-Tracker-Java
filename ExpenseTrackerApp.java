package com.tracker;

import com.tracker.repository.ExpenseRepository;
import com.tracker.service.ExpenseService;
import com.tracker.ui.ConsoleUI;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        System.out.println("Starting Expense Tracker Application...");
        
        // Define paths to store data
        String expensesFile = "expenses.csv";
        String budgetFile = "budget.txt";

        // Initialize components
        ExpenseRepository repository = new ExpenseRepository(expensesFile, budgetFile);
        ExpenseService service = new ExpenseService(repository);
        ConsoleUI ui = new ConsoleUI(service);

        // Start the console user interface
        ui.start();
    }
}
