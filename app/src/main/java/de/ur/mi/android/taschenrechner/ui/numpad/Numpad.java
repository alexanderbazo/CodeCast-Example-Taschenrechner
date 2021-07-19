package de.ur.mi.android.taschenrechner.ui.numpad;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

/*
 * Die Numpad-Klasse: Funktion und Aufbau
 *
 * Hier wird das sichtbare und verwendbare Tastenfeld des Taschenrechners verwaltet. Die Klasse
 * ist um das RecyclerView aufgebaut, dass zur Darstellung der einzelnen Schaltflächen verwendet
 * wird. Das View wird in der Activity referenziert und bei der Erstellung der notwendigen Instanz
 * dieser Klasse an deren Konstruktor übergeben. Die Numpad-Klasse initialisiert selbstständig den
 * benötigten Adapter, über den die Inhalte des RecyclerViews bereitgestellt werden. Über eine
 * Listener-Schnittstelle zwischen Adapter und Numpad-Klasse werden die Klicks auf die Buttons
 * weitergegeben und hier abgefangen. Die Numpad-Klasse fungiert dabei als Listener und implementiert
 * dafür das Interface, das innerhalb des Adapters definiert wird. Der Adapter kommuniziert dabei alle
 * Tastendrücke über die gleiche Callback-Methode und übergibt die gedrückte Taste, in Form
 * eines Wertes aus dem Button-Enum, als Parameter. Die Numpad-Klasse verarbeitet das Event, stellt
 * fest, welche Art von Taste gedrückt wurde und gibt diese Information dann in Form spezifischerer
 * Events an die Activity weiter, die - ebenfalls über eine Listener-Schnittstelle - mit dem Numpad
 * verbunden ist.
 */
public class Numpad implements NumpadAdapter.ButtonListener {


    private Button lastButtonPressed;
    private final NumpadListener listener;

    public Numpad(RecyclerView view, NumpadListener listener) {
        this.listener = listener;
        init(view);
    }

    private void init(RecyclerView view) {
        NumpadAdapter adapter = new NumpadAdapter(this);
        view.setAdapter(adapter);
    }

    /*
     * Die Numpad-Klasse: Verarbeiten der Klick-Events
     *
     * Über diese Callback-Methode gelangt die Information über eine gedrückte Taste aus dem Adapter
     * in die Numpad-Klasse. Die genaue Taste wird dabei über den Parameter button spezifiziert. Diese
     * sehr allgemeine Meldung wird innerhalb der Methode präzisiert: Die Numpad-Klasse stellt fest,
     * zu welcher Kategorie die gedrückte Taste gehört. Im Falle eines ausgewählten Operators wird
     * zusätzlich geprüft, ob auch die zuletzt gedrückte Taste einen Operator darstellt. Zu diesem
     * Zweck wird die jeweils zu letzt betätigte Taste in einer Instanzvariable (lastButtonPressed)
     * zwischengespeichert. Über diesen Mechanismus kann die Numpad-Klasse die angeschlossene Activity,
     * die hier als NumpadListener fungiert, präzise über die Intention der Nutzer*innen bzw. die
     * Art des Eingabe-Events informieren. Diesen Information wird dabei über den Aufruf der
     * verschiedenen Interface- bzw. Callback-Methoden weitergegeben. So kann innerhalb der Activity
     * leicht zwischen "normalen" Ziffern-Tasten, dem Hinzufügen oder Überschreiben von Operatoren
     * und der Auswahl der restlichen Funktionen unterschieden werden.
     */
    @Override
    public void onButtonPressed(Button button) {
        switch (button.type) {
            case NUMBER:
                listener.onNumberButtonPressed(button);
                break;
            case OPERATOR:
                if (lastButtonPressed == null) {
                    listener.onOperatorButtonPressed(button);
                } else if (lastButtonPressed.type != ButtonType.OPERATOR) {
                    listener.onOperatorButtonPressed(button);
               } else {
                    listener.onOperatorButtonOverwritten(button);
                }
                break;
            case COMMAND:
                if (button == Button.BUTTON_DELETE) {
                    listener.onClearButtonPressed();
                    break;
                }
                if (button == Button.BUTTON_RESULT) {
                    listener.onResultButtonPressed();
                    break;
                }
            default:
                break;
        }
        lastButtonPressed = button;
    }

    /*
     * Listener-Interface zur Weitergabe der verschiedenen Eingabe-Events aus dem Tastenfeld
     */
    public interface NumpadListener {
        // Wird aufgerufen, wenn eine Zahl eingegeben wurde
        void onNumberButtonPressed(Button button);

        // Wird aufgerufen, wenn ein Operator eingegeben wurde
        void onOperatorButtonPressed(Button button);

        // Wird aufgerufen, wenn direkt nach einem Operator erneut ein weiterer Operator eingegeben wurde
        void onOperatorButtonOverwritten(Button button);

        // Wird aufgerufen, wenn der Clear-Button (C) gedrückt wurde
        void onClearButtonPressed();

        // Wird aufgerufen, wenn der Result-Button (=) gedrückt wurde
        void onResultButtonPressed();
    }
}
