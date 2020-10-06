package com.jefflopes.calculadorasalarioliquido.data;

public class CalculationResult {
    public Double liquidSalary;
    public Double deductionsInPercentage;
    public Double inss;
    public Double irrf;
    public Double otherDiscounts;

    public CalculationResult(Double LiquidSalary, Double DeductionsInPercentage, Double Inss,
                             Double Irrf, Double OtherDiscounts) {

        this.liquidSalary = LiquidSalary;
        this.deductionsInPercentage = DeductionsInPercentage;
        this.inss = Inss;
        this.irrf = Irrf;
        this.otherDiscounts = OtherDiscounts;
    }
}
