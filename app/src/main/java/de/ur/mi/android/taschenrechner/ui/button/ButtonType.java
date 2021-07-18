package de.ur.mi.android.taschenrechner.ui.button;


/*
 * Definition und Repräsentation der Taschenrechnertasten
 *
 * Über dieses Enum werden die drei Kategorien definiert, über die die eigentlichen Tasten, definiert
 * im Enum "Button", eingeteilt werden. Alle Zifferntasten werden über den Typ "NUMBER" zusammengefasst.
 * Die Operatoren werden mit dem Typ "OPERATOR" gekennzeichnet und die übrigen Taste, die zur Steuerung
 * der App, z.B. zur Anzeige des Ergebnis, verwendet werden, werden mit dem Typ "COMMAND" beschrieben.
 * Die Verwendung eines Enums zur Aufteilung der Tasten in verschiedene Gruppen erlaubt später die
 * einfache Verarbeitung der Eingaben, da, z.B. über die Verwendung der Enum-Wert in einem switch-
 * Case, einfach die Zugehörigkeit eines gedrückten Buttons zu einer der Kategorien festgestellt werden
 * kann. So kann dann z.B. innerhalb der App das Drücken von Tasten einer bestimmten Kategorie einheitlich
 * behandelt werden.
 */
public enum ButtonType {
    NUMBER,
    OPERATOR,
    COMMAND;
}
