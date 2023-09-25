package com.cs407.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber1;
    private EditText editTextNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('+');
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('-');
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('*');
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('/');
            }
        });
    }

    private void performCalculation(char operator) {
        String strNum1 = editTextNumber1.getText().toString();
        String strNum2 = editTextNumber2.getText().toString();

        if (TextUtils.isEmpty(strNum1) || TextUtils.isEmpty(strNum2)) {
            Toast.makeText(this, "Please enter both numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        int num1;
        int num2;

        try {
            num1 = Integer.parseInt(strNum1);
            num2 = Integer.parseInt(strNum2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid integers.", Toast.LENGTH_SHORT).show();
            return;
        }

        int result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    Toast.makeText(this, "Division by zero is not allowed.", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        // Start the ResultActivity and pass the result
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
