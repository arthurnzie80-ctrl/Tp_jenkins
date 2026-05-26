package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceTest {

    private CommandeService service;
    private Panier panier;

    @BeforeEach
    void setUp() {
        service = new CommandeService();
        panier  = new Panier();
    }

    // ── Tests calculerTotal ──

    @Test
    @DisplayName("Total correct pour 3 stylos à 2€")
    void calculerTotal_TroisStylos_RetourneSix() {
        // GIVEN
        panier.ajouter(new Article("Stylo", 2.0, 10), 3);
        // WHEN
        double total = service.calculerTotal(panier);
        // THEN
        assertEquals(6.0, total, 0.001);
    }

    @Test
    @DisplayName("Total correct pour plusieurs articles")
    void calculerTotal_PlusieursArticles_RetourneSomme() {
        // GIVEN
        panier.ajouter(new Article("Stylo",  2.0, 10), 3);
        panier.ajouter(new Article("Cahier", 5.0, 10), 2);
        // WHEN
        double total = service.calculerTotal(panier);
        // THEN
        assertEquals(16.0, total, 0.001);
    }

    @Test
    @DisplayName("Panier vide lève une IllegalArgumentException")
    void calculerTotal_PanierVide_LeveException() {
        // GIVEN — panier vide créé dans setUp()
        // WHEN + THEN
        assertThrows(
            IllegalArgumentException.class,
            () -> service.calculerTotal(panier)
        );
    }

    @Test
    @DisplayName("Panier null lève une IllegalArgumentException")
    void calculerTotal_PanierNull_LeveException() {
        // WHEN + THEN
        assertThrows(
            IllegalArgumentException.class,
            () -> service.calculerTotal(null)
        );
    }

    // ── Tests appliquerRemise ──

    @Test
    @DisplayName("Remise 10% sur 100€ = 90€")
    void appliquerRemise_DixPourcent_RetourneQuatreVingtDix() {
        // GIVEN
        // WHEN
        double resultat = service.appliquerRemise(100.0, 10);
        // THEN
        assertEquals(90.0, resultat, 0.001);
    }

    @Test
    @DisplayName("Remise 0% ne change pas le total")
    void appliquerRemise_ZeroPourcent_RetourneTotalInchange() {
        // GIVEN
        // WHEN
        double resultat = service.appliquerRemise(100.0, 0);
        // THEN
        assertEquals(100.0, resultat, 0.001);
    }

    @Test
    @DisplayName("Remise 100% retourne zéro")
    void appliquerRemise_CentPourcent_RetourneZero() {
        // GIVEN
        // WHEN
        double resultat = service.appliquerRemise(200.0, 100);
        // THEN
        assertEquals(0.0, resultat, 0.001);
    }

    @Test
    @DisplayName("Remise négative lève une IllegalArgumentException")
    void appliquerRemise_RemiseNegative_LeveException() {
        // WHEN + THEN
        assertThrows(
            IllegalArgumentException.class,
            () -> service.appliquerRemise(100.0, -5)
        );
    }

    @Test
    @DisplayName("Remise > 100 lève une IllegalArgumentException")
    void appliquerRemise_RemiseSupCent_LeveException() {
        // WHEN + THEN
        assertThrows(
            IllegalArgumentException.class,
            () -> service.appliquerRemise(100.0, 150)
        );
    }

    // ── Tests categoriserCommande ──

    @Test
    @DisplayName("Total 30€ = PETITE commande")
    void categoriserCommande_TrenteEuros_RetournePetite() {
        // GIVEN
        // WHEN
        String categorie = service.categoriserCommande(30.0);
        // THEN
        assertEquals("PETITE", categorie);
    }

    @Test
    @DisplayName("Total 100€ = MOYENNE commande")
    void categoriserCommande_CentEuros_RetourneMoyenne() {
        // GIVEN
        // WHEN
        String categorie = service.categoriserCommande(100.0);
        // THEN
        assertEquals("MOYENNE", categorie);
    }

    @Test
    @DisplayName("Total 300€ = GRANDE commande")
    void categoriserCommande_TroisCentEuros_RetourneGrande() {
        // GIVEN
        // WHEN
        String categorie = service.categoriserCommande(300.0);
        // THEN
        assertEquals("GRANDE", categorie);
    }
}