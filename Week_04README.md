Internship Progress Report: Week 4
Project Title: Console-Based Expense Tracker Application
Domain: Core Java Development

I. Overview:
During Week 4, t he primary objective was to build the presentation layer, interactive console UI, automated batch execution workflow, and complete the full integration of the Console-Based Expense Tracker application using pure Core Java.

The focus was on creating a user-friendly, robust, and resilient menu-driven interface (ConsoleUI.java), orchestrating the application lifecycle through ExpenseTrackerApp.java, implementing budget status alerts with real-time spending calculations, adding category selection workflows (both predefined and custom categories), and finalizing the project for end-to-end execution via run.bat and persistent CSV storage.
II. Achievements:
A. Presentation & Console UI Layer (com.tracker.ui & com.tracker)
->ExpenseTrackerApp.java (Application Entry Point):

•	Configured application orchestration: decoupled the initialization of ExpenseRepository (expenses.csv, budget.txt), ExpenseService, and ConsoleUI.
•	Implemented clean application bootstrap and lifecycle execution via ui.start().
->ConsoleUI.java (Interactive User Interface & Menu System):

•	Main Dashboard & Navigation Loop: Implemented a robust 7-option interactive menu loop handling add, view, edit, delete, spending reports, budget settings, and graceful exit.
->Dynamic Real-Time Budget Alert Banner (checkAndAlertBudget):
•	Displays formatted real-time budget tracking on every dashboard load.
•	Automatically issues a prominent warning banner when active monthly spending exceeds the budget threshold.
->Interactive Category Selection System (selectCategory):
•	Pre-seeded 8 standard categories (Food, Rent/Bills, Transport, Entertainment, Shopping, Health, Education, Other).
•	Added seamless support for on-the-fly custom category entry.
->Smart Expense Editing (editExpense):
•	Displays existing record details and allows updating fields selectively while preserving original values when inputs are left blank.
->Dedicated Spending Reports & Analytics Submenu (showReportsMenu):
•	Overall Total Spending computation.
•	Category-based filtering with subtotal calculation (filterByCategory).
•	Month-based temporal filtering with monthly sums (filterByMonth).
•	Category-wise Spending Breakdown with formatted percentage share calculation (viewCategoryBreakdown).
->Budget Settings Management Submenu (showBudgetMenu):
•	View current budget, monthly spending, and remaining budget balances.
•	Set or update monthly budget limits dynamically.
->Robust Input Readers with Error Handling (readIntegerInput, readDoubleInput, readDateInput):
•	Looped validation routines preventing crashes from non-numeric inputs, negative numbers, or invalid date strings (DateTimeParseException, NumberFormatException).
•	Supports pressing Enter to auto-default transaction dates to current date (LocalDate.now()).
B. Automation & Data Persistence Layer
->run.bat Execution Script:

•	Created a 1-click Windows batch script that automates compilation of all packages (com.tracker.*) into a bin/ directory and immediately launches the application.
->expenses.csv & budget.txt Persistence Integration:

•	Verified end-to-end persistence ensuring that all transactions, modifications, and budget limits persist across multiple application restart cycles.
•	Successfully finalized all project requirements, demonstrating complete mastery of Core Java principles.
GitHub Link:

2. Java Project Contributions:
Completed the final milestone of the Console-Based Expense Tracker with a focus on console presentation architecture, real-time threshold monitoring, resilient input handling routines, multi-level reporting submenus, and 1-click execution scripting.
Engaged in effective collaboration with team members and completed full project integration.
3. Learning Java:
-> Core Java Concepts Learned & Project-Specific Applications:

1.	Layered Decoupling & MVC-Style Architecture:
•	Achieved strict separation of concerns where UI (ConsoleUI), business logic (ExpenseService), and data access (ExpenseRepository) communicate cleanly without tight coupling.
2. Defensive Console Input Streams & Looped Validation:
•	Engineered fail-safe input parsing loops that catch NumberFormatException and DateTimeParseException inline, guiding the user back to valid input without terminating the application.
3. Formatted String Output & Percentage Calculations:
•	Leveraged System.out.format() and String.format() with alignment specifiers to output neat, tabular terminal reports.
4. Conditional Business Rules & Real-Time Alert Triggers:
•	Implemented dynamic budget evaluation logic that evaluates ongoing monthly financial health before rendering user menus.
5. Modern Date/Time Interoperability:
•	Utilized DateTimeFormatter.ISO_LOCAL_DATE and YearMonth.parse() to seamlessly transition between user-typed strings and temporal domain objects. 
   
III. Challenges:

1. Java Project Complexity:
•	Faced challenges in managing complex multi-level menu navigation loops and ensuring selective field updates in editExpense() without overwriting untouched values.
•	Successfully implemented fallback checks and validated input flows to ensure seamless user experience.

IV. Learning Resources:

1. Official Java Documentation & Technical References:
•	Consulted Oracle Official Java SE Documentation for java.util.Scanner, java.time.format.DateTimeFormatter, and string formatting specifiers.
•	Referenced Baeldung Java Guides for CLI menu architecture, input validation best practices, and stream reduction techniques.
2.  Internship Modules & Training:
•	Utilized  official curriculum guidelines for clean coding standards, exception hierarchy, and modular console interface design.
•	Attended weekly internship webinars to review code quality and industrial Core Java deployment standards.
3. Video Lectures & Guided Practice:
•	Engaged with the Code with Harry Core Java playlist to reinforce console I/O handling, looping control structures, and batch compilation workflows.

V. Project Conclusion & Final Outcomes:
•	Complete Feature Delivery: All 10 project requirements (Recording, Categories, Filtering, Modification, Deletion, Persistence, Summary Reports, Budget Alerts, Console UI, and Error Handling) are fully functional.
•	Production-Ready Core Java Code: The codebase is fully modular, documented, tested, and executable via run.bat.
•	Repository Ready: Final source code, documentation, and weekly reports are committed and pushed to GitHub.

