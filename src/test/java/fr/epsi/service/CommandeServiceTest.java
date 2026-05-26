package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceTest {

    private CommandeService service;
    private Panier panier;
    private Article article;

    @BeforeEach
    void setUp() {
        service = new CommandeService();
        panier = new Panier();
        article = new Article("Laptop", 999.99, 10);
    }

    @Test
    void ajouterAuPanier_succes() {
        assertTrue(service.ajouterAuPanier(panier, article, 2));
        assertEquals(2, panier.getArticles().size());
        assertEquals(8, article.getStock());
    }

    @Test
    void ajouterAuPanier_stockInsuffisant() {
        assertFalse(service.ajouterAuPanier(panier, article, 15));
    }

    @Test
    void ajouterAuPanier_articleNull() {
        assertFalse(service.ajouterAuPanier(panier, null, 1));
    }

    @Test
    void ajouterAuPanier_panierNull() {
        assertFalse(service.ajouterAuPanier(null, article, 1));
    }

    @Test
    void passerCommande_succes() {
        service.ajouterAuPanier(panier, article, 1);
        assertTrue(service.passerCommande(panier));
        assertTrue(panier.getArticles().isEmpty());
    }

    @Test
    void passerCommande_panierVide() {
        assertFalse(service.passerCommande(panier));
    }

    @Test
    void passerCommande_panierNull() {
        assertFalse(service.passerCommande(null));
    }

    @Test
    void calculerTotal_succes() {
        service.ajouterAuPanier(panier, article, 2);
        assertEquals(1999.98, service.calculerTotal(panier), 0.01);
    }

    @Test
    void calculerTotal_panierVide() {
        assertEquals(0.0, service.calculerTotal(panier));
    }

    @Test
    void calculerTotal_panierNull() {
        assertEquals(0.0, service.calculerTotal(null));
    }

    @Test
    void ajouterAuPanier_quantiteZero() {
        assertTrue(service.ajouterAuPanier(panier, article, 0));
        assertEquals(0, panier.getArticles().size());
    }
}