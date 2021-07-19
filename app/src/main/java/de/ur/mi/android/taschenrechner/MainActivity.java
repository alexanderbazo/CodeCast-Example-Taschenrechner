package de.ur.mi.android.taschenrechner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.display.Display;
import de.ur.mi.android.taschenrechner.ui.numpad.Numpad;

/*
 * Taschenrechner-App
 *
 * Diese App stellt Nutzer*innen einen einfachen Taschenrechner zur Verfügung. Über ein Tastenfeld
 * können mathematische Gleichungen eingegeben und gelöst werden. Der Quellcode ist grob in zwei
 * Bereiche aufgeteilt. In Package "numpad" befinden sich die Klassen, die zur Darstellung und
 * Steuerung des Tastenfelds notwendig sind. Über die Klasse "Display" werden die Eingaben der
 * Nutzer*innen und die daraus resultierenden Ergebnisse angezeigt.
 *
 * Für die Darstellung des Tastenfelds wird ein RecyclerView verwendet, dessen Inhalte über einen
 * entsprechende Adapter und eine ViewHolder-Klasse konfiguriert und eingebunden wird. Für die
 * Definition der verwendeten Buttons werden in diesem Kontext zwei Enums genutzt, die die möglichen
 * Schaltflächen festlegen und über die diese in verschiedene Kategorien, z.B. Ziffern oder
 * Operatoren eingeteilt werden.
 *
 * Die Durchführung der mathematischen Berechnungen erfolgt über die externe Bibliothek "exp4j". Diese
 * wird als Gradle-Dependency in das Projekt integriert und in der Klasse "CalculatorHelper" zur
 * Auswertung der eingegebenen Ausdrücke verwendet. Dazu wird aus den Eingaben der Nutzer*innen ein
 * valider, mathematischer Ausdruck zusammengesetzt und bei Bedarf über die "Expression"-Klasse
 * der "exp4j"-Bibliothek ausgewertet. Das so gewonnenen Ergebnis wird dann in der App angezeigt.
 */
public class MainActivity extends AppCompatActivity implements Numpad.NumpadListener {

    /*
     * Taschenrechner-App
     *
     * Bei der Umsetzung der App wurde versucht, alle notwendigen Funktionen in separaten, inhaltlich
     * getrennten Komponenten der Anwendung zu realisieren. Die zentrale Activity der Anwendung
     * dient als Einstiegspunkt und initialisiert die übrigen Bereiche der App. Dabei handelt es sich
     * um die Instanzen der beiden Klassen, die zur Darstellung bzw. Steuerung des Eingabefelds
     * (Numpad) bzw. zum Ausgabebereich (Display) verwendet werden. Im Anschluss fungiert die
     * Activity als Mediator bzw. Vermittler zwischen diesen beiden Komponenten, in dem sie Events
     * abfängt und diese ggf. in Form konkreter Anweisungen an die jeweils andere Komponenten
     * weitergibt. Beide Komponenten werden in privaten Instanzvariablen der Activity gehalten.
     */
    private Numpad numpad;
    private Display display;

    /*
     * Initialisierung der App
     *
     * Die Initialisierung der App beginnt in der Lifecycle-Methode "onCreate" durch den Aufruf der
     * Methode initUI. Das eigentliche Setup der verschiedenen UI-Komponenten, der Instanzen der
     * beiden Klassen Numpad und Display,  erfolgt in dieser ausgelagerten Methode.
     * "onCreate" wird automatisch im Rahmen des Startvorgangs der App aufgerufen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    /*
     *  Initialisierung der App
     *
     * Die Methode "initUI" initialisiert die beiden UI-Komponenten Numpad und Display. Beide Klassen
     * müssen die jeweils relevanten UI-Elemente, z.B. das RecylcerView mit den Tasten zur Eingabe
     * von Ziffern und Operatoren kennen, um ihre Aufgaben korrekt erfüllen zu können. Die
     * Referenzierung dieser Elemente erfolgt bereits auf Ebene der Activity. Den Konstruktoren beider
     * Komponenten werden die so gefundenen View-Instanzen direkt übergeben. Dadurch benötigen beide
     * Klassen keinen direkten Zugriff auf das Layout der Activity. Die von oben nach unten weiter zu
     * gebenden Informationen werden so auf das notwendigste reduziert. So kann u.a. vermieden werden,
     * dass aus den beiden Klassen ungewollt auf die Bereiche des UIs zugegriffen wird, die von der
     * jeweils anderen Komponenten verwaltet werden. Um die im Kontext des Tastenfelds auftretenden
     * Eingaben der Nutzer*innen in anderen Komponenten abfangen zu können, stellt die Klasse eine
     * Listener-Schnittstelle zur Verfügung. Diese Activity implementiert das notwendige Interface
     * und übergibt im Konstruktor der Numpad-Klasse eine entsprechende Selbstreferenz, um sich so
     * bei dieser als Listener zu registrieren. Die notwendigen Callback-Methoden werden auf
     * Klassenebene implementiert.
     */
    private void initUI() {
        setContentView(R.layout.activity_main);
        numpad = new Numpad(findViewById(R.id.view_input_numpad), this);
        display = new Display(findViewById(R.id.text_output_term), findViewById(R.id.text_output_result));
    }

