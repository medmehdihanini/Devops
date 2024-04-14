package tn.esprit.tp1yassinejallouli4twin7.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tn.esprit.tp1yassinejallouli4twin7.entities.Foyer;


import java.util.List;

public interface IFoyerServices {
    Foyer ajouterFoyer(Foyer f);
    Foyer updateFoyer(Foyer f);
    void supprimerFoyer(long idFoyer);
    Foyer getFoyer(long idFoyer);
    List<Foyer> getAllFoyer();

    Page<Foyer> getAllFoyerWP(Pageable pageable);

    Foyer ajouterFoyerEtAffecterAUniversite(Foyer f,Long idUniv);

    Foyer ajouterFoyerEtAffecterAResto(Foyer f, long idResto);
    Foyer desaffecterFoyerAResto(long idFoyer, long idResto) ;



}
