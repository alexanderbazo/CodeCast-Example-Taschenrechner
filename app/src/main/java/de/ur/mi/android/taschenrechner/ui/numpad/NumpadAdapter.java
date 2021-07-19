package de.ur.mi.android.taschenrechner.ui.numpad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.ur.mi.android.taschenrechner.R;
import de.ur.mi.android.taschenrechner.ui.button.Button;

/*
 * Die Numpad-Klasse: Der Adapter
 *
 * Diese Adapterklasse stellt die Informationen bereit, mit denen das angeschlossene RecyclerView
 * das eigentliche Tastenfeld rendert. Adapter und View werden in der Numpad-Klasse miteinander
 * verbunden. Das Tastenfeld, d.h. die genaue Anordnung der einzelnen Buttons, wird über den
 * Inhalt des Arrays buttons definiert, das hier als Instanzvariable gehalten wird. In dem als
 * Literal initialisierten Array werden die einzelnen Werte des Button-Enums, d.h. die verfügbaren Tasten
 * in der Reihenfolge angegeben, in der sie im UI erscheinen sollen. Das RecyclerView ist in der
 * Layout-Datei (activity_main.xml) so konfiguriert, dass die vom Adapter übergebenen Inhalte in einem
 * Raster mit vier Spalten angezeigt werden. Die Reihenfolge der Button im Array ergibt sich aus
 * ihrer gewünschten Position in diesem Gitter. Dabei werden die Buttons zeilenweise (von links nach
 * rechts) angegeben. Dadurch können die Tasten auf der einen Seite im Enum nachvollziehbar nach den
 * jeweiligen Kategorien (Ziffern, Operatoren und Funktionen) sortiert werden während auf der anderen
 * Seite für die eigentliche Darstellung im UI eine davon abweichende Sortierung über die Position im
 * Button-Array erreicht wird.
 *
 * Der Adapter übernimmt eine vermittelnde Rolle im Rahmen der Weitergabe und Verarbeitung
 * der Eingabe-Events innerhalb der Anwendung. Beim Erstellen der einzelnen ViewHolder, die jeweils
 * einen der Buttons des Tastenfelds darstellen, wird ein Listener registriert, der über Klicks
 * auf die einzelnen Buttons informiert wird. Die Aufgabe des Listeners übernimmt der Adapter selbst,
 * leitet die in der Callback-Funktion abgefangenen Klicks dann aber direkt an eine weitere Komponente der App
 * weiter, die - ebenfalls als Listener - beim Adapter registriert ist. Konkret handelt es sich dabei
 * um eine Instanz der Numpad-Klasse. Hier besteht  Verbesserungspotential für das Software Design
 * der Anwendung: Da der Adapter beide Seiten der Kommunikationskette, das Numpad als Listener bzw.
 * Observer und den ViewHolder als Observable, kennt, könnte der Mechanismus sehr leicht
 * vereinfacht werden. Statt selbst als Zwischenstufe für die Weitergabe der Events zu fungieren,
 * könnten im Adapter auch direkt die Referenzen auf den übergeordneten Listener (hier das Numpad)
 * an die einzelnen ViewHolder weitergegeben werden, die die Klicks dann, ohne Umweg über den Adapter,
 * an die Numpad-Klasse weitergeben. Damit dies funktioniert, müssten die ViewHolder dauerhaft über
 * die Information verfügen, welchen konkreten Button sie aktuell darstellen. Bei einem Klick muss
 * diese Information (d.h. welcher Button wurde betätigt) an die Numpad-Klasse weitergeben werden, damit
 * das Ereignis korrekt in der weiteren App verarbeitet werden kann. Aktuell stellen die ViewHolder nur
 * die eigene Position innerhalb des RecyclerViews fest und übergeben diese (als int-Wert) über die
 * Callback-Funktion an den Adapter. Erst hier wird dann über die Positionsangabe festgestellt,
 * welchen konkreten Button des Tastenfelds der angeklickte ViewHolder repräsentiert, in dem an der
 * entsprechenden Stelle des Button-Arrays nachgeschlagen wird. Diese Information wird dann an die
 * Numpad-Klasse weitergereicht.
 */
public class NumpadAdapter extends RecyclerView.Adapter<NumpadButtonViewHolder> implements NumpadButtonViewHolder.OnCLickListener {

    public final ButtonListener listener;
    public final Button[] buttons = {Button.BUTTON_SEVEN, Button.BUTTON_EIGHT, Button.BUTTON_NINE, Button.BUTTON_DIVISION, Button.BUTTON_FOUR, Button.BUTTON_FIVE, Button.BUTTON_SIX, Button.BUTTON_MULTIPLICATION, Button.BUTTON_ONE, Button.BUTTON_TWO, Button.BUTTON_THREE, Button.BUTTON_MINUS, Button.BUTTON_ZERO, Button.BUTTON_SEPARATOR, Button.BUTTON_RESULT, Button.BUTTON_PLUS, Button.BUTTON_DELETE};

    public NumpadAdapter(ButtonListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NumpadButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_numpad_button, parent, false);
        return new NumpadButtonViewHolder(v, parent.getContext(), this);
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
