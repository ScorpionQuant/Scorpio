package app.pricer.service;

import lib.calc.CalcRequest;
import lib.calc.CalcResponse;

public interface CalcHandler {

    CalcResponse handle(CalcRequest request);

}
