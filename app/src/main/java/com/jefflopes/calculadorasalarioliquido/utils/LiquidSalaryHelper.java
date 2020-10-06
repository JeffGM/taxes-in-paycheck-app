package com.jefflopes.calculadorasalarioliquido.utils;

import android.os.Build;
import android.util.Range;

import androidx.annotation.RequiresApi;

import com.jefflopes.calculadorasalarioliquido.data.SalaryInfo;
import com.jefflopes.calculadorasalarioliquido.data.TributeTuple;


public class LiquidSalaryHelper {
    private Double minSalary = 1045.00;

    private TributeTuple[] inssQuotas = new TributeTuple[]{new TributeTuple(0.075, 0.00),
            new TributeTuple(0.09, 15.67),
            new TributeTuple(0.12, 78.36),
            new TributeTuple(0.14, 141.05)};

    private TributeTuple[] irrfQuotas = {new TributeTuple(0.0, 0.00),
            new TributeTuple(0.075, 142.80),
            new TributeTuple(0.15, 354.80),
            new TributeTuple(0.225, 636.13),
            new TributeTuple(0.275, 869.36)};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Double calculateINSS(Double salary) {
        Double applicableAliquot, applicableDeduction, inss;
        Double maxInssContributionLimit = 713.10;

        Range<Double> firstAliquotRange = Range.create(0.00, this.minSalary);
        Range<Double> secondAliquotRange = Range.create(this.minSalary, 2089.63);
        Range<Double> thirdAliquotRange = Range.create(2089.63, 3134.40);

        if(firstAliquotRange.contains(salary)) {
            applicableAliquot = this.inssQuotas[0].aliquot;
            applicableDeduction = this.inssQuotas[0].deduction;
        } else if(secondAliquotRange.contains(salary)) {
            applicableAliquot = this.inssQuotas[1].aliquot;
            applicableDeduction = this.inssQuotas[1].deduction;
        } else if(thirdAliquotRange.contains(salary)) {
            applicableAliquot = this.inssQuotas[2].aliquot;
            applicableDeduction = this.inssQuotas[2].deduction;
        } else {
            applicableAliquot = this.inssQuotas[3].aliquot;
            applicableDeduction = this.inssQuotas[3].deduction;
        }

        inss = (applicableAliquot * salary - applicableDeduction);

        return inss > maxInssContributionLimit ? maxInssContributionLimit : inss;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Double calculateIRRF(Double salary, Double inss, Integer numDependents) {
        Double applicableAliquot, applicableDeduction, irrf;
        Double calcBase = salary - inss - (numDependents * 189.59);

        Range<Double> firstAliquotRange = Range.create(0.00, 1903.98);
        Range<Double> secondAliquotRange = Range.create(1903.99, 2826.65);
        Range<Double> thirdAliquotRange = Range.create(2826.65, 3751.05);
        Range<Double> fourthAliquotRange = Range.create(3751.05, 4664.68);

        if(firstAliquotRange.contains(calcBase)) {
            applicableAliquot = this.irrfQuotas[0].aliquot;
            applicableDeduction = this.irrfQuotas[0].deduction;
        } else if(secondAliquotRange.contains(calcBase)) {
            applicableAliquot = this.irrfQuotas[1].aliquot;
            applicableDeduction = this.irrfQuotas[1].deduction;
        } else if(thirdAliquotRange.contains(calcBase)) {
            applicableAliquot = this.irrfQuotas[2].aliquot;
            applicableDeduction = this.irrfQuotas[2].deduction;
        } else if(fourthAliquotRange.contains(calcBase)) {
            applicableAliquot = this.irrfQuotas[3].aliquot;
            applicableDeduction = this.irrfQuotas[3].deduction;
        } else {
            applicableAliquot = this.irrfQuotas[4].aliquot;
            applicableDeduction = this.irrfQuotas[4].deduction;
        }

        irrf = (applicableAliquot * salary - applicableDeduction);

        return irrf;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SalaryInfo calculateLiquidSalary(SalaryInfo salaryInfo) {
        Double inss = this.calculateINSS(salaryInfo.bruteSalary);
        Double irrf = this.calculateIRRF(salaryInfo.bruteSalary, inss, salaryInfo.numberOfDependents);

        salaryInfo.liquidSalary = salaryInfo.bruteSalary - inss - irrf - salaryInfo.otherDeductions;
        salaryInfo.inss = inss;
        salaryInfo.irrf = irrf;

        return salaryInfo;
    }
}
