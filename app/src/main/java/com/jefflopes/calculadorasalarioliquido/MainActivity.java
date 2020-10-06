package com.jefflopes.calculadorasalarioliquido;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.jefflopes.calculadorasalarioliquido.data.SalaryInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        EditText bruteSalaryEditText = findViewById(R.id.bruteSalaryEditText);
        EditText numDependentsEditText = findViewById(R.id.dependentNumberEditText);
        EditText otherDeductionsEditText = findViewById(R.id.otherDeductionsEditText);

        Double bruteSalary = 0.00;
        Integer numDependents = 0;
        Double otherDeductions = 0.00;

        String bruteSalaryText = bruteSalaryEditText.getText().toString();
        String numDependentsText = numDependentsEditText.getText().toString();
        String otherDeductionsText = otherDeductionsEditText.getText().toString();

        if(!TextUtils.isEmpty(bruteSalaryText))
            bruteSalary = Double.parseDouble(bruteSalaryText);

        if(!TextUtils.isEmpty(numDependentsText))
            numDependents = Integer.parseInt(numDependentsText);

        if(!TextUtils.isEmpty(otherDeductionsText))
            otherDeductions = Double.parseDouble(otherDeductionsText);

        if(bruteSalary != 0.00) {
            SalaryInfo salaryInfo = new SalaryInfo(bruteSalary, numDependents, otherDeductions);
            Intent calculateIntent = new Intent(this, ResultActivity.class);

            calculateIntent.putExtra("SALARY_INFO", (new Gson()).toJson(salaryInfo));
            startActivity(calculateIntent);
        } else {
            Toast.makeText(this, R.string.without_salary_input, Toast.LENGTH_SHORT).show();
        }

    }
}
