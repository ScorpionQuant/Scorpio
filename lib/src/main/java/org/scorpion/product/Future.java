package org.scorpion.product;

import java.time.LocalDate;

public interface Future {

    Instrument getUnderlying();

    SettlementType getSettlementType();

    long getTimeToMaturity();

    LocalDate getSettlementDate();

    LocalDate getFirstTradeDate();

    LocalDate getLastTradeDate();
}
