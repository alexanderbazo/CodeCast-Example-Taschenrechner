package de.ur.mi.android.taschenrechner.ui.display;

import android.widget.TextView;

import de.ur.mi.android.taschenrechner.helper.CalculatorHelper;

/**
 * Diese Klasse verwaltet bzw. repräsentiert das Display des Taschenrechners. Über öffentliche
 * Methoden kann der angezeigte Term erweitert, gelöscht und gelöst werden.
 */
public class Display {

    private static final String DEFAULT_TERM = ""; // Inhalt für ein "leeres" Display

    private TextView termView; // TextView zur Darstellung des aktuellen Terms
    private TextView resultView; // TextView zur Darstellung des ausgewerteten Terms (= Ergebnis)
    private String currentTerm; // Zwischenspeicher des aktuellen Term als String

    public Display(TextView termView, TextView resultView) {
        this.termView = termView;
        this.resultView = resultView;
        currentTerm = DEFAULT_TERM;
        update(false);
    }

    /*
     * Hängt den angegebenen String an den Term an und aktualisiert das UI mit dem neuen Term und
     * dessen Ergebnis.
     */
    public void appendTerm(String term) {
        currentTerm = currentTerm.concat(term);
        update(false);
    }

    /*
     * Ersetzt das letzte Zeichen des aktuellen Terms mit dem angegebenen String und aktualisiert
     * das UI mit dem neuen Term und dessen Ergebnis.
     */
    public void replaceLastOperator(String operator) {
        currentTerm = currentTerm.substring(0, currentTerm.length() - 1).concat(operator);
        update(false);
    }

    /*
     * Setzt den aktuellen Term zurück (Term wird auf einen leeren String gesetzt) und aktualisiert
     * das UI mit dem neuen, leeren Term und dessen (leeren) Ergebnis.
     */
    public void clear() {
        currentTerm = DEFAULT_TERM;
        update(false);
    }

    /*
     * Löst den aktuellen Term und zeigt das Ergebnis im UI ein. Der aktuell angezeigte Term wird
     * dabei ebenfalls durch das berechnete Ergebnis ersetzt.
     */
    public void solve() {
        update(true);
    }

    /*
     * Aktualisiert das UI des Displays auf Basis des Terms, der aktuell in der Variable currentTerm
     * gespeichert ist. Falls für den Parameter clearTerm der Wert true übergeben wird, wird der
     * aktuell angezeigte Term auf dessen Ergebnis reduziert (aus 5*5 wird 25) und der Inhalt des
     * Ergebnisfelds entfernt.
     *
     */
    private void update(boolean clearTerm) {
        // Anzeigen des aktuellen Terms im UI
        termView.setText(currentTerm);
        // Wenn der aktuelle Term leer ist, müssen keine Ergebnisse berechnet werden
        if (currentTerm.equals(DEFAULT_TERM)) {
            resultView.setText(DEFAULT_TERM);
            return;
        }
        // Wenn an letzter Stelle des Terms ein Operator steht, kann dieser nicht ausgewertet werden
        if (!Character.isDigit(currentTerm.charAt(currentTerm.length() - 1))) {
            return;
        }
        // Auswerten des Terms und anzeigen des Ergebnis im UI
        resultView.setText(CalculatorHelper.calculate(currentTerm));
        // Behandlen des besonderen Falls, wenn der Term vollständig aufgelöst werden soll
        if (clearTerm) {
            currentTerm = resultView.getText().toString();
            termView.setText(currentTerm);
            resultView.setText(DEFAULT_TERM);
        }
    }

}
