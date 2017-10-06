package org.scorpion.product;

/**
 * Instrument provides a service for the basic information of the financial instrument
 */
public interface Instrument {

    String getSymbol();

    Exchange getExchange();

    String getDescription();

    ProductType getProductType();
}
