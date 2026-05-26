package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceIT {

    @Test
    @DisplayName("Scénario complet : achat avec remise et catégorisation")
    void scenarioAchatAvecRemiseEtCategorisation() {
        // GIVEN
        CommandeService service = new CommandeService();
        Panier panier = new Panier();
        panier.ajouter(new Article("Laptop", 150.0, 5), 1);
        panier.ajouter(new Article("Souris", 25.0, 10), 2);

        // WHEN
        double total = service.calculerTotal(panier);
        double totalRemise = service.appliquerRemise(total, 10);
        String categorie = service.categoriserCommande(totalRemise);

        // THEN
        assertEquals(200.0, total, 0.001);
        assertEquals(180.0, totalRemise, 0.001);
        assertEquals("MOYENNE", categorie);
    }

    @Test
    @DisplayName("Scénario grande commande sans remise")
    void scenarioGrandeCommandeSansRemise() {
        // GIVEN
        CommandeService service = new CommandeService();
        Panier panier = new Panier();
        panier.ajouter(new Article("Ordinateur", 800.0, 3), 1);

        // WHEN
        double total = service.calculerTotal(panier);
        double totalRemise = service.appliquerRemise(total, 0);
        String categorie = service.categoriserCommande(totalRemise);

        // THEN
        assertEquals(800.0, total, 0.001);
        assertEquals(800.0, totalRemise, 0.001);
        assertEquals("GRANDE", categorie);
    }

    @Test
    @DisplayName("Scénario petite commande avec remise maximale")
    void scenarioPetiteCommandeRemiseMaximale() {
        // GIVEN
        CommandeService service = new CommandeService();
        Panier panier = new Panier();
        panier.ajouter(new Article("Stylo", 10.0, 10), 3);

        // WHEN
        double total = service.calculerTotal(panier);
        double totalRemise = service.appliquerRemise(total, 50);
        String categorie = service.categoriserCommande(totalRemise);

        // THEN
        assertEquals(30.0, total, 0.001);
        assertEquals(15.0, totalRemise, 0.001);
        assertEquals("PETITE", categorie);
    }
}