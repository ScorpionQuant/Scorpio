package org.scorpion.product;

/**
 * Trading exchange
 */
public enum Exchange {

    SHE("Shanghai Stock Exchange"),
    SZE("Shenzhen Stock Exchange"),
    DCE("Dalian Commodity Exchange"),
    SHF("Shanghai Future Exchange"),
    OTC("Over the counter");

    private String fullName;

    Exchange(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }

}
