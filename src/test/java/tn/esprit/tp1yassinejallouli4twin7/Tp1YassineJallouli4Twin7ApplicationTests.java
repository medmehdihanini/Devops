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

@SpringBootTest
class Tp1YassineJallouli4Twin7ApplicationTests {

    @Autowired
    private IFoyerServices foyerService;

    @Autowired
    private IFoyerRepo foyerRepository;

    @Test
    void contextLoads() {
    }

}