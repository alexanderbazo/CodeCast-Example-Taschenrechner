package de.ur.mi.android.taschenrechner.ui.button;

public enum Button {
    BUTTON_ZERO(ButtonType.NUMBER, "0"),
    BUTTON_ONE(ButtonType.NUMBER, "1"),
    BUTTON_TWO(ButtonType.NUMBER, "2"),
    BUTTON_THREE(ButtonType.NUMBER, "3"),
    BUTTON_FOUR(ButtonType.NUMBER, "4"),
    BUTTON_FIVE(ButtonType.NUMBER, "5"),
    BUTTON_SIX(ButtonType.NUMBER, "6"),
    BUTTON_SEVEN(ButtonType.NUMBER, "7"),
    BUTTON_EIGHT(ButtonType.NUMBER, "8"),
    BUTTON_NINE(ButtonType.NUMBER, "9"),
    BUTTON_SEPARATOR(ButtonType.OPERATOR, "."),
    BUTTON_PLUS(ButtonType.OPERATOR, "+"),
    BUTTON_MINUS(ButtonType.OPERATOR, "-"),
    BUTTON_MULTIPLICATION(ButtonType.OPERATOR, "*"),
    BUTTON_DIVISION(ButtonType.OPERATOR, "/"),
    BUTTON_DELETE(ButtonType.COMMAND, "C"),
    BUTTON_RESULT(ButtonType.COMMAND, "=");

    public final ButtonType type;
    public final String label;

    Button(ButtonType type, String label) {
        this.type = type;
        this.label = label;
    }
}
