package tn.esprit.tp1yassinejallouli4twin7.TestUniversite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.tp1yassinejallouli4twin7.entities.Foyer;
import tn.esprit.tp1yassinejallouli4twin7.entities.Universite;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IFoyerRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IUniversiteRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.UniversiteServiceImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class UniversiteTest {
    @Mock
    private IUniversiteRepo universiteRepository;
    @Mock
    private IFoyerRepo foyerRepo;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAjouterUniversite() {
        Universite universite = new Universite();

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.ajouterUniversite(universite);

        assertEquals(universite, result);
    }

    @Test
    public void testUpdateUniversite() {
        Universite universite = new Universite();

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertEquals(universite, result);
    }

    @Test
    public void testSupprimerUniversite() {
        long idUniversite = 1;

        // Pas de comportement spécifique à définir car la méthode supprimerUniversite est de type void

        assertDoesNotThrow(() -> universiteService.supprimerUniversite(idUniversite));
    }

    @Test
    public void testGetUniversite() {
        long idUniversite = 1;
        Universite universite = new Universite();

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Universite result = universiteService.getUniversite(idUniversite);

        assertEquals(universite, result);
    }

    @Test
    public void testGetAllUniversite() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite());
        universites.add(new Universite());

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.getAllUniversite();

        assertEquals(2, result.size());
    }
    @Test
     public void testAffecterFoyerAUniversite() {
        // Prepare test data
        long idFoyer = 1;
        long idUniversite = 1;
        Universite universite = new Universite();

        // Mock the behavior of the repository
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        // Call the method under test
        Universite result = universiteService.affecterFoyerAUniversite(idFoyer, idUniversite);

        // Assert that the method returns the expected Universite object
        assertEquals(universite, result);

        // Add more assertions if needed
    }
    @Test
    public void testDesaffecterFoyerAUniversite() {
        long idFoyer = 1;
        long idUniversite = 1;
        Universite universite = new Universite();

        // Mock the behavior of findById for both repos
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(foyerRepo.findById(idFoyer)).thenReturn(Optional.of(new Foyer()));

        Universite result = universiteService.desaffecterFoyerAUniversite(idFoyer, idUniversite);

        assertEquals(universite, result);
    }
}
