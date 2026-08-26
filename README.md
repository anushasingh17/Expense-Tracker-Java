Internship Progress Report: Week 1
Project Title: Console-Based Expense Tracker Application  
Domain: Core Java Development  
Milestone: Java Fundamentals, OOP Concepts & Git/GitHub Repository Setup  

1.Summary & Objective
Week 1 was dedicated to mastering Core Java programming fundamentals, understanding Object-Oriented Programming (OOP) design patterns, set up the VS Code and downloaded JDK

 2. Core Java Concepts & Learning Modules

 A. Java Fundamentals & Environment Setup
- Installed and configured **Java Development Kit (JDK 17+)** and configured environment variables (`JAVA_HOME`, `PATH`).
- Explored Java syntax, primitive and reference data types, operators, and control flow structures (`if-else`, `switch-case`, `for`, `while` loops).
- Understood the compilation process: Java Source (`.java`) -> Bytecode (`.class`) -> Java Virtual Machine (JVM).

 B. Object-Oriented Programming (OOP) Principles
- Encapsulation: Using private fields, public getters, setters, and constructors to protect data integrity.
- Abstraction: Designing clean interfaces (`ExpenseRepository`, `CategoryRepository`) to separate contract from implementation.
- Polymorphism & Inheritance: Structuring reusable service layers and custom exception hierarchies (`Exception` subclasses).
- Collections Framework: Introduction to `List`, `ArrayList`, `Map`, `LinkedHashMap`, and Java 8 Streams.

 3. Project Requirements & Architecture Analysis
- Reviewed the problem statement for the **Console-Based Expense Tracker**.
- Identified core entities: `Expense` (id, date, amount, category, description) and `Category` (id, name, description).
- Planned to create a 3-tier system architecture (UI Layer -> Service Layer -> Repository Layer).

4. Key Milestones Completed in Week 1
1. Completed Core Java language fundamentals and OOP principles.
2. Initialized the project workspace and repository structure.
3. Configured Git version control and established the GitHub repository.
4. Finalized the architectural design and functional specifications for the Expense Tracker.

Internship Progress Report: Week 2 Project Title: Console-Based Expense Tracker Application Domain: Core Java Development Milestone: Week 2 — Domain Modeling, CSV Persistence & Expense CRUD Operations

Summary & Objectives During Week 2, the objective was to develop the core functional version of the Console-Based Expense Tracker application using pure Core Java. The implementation focused on Object-Oriented Programming (OOP) principles, structured class design, dynamic in-memory collections, Core Java file I/O for persistent CSV storage, and an interactive console user interface.
2.Key Modules & Features Implemented

A. Domain Model Layer (com.tracker.model) Expense.java: Implements Encapsulation with private fields: id, amount, category, date (LocalDate), and description. Custom Serialization: Includes toCsv() for converting objects to CSV lines with delimiter escaping (replacing commas with semicolons). Factory Method: Includes fromCsv(String line) to parse and reconstruct Expense objects safely. Formatted Display: Overrides toString() to print neatly aligned expense records with currency formatting. Category.java: Encapsulates category attributes (id, name) for classification.

B. Data Persistence Layer (com.tracker.repository) ExpenseRepository.java: Eliminates the need for an external database by managing records inside expenses.csv. Uses BufferedReader and FileReader to load records on application startup. Uses BufferedWriter and FileWriter to automatically update the CSV file when expenses are added or deleted.

C. Business Logic Layer (com.tracker.service) ExpenseService.java: Manages in-memory data using ArrayList. Auto-increments unique IDs dynamically based on the highest existing record ID. Pre-seeds standard categories (Food, Travel, Education, Shopping, Bills, Other). Implements core operations: addExpense(), getAllExpenses(), findById(), deleteExpense(), and getTotalSpent().

D. Presentation UI Layer (com.tracker.ui) ExpenseTrackerApp.java: Features an interactive Scanner-driven while-loop menu with 7 distinct options: Add Expense (supports 'today' shortcut) View All Expenses Find Expense by ID Delete Expense Spending Reports & Summaries View Categories Exit Provides clear success feedback and informative error messages.

Core Java Concepts Applied Object-Oriented Programming (OOP): Encapsulation: Private instance variables with public getters and setters. Static Methods & Constants: Used DateTimeFormatter.ISO_LOCAL_DATE and static factory method fromCsv(). Java Collections Framework: ArrayList and List for runtime list management. Core Java File I/O: Stream handling with BufferedReader, BufferedWriter, FileReader, and FileWriter. Modern Date/Time API: Date representation and parsing using java.time.LocalDate. Exception Handling: Robust try-catch blocks handling NumberFormatException, DateTimeParseException, and IOException.

Upcoming Goals for Week 3 Implement multi-criteria filtering (by category, date range, and amount). Add keyword search functionality across expense descriptions. Add support for category creation and category editing. Enhance spending analytics and monthly breakdown reports.

Internship Progress Report: Week 3 Project Title: Console-Based Expense Tracker Application Domain: Core Java Development

I. Overview: During Week 3, the objective was to implement advanced data processing, category aggregations, monthly expenditure tracking, expense modification, and a monthly budget management subsystem for the Console-Based Expense Tracker application using pure Core Java.

