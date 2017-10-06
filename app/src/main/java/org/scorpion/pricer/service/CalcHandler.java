package org.scorpion.pricer.service;

import org.scorpion.calc.CalcRequest;
import org.scorpion.calc.CalcResponse;

public interface CalcHandler {

    CalcResponse handle(CalcRequest request);

}
