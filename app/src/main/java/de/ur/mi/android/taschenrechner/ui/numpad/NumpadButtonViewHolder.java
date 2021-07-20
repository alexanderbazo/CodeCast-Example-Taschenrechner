package de.ur.mi.android.taschenrechner.ui.numpad;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.R;
import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

/*
@codecast 7. Die Numpad-Klasse | 4. Der ViewHolder (default)
@url https://www.tobiasdollhofer.de/taschenrechner/07-04-ViewHolder.mp3
*/
public class NumpadButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final android.widget.Button inputButton;
    public final Context context;
    public final OnCLickListener listener;

    public NumpadButtonViewHolder(@NonNull View buttonView, Context context, OnCLickListener listener) {
        super(buttonView);
        this.inputButton = buttonView.findViewById(R.id.numpad_button);
        this.context = context;
        this.listener = listener;
        this.inputButton.setOnClickListener(this);
    }

    /*
    @codecast 7. Die Numpad-Klasse | 5. Buttons unterschiedlich darstellen (default)
    @url https://www.tobiasdollhofer.de/taschenrechner/07-05-Button-Design.mp3
    */
    public void bindButton(Button button) {
        if (button.type == ButtonType.OPERATOR) {
            inputButton.setBackground(AppCompatResources.getDrawable(context, R.drawable.operator_button_shape));
        }
        if (button.type == ButtonType.COMMAND) {
            inputButton.setBackground(AppCompatResources.getDrawable(context, R.drawable.command_button_shape));
        }
        inputButton.setText(button.label);
    }

    @Override
    public void onClick(View v) {
        listener.onButtonClicked(getAbsoluteAdapterPosition());
    }

    public interface OnCLickListener {
        void onButtonClicked(int adapterPosition);
    }
}
