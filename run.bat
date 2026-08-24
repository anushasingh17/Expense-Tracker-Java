@echo off
echo ===================================================
echo   Compiling Expense Tracker Application...
echo ===================================================
if not exist bin mkdir bin
javac -d bin src\com\tracker\model\Expense.java src\com\tracker\repository\ExpenseRepository.java src\com\tracker\service\ExpenseService.java src\com\tracker\ui\ConsoleUI.java src\com\tracker\ExpenseTrackerApp.java

if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed!
    pause
    exit /b %errorlevel%
)

echo [SUCCESS] Compilation successful!
echo.
echo ===================================================
echo   Launching Expense Tracker Application...
echo ===================================================
java -cp bin com.tracker.ExpenseTrackerApp
pause
