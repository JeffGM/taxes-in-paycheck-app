package com.jefflopes.calculadorasalarioliquido;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jefflopes.calculadorasalarioliquido.data.SalaryInfo;
import com.jefflopes.calculadorasalarioliquido.utils.LiquidSalaryHelper;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent calculateIntent = getIntent();
        String serializedObject = calculateIntent.getStringExtra("SALARY_INFO");
        SalaryInfo salaryInfo = (new Gson()).fromJson(serializedObject, SalaryInfo.class);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        LiquidSalaryHelper liquidSalaryHelper = new LiquidSalaryHelper();

        salaryInfo = liquidSalaryHelper.calculateLiquidSalary(salaryInfo);

        TextView bruteSalaryTextView = findViewById(R.id.bruteSalaryLabel);
        bruteSalaryTextView.setText(getString(R.string.currency_brl_sign) + " " + decimalFormat.format(salaryInfo.bruteSalary));

        TextView inssTextView = findViewById(R.id.INSSLabel);
        inssTextView.setText(getString(R.string.currency_brl_sign) + " "  + decimalFormat.format(salaryInfo.inss));

        TextView irrfTextView = findViewById(R.id.IRRFLabel);
        irrfTextView.setText(getString(R.string.currency_brl_sign) + " "  + decimalFormat.format(salaryInfo.irrf));

        TextView otherDiscountsTextView = findViewById(R.id.OtherDiscountsLabel);
        otherDiscountsTextView.setText(getString(R.string.currency_brl_sign) + " "  + decimalFormat.format(salaryInfo.otherDeductions));

        TextView liquidSalaryTextView = findViewById(R.id.liquidSalaryLabel);
        liquidSalaryTextView.setText(getString(R.string.currency_brl_sign) + " "  + decimalFormat.format(salaryInfo.liquidSalary));

        TextView deductionsTextView = findViewById(R.id.deductionsPercentageLabel);
        deductionsTextView.setText(decimalFormat.format(salaryInfo.getDeductionsInPercentage()) + "%");
    }
}
