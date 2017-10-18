package org.scorpion.calc.analytic.discount;

public enum DiscountType {

    FLAT("Discount by flat value"),
    CURVE("Discount by a given curve");

    private String typeName;

    DiscountType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
