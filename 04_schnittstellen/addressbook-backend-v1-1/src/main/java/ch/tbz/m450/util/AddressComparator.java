package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

/**
 * Vergleicht zwei Adressen zuerst nach Nachnamen, bei Gleichstand nach Vornamen.
 */
public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        // Zuerst nach Nachnamen vergleichen
        int lastnameCompare = a1.getLastname().compareTo(a2.getLastname());

        // Wenn die Nachnamen gleich sind, zusätzlich nach Vornamen vergleichen
        if (lastnameCompare == 0) {
            return a1.getFirstname().compareTo(a2.getFirstname());
        }

        return lastnameCompare;
    }

}