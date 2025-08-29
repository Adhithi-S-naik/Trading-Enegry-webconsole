-- SQL Server Setup Script for Energy Trading Application
-- Run this script in SQL Server Management Studio (SSMS) or sqlcmd

-- Create the database
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'EnergyTradingDB')
BEGIN
    CREATE DATABASE EnergyTradingDB;
    PRINT 'Database EnergyTradingDB created successfully.';
END
ELSE
BEGIN
    PRINT 'Database EnergyTradingDB already exists.';
END
GO

-- Use the database
USE EnergyTradingDB;
GO

-- Drop existing table if it exists
IF OBJECT_ID('trades', 'U') IS NOT NULL
BEGIN
    DROP TABLE trades;
    PRINT 'Existing trades table dropped.';
END
GO

-- Create the trades table
CREATE TABLE trades (
    trade_id INT IDENTITY(1,1) NOT NULL,
    trade_date DATE NOT NULL,
    counterparty NVARCHAR(100) NOT NULL,
    commodity NVARCHAR(50) NOT NULL,
    volume DECIMAL(10,2) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    trade_type NVARCHAR(10) NOT NULL,
    CONSTRAINT PK_trades PRIMARY KEY (trade_id),
    CONSTRAINT CHK_trade_type CHECK (trade_type IN ('BUY','SELL')),
    CONSTRAINT CHK_volume CHECK (volume > 0),
    CONSTRAINT CHK_price CHECK (price >= 0)
);
PRINT 'Trades table created successfully.';
GO

-- Create indexes for better performance
CREATE INDEX IX_trades_counterparty ON trades(counterparty);
CREATE INDEX IX_trades_commodity ON trades(commodity);
CREATE INDEX IX_trades_trade_date ON trades(trade_date);
PRINT 'Indexes created successfully.';
GO

-- Insert sample data
INSERT INTO trades (trade_date, counterparty, commodity, volume, price, trade_type) VALUES
('2024-01-15', 'ABC Energy Corp', 'Crude Oil', 1000.00, 75.50, 'BUY'),
('2024-01-16', 'XYZ Trading Ltd', 'Natural Gas', 500.00, 3.25, 'SELL'),
('2024-01-17', 'Global Power Inc', 'Coal', 2000.00, 45.00, 'BUY'),
('2024-01-18', 'Renewable Energy Co', 'Solar Power', 750.00, 120.00, 'BUY'),
('2024-01-19', 'Fossil Fuel Ltd', 'Coal', 1500.00, 42.50, 'SELL');
PRINT 'Sample data inserted successfully.';
GO

-- Verify the setup
SELECT COUNT(*) as TotalTrades FROM trades;
SELECT TOP 3 * FROM trades ORDER BY trade_date;
GO

PRINT 'SQL Server setup completed successfully!';
PRINT 'You can now run the Java application.';
GO
