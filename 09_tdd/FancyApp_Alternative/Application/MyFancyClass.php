<?php
/**
 * Alternative Loesung ("andere Gruppe") - mit KI generiert, um sie mit
 * der eigenen Loesung vergleichen zu koennen.
 */

class MyFancyClass
{
    public function shortString($text, $length, $ending = '...') {
        if (strlen($ending) > $length) {
            return false;
        }
        if (strlen($text) > $length) {
            $text = substr($text, 0, $length - strlen($ending)) . $ending;
        }
        return $text;
    }

    public function calcAverage($values) {
        if (empty($values)) {
            return 0;
        }
        $numbers = array_filter($values, 'is_numeric');
        if (count($numbers) !== count($values)) {
            return false;
        }
        return array_sum($numbers) / count($numbers);
    }

    public function getOpposite($value, $delimiter = ',') {
        switch (gettype($value)) {
            case 'array':
                return implode($delimiter, $value);
            case 'string':
                return explode($delimiter, $value);
            default:
                return false;
        }
    }
}