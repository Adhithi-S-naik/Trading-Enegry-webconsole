import java.sql.SQLException;
import java.util.List;

public class TradeService {
    private final TradeDAO tradeDAO;

    public TradeService(TradeDAO tradeDAO) {
        this.tradeDAO = tradeDAO;
    }

    public boolean createTrade(String tradeDate, String counterparty, String commodity,
                               double volume, double price, String tradeType) throws SQLException {
        // Validate input
        if (!isValidInput(tradeDate, counterparty, commodity, volume, price, tradeType)) {
            return false;
        }

        Trade trade = new Trade(tradeDate, counterparty, commodity, volume, price, tradeType.toUpperCase());
        return tradeDAO.addTrade(trade);
    }

    public List<Trade> getAllTrades() throws SQLException {
        return tradeDAO.getAllTrades();
    }

    public boolean updateTrade(int tradeId, double newPrice, double newVolume) throws SQLException {
        if (newPrice <= 0 || newVolume <= 0) {
            System.out.println("Price and volume must be positive values.");
            return false;
        }
        return tradeDAO.updateTrade(tradeId, newPrice, newVolume);
    }

    public boolean deleteTrade(int tradeId) throws SQLException {
        return tradeDAO.deleteTrade(tradeId);
    }

    public List<Trade> searchTrades(String searchType, String searchValue) throws SQLException {
        String column = searchType.equals("1") ? "Counterparty" : "Commodity";
        return tradeDAO.searchTrades(column, searchValue);
    }

    public Trade getTradeById(int tradeId) throws SQLException {
        return tradeDAO.getTradeById(tradeId);
    }

    private boolean isValidInput(String tradeDate, String counterparty, String commodity,
                                 double volume, double price, String tradeType) {
        if (tradeDate == null || tradeDate.trim().isEmpty()) {
            System.out.println("Trade date cannot be empty.");
            return false;
        }
        if (counterparty == null || counterparty.trim().isEmpty()) {
            System.out.println("Counterparty cannot be empty.");
            return false;
        }
        if (commodity == null || commodity.trim().isEmpty()) {
            System.out.println("Commodity cannot be empty.");
            return false;
        }
        if (volume <= 0) {
            System.out.println("Volume must be positive.");
            return false;
        }
        if (price <= 0) {
            System.out.println("Price must be positive.");
            return false;
        }
        if (tradeType == null || (!tradeType.equalsIgnoreCase("BUY") && !tradeType.equalsIgnoreCase("SELL"))) {
            System.out.println("Trade type must be BUY or SELL.");
            return false;
        }
        return true;
    }
}
