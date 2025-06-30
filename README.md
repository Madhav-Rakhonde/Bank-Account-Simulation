# Java Bank Application

A simple console-based banking application built in Java that allows users to create accounts, manage transactions, and store data persistently.

## Features

### Account Management
- **Account Registration**: Create new bank accounts with unique account numbers
- **Secure Login**: PIN-based authentication system (4-digit PIN)
- **Account Details**: View comprehensive account information

### Banking Operations
- **Deposit Money**: Add funds to your account
- **Withdraw Money**: Remove funds with balance validation
- **Balance Inquiry**: Check current account balance
- **Transaction History**: View last 10 transactions with timestamps

### Data Persistence
- **File Storage**: All account data is automatically saved to `accounts.txt`
- **Auto-Load**: Account data is restored when the application starts
- **Real-time Saving**: Changes are saved immediately after each transaction

## Project Structure

```
JavaBank/
├── Account.java      # Account class with banking operations
├── BankApp.java      # Main application with user interface
├── FileManager.java  # File I/O operations for data persistence
└── accounts.txt      # Data file (created automatically)
```

## Classes Overview

### Account.java
- Manages individual account data and operations
- Handles deposits, withdrawals, and transaction history
- Provides data serialization for file storage
- Maintains security through PIN validation

### BankApp.java
- Main application entry point
- Provides console-based user interface
- Handles user input validation and error management
- Manages application flow and menu systems

### FileManager.java
- Handles loading and saving account data
- Manages file I/O operations
- Provides error handling for file operations

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line interface or Java IDE

### Compilation and Execution

1. **Compile the Java files:**
   ```bash
   javac *.java
   ```

2. **Run the application:**
   ```bash
   java BankApp
   ```

### Alternative (Using IDE)
1. Import all Java files into your IDE
2. Run `BankApp.java` as the main class

## Usage Guide

### First Time Setup
1. Launch the application
2. Select "Register New Account" from the main menu
3. Provide required information:
   - Full name
   - Unique account number
   - 4-digit PIN
   - Initial balance

### Banking Operations
1. Select "Login" from the main menu
2. Enter your account number and PIN
3. Choose from available operations:
   - Deposit funds
   - Withdraw funds
   - Check balance
   - View transaction history
   - View account details
   - Logout

### Data Storage
- Account data is automatically saved to `accounts.txt`
- The file is created in the same directory as the application
- Data persists between application sessions

## Input Validation

The application includes robust input validation:
- **PIN Format**: Must be exactly 4 digits
- **Account Numbers**: Must be unique across all accounts
- **Transaction Amounts**: Must be positive numbers
- **Withdrawal Limits**: Cannot exceed current balance
- **Numeric Input**: Handles invalid number formats gracefully

## Error Handling

- **File Operations**: Graceful handling of missing or corrupted data files
- **Invalid Input**: User-friendly error messages with retry prompts
- **Balance Validation**: Prevents overdrafts and invalid transactions
- **PIN Security**: Incorrect PIN attempts are logged but don't lock accounts

## Transaction History

- Maintains last 10 transactions per account
- Each transaction includes timestamp
- Shows both successful and failed transaction attempts
- Automatically rotates old transactions when limit is reached

## Security Features

- PIN-based authentication
- Account number validation
- Transaction logging
- Input sanitization
- No plaintext PIN storage in memory after validation

## File Format

The application stores data in a custom text format:
```
BEGIN_ACCOUNT
Name:John Doe
Account:12345
PIN:1234
Balance:1000.0
Txn:[2024-01-01 10:30:00] Account created with balance: ₹1000.0
Txn:[2024-01-01 11:15:00] Deposited: ₹500.0
END_ACCOUNT
```

## Future Enhancements

Potential improvements for the application:
- Account locking after multiple failed PIN attempts
- Interest calculation for savings accounts
- Transfer money between accounts
- Account closure functionality
- Detailed reporting and analytics
- GUI interface using Swing or JavaFX
- Database integration instead of file storage
- Multi-currency support

## Troubleshooting

### Common Issues

**"No account file found, starting fresh"**
- This is normal for first-time usage
- The application will create the file automatically

**"Account number already exists"**
- Choose a different account number
- Account numbers must be unique

**"PIN must be 4 digits"**
- Ensure PIN contains exactly 4 numeric digits
- No letters or special characters allowed

**"Insufficient balance"**
- Check your current balance before withdrawing
- Cannot withdraw more than available balance

### File Permissions
Ensure the application has write permissions in its directory to create and modify the `accounts.txt` file.

## Learning Outcomes

This project demonstrates and teaches several important programming concepts:

### Object-Oriented Programming (OOP)
- **Encapsulation**: Private fields with public methods for controlled access
- **Data Abstraction**: Account class hides internal implementation details
- **Method Organization**: Logical grouping of related functionality
- **Constructor Usage**: Proper object initialization with parameters

### Java Core Concepts
- **Collections Framework**: Using `ArrayList` for transaction history and `HashMap` for account storage
- **String Manipulation**: PIN validation, input parsing, and data formatting
- **Exception Handling**: Try-catch blocks for robust error management
- **Static Methods**: Utility methods in FileManager and BankApp classes
- **Scanner Class**: Reading and validating user input from console

### File I/O Operations
- **BufferedReader/BufferedWriter**: Efficient file reading and writing
- **File Persistence**: Converting objects to text format and back
- **Data Serialization**: Custom format for storing account data
- **Exception Handling**: Managing FileNotFoundException and IOException

### Data Structures and Algorithms
- **HashMap**: Fast account lookup by account number
- **ArrayList**: Dynamic transaction history with size limits
- **FIFO Queue Simulation**: Removing oldest transactions when limit reached
- **String Processing**: Parsing file data back into objects

### Input Validation and Security
- **Data Type Validation**: Ensuring numeric inputs are valid
- **Business Rule Enforcement**: PIN format, positive amounts, sufficient balance
- **Input Sanitization**: Handling invalid user input gracefully
- **Authentication**: Simple PIN-based security system

### Software Design Patterns
- **Single Responsibility Principle**: Each class has a specific purpose
- **Separation of Concerns**: UI, business logic, and data access separated
- **Factory Pattern**: Creating Account objects from file data
- **State Management**: Maintaining application state throughout execution

### Error Handling and User Experience
- **Defensive Programming**: Validating all inputs before processing
- **User-Friendly Messages**: Clear error messages and prompts
- **Graceful Degradation**: Application continues running despite errors
- **Input Recovery**: Allowing users to retry after invalid input

### Software Development Practices
- **Code Organization**: Logical file structure and class hierarchy
- **Documentation**: Clear method names and consistent formatting
- **Testing Scenarios**: Handling edge cases and boundary conditions
- **Version Control Ready**: Well-structured code suitable for collaboration

### Real-World Applications
- **Banking Domain Knowledge**: Understanding basic banking operations
- **Data Persistence**: Importance of saving user data between sessions
- **User Interface Design**: Creating intuitive console-based interactions
- **System Architecture**: Separating different layers of application logic

### Programming Best Practices
- **Naming Conventions**: Meaningful variable and method names
- **Code Reusability**: Methods that can be called multiple times
- **Resource Management**: Proper handling of file resources
- **Constant Usage**: Using final variables for configuration values

This project serves as an excellent introduction to building complete applications that combine multiple programming concepts into a cohesive, functional system.

## License

This project is created for educational purposes. Feel free to modify and distribute as needed.

## Author

Java Bank Application - A console-based banking system demonstration

---

For questions or issues, please refer to the source code comments or create an issue in the project repository.
