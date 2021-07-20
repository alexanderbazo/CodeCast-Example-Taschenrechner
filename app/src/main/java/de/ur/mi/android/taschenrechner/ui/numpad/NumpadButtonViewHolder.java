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
 * Die Numpad-Klasse: Der ViewHolder
 *
 * Über diesen ViewHolder werden die einzelnen Elemente des RecyclerView initialisiert und
 * gesteuert. Die Klasse ist dabei relativ einfach aufgebaut. Im Konstruktor werden dem ViewHolder
 * die notwendigen Informationen über das zu verwaltende View-Element übergeben. Hier wird der
 * Listener registriert, mit dem die Klicks auf die einzelnen Buttons abgefangen werden. In der
 * entsprechenden Callback-Methode, das relevante Interface wird auf Klassenebene implementiert,
 * beginnt die App-interne Verarbeitung der Eingabeevents. Der ViewHolder gibt dabei den "Klick"
 * direkt an die nächste Komponente weiter, die eine einfache, direkt in der Klasse definierte,
 * Listener-Schnittstelle implementiert und im Konstruktor als Listener an den ViewHolder übergeben
 * wird. Die eigentliche Verarbeitung des Eingabeevents findet dann in dieser Komponente statt. In
 * dieser App handelt es sich dabei um den NumpadAdapter, der - als eine Art Relais - die Klicks aus
 * den einzelnen ViewHoldern abfängt und an eine Instanz der Numpad-Klasse weitergibt.
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
     * Die Numpad-Klasse: Verschiedene UIs
     *
     * Über die bindButton-Methode wird der ViewHolder aktualisiert. Die Inhalte und die Darstellung
     * des verwalteten Views werden aktualisiert, um den als Parameter übergebenen Button im UI
     * darzustellen. In dieser konkreten Anwendung wird diese Methode vergleichsweise selten aufgerufen,
     * da sich die Darstellung des Tastenfelds während der Verwendung der App nicht ändern wird und nur
     * Systemereignisse, wie z.B. das Reaktivieren der App aus dem Hintergrund, eine Aktualisierung
     * des UIs und damit einen Aufruf dieser Methode auslösen werden. Das grundsätzliche Prinzip einer
     * öffentlichen Methode im ViewHolder, der ein Datenobjekt als Parameter übergeben wird und in der
     * der Zustand dieses Objekts in die entsprechenden Elemente des Views übertragen wird, eignet sich
     * besonders gut für solche (Recycler-) Views, deren Inhalte dynamisch angepasst werden.
     *
     * Ein Feature dieses ViewHolder ist die Möglichkeit zur unterschiedlichen Darstellung verschiedener
     * Arten von Tasten. Über den Typ des übergebenen Buttons werden Ziffer, Operatoren und andere Befehle
     * unterschieden. Für Zifferntasten bleibt der View unverändert, d.h. das im Konstruktor übergebene View
     * wird in der Darstellung nicht angepasst. Für die übrigen Kategorien wird die Hintergrundgrafik
     * des Buttons angepasst. Dafür stehen in den Ressourcen zwei Vektorgrafiken mit jeweils unter-
     * schiedlicher Hintergrundfarbe bereit, die hier ausgewählt und als Hintergrund des Buttons
     * gesetzt werden. In allen Fällen wir die im Button-Parameter übergebene Beschriftung ("label")
     * verwendet, um den auf dem Button angezeigten Text anzupassen.
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
