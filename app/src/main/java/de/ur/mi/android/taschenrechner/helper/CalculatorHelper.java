package de.ur.mi.android.taschenrechner.helper;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


/*
 * Verwendung der "exp4j"-Bibliothek
 *
 * Diese Klasse dient der vereinfachten Nutzung der "exp4j"-Bibliothek. Über eine öffentliche,
 * statische Methode kann ein mathematischer Ausdruck in Form eines Strings übergeben werden.
 * In der Methode wird durch den Einsatz verschiedener "exp4j"-Klassen ein Ergebnis für den im String
 * repräsentierten Ausdruck berechnet, das, falls eine korrekte Auswertung des Ausdrucks möglich war,
 * ebenfalls als String-Variable zurückgegeben wird.
 */
public class CalculatorHelper {

    private static final String ERROR_MESSAGE = "Error";
    private static final String ERROR_UPPER_CASE = "ERROR";

    /*
     * Verwendung der "exp4j"-Bibliothek
     *
     * Um über die "exp4j"-Bibliothek ein entsprechendes Ergebnis zu berechnen, sind mehrere Schritte
     * notwendig, die innerhalb dieser Methode, bzw. in einer weiteren, ausgelagerten Methode der
     * Klasse, durchgeführt werden.
     *
     * In einem ersten Schritt wird die übergeben Eingabe, d.h. der durch
     * die Nutzer*innen eingegebene Term, bereinigt. Dadurch wird z.B. sichergestellt, dass kompatible
     * Operatoren verwendet werden. Im nächsten Schritt wird über die Hilfsklasse "ExpressionBuilder",
     * die Teil der "exp4j"-Bibliothek ist, eine Expression-Instanz, erstellt. Durch Instanzen dieser
     * Klasse werden in der "exp4j"-Bibliothek mathematische Ausdrücke repräsentiert. Im Anschluss wird
     * versucht, den so generierten Ausdruck aufzulösen. Gelingt dies, wird das Resultat als Ergebnis
     * der Methode zurückgegeben. Schlägt die Berechnung fehl, wird das durch das Abfangen der
     * Exceptions bemerkt, die dabei von der "exp4j"-Bibliothek erzeugt werden. Im  Falle einer
     * fehlgeschlagenen Berechnung gibt die Methode eine textuelle Fehlermeldung an die aufrufende
     * Stelle der App zurück.
     */
    public static String calculate(String expression) {
        String cleanedExpression = cleanExpression(expression);

        if (expression.contains(ERROR_UPPER_CASE)) return ERROR_MESSAGE;

        Expression exp = new ExpressionBuilder(cleanedExpression).build();

        try {
            return String.valueOf(exp.evaluate());
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    private static String cleanExpression(String expression) {
        String result = expression.toUpperCase();

        if (result.contains("X")) {
            result = result.replaceAll("X", "*");
        }

        if (result.contains("÷")) {
            result = result.replaceAll("÷", "/");
        }

        return result;

    }

}
