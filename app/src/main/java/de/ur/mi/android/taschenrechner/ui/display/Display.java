package de.ur.mi.android.taschenrechner.ui.display;

import android.widget.TextView;

import de.ur.mi.android.taschenrechner.helper.CalculatorHelper;


/*
@codecast 6. Die Display-Klasse | 1. Übersicht
@url https://tobiasdollhofer.de/TaschenrechnerCast/06-01-Overview.mp3
*/
public class Display {


    /*
    @codecast 6. Die Display-Klasse | 2. Instanzvariablen
    @url https://tobiasdollhofer.de/TaschenrechnerCast/06-02-Variables.mp3
    */
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

    /*
    @codecast 6. Die Display-Klasse | 3. Anpassen des aktuellen Terms
    @url https://tobiasdollhofer.de/TaschenrechnerCast/06-03-AddToTerm.mp3
    */
    public void appendTerm(String term) {
        currentTerm = currentTerm.concat(term);
        update(false);
    }


    /*
    @codecast 6. Die Display-Klasse | 4. Operatoren überschreiben
    @url https://tobiasdollhofer.de/TaschenrechnerCast/06-04-OverrideOperators.mp3
    */
    public void replaceLastOperator(String operator) {
        currentTerm = currentTerm.substring(0, currentTerm.length() - 1).concat(operator);
        update(false);
    }

    /*
    @codecast 6. Die Display-Klasse | 5. Zurücksetzen des Displays
    @url https://tobiasdollhofer.de/TaschenrechnerCast/06-05-Reset.mp3
    */
    public void clear() {
        currentTerm = DEFAULT_TERM;
        update(false);
    }

    /*
    @codecast 6. Die Display-Klasse | 7. Anzeigen des Ergebnis
    @url https://tobiasdollhofer.de/TaschenrechnerCast/06-06-ShowResults.mp3
    */
    public void solve() {
        update(true);
    }
    
    /*
    @codecast 6. Die Display-Klasse | 8. Aktualisieren der Anzeige
    @url https://tobiasdollhofer.de/TaschenrechnerCast/06-07-Update.mp3
    */
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
