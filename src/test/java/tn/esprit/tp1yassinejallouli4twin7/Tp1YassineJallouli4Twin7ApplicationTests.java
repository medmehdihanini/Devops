package tn.esprit.tp1yassinejallouli4twin7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.tp1yassinejallouli4twin7.entities.Foyer;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IFoyerRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IFoyerServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Tp1YassineJallouli4Twin7ApplicationTests {

    @Autowired
    private IFoyerServices foyerService;

    @Autowired
    private IFoyerRepo foyerRepository;

    @Test
    void contextLoads() {
        assertNotNull(foyerService);

    }

    @Test
    void testAjouterFoyer() {
        // Given
        Foyer foyerToAdd = new Foyer(); // Create a sample Foyer object

        // When
        Foyer savedFoyer = foyerService.ajouterFoyer(foyerToAdd); // Call the method to be tested

        // Then
        assertNotNull(savedFoyer); // Verify that the returned Foyer object is not null
        Foyer retrievedFoyer = foyerRepository.findById(savedFoyer.getIdFoyer()).orElse(null); // Retrieve the saved Foyer from the repository
        assertNotNull(retrievedFoyer); // Verify that the retrieved Foyer is not null
        assertEquals(foyerToAdd, retrievedFoyer); // Verify that the retrieved Foyer matches the input Foyer
    }
}