package tn.esprit.tp1yassinejallouli4twin7.testFoyer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.tp1yassinejallouli4twin7.entities.Bloc;
import tn.esprit.tp1yassinejallouli4twin7.entities.Foyer;
import tn.esprit.tp1yassinejallouli4twin7.entities.Universite;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IFoyerRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IUniversiteRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IBlocService;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.FoyerServicesImpl;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoyerServiceImplTest {

    @Mock
    private IFoyerRepo foyerRepo;
/*
    @Mock
    private IUniversiteRepo universiteRepo;

    @Mock
    private IBlocService blocService;
*/
    @InjectMocks
    private FoyerServicesImpl foyerServicesimpl;

    @Test
    public void testAjouterFoyer() {
        // Given
        Foyer foyer = new Foyer(); // Assuming you have a Foyer class
        when(foyerRepo.save(any(Foyer.class))).thenReturn(foyer); // Mocking the save method of the repository to return a mocked Foyer object

        // When
        Foyer savedFoyer = foyerServicesimpl.ajouterFoyer(foyer); // Calling the method to be tested

        // Then
        assertNotNull(savedFoyer); // Verifying that the returned Foyer object is not null
        verify(foyerRepo, times(1)).save(any(Foyer.class)); // Verifying that the save method of the repository is called exactly once with any Foyer object
    }

    @Test
    public void testSupprimerFoyer() {
        // Given
        long idFoyerToDelete = 1L;

        // When
        foyerServicesimpl.supprimerFoyer(idFoyerToDelete);

        // Then
        verify(foyerRepo, times(1)).deleteById(idFoyerToDelete);
    }
    @Test
    public void testUpdateFoyer() {
        // Create a Foyer object for testing
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L); // Assuming the ID is set

        // Mock the behavior of the foyerRepository.save method to return the same Foyer object
        when(foyerRepo.save(any(Foyer.class))).thenReturn(foyer);

        // Call the method to be tested
        Foyer updatedFoyer = foyerServicesimpl.updateFoyer(foyer);

        // Verify that the save method of the repository is called exactly once with any Foyer object
        verify(foyerRepo, times(1)).save(any(Foyer.class));

        // Assert that the returned Foyer object is the same as the one passed to the method
        assertEquals(foyer, updatedFoyer);
    }

    @Test
    public void testGetFoyer() {
        // Create a Foyer object for testing
        Foyer expectedFoyer = new Foyer();
        expectedFoyer.setIdFoyer(1L); // Assuming the ID is set

        // Mock the behavior of the foyerRepository.findById method to return the expected Foyer object when called with the specified ID
        when(foyerRepo.findById(anyLong())).thenReturn(Optional.of(expectedFoyer));

        // Call the method to be tested
        Foyer retrievedFoyer = foyerServicesimpl.getFoyer(1L); // Assuming the ID is 1

        // Verify that the findById method of the repository is called exactly once with the specified ID
        verify(foyerRepo, times(1)).findById(1L); // Assuming the ID is 1

        // Assert that the returned Foyer object is the same as the expected Foyer object
        assertEquals(expectedFoyer, retrievedFoyer);
    }


    @Test
    public void testGetAllFoyer() {
        // Create a list of Foyer objects for testing
        List<Foyer> expectedFoyers = new ArrayList<>();
        // Add some sample Foyer objects to the list (you may need to adjust this based on your actual data)
        expectedFoyers.add(new Foyer());
        expectedFoyers.add(new Foyer());
        expectedFoyers.add(new Foyer());

        // Mock the behavior of the foyerRepository.findAll method to return the list of expected Foyer objects
        when(foyerRepo.findAll()).thenReturn(expectedFoyers);

        // Call the method to be tested
        List<Foyer> retrievedFoyers = foyerServicesimpl.getAllFoyer();

        // Verify that the findAll method of the repository is called exactly once
        verify(foyerRepo, times(1)).findAll();

        // Assert that the returned list of Foyer objects is the same as the expected list
        assertEquals(expectedFoyers, retrievedFoyers);
    }
/*
    @Test
    public void testAjouterFoyerEtAffecterAUniversite() {
        // Create a sample Foyer
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);

        // Create a sample Universite
        Universite universite = new Universite();
        universite.setIdUniversite(1L);

        // Set up mock behavior for the repository methods
        when(universiteRepo.findById(1L)).thenReturn(Optional.of(universite));

        // Call the method to be tested
        Foyer result = foyerServicesimpl.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        // Verify that the universiteRepo.findById method is called once with the specified ID
        verify(universiteRepo, times(1)).findById(1L);

        // Verify that the Universite's foyer is set to the provided Foyer
        assertEquals(foyer, universite.getFoyer());

        // Verify that the blocService.ajouterBloc method is called for each bloc in the foyer
        for (Bloc bloc : foyer.getBlocs()) {
            verify(blocService, times(1)).ajouterBloc(bloc);
        }

        // Assert that the returned Foyer is the same as the provided one
        assertEquals(foyer, result);
    }
*/
}
