package org.scorpion.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class Stock implements ListedInstrument {

    private String symbol;
    private String description;
    private Exchange exchange;
    private double tickSize;
    private double lotSize;
    private boolean isDelisted;
    private LocalDate listedDate;

    public Stock(String symbol,
                 String description,
                 Exchange exchange,
                 double tickSize,
                 double lotSize,
                 boolean isDelisted,
                 LocalDate listedDate) {
        this.symbol = symbol;
        this.description = description;
        this.exchange = exchange;
        this.tickSize = tickSize;
        this.lotSize = lotSize;
        this.isDelisted = isDelisted;
        this.listedDate = listedDate;
    }

    @Override
    public Boolean isDelisted() {
        return isDelisted;
    }

    @Override
    public double getLotSize() {
        return lotSize;
    }

    @Override
    public double getTickSize() {
        return tickSize;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Exchange getExchange() {
        return exchange;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.Stock;
    }

    public LocalDate getListedDate() {
        return listedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        return new EqualsBuilder()
                .append(tickSize, stock.tickSize)
                .append(lotSize, stock.lotSize)
                .append(isDelisted, stock.isDelisted)
                .append(symbol, stock.symbol)
                .append(description, stock.description)
                .append(exchange, stock.exchange)
                .append(listedDate, stock.listedDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(symbol)
                .append(description)
                .append(exchange)
                .append(tickSize)
                .append(lotSize)
                .append(isDelisted)
                .append(listedDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("description", description)
                .append("exchange", exchange)
                .append("tickSize", tickSize)
                .append("lotSize", lotSize)
                .append("isDelisted", isDelisted)
                .append("listedDate", listedDate)
                .toString();
    }
}
