import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TradeController {
    private final TradeService tradeService;
    private final Scanner scanner;

    public TradeController(TradeService tradeService, Scanner scanner) {
        this.tradeService = tradeService;
        this.scanner = scanner;
    }

    public void addTrade() {
        try {
            System.out.println("\n--- Add New Trade ---");

            String date = InputValidator.getValidDate(scanner, "Enter trade date (YYYY-MM-DD): ");
            String counterparty = InputValidator.getValidString(scanner, "Enter counterparty: ");
            String commodity = InputValidator.getValidString(scanner, "Enter commodity: ");
            double volume = InputValidator.getValidDouble(scanner, "Enter volume: ");
            double price = InputValidator.getValidDouble(scanner, "Enter price: ");
            String tradeType = InputValidator.getValidTradeType(scanner, "Enter trade type (BUY/SELL): ");

            if (tradeService.createTrade(date, counterparty, commodity, volume, price, tradeType)) {
                System.out.println("✓ Trade added successfully!");
            } else {
                System.out.println("✗ Failed to add trade.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewAllTrades() {
        try {
            System.out.println("\n--- All Trades ---");
            List<Trade> trades = tradeService.getAllTrades();

            if (trades.isEmpty()) {
                System.out.println("No trades found in the database.");
                return;
            }

            System.out.println("Total trades: " + trades.size());
            System.out.println("-".repeat(100));

            for (Trade trade : trades) {
                System.out.println(trade);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void updateTrade() {
        try {
            System.out.println("\n--- Update Trade ---");

            int tradeId = InputValidator.getValidInteger(scanner, "Enter Trade ID to update: ");

            // Check if trade exists
            Trade existingTrade = tradeService.getTradeById(tradeId);
            if (existingTrade == null) {
                System.out.println("✗ Trade with ID " + tradeId + " not found.");
                return;
            }

            System.out.println("Current trade details: " + existingTrade);

            double newPrice = InputValidator.getValidDouble(scanner, "Enter new price: ");
            double newVolume = InputValidator.getValidDouble(scanner, "Enter new volume: ");

            if (tradeService.updateTrade(tradeId, newPrice, newVolume)) {
                System.out.println("✓ Trade updated successfully!");
            } else {
                System.out.println("✗ Failed to update trade.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void deleteTrade() {
        try {
            System.out.println("\n--- Delete Trade ---");

            int tradeId = InputValidator.getValidInteger(scanner, "Enter Trade ID to delete: ");

            // Check if trade exists and show details
            Trade existingTrade = tradeService.getTradeById(tradeId);
            if (existingTrade == null) {
                System.out.println("✗ Trade with ID " + tradeId + " not found.");
                return;
            }

            System.out.println("Trade to be deleted: " + existingTrade);
            System.out.print("Are you sure you want to delete this trade? (y/N): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                if (tradeService.deleteTrade(tradeId)) {
                    System.out.println("✓ Trade deleted successfully!");
                } else {
                    System.out.println("✗ Failed to delete trade.");
                }
            } else {
                System.out.println("Delete operation cancelled.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void searchTrades() {
        try {
            System.out.println("\n--- Search Trades ---");
            System.out.println("1. Search by Counterparty");
            System.out.println("2. Search by Commodity");

            int choice = InputValidator.getValidInteger(scanner, "Enter search option (1 or 2): ");

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please select 1 or 2.");
                return;
            }

            String searchValue = InputValidator.getValidString(scanner, "Enter search value: ");
            List<Trade> results = tradeService.searchTrades(String.valueOf(choice), searchValue);

            if (results.isEmpty()) {
                System.out.println("No trades found matching your search criteria.");
            } else {
                System.out.println("\nSearch Results (" + results.size() + " found):");
                System.out.println("-".repeat(100));
                for (Trade trade : results) {
                    System.out.println(trade);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}