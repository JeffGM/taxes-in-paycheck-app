package com.jefflopes.calculadorasalarioliquido.utils;

import android.os.Build;
import android.util.Range;

import androidx.annotation.RequiresApi;

import com.jefflopes.calculadorasalarioliquido.data.CalculationResult;
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
            new TributeTuple(0.225, 636.13)};

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

    public Double calculateIRRF(Double salary, Integer numDependents) {}
    public CalculationResult calculateLiquidSalary(Double salary, Integer numDependents, Double otherDiscounts) {}
}
