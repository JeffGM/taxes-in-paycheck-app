package com.jefflopes.calculadorasalarioliquido.data;

public class SalaryInfo {
    public Double bruteSalary;
    public Integer numberOfDependents;
    public Double otherDeductions;
    public Double liquidSalary;
    public Double inss;
    public Double irrf;

    public SalaryInfo(Double BruteSalary, Integer NumberOfDependents, Double OtherDeductions) {
        this.bruteSalary = BruteSalary;
        this.numberOfDependents = NumberOfDependents;
        this.otherDeductions = OtherDeductions;
    }

    public Double getDeductionsInPercentage() {
        if((this.bruteSalary != null && this.bruteSalary != 0) &&
                (this.liquidSalary != null && this.liquidSalary != 0)) {
            return (1 - this.liquidSalary / bruteSalary) * 100;
        } else {
            return 0.00;
        }
    }
}
