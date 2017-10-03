package lib.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class ListedFuture implements Future, ListedInstrument {

    private Instrument underlying;
    private SettlementType settlementType;
    private String description;
    private String symbol;
    private Exchange exchange;
    private double lotSize;
    private double tickSize;
    private boolean isDelisted;
    private LocalDate settlementDate;
    private LocalDate firstTradeDate;
    private LocalDate lastTradeDate;

    public ListedFuture(Instrument underlying,
                        SettlementType settlementType,
                        String description,
                        String symbol,
                        Exchange exchange,
                        double lotSize,
                        double tickSize,
                        boolean isDelisted,
                        LocalDate settlementDate,
                        LocalDate firstTradeDate,
                        LocalDate lastTradeDate) {
        this.underlying = underlying;
        this.settlementType = settlementType;
        this.description = description;
        this.symbol = symbol;
        this.exchange = exchange;
        this.lotSize = lotSize;
        this.tickSize = tickSize;
        this.isDelisted = isDelisted;
        this.settlementDate = settlementDate;
        this.firstTradeDate = firstTradeDate;
        this.lastTradeDate = lastTradeDate;
    }

    @Override
    public Instrument getUnderlying() {
        return underlying;
    }

    @Override
    public SettlementType getSettlementType() {
        return settlementType;
    }

    @Override
    public long getTimeToMaturity() {
        // TODO trading calendar is essential to get exact days
        return 0;
    }

    @Override
    public LocalDate getSettlementDate() {
        return settlementDate;
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
        return ProductType.Future;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ListedFuture that = (ListedFuture) o;

        return new EqualsBuilder()
                .append(lotSize, that.lotSize)
                .append(tickSize, that.tickSize)
                .append(isDelisted, that.isDelisted)
                .append(underlying, that.underlying)
                .append(settlementType, that.settlementType)
                .append(description, that.description)
                .append(symbol, that.symbol)
                .append(exchange, that.exchange)
                .append(settlementDate, that.settlementDate)
                .append(firstTradeDate, that.firstTradeDate)
                .append(lastTradeDate, that.lastTradeDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(underlying)
                .append(settlementType)
                .append(description)
                .append(symbol)
                .append(exchange)
                .append(lotSize)
                .append(tickSize)
                .append(isDelisted)
                .append(settlementDate)
                .append(firstTradeDate)
                .append(lastTradeDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("underlying", underlying)
                .append("settlementType", settlementType)
                .append("description", description)
                .append("symbol", symbol)
                .append("exchange", exchange)
                .append("lotSize", lotSize)
                .append("tickSize", tickSize)
                .append("isDelisted", isDelisted)
                .append("settlementDate", settlementDate)
                .append("firstTradeDate", firstTradeDate)
                .append("lastTradeDate", lastTradeDate)
                .toString();
    }
}
