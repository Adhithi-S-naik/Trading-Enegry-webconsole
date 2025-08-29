import java.sql.*;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        System.out.println("Testing Database Connection...");
        System.out.println("================================");
        
        try {
            // Test basic connection
            System.out.println("1. Testing basic connection...");
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            
            if (connection != null && !connection.isClosed()) {
                System.out.println(" Connection successful!");
                System.out.println("   Database: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("   Version: " + connection.getMetaData().getDatabaseProductVersion());
                System.out.println("   User: " + connection.getMetaData().getUserName());
            }
            
            // Test a simple query
            System.out.println("\n2. Testing simple query...");
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT @@VERSION as version");
                if (rs.next()) {
                    System.out.println(" Query executed successfully!");
                    System.out.println("   SQL Server Version: " + rs.getString("version"));
                }
            }
            
            // Test database access
            System.out.println("\n3. Testing database access...");
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT DB_NAME() as current_db");
                if (rs.next()) {
                    System.out.println(" Database access confirmed!");
                    System.out.println("   Current Database: " + rs.getString("current_db"));
                }
            }
            
            // Close connection
            dbConnection.closeConnection();
            System.out.println("\n All tests passed! Database connection is working properly.");
            
        } catch (SQLException e) {
            System.err.println(" Database connection failed!");
            System.err.println("Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            
            // Provide specific troubleshooting tips
            if (e.getMessage().contains("Login failed")) {
                System.err.println("\n Troubleshooting: Login failed");
                System.err.println("   - Check if your Windows account has access to EnergyTradingDB");
                System.err.println("   - Verify SQL Server allows Windows Authentication");
                System.err.println("   - Run: CREATE USER [YourWindowsDomain\\YourUsername] FOR LOGIN [YourWindowsDomain\\YourUsername]");
            } else if (e.getMessage().contains("Server not found")) {
                System.err.println("\n Troubleshooting: Server not found");
                System.err.println("   - Check if SQL Server is running");
                System.err.println("   - Verify port 1433 is correct");
                System.err.println("   - Try using 'localhost' instead of IP address");
            } else if (e.getMessage().contains("Driver not found")) {
                System.err.println("\n Troubleshooting: Driver not found");
                System.err.println("   - Ensure Microsoft JDBC driver is in your classpath");
                System.err.println("   - Check if mssql-jdbc.jar is included in your project");
            }
            
        } catch (Exception e) {
            System.err.println(" Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
