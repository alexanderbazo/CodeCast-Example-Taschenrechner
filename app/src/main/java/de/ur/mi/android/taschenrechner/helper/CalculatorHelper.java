package de.ur.mi.android.taschenrechner.helper;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorHelper {

    private static final String ERROR_MESSAGE = "Error";
    private static final String ERROR_UPPER_CASE = "ERROR";

    /**
     * Mit dieser Methode kann ein Ausdruck, in Form eines Strings, berechnet werden.
     * @param expression Term/Ausdruck der berechnet werden soll
     * @return Ergebnis der Rechnung als String-Wert, falls ein Fehler auftritt wird 'Error'
     * zurückgegeben
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
