package de.ur.mi.android.taschenrechner.ui.numpad;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

/*
@codecast 7. Die Numpad-Klasse | 1. Funktion und Aufbau (default)
@url https://audiobook.software-engineering.education/codecast/07-01-Overview.mp3
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
    @codecast 7. Die Numpad-Klasse | 2. Verarbeiten der Klick-Events (default)
    @url https://audiobook.software-engineering.education/codecast/07-02-Events.mp3
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
