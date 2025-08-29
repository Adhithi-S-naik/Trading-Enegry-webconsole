import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TradeDAO {
    private final Connection connection;
    
    public TradeDAO(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addTrade(Trade trade) throws SQLException {
        String sql = "INSERT INTO Trades (TradeDate, Counterparty, Commodity, Volume, Price, TradeType) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, trade.getTradeDate());
            ps.setString(2, trade.getCounterparty());
            ps.setString(3, trade.getCommodity());
            ps.setDouble(4, trade.getVolume());
            ps.setDouble(5, trade.getPrice());
            ps.setString(6, trade.getTradeType());
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        trade.setTradeId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }
    
    public List<Trade> getAllTrades() throws SQLException {
        List<Trade> trades = new ArrayList<>();
        String sql = "SELECT * FROM Trades ORDER BY TradeID";
        
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                trades.add(mapResultSetToTrade(rs));
            }
        }
        return trades;
    }
    
    public boolean updateTrade(int tradeId, double newPrice, double newVolume) throws SQLException {
        String sql = "UPDATE Trades SET Price=?, Volume=? WHERE TradeID=?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setDouble(2, newVolume);
            ps.setInt(3, tradeId);
            
            return ps.executeUpdate() > 0;
        }
    }
    
    public boolean deleteTrade(int tradeId) throws SQLException {
        String sql = "DELETE FROM Trades WHERE TradeID=?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tradeId);
            return ps.executeUpdate() > 0;
        }
    }
    
    public List<Trade> searchTrades(String searchColumn, String searchValue) throws SQLException {
        List<Trade> trades = new ArrayList<>();
        String sql = "SELECT * FROM Trades WHERE " + searchColumn + " LIKE ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + searchValue + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    trades.add(mapResultSetToTrade(rs));
                }
            }
        }
        return trades;
    }
    
    public Trade getTradeById(int tradeId) throws SQLException {
        String sql = "SELECT * FROM Trades WHERE TradeID=?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tradeId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTrade(rs);
                }
            }
        }
        return null;
    }
    
    private Trade mapResultSetToTrade(ResultSet rs) throws SQLException {
        return new Trade(
            rs.getInt("TradeID"),
            rs.getDate("TradeDate").toString(),
            rs.getString("Counterparty"),
            rs.getString("Commodity"),
            rs.getDouble("Volume"),
            rs.getDouble("Price"),
            rs.getString("TradeType")
        );
    }
}
