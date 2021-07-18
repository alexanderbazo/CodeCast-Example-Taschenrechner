package de.ur.mi.android.taschenrechner.ui.button;

/*
 * Definition und Repräsentation der Taschenrechnertasten
 *
 * Innerhalb der Anwendung müssen an verschiedenen Stellen die Tasten des Taschenrechners repräsentiert
 * werden. Auf Ebene der Display-Klasse bzw. konkreter im Kontext des RecyclerView und der verwendeten
 * Adapter-Klasse müssen einzelne Views mit der passenden Beschriftung angezeigt werden. Innerhalb der
 * App müssen dann die jeweils gedrückten Tasten zwischen den Komponenten kommuniziert werden. Die
 * Werte dieses Enums dienen dazu, alle verfügbaren Tasten für diese Zwecke eindeutig zu beschreiben.
 * Für jede mögliche Taste wird dabei ein Typ bzw. eine Kategorie sowie die jeweilige Beschriftung
 * festgehalten. Der Typ dient zur Definition der verschiedenen Arten von Tasten, also der Unterscheidung
 * von Zifferntasten, Tasten zur Eingabe von mathematischen Operatoren und den zusätzlichen Tasten
 * zur Auflösung bzw. zum Zurücksetzten des aktuellen Ausdrucks. Ein Enum eignet sich hier besonders,
 * da dadurch eine fixe Menge an Möglichkeiten definiert werden kann und dabei mit jeder Option
 * zusätzliche Attribute, wie hier z.B. die Tastenbeschriftung, festgehalten werden kann.
 */
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
