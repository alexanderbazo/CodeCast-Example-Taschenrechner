package de.ur.mi.android.taschenrechner.helper;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


/*
@codecast 4. Verwendung der "exp4j"-Bibliothek | 1. Übersicht
@url https://tobiasdollhofer.de/TaschenrechnerCast/04-01-Overview.mp3
*/
public class CalculatorHelper {

    private static final String ERROR_MESSAGE = "Error";
    private static final String ERROR_UPPER_CASE = "ERROR";


    /*
    @codecast 4. Verwendung der "exp4j"-Bibliothek | 2. Aufbau der Hilfsklasse
    @url https://tobiasdollhofer.de/TaschenrechnerCast/04-02-Helper.mp3
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
