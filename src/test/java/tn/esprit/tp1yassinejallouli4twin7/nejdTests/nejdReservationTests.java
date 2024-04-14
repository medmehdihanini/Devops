package tn.esprit.tp1yassinejallouli4twin7.nejdTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tp1yassinejallouli4twin7.entities.*;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IBlocRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IChambreRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IEtudiantRepo;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IReservationRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.ReservationServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class nejdReservationTests {

    @Mock
    private IReservationRepo resrvationRepository;
    @Mock
    private IBlocRepo blocRepo;

    @Mock
    private IChambreRepo chambreRepository;

    @Mock
    private IEtudiantRepo etudiantRepository;

    @Mock
    private IBlocRepo iblocRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    public void testAjouter_Reservation() {
        Reservation reservation = new Reservation();
        when(resrvationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.AjouterReservation(reservation);
        verify(resrvationRepository, times(1)).save(reservation);
        assertSame(reservation, result);
    }

    @Test
    public void testUpdateReservation() {
        Long id = 1L;
        String etat = "ACCEPTE";
        Reservation reservation = new Reservation();
        reservation.setIdReservation(id);
        when(resrvationRepository.findById(id)).thenReturn(java.util.Optional.of(reservation));
        when(resrvationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.UpdateReservation(id, etat);
        verify(resrvationRepository, times(1)).findById(id);
        verify(resrvationRepository, times(1)).save(reservation);
        assertEquals(EtatReservation.ACCEPTE, reservation.getEtat());
        assertSame(reservation, result);
    }

    @Test
    public void testSupprimerReservation() {
        Long cinEtudiant = 12345L;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setCin(cinEtudiant);
        Reservation reservation1 = new Reservation();
        reservation1.setNumReservation("asez");
        reservation1.setIdReservation((long) 1);
        reservation1.setEtudiants(new HashSet<>(Arrays.asList(etudiant)));
        Reservation reservation2 = new Reservation();
        reservation2.setNumReservation("asjez");
        reservation2.setIdReservation((long) 2);
        reservation2.setEtudiants(new HashSet<>(Arrays.asList(etudiant)));
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(etudiantRepository.findEtudiantByCin(cinEtudiant)).thenReturn(etudiant);
        when(resrvationRepository.findByEtudiants(etudiant)).thenReturn(reservations);
        reservationService.SupprimerReservation(reservation1.getIdReservation(), cinEtudiant);
        verify(resrvationRepository, times(1)).delete(reservation1);
        assertEquals(Optional.empty(), resrvationRepository.findById(reservation1.getIdReservation()));
    }

    @Test
    public void testGetReservation() {
        // Arrange
        long idReservation = 1L;
        Reservation reservation = new Reservation();
        when(resrvationRepository.findById(idReservation)).thenReturn(java.util.Optional.of(reservation));
        Reservation result = reservationService.GetReservation(idReservation);
        verify(resrvationRepository, times(1)).findById(idReservation);
        assertSame(reservation, result);
    }

    @Test
    public void testGetAllReservation() {
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(resrvationRepository.findAll()).thenReturn(reservations);
        List<Reservation> result = reservationService.GetAllReservation();
        verify(resrvationRepository, times(1)).findAll();
        assertSame(reservations, result);
    }

    @Test
    public void testAjouterReservationEtAssignerAChambreEtAEtudiant() {
        Reservation reservation = new Reservation();
        reservation.setEtudiants(new HashSet<>());
        reservation.setIdReservation(1L);
        Long numChambre = 1L;
        Long cin = 123445L;
        Chambre chambre = new Chambre();
        chambre.setIdChambre(numChambre);
        chambre.setNumeroChambre(numChambre);
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(2L);
        etudiant.setCin(cin);
        when(resrvationRepository.findById(reservation.getIdReservation())).thenReturn(Optional.of(reservation));
        when(chambreRepository.findById(chambre.getIdChambre())).thenReturn(Optional.of(chambre));
        when(etudiantRepository.findEtudiantByCin(cin)).thenReturn(etudiant);
        Reservation result = reservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(reservation, numChambre, cin);
        verify(resrvationRepository, times(1)).findById(reservation.getIdReservation());
        verify(chambreRepository, times(1)).findById(numChambre);
        verify(etudiantRepository, times(1)).findEtudiantByCin(cin);
        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(resrvationRepository, times(1)).save(reservationCaptor.capture());
        Reservation capturedReservation = reservationCaptor.getValue();
        assertNotNull(capturedReservation);
        assertEquals(reservation, capturedReservation);
        assertTrue(chambre.getReservations().contains(reservation));
        assertTrue(reservation.getEtudiants().contains(etudiant));
    }
    @Test
    public void testGetReservationParAnneeUniversitaire() {
        Date debutAnnee = new Date();
        Date finAnnee = new Date();
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(resrvationRepository.findByAnneeUniversitaireBetween(debutAnnee, finAnnee)).thenReturn(reservations);
        long result = reservationService.getReservationParAnneeUniversitaire(debutAnnee, finAnnee);
        verify(resrvationRepository, times(1)).findByAnneeUniversitaireBetween(debutAnnee, finAnnee);
        assertEquals(reservations.size(), result);
    }

    @Test
    public void testAjouterReservation() {
        long idChambre = 1L;
        long cinEtudiant = 12345L;
        Reservation r = new Reservation();
        Chambre ch = new Chambre();
        Etudiant et = new Etudiant();
        Bloc b=new Bloc();
        b.setNomBloc("test");
        ch.setBlocchambre(b);
        LocalDate currentDate = LocalDate.now();
        LocalDate thisyear = currentDate;
        LocalDate nextyear = currentDate.plusYears(1);
        Reservation existingReservation = new Reservation();
        existingReservation.setIdReservation(2L);
        when(chambreRepository.findById(idChambre)).thenReturn(java.util.Optional.of(ch));
        when(etudiantRepository.findEtudiantByCin(cinEtudiant)).thenReturn(et);
        when(resrvationRepository.getCurrentReservationByEtudiantId(cinEtudiant, thisyear, nextyear)).thenReturn(existingReservation);
        when(resrvationRepository.save(r)).thenReturn(r);
        Reservation result = reservationService.ajouterReservation(idChambre, cinEtudiant, r);
        verify(chambreRepository, times(1)).findById(idChambre);
        verify(etudiantRepository, times(1)).findEtudiantByCin(cinEtudiant);
        verify(resrvationRepository, times(1)).getCurrentReservationByEtudiantId(cinEtudiant, thisyear, nextyear);
        verify(resrvationRepository, times(1)).save(r);
        assertEquals(ch.getNumeroChambre() + "-" + ch.getBlocchambre().getNomBloc() + "-" + cinEtudiant, r.getNumReservation());
        assertEquals(existingReservation.getIdReservation(), r.getIdReservation());
        assertEquals(EtatReservation.EN_ATTENTE, r.getEtat());
        assertSame(r, result);
    }
}
