package de.ur.mi.android.taschenrechner.ui.display;

import android.widget.TextView;

import de.ur.mi.android.taschenrechner.helper.CalculatorHelper;

/*
 * Die Display-Klasse
 *
 * In dieser Klasse werden alle Funktionen implementiert, die zur Darstellung des von den Nutzer*innen
 * eingegebenen Terms und dessen Ergebnis benötigt werden. Eine Instanz der Klasse wird innerhalb der
 * Activity erstellt. Dabei werden Referenzen auf die beiden benötigten UI-Elemente, zwei TextViews
 * zur Anzeige des eingegebenen Terms und dessen Ergebnis übergeben. Die Display-Klasse greift auf
 * die "CalculatorHelper"-Klasse (und damit indirekt auf die "ex4j"-Bibliothek zu, um aus dem
 * eingegebenen Term das korrekte Ergebnis zu berechnen. Diese Berechnung findet nicht in der Display-
 * Klasse statt, die sich nur um die Verarbeitung der Eingaben bzw. der Darstellung des aktuellen
 * Zustands der App kümmert. Die Activity übergibt die eingegebenen Ziffern und Operatoren an die
 * Display-Klasse, die den daraus resultierenden Term in einer Instanzvariable als String zwischen-
 * speichert und nach jeder relevanten Eingabe ein neues Ergebnis berechnen lässt un dieses dann
 * im User Interface darstellt. Gleiches gilt für das Auflösen bzw. Löschen der Term: Erfolgt eine
 * entsprechende Eingabe der Nutzer*innen über das Tastenfeld, wird diese in der Activity abgefangen
 * und an das Display weitergegeben, dass dann Term und angezeigtes Ergebnis entsprechend anpasst.
 */
public class Display {

    /*
     * Die Display-Klasse: Instanzvariablen
     *
     * Über Instanzvariablen werden in der Display-Klasse die wichtigsten UI-Elemente und Zwischenwerte
     * festgehalten, die die Instanzen zur Erfüllung ihrer Aufgaben benötigen. Dazu gehört auch eine
     * Konstante, in der eine (leere) Zeichenkette gespeichert ist, die als Standardinhalt für
     * die Ausgabe des Display verwendet wird. Solange keine Ziffern oder Operatoren durch die
     * Nutzer*innen eingeben wurden, wird dieser Text angezeigt, der ebenfalls im entsprechenden
     * View eingetragen wird, wenn die Nutzer*innen den Rechner über die entsprechende Taste zurücksetzen.
     * In weiteren Variablen werden die beiden UI-Elemente gespeichert, die über den Konstruktor an
     * die Instanzen der Display-Klasse übergeben werden, und die zur Darstellung des aktuellen Terms
     * sowie des berechneten Ergebnis verwendet werden. In der Instanzvariable "currentTerm" wird
     * der aktuelle Term zusätzlich auch als String zwischengespeichert. Alle Änderungen am Term
     * sowie die Berechnung des Ergebnis erfolgt immer auf Basis dieser Variable. Deren Inhalt dann
     * auch zur Aktualisierung des entsprechenden TextViews verwendet wird, das stets den aktuell
     * eingegebenen Term darstellen soll.
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
     * Die Display-Klasse: Verarbeiten von Eingaben und Aktualisierungen des UIs
     *
     * Über diese Methode wird ein weiteres Zeichen, eine Ziffer oder ein Operator, an den
     * aktuellen Term angehängt. Der neue, als Parameter an die Methode übergebene, Teil wird dazu
     * zuerst an die String-Variable angehängt, die als Zwischenspeicher des aktuellen Terms dient.
     * Im Anschluss wird die "update"-Methode aufgerufen, in der das Ergebnis des Terms berechnet und
     * im UI angezeigt wird. Die Auslagerung des zweiten Teils in eine separate Methode erfolgt auch
     * deshalb, da so das Aktualisieren der Anzeige auf Basis des angepassten Terms auch von anderen
     * Stellen innerhalb der Klasse aufgerufen werden kann. Die Aktualisierung des UIs erfolgt nach
     * der Eingabe der Nutzer*innen. Daher werden die verschiedenen Formen der Anpassung des Terms
     * und die allgemeine Berechnung und Darstellung des Ergebnis unabhängig voneinander implementiert.
     */
    public void appendTerm(String term) {
        currentTerm = currentTerm.concat(term);
        update(false);
    }

    /**
     * Die Display-Klasse: Verarbeiten von Eingaben
     *
     * Diese Methode wird von der Activity aufgerufen, wenn die Nutzer*innen den zuletzt eingegebenen
     * Operator überschreiben wollen. Dabei wird das letzte Zeichen im aktuellen Term durch den
     * neuen, als Parameter übergebenen Term überschrieben. Im Anschluss wird die in der "update"-
     * Methode generisch implementierte Aktualisierung des UIs ausgelöst.
     */
    public void replaceLastOperator(String operator) {
        currentTerm = currentTerm.substring(0, currentTerm.length() - 1).concat(operator);
        update(false);
    }

