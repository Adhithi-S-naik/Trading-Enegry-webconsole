public class Trade {
    private int tradeId;
    private String tradeDate;
    private String counterparty;
    private String commodity;
    private double volume;
    private double price;
    private String tradeType;
    
    // Default constructor
    public Trade() {}
    
    // Constructor without ID (for new trades)
    public Trade(String tradeDate, String counterparty, String commodity, 
                double volume, double price, String tradeType) {
        this.tradeDate = tradeDate;
        this.counterparty = counterparty;
        this.commodity = commodity;
        this.volume = volume;
        this.price = price;
        this.tradeType = tradeType;
    }
    
    // Constructor with ID (for existing trades)
    public Trade(int tradeId, String tradeDate, String counterparty, String commodity, 
                double volume, double price, String tradeType) {
        this(tradeDate, counterparty, commodity, volume, price, tradeType);
        this.tradeId = tradeId;
    }
    
    // Getters and Setters
    public int getTradeId() { return tradeId; }
    public void setTradeId(int tradeId) { this.tradeId = tradeId; }
    
    public String getTradeDate() { return tradeDate; }
    public void setTradeDate(String tradeDate) { this.tradeDate = tradeDate; }
    
    public String getCounterparty() { return counterparty; }
    public void setCounterparty(String counterparty) { this.counterparty = counterparty; }
    
    public String getCommodity() { return commodity; }
    public void setCommodity(String commodity) { this.commodity = commodity; }
    
    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public String getTradeType() { return tradeType; }
    public void setTradeType(String tradeType) { this.tradeType = tradeType; }
    
    @Override
    public String toString() {
        return String.format("ID:%d | Date:%s | Counterparty:%s | Commodity:%s | Volume:%.2f | Price:%.2f | Type:%s",
                tradeId, tradeDate, counterparty, commodity, volume, price, tradeType);
    }
}
