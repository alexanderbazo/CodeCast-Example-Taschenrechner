package de.ur.mi.android.taschenrechner.ui.numpad;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

/**
 * Diese Klasse verwaltet bzw. repräsentiert das Tastenfeld des Taschenrechners. Es initialisiert
 * den notwendigen Adapter für die korrekte Verwendung des übergebenen RecyclerViews, fängt die Events
 * ab, die der Adapter aussendet, wenn einzelne Buttons des Tastenfelds betätigt werden und wandelt
 * diese anhand des Typs der Button und des zuvor gedrückten Buttons in aussagekräftigere Events um,
 * die an einen angeschlossenen Listener (wird im Konstruktor gesetzt) weitergegeben werden.
 */
public class Numpad implements NumpadAdapter.ButtonListener {

    // Zwischenspeicher für den zuletzt betätigten Button: Wird verwendet, um zu identifizieren, ob
    // direkt nacheinander zwei Operatoren eingegeben wurden.
    private Button lastButtonPressed;
    private final NumpadListener listener;

    public Numpad(RecyclerView view, NumpadListener listener) {
        this.listener = listener;
        init(view);
    }

    /*
     * Initialisiert den Adapter und verbindet diesen mit dem RecyclerView um die Darstellung
     * des Tastenfelds zu ermöglichen. Das Drücken einzelner Tasten wird im Adapter festgestellt und
     * an die Callback-Methoden eines Listeners weitergegeben. Numpad registriert sich beim Adapter
     * über den entsprechenden Parameter des Konstruktors selbst als einen solchen Listener.
     */
    private void init(RecyclerView view) {
        NumpadAdapter adapter = new NumpadAdapter(this);
        view.setAdapter(adapter);
    }

    /*
     * Wird vom Adapter aufgerufen (Callback-Methode des Listeners) wenn einer der Buttons des
     * Tastenfelds gedrückt wurde. Die Methode prüft, welche Taste gedrückt wurde und informiert
     * den angeschlossenen Listener durch den Aufruf der entsprechenden Callback-Methode
     */
    @Override
    public void onButtonPressed(Button button) {
        switch (button.type) {
            // Eingabe einer Ziffer
            case NUMBER:
                listener.onNumberButtonPressed(button);
                break;
            // Eingabe eines Operators
            case OPERATOR:
                if (lastButtonPressed == null) {
                    listener.onOperatorButtonPressed(button);
                } else if (lastButtonPressed.type != ButtonType.OPERATOR) {
                    listener.onOperatorButtonPressed(button);
                // Handling des Falls, dass direkt nacheinander zwei Operatoren eingegeben wurden
                } else {
                    listener.onOperatorButtonOverwritten(button);
                }
                break;
            case COMMAND:
                // Drücken des Clear-Buttons
                if (button == Button.BUTTON_DELETE) {
                    listener.onClearButtonPressed();
                    break;
                }
                // Drücken des Result-Buttons
                if (button == Button.BUTTON_RESULT) {
                    listener.onResultButtonPressed();
                    break;
                }
            default:
                break;
        }
        // Speichern des gedrückten Buttons um bei der nächsten Eingabe zu Prüfen ob zwei Operatoren
        // nacheinander eingegeben wurden
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
