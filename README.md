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

The application follows a layered architecture pattern:

```
┌─────────────────┐
│   TradeApp     │  ← Main Application & CLI
├─────────────────┤
│ TradeController │  ← Controller Layer
├─────────────────┤
│ TradeService    │  ← Business Logic Layer
├─────────────────┤
│   TradeDAO      │  ← Data Access Layer
├─────────────────┤
│DatabaseConnection│  ← Database Layer
└─────────────────┘
```

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
└── README.md                     # This file
```

## 🛠️ Prerequisites

- **Java**: JDK 11 or higher
- **Database**: Microsoft SQL Server (2016 or later)
- **SQL Server JDBC Driver**: Included in the `lib/` folder

## ⚙️ Setup Instructions

### 1. Database Setup

1. **Install SQL Server** (if not already installed)
2. **Create Database**:
   ```sql
   CREATE DATABASE EnergyTradingDB;
   ```

3. **Create Trades Table**:
   ```sql
   USE EnergyTradingDB;
   
   CREATE TABLE Trades (
       TradeID INT IDENTITY(1,1) PRIMARY KEY,
       TradeDate VARCHAR(20) NOT NULL,
       Counterparty VARCHAR(100) NOT NULL,
       Commodity VARCHAR(50) NOT NULL,
       Volume DECIMAL(10,2) NOT NULL,
       Price DECIMAL(10,2) NOT NULL,
       TradeType VARCHAR(20) NOT NULL
   );
   ```

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
   java -cp "lib/*;src" TradeApp
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
- **Connection Management**: Database connections are properly managed and closed

## 📈 Future Enhancements

- [ ] Web-based user interface
- [ ] Real-time market data integration
- [ ] Advanced reporting and analytics
- [ ] Multi-user support with role-based access
- [ ] Audit trail and logging
- [ ] REST API endpoints

## 👥 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 📞 Support

For issues and questions:
- Check the troubleshooting section above
- Review the code comments for implementation details
- Ensure all prerequisites are met

---

**Note**: This application is designed for educational and demonstration purposes. For production use, additional security measures and error handling should be implemented.
