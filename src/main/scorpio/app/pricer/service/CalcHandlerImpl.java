package app.pricer.service;

import lib.calc.*;
import lib.calc.calculator.CalculatorType;
import lib.calc.calculator.GeneralizedBSM;
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
