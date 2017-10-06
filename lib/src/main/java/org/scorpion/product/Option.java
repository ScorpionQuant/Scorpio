package org.scorpion.product;

import java.time.LocalDate;

public interface Option {

    Instrument getUnderlying();

    double getStrike();

    PutCall getPutCall();

    ExerciseType getExerciseType();

    long getContractSize();

    long getTimeToMaturity();

    LocalDate getExpiryDate();

    LocalDate getFirstTradeDate();

    LocalDate getLastTradeDate();

    LocalDate getSettlementDate();
}
