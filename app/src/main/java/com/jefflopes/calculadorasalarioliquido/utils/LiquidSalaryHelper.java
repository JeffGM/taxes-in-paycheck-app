package com.jefflopes.calculadorasalarioliquido.utils;

import com.jefflopes.calculadorasalarioliquido.data.CalculationResult;
import com.jefflopes.calculadorasalarioliquido.data.TributeTuple;


public class LiquidSalaryHelper {
    private Double minSalary = 1045.00;
    private TributeTuple[] inssQuotas = {new TributeTuple(0.075, 0.00),
                                  new TributeTuple(0.09, 15.67),
                                  new TributeTuple(0.12, 78.36),
                                  new TributeTuple(0.14, 141.05)};

    private TributeTuple[] irrfQuotas = {new TributeTuple(0.0, 0.00),
            new TributeTuple(0.075, 142.80),
            new TributeTuple(0.15, 354.80),
            new TributeTuple(0.225, 636.13)};

    public Double calculateINSS(Double salary) {}
    public Double calculateIRRF(Double salary, Integer numDependents) {}
    public CalculationResult calculateLiquidSalary(Double salary, Integer numDependents, Double otherDiscounts) {}
}
