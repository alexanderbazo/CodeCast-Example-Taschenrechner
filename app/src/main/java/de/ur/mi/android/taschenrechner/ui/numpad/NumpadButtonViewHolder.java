package de.ur.mi.android.taschenrechner.ui.numpad;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.R;
import de.ur.mi.android.taschenrechner.ui.button.Button;
import de.ur.mi.android.taschenrechner.ui.button.ButtonType;

public class NumpadButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView textView;
    public final Context context;
    public final OnCLickListener listener;

    public NumpadButtonViewHolder(@NonNull View buttonView, Context context, OnCLickListener listener) {
        super(buttonView);
        this.textView = buttonView.findViewById(R.id.numpad_button);
        this.context = context;
        this.listener = listener;
        this.textView.setOnClickListener(this);
    }

    public void bindButton(Button button) {
        if (button.type == ButtonType.OPERATOR) {
            textView.setBackground(AppCompatResources.getDrawable(context, R.drawable.operator_button_shape));
        }
        if (button.type == ButtonType.COMMAND) {
            textView.setBackground(AppCompatResources.getDrawable(context, R.drawable.command_button_shape));
        }
        textView.setText(button.label);
    }

    @Override
    public void onClick(View v) {
        listener.onButtonClicked(getAbsoluteAdapterPosition());
    }

    public interface OnCLickListener {
        void onButtonClicked(int adapterPosition);
    }
}
