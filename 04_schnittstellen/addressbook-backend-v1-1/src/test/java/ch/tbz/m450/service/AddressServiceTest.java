package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests für die Klasse AddressService.
 * Die H2-Datenbank wird weggemockt: Statt des echten AddressRepository
 * (das mit der Datenbank redet) wird ein Mockito-Mock verwendet.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    // Mock des Repositories = die weggemockte Datenbank
    @Mock
    private AddressRepository addressRepository;

    // Der Service, in den der Mock automatisch eingesetzt wird
    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;

    @BeforeEach
    void setUp() {
        address1 = new Address(1, "Max", "Muster", "0791234567", new Date());
        address2 = new Address(2, "Anna", "Beispiel", "0797654321", new Date());
    }

    @Test
    @DisplayName("save() gibt die gespeicherte Adresse zurück")
    void testSave() {
        // Vorgeben, was der Mock zurückgibt, wenn save aufgerufen wird
        when(addressRepository.save(address1)).thenReturn(address1);

        Address result = addressService.save(address1);

        assertEquals(address1, result);
        // Prüfen, dass der Service save am Repository genau einmal aufgerufen hat
        verify(addressRepository, times(1)).save(address1);
    }

    @Test
    @DisplayName("getAll() gibt alle Adressen aus dem Repository zurück")
    void testGetAll() {
        // Der Mock liefert eine feste Liste, ohne echte Datenbank
        when(addressRepository.findAll()).thenReturn(List.of(address1, address2));

        List<Address> result = addressService.getAll();

        assertEquals(2, result.size());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("getAddress() gibt die Adresse mit der passenden ID zurück")
    void testGetAddressFound() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address1));

        Optional<Address> result = addressService.getAddress(1);

        assertTrue(result.isPresent());
        assertEquals("Max", result.get().getFirstname());
        verify(addressRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("getAddress() gibt ein leeres Optional zurück, wenn die ID nicht existiert")
    void testGetAddressNotFound() {
        when(addressRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Address> result = addressService.getAddress(99);

        assertTrue(result.isEmpty());
        verify(addressRepository, times(1)).findById(99);
    }
}