    /*
     * Die Display-Klasse: Verarbeiten von Eingaben
     *
     * Hier wird der aktuell angezeigte Term durch einen Standardwert, d.h. eine leere Zeichenkette,
     * ersetzt. Im Anschluss wird auch hier die in der "update"-Methode generisch implementierte
     * Aktualisierung des UIs ausgelöst.
     */
    public void clear() {
        currentTerm = DEFAULT_TERM;
        update(false);
    }

    /*
     * Die Display-Klasse: Verarbeiten von Eingaben
     *
     * Durch das Betätigen der "="-Taste lösen die Nutzer*innen das endgültige Auflösen des aktuellen
     * Terms aus. Dabei soll, abweichend von den anderen Eingaben, ein angepasste Verhalten des
     * Taschenrechners ausgeführt werden: Wie bei den übrigen Nutzeraktionen soll auch hier das Ergebnis
     * des aktuellen Terms berechnet und angezeigt werden. Im Anschluss and die Berechnung wird der
     * aktuell angezeigte Term durch dieses Ergebnis ersetzt. So kann, aufbauend auf dem berechneten Ergebnis,
     * ein neuer Rechenausdruck eingeben werden. Das Ergebnis fungiert dabei als erste Zahl des neuen
     * Terms. Auch diese Form der UI-Aktualisierung ist in der generischen "update"-Methode dieser
     * Klasse definiert. Um dieses, vom Standardablauf der Methode abweichenden, Verhalten auszulösen,
     * wird beim Aufruf der Methode "true" als Parameterwert übergeben.
     */
    public void solve() {
        update(true);
    }

    /*
     * Die Display-Klasse: Aktualisieren des UIs
     *
     * Hier wird der Bereich des UIs aktualisiert, für den die Display-Klasse zuständig ist. In der
     * Methode werden dazu zwei verschiedene Aktualisierungsvorgänge implementiert, die beim Aufrufen
     * der Methode durch die Auswahl des "clearTerm"-Parameters unterschieden werden können.
     *
     * Im Normalfall (für "clearTerm" wird der Wert false übergeben), wird auf Basis des aktuell in
     * der Variable "currentTerm" gespeicherten Rechenausdrucks dessen Ergebnis berechnet und im
     * entsprechenden Element des UIs angezeigt. Dazu wird der Term zuerst im TextView "termView",
     * der stets die aktuellste Fassung des eingegebenen Ausdrucks enthalten soll, angezeigt. Im
     * Anschluss wird geprüft, ob der aktuelle Term leer ist. Falls dies so ist, entfällt jede weitere
     * Berechnung und im "resultView", dass stets das aktuelle Ergebnis anzeigen soll, wird ebenfalls
     * eine leere Zeichenkette eingetragen und die Methode wird durch eine Returnanweisung beendet.
     * Befindet sich in der Variable "currentTerm" aber ein Ausdruck, wird zuerst geprüft, ob dieser
     * valide ist, d.h., ob über die Hilfsklasse auf Basis des aktuellen Terms ein Ergebnis berechnet werden
     * kann. Das ist nicht der Fall, wenn als letztes Element des Terms ein Operator eingetragen wurde.
     * Ist das der Fall, wird die Methode beendet, ohne das eine Aktualisierung des in "resultView"
     * angezeigten Ergebnis erfolgt. Die Fallunterscheidung erfolgt hier, und nicht etwa schon in
     * den Methoden mit denen die Eingabe von Ziffern und Operatoren verarbeitet wird, da der erste
     * Teil der update-Methode, das Anzeigen des geänderten Terms, in beiden Fällen erfolgen muss.
     * Liegt ein valider Term vor, wird dessen Ergebnis berechnet, in dem dieser an die entsprechende
     * Methode der Hilfsklasse übergeben wird. Deren Rückgabe wird zur Aktualisierung des in "resultView"
     * angezeigten Texts verwendet.
     *
     * Falls beim Aufruf der Methode für den Parameter "clearTerm" der Wert true übergeben wird,
     * erfolgt zuletzt eine zusätzliche Änderung des UIs und des von der Klasse verwalteten Terms im
     * Zwischenspeicher: Dieser Zwischenspeicher, die String-Variable "currentTerm" wird überschrieben,
     * so dass dort nur noch das zuletzt berechnete Ergebnis gespeichert ist, das zusätzlich auch im
     * TextView zur Ausgabe des aktuellen Terms eingetragen wird. Das TextView zur Darstellung des
     * aktuellen Ergebnis wird zuletzt auf einen Standardwert zurückgesetzt.
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
