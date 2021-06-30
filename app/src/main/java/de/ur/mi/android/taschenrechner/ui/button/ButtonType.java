package de.ur.mi.android.taschenrechner.ui.button;

/*
 * Enum mit Kategorien zur Einordnung aller Tasten des Tastenfelds
 */
public enum ButtonType {
    NUMBER, // Einfache Ziffern: 0 bis 9
    OPERATOR, // Operatoren: +,-,*,/ und . (Dezimaltrennzeichen)
    COMMAND; // Befehle: Clear und Result
}
