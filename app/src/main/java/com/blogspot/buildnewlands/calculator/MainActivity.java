package com.blogspot.buildnewlands.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText operand1, operand2;
    private Button btnAddition, btnSubtraction, btnMultiplication, btnDivision, btnClear;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operand1 = (EditText)findViewById(R.id.operand1);
        operand2 = (EditText)findViewById(R.id.operand2);
        btnAddition = (Button)findViewById(R.id.btnAddition);
        btnSubtraction = (Button)findViewById(R.id.btnSubtraction);
        btnMultiplication = (Button)findViewById(R.id.btnMultiplication);
        btnDivision = (Button)findViewById(R.id.btnDivision);
        btnClear = (Button)findViewById(R.id.btnClear);
        txtResult = (TextView)findViewById(R.id.txtResult);

        // Addition +
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });

        // Subtraction -
        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(2);
            }
        });

        // Multiplication *
        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(3);
            }
        });

        // Division /
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(4);
            }
        });

        // Clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand1.setText("");
                operand2.setText("");
                txtResult.setText("0.00");
                operand1.requestFocus();
            }
        });
    }

    public void check(int oper){
        if((operand1.getText().length()>0) && (operand2.getText().length()>0)){
            // You have to use BigDecimal, double operation is not accurate
            BigDecimal oper1 = new BigDecimal(operand1.getText().toString());
            BigDecimal oper2 = new BigDecimal(operand2.getText().toString());

            switch (oper){
                case 1 : // +
                    txtResult.setText(Double.toString(oper1.add(oper2).doubleValue()));
                    break;
                case 2 : // -
                    txtResult.setText(Double.toString(oper1.subtract(oper2).doubleValue()));
                    break;
                case 3 : // *
                    txtResult.setText(Double.toString(oper1.multiply(oper2).doubleValue()));
                    break;
                case 4 : // /
                    if(oper2.compareTo(new BigDecimal(0))==0) // compare to 0
                        Toast.makeText(MainActivity.this, "Division by zero is undefined", Toast.LENGTH_LONG).show();
                    else
                        txtResult.setText(Double.toString(oper1.divide(oper2,10,BigDecimal.ROUND_HALF_UP).doubleValue()));
                    break;
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Please enter numbers in both operand fields", Toast.LENGTH_LONG).show();
        }
    }
}