The implementation focused on modern Java features including the Java 8 Stream API, Lambda expressions, the java.time.YearMonth temporal API, stream collectors (groupingBy, summingDouble), defensive programming with unmodifiable collections, and multi-file data persistence using Core Java file I/O.

II. Achievements: A. Data Persistence Layer (com.tracker.repository) ExpenseRepository.java:

Implemented multi-file persistence to independently manage expense transactions (expenses.csv) and user monthly budget limits (budget.txt). Enhanced loadExpenses() with automated header detection and fallback parsing to safely skip CSV header rows. Implemented saveExpenses(List) to write formatted CSV records. Implemented loadBudget() and saveBudget(double) to store and retrieve the user's monthly budget limit across application restarts. B. Business Logic Layer (com.tracker.service) ExpenseService.java:

Dynamic ID Generation: Implemented calculateNextId() using stream().mapToInt(Expense::getId).max() to auto-increment unique IDs based on existing data.

Expense Modification: Implemented editExpense() allowing updates to amount, category, date, and description with auto-synchronization to CSV storage.

Defensive Encapsulation: Wrapped returned lists in Collections.unmodifiableList() within getExpenses() to prevent unauthorized external mutation.

Stream-Based Search & Deletion: Utilized .filter().findFirst() in getExpenseById() and removeIf() in deleteExpense() for safe record removal.

Category & Temporal Filtering:

Implemented getExpensesByCategory() for case-insensitive category filtering using Streams. Implemented getExpensesByMonth(YearMonth) to query expenses for any specific month. Implemented getCurrentMonthTotal() to calculate active monthly spending. Category Aggregation: Implemented getCategorySummaries() using Collectors.groupingBy() and Collectors.summingDouble() to generate a Map<String, Double> of total spending per category.

Monthly Budget Management:

Implemented getMonthlyBudget() and setMonthlyBudget(). Implemented isBudgetExceeded() to trigger warnings when spending exceeds the limit. Implemented getRemainingBudget() to compute available funds for the current month. Successfully executed basic and intermediate tasks, showcasing growing proficiency.

Java Project Contributions: Name of the project:- -Contributed code to Console-Based Expense Tracker with a focus on Java 8 Stream API, Lambda expressions, Date/Time temporal queries (YearMonth), Stream Collectors for categorized financial summaries, and multi-file persistence. -Engaged in effective collaboration with team members.
3.Learning Java: ->Core Java Concepts Learned & Project -Specific Application 1.Declarative Stream Pipelines vs. Imperative Loops: Replaced bulky manual for loops with concise Java 8 stream pipelines (.filter(), .mapToDouble(), .sum()) to compute grand totals and monthly metrics in single, expressive statements. 2.Multi-Level Aggregation with Downstream Collectors: Mastered Collectors.groupingBy() combined with Collectors.summingDouble() to automatically transform flat transaction lists into structured Map<String, Double> category breakdown reports. 3.Temporal Queries with java.time.YearMonth: Learned to extract YearMonth.from(LocalDate) for calendar-month comparisons, avoiding error-prone string parsing when calculating current-month totals and budget balances. 4.Defensive API Design & Data Integrity: Applied Collections.unmodifiableList() to return read-only views of the internal expense store, preventing unauthorized external modification while maintaining encapsulation. Utilized Predicate-based removeIf() for clean, concurrent-safe record deletion. 5.Primitive Stream Reduction for Sequence Safety: Used IntStream (mapToInt().max().orElse(0) + 1) to dynamically deduce the next unique transaction ID directly from disk-loaded records. 6.Multi-Channel File I/O Resilience: Coordinated dual-stream file handling for transactional records (expenses.csv) and configuration states (budget.txt) with automated header-detection fallbacks.

III. Challenges:

Java Project Complexity: -Faced complexity in understanding advanced Java 8 Collector chaining, predicate filtering nuances, and synchronizing multi-file persistence without race conditions. -Seeking guidance to overcome challenges and enhance understanding.

IV. Learning Resources:

1.Official Java Documentation & Technical References: Consulted Oracle Official Java SE Documentation for java.util.stream.Collectors, java.time.YearMonth, and java.util.Collections to implement robust stream pipelines and defensive copying. Utilized Baeldung Java Guides for deep-dive tutorials on custom Collectors, Lambda functional interfaces, and stream exception handling.
2.Internship Modules & Training: Utilized official curriculum and architecture documentation for reference on 3-tier modular system design (Model-Repository-Service). Attended internship technical webinars and Q&A sessions to understand industrial best practices for flat-file persistence and edge-case validation.
3.Video Lectures & Guided Practice: Engaged with the Code with Harry Core Java playlist to reinforce Java 8 functional programming, Stream operations, and file stream handling techniques.
V. Next Week’s Goals -Implement ASCII visual progress bars and formatted reports for budget utilization and category shares. 
-Add automated unit testing using JUnit 5 for repository and service layer methods. -Conduct comprehensive edge-case testing and code refactoring.
-Finalize documentation, README.md, and complete the final Week 4 project release.
