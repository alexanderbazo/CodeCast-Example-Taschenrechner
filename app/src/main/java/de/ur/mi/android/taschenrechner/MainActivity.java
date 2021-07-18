package de.ur.mi.android.taschenrechner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.display.Display;
import de.ur.mi.android.taschenrechner.ui.numpad.Numpad;

/*
 @codecast 1. Übersicht | 1. Zweck und Aufbau der Anwendung
 @url https://tobiasdollhofer.de/TaschenrechnerCast/01-01-Intro.mp3
 */
public class MainActivity extends AppCompatActivity implements Numpad.NumpadListener {

    /*
     @codecast 1. Übersicht | 2. Die zentrale Activity und die übrigen Komponenten
     @url https://tobiasdollhofer.de/TaschenrechnerCast/01-02-Activity.mp3
     */
    private Numpad numpad;
    private Display display;

    /*
     @codecast 2. Initialisierung der App | 1. Start der Initialisierung
     @url https://tobiasdollhofer.de/TaschenrechnerCast/02-01-OnCreate.mp3
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    /*
    @codecast 2. Initialisierung der App | 2. Initialisierung der UI-Klassen
    @url https://tobiasdollhofer.de/TaschenrechnerCast/02-02-UI-Klassen.mp3
    */
    private void initUI() {
        setContentView(R.layout.activity_main);
        numpad = new Numpad(findViewById(R.id.view_input_numpad), this);
        display = new Display(findViewById(R.id.text_output_term), findViewById(R.id.text_output_result));
    }


    /*
    @codecast 3. Handling der Eingabe-Events | 1. Eingabe von Ziffern
    @url https://tobiasdollhofer.de/TaschenrechnerCast/03-01-Numbers.mp3
    */
    @Override
    public void onNumberButtonPressed(Button button) {
        display.appendTerm(button.label);
    }


    /*
    @codecast 3. Handling der Eingabe-Events | 2. Eingabe von Operatoren
    @url https://tobiasdollhofer.de/TaschenrechnerCast/03-02-Operators.mp3
    */
    @Override
    public void onOperatorButtonPressed(Button button) {
        display.appendTerm(button.label);
    }


    /*
    @codecast 3. Handling der Eingabe-Events | 3. Überschreiben von Operatoren
    @url https://tobiasdollhofer.de/TaschenrechnerCast/03-03-Override.mp3
   */
    @Override
    public void onOperatorButtonOverwritten(Button button) {
        display.replaceLastOperator(button.label);
    }


    /*
    @codecast 3. Handling der Eingabe-Events | 4. Zurücksetzten des UIs
    @url https://tobiasdollhofer.de/TaschenrechnerCast/03-04-Clear.mp3
    */
    @Override
    public void onClearButtonPressed() {
        display.clear();
    }

    /*
    @codecast 3. Handling der Eingabe-Events | 5. Auflösen des Ausdrucks
    @url https://tobiasdollhofer.de/TaschenrechnerCast/03-05-Results.mp3
    */
    @Override
    public void onResultButtonPressed() {
        display.solve();
    }
}
