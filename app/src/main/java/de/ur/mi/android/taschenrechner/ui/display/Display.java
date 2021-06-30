package de.ur.mi.android.taschenrechner.ui.display;

import android.widget.TextView;

import de.ur.mi.android.taschenrechner.helper.CalculatorHelper;

public class Display {

    private static final String DEFAULT_TERM = "";

    private TextView termView;
    private TextView resultView;
    private String currentTerm;

    public Display(TextView termView, TextView resultView) {
        this.termView = termView;
        this.resultView = resultView;
        currentTerm = DEFAULT_TERM;
        update(false);
    }

    public void appendTerm(String term) {
        currentTerm = currentTerm.concat(term);
        update(false);
    }

    public void replaceLastOperator(String operator) {
        currentTerm = currentTerm.substring(0, currentTerm.length() - 1).concat(operator);
        update(false);
    }

    public void clear() {
        currentTerm = DEFAULT_TERM;
        update(false);
    }

    public void solve() {
        update(true);
    }

    private void update(boolean clearTerm) {
        termView.setText(currentTerm);
        if (currentTerm.equals(DEFAULT_TERM)) {
            resultView.setText(DEFAULT_TERM);
            return;
        }
        if (!Character.isDigit(currentTerm.charAt(currentTerm.length() - 1))) {
            return;
        }
        resultView.setText(CalculatorHelper.calculate(currentTerm));
        if (clearTerm) {
            currentTerm = resultView.getText().toString();
            termView.setText(currentTerm);
            resultView.setText(DEFAULT_TERM);
        }
    }

}
