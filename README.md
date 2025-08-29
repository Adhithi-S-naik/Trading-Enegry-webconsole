# Energy Trading Management System

A comprehensive Java-based application for managing energy trading operations, built with a clean architecture pattern and SQL Server database integration.

## 🚀 Features

- **Trade Management**: Add, view, edit, delete, and search energy trades
- **Database Integration**: SQL Server database with JDBC connectivity
- **Input Validation**: Robust input validation and error handling
- **Clean Architecture**: MVC pattern with Service and DAO layers
- **Singleton Database Connection**: Efficient database connection management
- **Interactive CLI**: User-friendly command-line interface

## 🏗️ Architecture
<img width="1269" height="571" alt="Screenshot 2025-08-28 at 8 39 10 PM" src="https://github.com/user-attachments/assets/8ad87482-a60c-4f40-b22e-982602735663" />


## 📁 Project Structure

```
Trading-energy/
├── lib/                          # External Dependencies
│   ├── mssql-jdbc-12.6.4.jre11.jar
│   └── mssql-jdbc_auth-12.6.4.x64.dll
├── src/                          # Source Code
│   ├── TradeApp.java            # Main application class
│   ├── TradeController.java     # Controller layer
│   ├── TradeService.java        # Business logic layer
│   ├── TradeDAO.java            # Data access layer
│   ├── Trade.java               # Trade entity/model
│   ├── DatabaseConnection.java  # Database connection manager
│   └── InputValidator.java      # Input validation utilities
└── sqlserver-Script-toSetUpDB.sql #DataBase setup
```

## 🛠️ Prerequisites

- **Java**: JDK 11 or higher
- **Database**: Microsoft SQL Server (2016 or later)
- **SQL Server JDBC Driver**: Included in the `lib/` folder

## ⚙️ Setup Instructions

### 1. Database Setup

1. **Install SQL Server** (if not already installed)
2. Run the sqlserver-Script-toSetUpDB.sql script to **create the Database and tables**.
   
### 2. Application Setup

1. **Clone/Download** the project to your local machine
2. **Navigate** to the project directory:
   ```bash
   cd Trading-energy
   ```

3. **Compile** the Java source files:
   ```bash
   javac -cp "lib/*" src/*.java
   ```

4. **Run** the application:
   ```bash
   java "-Djava.library.path=lib" -cp "src;lib/*" TradeApp
   ```

## 🎯 Usage

### Main Menu Options

1. **Add Trade** - Create a new energy trade
2. **View All Trades** - Display all trades in the system
3. **Update Trade** - Modify existing trade details
4. **Delete Trade** - Remove trades from the system
5. **Search Trades** - Find specific trades by criteria
6. **Exit** - Close the application

### Sample Trade Data

- **Trade Date**: Format as string (e.g., "2024-01-15")
- **Counterparty**: Trading partner name
- **Commodity**: Energy type (e.g., "Natural Gas", "Electricity", "Oil")
- **Volume**: Quantity in appropriate units
- **Price**: Price per unit
- **Trade Type**: "Buy" or "Sell"

## 🔧 Configuration

### Database Connection

The database connection is configured in `DatabaseConnection.java`:

```java
private static final String URL = 
    "jdbc:sqlserver://localhost:1433;databaseName=EnergyTradingDB;encrypt=false;integratedSecurity=true;";
```

**Modify these parameters as needed:**
- `localhost:1433` - Server address and port
- `EnergyTradingDB` - Database name
- `encrypt=false` - Encryption setting
- `integratedSecurity=true` - Windows authentication

### Alternative Authentication

For SQL Server authentication, modify the connection string:
```java
"jdbc:sqlserver://localhost:1433;databaseName=EnergyTradingDB;encrypt=false;user=YourUsername;password=YourPassword;"
```

## 🧪 Testing

Run the test class to verify database connectivity:
```bash
java -cp "lib/*;src" DatabaseConnectionTest
```

## 📚 Dependencies

- **Microsoft SQL Server JDBC Driver**: Version 12.6.4
- **Java**: JDK 11+ (for enhanced switch expressions)

## 🚨 Troubleshooting

### Common Issues

1. **JDBC Driver Not Found**
   - Ensure `mssql-jdbc-12.6.4.jre11.jar` is in the `lib/` folder
   - Check classpath includes the lib directory

2. **Database Connection Failed**
   - Verify SQL Server is running
   - Check database name and server address
   - Ensure Windows authentication is enabled (or provide credentials)

3. **Compilation Errors**
   - Ensure Java 11+ is installed
   - Check all source files are in the `src/` directory

### Error Messages

- `"JDBC Driver not found"` - Missing SQL Server JDBC driver
- `"Failed to initialize application"` - Database connection issues
- `"Invalid option"` - Menu selection errors

## 🔒 Security Considerations

- **Database Security**: Use Windows authentication when possible
- **Input Validation**: All user inputs are validated before processing
- **Connection Management**: Database connections are properly managed and closed.

**Snapshots of Application**

**Start of an Application:** <br>
<br><img width="1353" height="484" alt="Screenshot 2025-08-29 234958" src="https://github.com/user-attachments/assets/53bebc91-142a-443c-9cb5-17f9e94ab5e3" /><br>

**Adding Trade**<br>
<br><img width="665" height="926" alt="Screenshot 2025-08-29 235131" src="https://github.com/user-attachments/assets/c602c306-1a15-4c5f-a27c-9f11bda16aee" /> <br>

**Viewing the Trades** <br>
<br> <img width="1555" height="273" alt="Screenshot 2025-08-29 235249" src="https://github.com/user-attachments/assets/938052f0-f16a-410b-9757-50421d3f3e9b" /> <br>

**Updating The Trade** <br>
<br> <img width="1885" height="254" alt="Screenshot 2025-08-29 235334" src="https://github.com/user-attachments/assets/04327175-6849-4f12-aebf-4759ffaedd53" />
 <br>**
**Deleting The Trade**<br>
<br> <img width="1833" height="224" alt="Screenshot 2025-08-29 235401" src="https://github.com/user-attachments/assets/07964b8a-f3de-41a5-b193-d05d67982dce" />
 <br>
**Searching The Trade**<br>
<br> <img width="1605" height="608" alt="Screenshot 2025-08-29 235448" src="https://github.com/user-attachments/assets/640a33c2-0af2-4f7d-b318-bb3b02c3360a" />
 <br>
