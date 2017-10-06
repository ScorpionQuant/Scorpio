package org.scorpion.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class ListedOption implements Option, ListedInstrument {

    private Instrument underlying;
    private String description;
    private String symbol;
    private Exchange exchange;
    private long contractSize;
    private double lotSize;
    private double tickSize;
    private double strike;
    private boolean isDelisted;
    private PutCall putCall;
    private ExerciseType exerciseType;
    private LocalDate expiryDate;
    private LocalDate firstTradeDate;
    private LocalDate lastTradeDate;
    private LocalDate settlementDate;

    public ListedOption(Instrument underlying,
                        String symbol,
                        Exchange exchange,
                        String description,
                        boolean isDelisted,
                        long contractSize,
                        double lotSize,
                        double tickSize,
                        double strike,
                        PutCall putCall,
                        ExerciseType exerciseType,
                        LocalDate expiryDate,
                        LocalDate firstTradeDate,
                        LocalDate lastTradeDate,
                        LocalDate settlementDate) {
        this.underlying = underlying;
        this.symbol = symbol;
        this.exchange = exchange;
        this.description = description;
        this.isDelisted = isDelisted;
        this.contractSize = contractSize;
        this.lotSize = lotSize;
        this.tickSize = tickSize;
        this.strike = strike;
        this.putCall = putCall;
        this.exerciseType = exerciseType;
        this.expiryDate = expiryDate;
        this.firstTradeDate = firstTradeDate;
        this.lastTradeDate = lastTradeDate;
        this.settlementDate = settlementDate;
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
        return ProductType.Option;
    }

    @Override
    public double getStrike() {
        return strike;
    }

    @Override
    public PutCall getPutCall() {
        return putCall;
    }

    @Override
    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    @Override
    public long getContractSize() {
        return contractSize;
    }

    @Override
    public long getTimeToMaturity() {
        // TODO trading calendar is essential to get the exact days
        return 0;
    }

    @Override
    public Instrument getUnderlying() {
        return underlying;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public LocalDate getFirstTradeDate() {
        return firstTradeDate;
    }

    @Override
    public LocalDate getLastTradeDate() {
        return lastTradeDate;
    }

    @Override
    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ListedOption that = (ListedOption) o;

        return new EqualsBuilder()
                .append(isDelisted, that.isDelisted)
                .append(contractSize, that.contractSize)
                .append(lotSize, that.lotSize)
                .append(tickSize, that.tickSize)
                .append(strike, that.strike)
                .append(underlying, that.underlying)
                .append(symbol, that.symbol)
                .append(exchange, that.exchange)
                .append(description, that.description)
                .append(putCall, that.putCall)
                .append(exerciseType, that.exerciseType)
                .append(expiryDate, that.expiryDate)
                .append(firstTradeDate, that.firstTradeDate)
                .append(lastTradeDate, that.lastTradeDate)
                .append(settlementDate, that.settlementDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(underlying)
                .append(symbol)
                .append(exchange)
                .append(description)
                .append(isDelisted)
                .append(contractSize)
                .append(lotSize)
                .append(tickSize)
                .append(strike)
                .append(putCall)
                .append(exerciseType)
                .append(expiryDate)
                .append(firstTradeDate)
                .append(lastTradeDate)
                .append(settlementDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("underlying", underlying)
                .append("symbol", symbol)
                .append("exchange", exchange)
                .append("description", description)
                .append("isDelisted", isDelisted)
                .append("contractSize", contractSize)
                .append("lotSize", lotSize)
                .append("tickSize", tickSize)
                .append("strike", strike)
                .append("putCall", putCall)
                .append("exerciseType", exerciseType)
                .append("expiryDate", expiryDate)
                .append("firstTradeDate", firstTradeDate)
                .append("lastTradeDate", lastTradeDate)
                .append("settlementDate", settlementDate)
                .toString();
    }

}
