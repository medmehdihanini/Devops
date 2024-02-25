package tn.esprit.tp1yassinejallouli4twin7.testEtudiant;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.tp1yassinejallouli4twin7.entities.Etudiant;
import tn.esprit.tp1yassinejallouli4twin7.entities.Universite;
import tn.esprit.tp1yassinejallouli4twin7.repositories.IEtudiantRepo;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.EtudiantServiceImpl;
import tn.esprit.tp1yassinejallouli4twin7.services.IMPL.UniversiteServiceImpl;
import tn.esprit.tp1yassinejallouli4twin7.services.IUniversiteService;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class testapi {
    @Autowired
    private UniversiteServiceImpl universiteService;
    @Autowired
    EtudiantServiceImpl etudiantService;
    @Autowired
    IEtudiantRepo etudiantRepo;
    @Test
    public void testAjouterUniversite() {
        Universite universite = new Universite();
        universite.setNomUniversite("test");
        universite.setAdresse("azer");
        Universite savedUniversite = universiteService.ajouterUniversite(universite);
        assertNotNull(savedUniversite);
    }



    @Test
    public void test_add_new_student_successfully() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setRole("user");
        etudiant.setEtat(0);

        // Act
        Boolean result = etudiantService.AjouterEtudiantAPI(etudiant);

        // Assert
        assertNotNull(result);
    }



    @Test
    public void test_update_etudiant_success() {
        // Arrange
        Etudiant etudiant = new Etudiant();

        // Act
        Etudiant result = etudiantService.UpdateEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals(etudiant, result);
    }



}
