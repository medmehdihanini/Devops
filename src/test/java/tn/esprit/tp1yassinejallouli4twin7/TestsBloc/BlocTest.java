package tn.esprit.tp1yassinejallouli4twin7.TestsBloc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.tp1yassinejallouli4twin7.entities.Bloc;

import tn.esprit.tp1yassinejallouli4twin7.entities.Chambre;
import tn.esprit.tp1yassinejallouli4twin7.entities.Foyer;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IBlocRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IChambreRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IFoyerRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlocTest {

    @Mock
    private IBlocRepo blocRepository;
    @Mock
    private IChambreRepo chambreRepo;

    @Mock
    private IFoyerRepo foyerRepo;

    @InjectMocks
    private BlocServiceImpl blocService;


    @Test
    public void test_valid_bloc_object() {
        // Arrange
        Bloc b = new Bloc();
        // Set properties of b

        // Mock the behavior of blocRepository.save()
        when(blocRepository.save(b)).thenReturn(b);

        // Act
        Bloc result = blocService.ajouterBloc(b);

        // Assert
        assertNotNull(result);
        assertEquals(b, result);
    }
    @Test
    public void test_updateBloc_returnsUpdatedBloc() {
        // Arrange
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(10L);

        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Act
        Bloc updatedBloc = blocService.updateBloc(bloc);

        // Assert
        assertEquals(bloc, updatedBloc);
    }
    @Test
    public void test_deleteBlocWithGivenID() {
        // Arrange
        long idBloc = 1;

        // Act
        blocService.supprimerBloc(idBloc);

        // Assert
        assertNull(blocService.getBloc(idBloc));
    }
    @Test
    public void test_getBlocWithNegativeID() {
        // Arrange
        long idBloc = -1;

        // Act
        Bloc result = blocService.getBloc(idBloc);

        // Assert
        assertNull(result);
    }
    @Test
    public void test_returns_all_blocs_when_repository_not_empty() {
        // Arrange
        List <Bloc> expectedBlocs = new ArrayList <> ();
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();
        expectedBlocs.add(bloc1);
        expectedBlocs.add(bloc2);
        when(blocRepository.findAll()).thenReturn(expectedBlocs);

        // Act
        List<Bloc> actualBlocs = blocService.getAllBlocs();

        // Assert
        assertEquals(expectedBlocs, actualBlocs);
    }
    @Test
    public void test_assign_chambres_to_bloc_with_valid_list_and_valid_bloc_name() {
        // Create a list of chambre numbers
        List<Long> numChambre = new ArrayList<>();
        numChambre.add(1L);
        numChambre.add(2L);

        // Create a mock Bloc object
        Bloc blocMock = new Bloc();
        // Set up any necessary properties of blocMock

        // Mock the behavior of blocRepo.findBlocByNomBloc()
        when(blocRepository.findBlocByNomBloc("nomBloc")).thenReturn(blocMock);

        // Call the method under test
        Bloc result = blocService.affecterChambresABloc(numChambre, "nomBloc");

        // Verify the interaction with the mock
        verify(blocRepository).findBlocByNomBloc("nomBloc");

        // Assert the result
        assertEquals(blocMock, result);
    }

    @Test
    public void test_valid_bloc_and_foyer_names() {
        // Arrange
        String nomBloc = "Bloc1";
        String nomFoyer = "Foyer1";
        Bloc b = new Bloc();
        Foyer f = new Foyer();
        b.setNomBloc(nomBloc);
        f.setNomFoyer(nomFoyer);

        when(blocRepository.findBlocByNomBloc(nomBloc)).thenReturn(b);
        when(foyerRepo.findFoyerByNomFoyer(nomFoyer)).thenReturn(f);

        // Act
        Bloc result = blocService.affecterBlocAFoyer(nomBloc, nomFoyer);

        // Assert
        assertEquals(f, result.getFoyer());
    }

    @Test
    public void test_return_all_blocs_with_foyer() {
        // Arrange
        List<Bloc> expectedBlocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        bloc1.setIdBloc(1L);
        bloc1.setNomBloc("Bloc 1");
        Foyer foyer1 = new Foyer();
        foyer1.setIdFoyer(1L);
        foyer1.setNomFoyer("Foyer 1");
        bloc1.setFoyer(foyer1);
        expectedBlocs.add(bloc1);

        Bloc bloc2 = new Bloc();
        bloc2.setIdBloc(2L);
        bloc2.setNomBloc("Bloc 2");
        Foyer foyer2 = new Foyer();
        foyer2.setIdFoyer(2L);
        foyer2.setNomFoyer("Foyer 2");
        bloc2.setFoyer(foyer2);
        expectedBlocs.add(bloc2);

        when(blocRepository.findAllWithFoyer()).thenReturn(expectedBlocs);

        // Act
        List<Bloc> actualBlocs = blocService.getAllblocsWithFoyer();

        // Assert
        assertEquals(expectedBlocs, actualBlocs);
    }
    @Test
    public void test_return_bloc_with_given_id() {
        // Arrange
        long idBloc = 1;
        Bloc expectedBloc = new Bloc();
        expectedBloc.setIdBloc(idBloc);
        Mockito.when(blocRepository.getBlocByIdBloc(idBloc)).thenReturn(expectedBloc);

        // Act
        Bloc actualBloc = blocService.getBlocById(idBloc);

        // Assert
        assertEquals(expectedBloc, actualBloc);
    }
    @Test
    public void test_getBlocByNom_existingNomBloc() {
        // Arrange
        String nomBloc = "Bloc1";
        Bloc expectedBloc = new Bloc();
        expectedBloc.setNomBloc(nomBloc);
        when(blocRepository.getBlocByNomBloc(nomBloc)).thenReturn(expectedBloc);

        // Act
        Bloc actualBloc = blocService.getBlocByNom(nomBloc);

        // Assert
        assertEquals(expectedBloc, actualBloc);
    }
}
