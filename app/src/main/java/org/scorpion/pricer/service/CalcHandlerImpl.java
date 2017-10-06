package org.scorpion.pricer.service;

import org.scorpion.calc.*;
import org.scorpion.calc.calculator.CalculatorType;
import org.scorpion.calc.calculator.GeneralizedBSM;
import org.springframework.stereotype.Service;

@Service
public class CalcHandlerImpl implements CalcHandler {

    @Override
    public CalcResponse handle(CalcRequest request) {

        String symbol = request.getSymbol();
        OptionInput input = request.getInput();
        Calculator calculator = getCalculator(request.getCalculatorType());

        return new CalcResponse(symbol, calculator.calculate(input));
    }

    private Calculator getCalculator(CalculatorType type) {
        switch (type) {
            case GBSM:
                return new GeneralizedBSM();
            default:
                return new GeneralizedBSM();
        }
    }



}
