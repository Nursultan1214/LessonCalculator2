package com.example.lesson27;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView number;
    public TextView result;
    public TextView operation;
    private Double operand = null;
    private String lastOperation = "=";
    private Button shape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        number = findViewById(R.id.tv_number);
        operation = findViewById(R.id.tv_operation);
        result = findViewById(R.id.tv_result);
        shape = findViewById(R.id.b_click);

        btnIntent();

    }
    private void btnIntent() {
        shape.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            String text = result.getText().toString();
            intent.putExtra("key",text);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        operation.setText(lastOperation);
        //setOnClickListener
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        number.append(button.getText());
        shape.setVisibility(View.GONE);
        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String num = number.getText().toString();
        shape.setVisibility(View.GONE);
        if (view.getId() == R.id.btnEqual) {
            shape.setVisibility(View.VISIBLE);
        }
        if (num.length() > 0 ) {
            num = num.replace(',', '.');
            try {
                preferenceOperation(Double.valueOf(num), op);
            } catch (NumberFormatException exception) {
                number.setText(lastOperation);
            }
        }
        lastOperation = op;
        operation.setText(lastOperation);
    }

    @SuppressLint("SetTextI18n")
    private void preferenceOperation(Double num, String op) {
        if (operand == null) {
            operand = num;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = op;
            }
            switch (lastOperation) {
                case "=":
                    operand = num;
                    break;
                case "/":
                    if (num == 0) {
                        operand = 0.0;
                    } else {
                        operand /= num;
                    }
                    break;
                case "*":
                    operand *= num;
                    break;
                case "+":
                    operand += num;
                    break;
                case "-":
                    operand -= num;
                    break;
                case "AC":
                    number.setText("0");
                    operation.setText("0");
                    result.setText("0");
                    operand = null;
                    break;

            }
        }
        assert operand != null;
        result.setText(operand.toString().replace('.', ','));
        number.setText("");
    }

    public void finishClick(View view) {
    }
}