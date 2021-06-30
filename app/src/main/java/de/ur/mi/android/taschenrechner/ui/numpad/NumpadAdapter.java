package de.ur.mi.android.taschenrechner.ui.numpad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.R;
import de.ur.mi.android.taschenrechner.ui.button.Button;

public class NumpadAdapter extends RecyclerView.Adapter<NumpadButtonViewHolder> implements NumpadButtonViewHolder.OnCLickListener {

    public final Context context;
    public final ButtonListener listener;
    public final Button[] buttons = {Button.BUTTON_SEVEN, Button.BUTTON_EIGHT, Button.BUTTON_NINE, Button.BUTTON_DIVISION, Button.BUTTON_FOUR, Button.BUTTON_FIVE, Button.BUTTON_SIX, Button.BUTTON_MULTIPLICATION, Button.BUTTON_ONE, Button.BUTTON_TWO, Button.BUTTON_THREE, Button.BUTTON_MINUS, Button.BUTTON_ZERO, Button.BUTTON_SEPARATOR, Button.BUTTON_RESULT, Button.BUTTON_PLUS, Button.BUTTON_DELETE};

    public NumpadAdapter(Context context, ButtonListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NumpadButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_numpad_button, parent, false);
        return new NumpadButtonViewHolder(v, context, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NumpadButtonViewHolder holder, int position) {
        holder.bindButton(buttons[position]);
    }

    @Override
    public int getItemCount() {
        return buttons.length;
    }

    @Override
    public void onButtonClicked(int adapterPosition) {
        listener.onButtonPressed(buttons[adapterPosition]);
    }

    public interface ButtonListener {
        void onButtonPressed(Button button);
    }
}
