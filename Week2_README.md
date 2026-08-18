Internship Progress Report: Week 2
Project Title: Console-Based Expense Tracker Application
Domain: Core Java Development
Milestone: Week 2 — Domain Modeling, CSV Persistence & Expense CRUD Operations

1. Summary & Objectives
During Week 2, the objective was to develop the core functional version of the Console-Based Expense Tracker application using pure Core Java.
The implementation focused on Object-Oriented Programming (OOP) principles, structured class design, dynamic in-memory collections, Core Java file I/O for persistent CSV storage, and an interactive console user interface.

2.Key Modules & Features Implemented

A. Domain Model Layer (com.tracker.model)
Expense.java:
Implements Encapsulation with private fields: id, amount, category, date (LocalDate), and description.
Custom Serialization: Includes toCsv() for converting objects to CSV lines with delimiter escaping (replacing commas with semicolons).
Factory Method: Includes fromCsv(String line) to parse and reconstruct Expense objects safely.
Formatted Display: Overrides toString() to print neatly aligned expense records with currency formatting.
Category.java:
Encapsulates category attributes (id, name) for classification.

B. Data Persistence Layer (com.tracker.repository)
ExpenseRepository.java:
Eliminates the need for an external database by managing records inside expenses.csv.
Uses BufferedReader and FileReader to load records on application startup.
Uses BufferedWriter and FileWriter to automatically update the CSV file when expenses are added or deleted.

C. Business Logic Layer (com.tracker.service)
ExpenseService.java:
Manages in-memory data using ArrayList<Expense>.
Auto-increments unique IDs dynamically based on the highest existing record ID.
Pre-seeds standard categories (Food, Travel, Education, Shopping, Bills, Other).
Implements core operations: addExpense(), getAllExpenses(), findById(), deleteExpense(), and getTotalSpent().

D. Presentation UI Layer (com.tracker.ui)
ExpenseTrackerApp.java:
Features an interactive Scanner-driven while-loop menu with 7 distinct options:
Add Expense (supports 'today' shortcut)
View All Expenses
Find Expense by ID
Delete Expense
Spending Reports & Summaries
View Categories
Exit
Provides clear success feedback and informative error messages.

3. Core Java Concepts Applied
Object-Oriented Programming (OOP):
Encapsulation: Private instance variables with public getters and setters.
Static Methods & Constants: Used DateTimeFormatter.ISO_LOCAL_DATE and static factory method fromCsv().
Java Collections Framework:
ArrayList<Expense> and List<Category> for runtime list management.
Core Java File I/O:
Stream handling with BufferedReader, BufferedWriter, FileReader, and FileWriter.
Modern Date/Time API:
Date representation and parsing using java.time.LocalDate.
Exception Handling:
Robust try-catch blocks handling NumberFormatException, DateTimeParseException, and IOException.

4. Upcoming Goals for Week 3
Implement multi-criteria filtering (by category, date range, and amount).
Add keyword search functionality across expense descriptions.
Add support for category creation and category editing.
Enhance spending analytics and monthly breakdown reports.
