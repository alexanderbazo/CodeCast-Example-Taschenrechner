package de.ur.mi.android.taschenrechner.ui.numpad;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

public class Numpad implements NumpadAdapter.ButtonListener {

    private Button lastButtonPressed;
    private final NumpadListener listener;

    public Numpad(Context context, RecyclerView view, NumpadListener listener) {
        this.listener = listener;
        init(context, view);
    }

    private void init(Context context, RecyclerView view) {
        NumpadAdapter adapter = new NumpadAdapter(context, this);
        view.setAdapter(adapter);
    }


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
