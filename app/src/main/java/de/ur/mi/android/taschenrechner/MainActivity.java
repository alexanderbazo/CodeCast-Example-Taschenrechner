package de.ur.mi.android.taschenrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.ur.mi.android.taschenrechner.helper.CalculatorHelper;

public class MainActivity extends AppCompatActivity {

    private TextView tvExpression;

    private int[] normalButtons = { R.id.btn_zero, R.id.btn_one, R.id.btn_two, R.id.btn_three,
            R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_seven, R.id.btn_eight, R.id.btn_nine,
            R.id.btn_dot, R.id.btn_multiply, R.id.btn_divide, R.id.btn_add, R.id.btn_subtract
    };

    private Button btnClear, btnEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
        setClickListeners();

    }

    private void initUI() {
        setContentView(R.layout.activity_main);

        tvExpression = findViewById(R.id.tv_expression);

        btnClear = findViewById(R.id.btn_clear);
        btnEquals = findViewById(R.id.btn_equals);
    }

    private void setClickListeners() {

        //ClickListener für alle numerischen Buttons
        for (int numericButtonId : normalButtons) {
            findViewById(numericButtonId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumericButtonClicked(v);
                }
            });
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearButtonClicked();
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsButtonClicked();
            }
        });

    }

    private void onEqualsButtonClicked() {
        String expression = tvExpression.getText().toString();
        if (!expression.isEmpty()) {
            String result = CalculatorHelper.calculate(expression);
            tvExpression.setText(result);
        }
    }

    private void onClearButtonClicked() {
        tvExpression.setText("");
    }

    private void onNumericButtonClicked(View v) {
        String currentExpression = tvExpression.getText().toString();
        Button clickedButton = (Button) v;
        String newExpression = currentExpression + clickedButton.getText();
        tvExpression.setText(newExpression);
    }

}
