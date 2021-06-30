package de.ur.mi.android.taschenrechner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.display.Display;
import de.ur.mi.android.taschenrechner.ui.numpad.Numpad;

/**
 * Zentrale Activity der Taschenrechner-App
 *
 * In dieser Activity werden die beiden UI-Komponenten der App initialisiert und gesteuert. Die
 * Activity fungiert dabei als Vermittler zwischen dem Tastenfeld (Numpad) zur Eingabe der
 * mathematischen Terme und den Textfeldern (Display) zur Ausgabe der jeweiligen Ergebnisse.
 *
 * Numpad: Verwaltet das Tastenfeld und informiert diese Activity über die Buttons, die von den
 * Nutzer*innen gedrückt wurden. Diese Informationen werden an das Display zur Darstellung von
 * Term und Ergebnis weitergegeben.
 *
 * Display: Verwaltet die beiden Textfelder zur Anzeige des aktuellen Terms und des entsprechenden
 * Ergebnisses. Hier wird der eingegebene Term mithilfe des CalculatorHelper in das korrekte
 * Ergebnis umgewandelt.
 */
public class MainActivity extends AppCompatActivity implements Numpad.NumpadListener {

    private Numpad numpad; // Selbsterstelle Klasse, die das Handling des Tastenfelds kapselt
    private Display display; // Selbsterstelle Klasse, die das Handling der TextViews kapselt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    /*
     * Initialisiert die beiden UI-Komponenten Numpad und Display. Die dort notwendigen Views werden
     * auf Ebene der Acitivity selektiert (findViewById) und als Parameter and die Konstruktoren der
     * jeweiligen Elemente weitergegeben.
     */
    private void initUI() {
        setContentView(R.layout.activity_main);
        numpad = new Numpad(getApplicationContext(), findViewById(R.id.view_input_numpad), this);
        display = new Display(findViewById(R.id.text_output_term), findViewById(R.id.text_output_result));
    }

    /*
     * Callback-Methode aus dem Numpad, die aufgerufen wird, wenn über das Tastenfeld eine Zahl
     * eingegeben wird.
     */
    @Override
    public void onNumberButtonPressed(Button button) {
        display.appendTerm(button.label);
    }

    /*
     * Callback-Methode aus dem Numpad, die aufgerufen wird, wenn über das Tastenfeld ein Operator
     * (+,-,*,/ oder .) eingegeben wird.
     */
    @Override
    public void onOperatorButtonPressed(Button button) {
        display.appendTerm(button.label);
    }

    /*
     * Callback-Methode aus dem Numpad, die aufgerufen wird, wenn über das Tastenfeld direkt nach
     * einem Operator erneut ein solcher eingegeben wird. Durch das separate Handling dieses
     * besonderen Falls wird vermieden, dass ungültige Terme mit direkt aufeinander folgenden
     * Operatoren entstehen.
     */
    @Override
    public void onOperatorButtonOverwritten(Button button) {
        display.replaceLastOperator(button.label);
    }

    /*
     * Callback-Methode aus dem Numpad, die aufgerufen wird, wenn auf dem Tastenfeld die C-Taste
     * (clear) gedrückt wird.
     */
    @Override
    public void onClearButtonPressed() {
        display.clear();
    }

    /*
     * Callback-Methode aus dem Numpad, die aufgerufen wird, wenn auf dem Tastenfeld die Gleich-Taste
     * (=) gedrückt wird.
     */
    @Override
    public void onResultButtonPressed() {
        display.solve();
    }
}
