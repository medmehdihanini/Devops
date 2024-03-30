package tn.esprit.tp1yassinejallouli4twin7.chambreTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.tp1yassinejallouli4twin7.entities.Chambre;
import tn.esprit.tp1yassinejallouli4twin7.entities.Bloc;
import tn.esprit.tp1yassinejallouli4twin7.entities.TypeChambre;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IBlocRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IChambreRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IFoyerRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.ChambreServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChambreTest {

    @Mock
    private IChambreRepo chambreRepo;

    @Mock
    private IBlocRepo blocRepo;

    @Mock
    private IFoyerRepo foyerRepo;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Test
    public void testAjouterChambre() {
        // Créer une chambre fictive
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(101L);

        // Mock du comportement de la méthode save du repo de la chambre
        when(chambreRepo.save(chambre)).thenReturn(chambre);

        // Appel de la méthode de service pour ajouter une chambre
        Chambre result = chambreService.ajouterChambre(chambre);

        // Vérification que la chambre retournée n'est pas nulle
        assertNotNull(result);
        // Vérification que la chambre retournée est la même que celle envoyée
        assertEquals(chambre, result);
    }

    @Test
    public void testSupprimerChambre() {
        // Créer une chambre fictive avec un ID
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);

        // Appel de la méthode de service pour supprimer une chambre
        chambreService.supprimerChambre(1L);

        // Vérification que la chambre avec cet ID est maintenant null
        assertNull(chambreService.getChambre(1L));
    }

    // Ajoutez d'autres tests selon vos besoins pour couvrir les différentes fonctionnalités de votre service de chambre
    @Test
    public void testGetChambre() {
        // Créer une chambre fictive avec un ID
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);

        // Mock du comportement de la méthode findById du repo de la chambre
        when(chambreRepo.findById(1L)).thenReturn(java.util.Optional.of(chambre));

        // Appel de la méthode de service pour récupérer une chambre par ID
        Chambre result = chambreService.getChambre(1L);

        // Vérification que la chambre retournée n'est pas nulle
        assertNotNull(result);
        // Vérification que la chambre retournée est la même que celle avec l'ID spécifié
        assertEquals(chambre, result);
    }

    @Test
    public void testGetAllChambres() {
        // Créer une liste fictive de chambres
        List<Chambre> chambreList = new ArrayList<>();
        chambreList.add(new Chambre());
        chambreList.add(new Chambre());

        // Mock du comportement de la méthode findAll du repo de la chambre
        when(chambreRepo.findAll()).thenReturn(chambreList);

        // Appel de la méthode de service pour récupérer toutes les chambres
        List<Chambre> result = chambreService.getAllChambres();

        // Vérification que la liste retournée n'est pas nulle
        assertNotNull(result);
        // Vérification que la taille de la liste retournée est la même que celle de la liste fictive
        assertEquals(chambreList.size(), result.size());
    }
    @Test
    public void testUpdateChambre() {
        // Créer une chambre fictive avec un ID
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);

        // Mock du comportement de la méthode save du repo de la chambre
        when(chambreRepo.save(chambre)).thenReturn(chambre);

        // Appel de la méthode de service pour mettre à jour une chambre
        Chambre result = chambreService.updateChambre(chambre);

        // Vérification que la chambre retournée n'est pas nulle
        assertNotNull(result);
        // Vérification que la chambre retournée est la même que celle envoyée
        assertEquals(chambre, result);
    }

   /* @Test
    public void testGetChambresParNomBloc() {
        // Créer une liste fictive de chambres
        List<Chambre> chambreList = new ArrayList<>();
        chambreList.add(new Chambre());
        chambreList.add(new Chambre());

        // Mock du comportement de la méthode findBlocByNomBloc du repo de bloc
        when(blocRepo.findBlocByNomBloc("BlocA")).thenReturn(new Bloc());

        // Mock du comportement de la méthode getChambres du repo de chambre
        when(chambreRepo.findByBlocchambreAndTypeC(new Bloc(), TypeChambre.SIMPLE)).thenReturn(chambreList);

        // Appel de la méthode de service pour récupérer les chambres par nom de bloc
        List<Chambre> result = chambreService.getChambresParNomBloc("BlocA");

        // Vérification que la liste retournée n'est pas nulle
        assertNotNull(result);
        // Vérification que la taille de la liste retournée est la même que celle de la liste fictive
        assertEquals(chambreList.size(), result.size());
    }

    @Test
    public void testNbChambreParTypeEtBloc() {
        // Créer un bloc fictif avec un ID
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);

        // Créer une liste fictive de chambres
        List<Chambre> chambreList = new ArrayList<>();
        chambreList.add(new Chambre());
        chambreList.add(new Chambre());
        chambreList.add(new Chambre());

        // Mock du comportement de la méthode findById du repo de bloc
        when(blocRepo.findById(1L)).thenReturn(Optional.of(bloc));

        // Mock du comportement de la méthode size du repo de chambre
        when(chambreRepo.findByBlocchambreAndTypeC(bloc, TypeChambre.SIMPLE)).thenReturn(chambreList);

        // Appel de la méthode de service pour obtenir le nombre de chambres par type et bloc
        long result = chambreService.nbChambreParTypeEtBloc(TypeChambre.SIMPLE, 1L);

        // Vérification que le résultat correspond au nombre de chambres dans la liste fictive
        assertEquals(chambreList.size(), result);
    }

*/

}
