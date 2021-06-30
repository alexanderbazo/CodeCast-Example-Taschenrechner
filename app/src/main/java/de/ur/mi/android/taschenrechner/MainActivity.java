package de.ur.mi.android.taschenrechner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.display.Display;
import de.ur.mi.android.taschenrechner.ui.numpad.Numpad;

public class MainActivity extends AppCompatActivity implements Numpad.NumpadListener {

    private Numpad numpad;
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_main);
        numpad = new Numpad(getApplicationContext(), findViewById(R.id.view_input_numpad), this);
        display = new Display(findViewById(R.id.text_output_term), findViewById(R.id.text_output_result));
    }

    @Override
    public void onNumberButtonPressed(Button button) {
        display.appendTerm(button.label);
    }

    @Override
    public void onOperatorButtonPressed(Button button) {
        display.appendTerm(button.label);
    }

    @Override
    public void onOperatorButtonOverwritten(Button button) {
        display.replaceLastOperator(button.label);
    }

    @Override
    public void onClearButtonPressed() {
        display.clear();
    }

    @Override
    public void onResultButtonPressed() {
        display.solve();
    }
}
