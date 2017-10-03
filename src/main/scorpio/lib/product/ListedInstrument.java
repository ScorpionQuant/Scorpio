package lib.product;

public interface ListedInstrument extends Instrument {

    Boolean isDelisted();

    double getLotSize();

    double getTickSize();
}
