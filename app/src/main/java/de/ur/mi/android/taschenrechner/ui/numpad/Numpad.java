package de.ur.mi.android.taschenrechner.ui.numpad;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

/*
 * Die Numpad-Klasse: Funktion und Aufbau
 *
 * Hier wird das sichtbare und verwendbare Tastenfeld des Taschenrechners verwaltet. Die Klasse
 * ist um das RecyclerView herum aufgebaut, das zur Darstellung der einzelnen Schaltflächen verwendet
 * wird. Das View wird in der Activity referenziert und bei der Erstellung der notwendigen Instanz
 * dieser Klasse an deren Konstruktor übergeben. Die Numpad-Klasse initialisiert selbstständig den
 * benötigten Adapter, über den die Inhalte für das RecyclerView bereitgestellt werden. Über eine
 * Listener-Schnittstelle zwischen Adapter und Numpad-Klasse werden die Klicks auf die Buttons
 * weitergegeben und abgefangen. Die Numpad-Klasse fungiert dabei als Listener und implementiert
 * dafür das Interface, das innerhalb der Adapter-Klasse definiert wird. Der Adapter kommuniziert  alle
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
     * zu welcher Kategorie die gedrückte Taste gehört und nutzt dazu die type-Eigenschaft der
     * Buttons. Die einfache Konstruktion des switch-Case ist möglich, da für diese Eigenschaft der
     * Buttons bewusst ein weiteres Enum (ButtonType) verwendet wurde, dessen Werte einfach als Cases
     * innerhalb der switch-Anweisung verwendet werden können. Im Falle eines ausgewählten Operators wird
     * dabei zusätzlich geprüft, ob auch die zuletzt gedrückte Taste einen Operator darstellt. Zu diesem
     * Zweck wird die jeweils zuletzt betätigte Taste in einer Instanzvariable (lastButtonPressed)
     * zwischengespeichert. Über diesen Mechanismus kann die Numpad-Klasse die angeschlossene Activity,
     * die hier als NumpadListener fungiert, präzise über die Intention der Nutzer*innen bzw. die
     * Art des Eingabe-Events informieren. Diese Information wird dabei über den Aufruf der
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

    public interface NumpadListener {
        void onNumberButtonPressed(Button button);

        void onOperatorButtonPressed(Button button);

        void onOperatorButtonOverwritten(Button button);

        void onClearButtonPressed();

        void onResultButtonPressed();
    }
}
