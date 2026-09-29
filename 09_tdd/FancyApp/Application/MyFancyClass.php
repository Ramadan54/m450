<?php
/**
 * Created by PhpStorm.
 * User: s.kemper
 * Date: 29.03.2022
 * Time: 22:05
 *
 * This class does a lot of things... but has no productive purpose.
 * The only purpose of this class is to teach the basic functions
 * of PHP and how to use them.
 */

class MyFancyClass
{
    public function shortString($text, $length, $ending = '...') {
        // Endung ist laenger als die erlaubte Laenge -> ungueltig
        if (mb_strlen($ending) > $length) {
            return false;
        }

        // Text ist kurz genug -> unveraendert zurueckgeben
        if (mb_strlen($text) <= $length) {
            return $text;
        }

        // Text kuerzen, damit Text + Endung genau $length Zeichen lang sind
        return mb_substr($text, 0, $length - mb_strlen($ending)) . $ending;
    }

    public function calcAverage($values) {
        // Leeres Array -> 0 (verhindert Division durch 0)
        if (count($values) === 0) {
            return 0;
        }

        // Sobald ein Wert keine Zahl ist -> ungueltig
        foreach ($values as $value) {
            if (!is_numeric($value)) {
                return false;
            }
        }

        // Durchschnitt = Summe / Anzahl
        return array_sum($values) / count($values);
    }

    public function getOpposite($value, $delimiter = ',') {
        // Array -> String (Elemente mit Trennzeichen zusammenfuegen)
        if (is_array($value)) {
            return implode($delimiter, $value);
        }

        // String -> Array (am Trennzeichen aufteilen)
        if (is_string($value)) {
            return explode($delimiter, $value);
        }

        // Alles andere (z.B. Objekt) -> ungueltig
        return false;
    }
}