package com.tracker.service;

import com.tracker.model.Expense;
import com.tracker.repository.ExpenseRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseRepository repository;
    private final List<Expense> expenses;
    private double monthlyBudget;
    private int nextId;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
        this.expenses = new ArrayList<>(repository.loadExpenses());
        this.monthlyBudget = repository.loadBudget();
        this.nextId = calculateNextId();
    }

    private int calculateNextId() {
        return expenses.stream()
                .mapToInt(Expense::getId)
                .max()
                .orElse(0) + 1;
    }

    // Add a new expense
    public Expense addExpense(double amount, String category, LocalDate date, String description) {
        Expense expense = new Expense(nextId++, amount, category, date, description);
        expenses.add(expense);
        repository.saveExpenses(expenses);
        return expense;
    }

    // Edit an existing expense
    public boolean editExpense(int id, double amount, String category, LocalDate date, String description) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expense.setAmount(amount);
                expense.setCategory(category);
                expense.setDate(date);
                expense.setDescription(description);
                repository.saveExpenses(expenses);
                return true;
            }
        }
        return false;
    }

    // Get all expenses
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    // Get expense by ID
    public Expense getExpenseById(int id) {
        return expenses.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Delete expense by ID
    public boolean deleteExpense(int id) {
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        if (removed) {
            repository.saveExpenses(expenses);
        }
        return removed;
    }

    // Calculate total of all expenses
    public double getTotalExpenses() {
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Get expenses filtered by category
    public List<Expense> getExpensesByCategory(String category) {
        return expenses.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category.trim()))
                .collect(Collectors.toList());
    }

    // Get expenses for a specific month
    public List<Expense> getExpensesByMonth(YearMonth yearMonth) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(yearMonth))
                .collect(Collectors.toList());
    }

    // Get total expenses for the current month
    public double getCurrentMonthTotal() {
        YearMonth currentMonth = YearMonth.now();
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(currentMonth))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Get summaries of expenses grouped by category
    public Map<String, Double> getCategorySummaries() {
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

    // Budget methods
    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double budget) {
        this.monthlyBudget = budget;
        repository.saveBudget(budget);
    }

    public boolean isBudgetExceeded() {
        if (monthlyBudget <= 0) {
            return false;
        }
        return getCurrentMonthTotal() > monthlyBudget;
    }

    public double getRemainingBudget() {
        if (monthlyBudget <= 0) {
            return 0.0;
        }
        return monthlyBudget - getCurrentMonthTotal();
    }
}