    /*
     * Handling der Eingabe-Events aus der Numpad-Klasse
     *
     * Diese Methode wird automatisch aufgerufen, wenn innerhalb des Numpads bemerkt wurde, dass die
     * Nutzer*innen einer der Buttons betätigt wurde, über den neue Ziffer eingegeben werden können.
     * Die Numpad-Klasse kommuniziert die jeweils gedrückte Schaltfläche über die Übergabe des
     * korrespondierenden Enum-Werts als Metodenparameter. Die Activity gibt diese Eingabe an die
     * Display-Klasse weiter und wählt dazu das Label, d.h. die auf dem Button dargestellte Ziffer,
     * aus. Im Display wird die Ziffer zur Aktualisierung des aktuellen Terms, zur Berechnung des
     * neuen Ergebnis und zur Darstellung dieser beiden Inhalte verwendet.
     */
    @Override
    public void onNumberButtonPressed(Button button) {
        display.appendTerm(button.label);
    }

    /*
     * Handling der Eingabe-Events aus der Numpad-Klasse
     *
     * Genau wie in der onNumberButtonPressed-Methode werden auch hier die aus der Numpad-Klasse
     * eingehenden Events verarbeitet. Der Methode wird beim Aufruf der passende Enum-Wert als
     * Parameter übergeben, mit dem der ausgewählten Operator repräsentiert wird. Dieser wird dann zur
     * Aktualisierung des aktuellen Terms bzw. dessen Darstellung an die Display-Klasse übergeben.
     */
    @Override
    public void onOperatorButtonPressed(Button button) {
        display.appendTerm(button.label);
    }

    /*
     * Handling der Eingabe-Events aus der Numpad-Klasse
     *
     * In dieser Methode wird ein besonderes Eingabe-Event verarbeitet, dass aus der Numpad-Klasse
     * an die Activity (als Listener) kommuniziert wird. Geben die Nutzer*innen direkt nacheinander
     * zwei Operatoren ein, interpretiert die App dies als Wunsch, den zuletzt ausgewählten Operator
     * zu überschreiben. Der neue Operator wird von der Activity an die Display-Klasse weitergegeben,
     * die den zuletzt eingegebenen Operator aus dem Term und der Anzeige entfernt und durch den
     * neuen ersetzt.
     */
    @Override
    public void onOperatorButtonOverwritten(Button button) {
        display.replaceLastOperator(button.label);
    }

    /*
     * Handling der Eingabe-Events aus der Numpad-Klasse
     *
     * In dieser Methode wird das Ereignis verarbeitet, mit dem die Numpad-Klasse das Drücken der
     * "C"-Taste durch die Nutzer*innen kommuniziert. In diesem Fall gibt die Activity der Display-
     * Klasse den Befehl, den aktuell angezeigten Term zurückzusetzen und den von der Klasse verwalteten
     * Bereich des UIs entsprechend zu aktualisieren.
     */
    @Override
    public void onClearButtonPressed() {
        display.clear();
    }

    /*
     * Handling der Eingabe-Events aus der Numpad-Klasse
     *
     * Drücken die Nutzer*innen auf die "="-Taste, gibt die Activity als Reaktion auf das hier
     * abgefangenen Event der Display-Klasse die Anweisung zur Auflösung des aktuellen Terms und zur
     * Anzeige des entsprechenden Ergebnis.
     */
    @Override
    public void onResultButtonPressed() {
        display.solve();
    }
}
