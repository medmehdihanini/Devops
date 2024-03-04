package tn.esprit.tp1yassinejallouli4twin7.testEtudiant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.tp1yassinejallouli4twin7.entities.Etudiant;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IEtudiantRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.EtudiantServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EtudiantServiceImplTest {

    @Mock
    private IEtudiantRepo etudiantRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Test
    public void testAjouterEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEt("John");
        etudiant.setPrenomet("Doe");
        etudiant.setCin(123456789);
        etudiant.setEcole("ABC School");
        etudiant.setEmail("john.doe@example.com");
        etudiant.setPassoword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.AjouterEtudiant(etudiant);

        // Assert
        assertEquals("user", result.getRole());
        assertEquals(1, result.getEtat());
        assertEquals("encodedPassword", result.getPassoword());
        verify(passwordEncoder).encode("password");
        verify(etudiantRepository, times(2)).save(any(Etudiant.class)); // Ensure save is called twice
    }
}
