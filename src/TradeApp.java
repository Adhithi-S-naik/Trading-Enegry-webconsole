import java.sql.SQLException;
import java.util.Scanner;

public class TradeApp {
    private final TradeController tradeController;
    private final Scanner scanner;
    private boolean isRunning;
    
    public TradeApp() throws SQLException {
        this.scanner = new Scanner(System.in);
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        TradeDAO tradeDAO = new TradeDAO(dbConnection.getConnection());
        TradeService tradeService = new TradeService(tradeDAO);
        this.tradeController = new TradeController(tradeService, scanner);
        this.isRunning = true;
    }
    
    public void start() {
        System.out.println("[Connected] Connected to EnergyTradingDB successfully.");
        System.out.println("Welcome to Energy Trading Management System!");
        
        while (isRunning) {
            try {
                showMenu();
                int choice = InputValidator.getValidInteger(scanner, "Enter option number: ");
                processChoice(choice);
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        
        cleanup();
    }
    
    private void showMenu() {
        System.out.println("\n" + "=".repeat(35));
        System.out.println("   ENERGY TRADING MANAGEMENT");
        System.out.println("=".repeat(35));
        System.out.println("1. [Add] Add Trade");
        System.out.println("2. [View] View All Trades");
        System.out.println("3. [Edit] Update Trade");
        System.out.println("4. [Delete] Delete Trade");
        System.out.println("5. [Search] Search Trades");
        System.out.println("6. [Exit] Exit");
        System.out.println("-".repeat(35));
    }
    
    private void processChoice(int choice) {
        switch (choice) {
            case 1 -> tradeController.addTrade();
            case 2 -> tradeController.viewAllTrades();
            case 3 -> tradeController.updateTrade();
            case 4 -> tradeController.deleteTrade();
            case 5 -> tradeController.searchTrades();
            case 6 -> {
                System.out.println("[Goodbye] Closing application. Thank you for Using!");
                isRunning = false;
            }
            default -> System.out.println("[Warning] Invalid option. Please select 1-6.");
        }
    }
    
    private void cleanup() {
        try {
            scanner.close();
            DatabaseConnection.getInstance().closeConnection();
            System.out.println("[Closed] Database connection closed.");
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        try {
            TradeApp app = new TradeApp();
            app.start();
        } catch (SQLException e) {
            System.err.println("Failed to initialize application: " + e.getMessage());
            System.err.println("Please check your database connection settings.");
        }
    }
}
