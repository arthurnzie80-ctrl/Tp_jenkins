package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandeServiceIT {

    @Test
    void scenarioAchatComplet() {
        CommandeService service = new CommandeService();
        Panier panier = new Panier();
        Article article = new Article("Téléphone", 499.99, 5);

        assertTrue(service.ajouterAuPanier(panier, article, 2));
        assertEquals(499.99 * 2, service.calculerTotal(panier), 0.01);
        assertTrue(service.passerCommande(panier));
        assertTrue(panier.getArticles().isEmpty());
    }

    @Test
    void scenarioStockInsuffisant() {
        CommandeService service = new CommandeService();
        Panier panier = new Panier();
        Article article = new Article("Tablette", 299.99, 1);

        assertFalse(service.ajouterAuPanier(panier, article, 5));
        assertEquals(0.0, service.calculerTotal(panier));
    }

    @Test
    void scenarioPanierMultipleArticles() {
        CommandeService service = new CommandeService();
        Panier panier = new Panier();
        Article a1 = new Article("Souris", 29.99, 10);
        Article a2 = new Article("Clavier", 59.99, 10);

        service.ajouterAuPanier(panier, a1, 1);
        service.ajouterAuPanier(panier, a2, 1);
        assertEquals(89.98, service.calculerTotal(panier), 0.01);
        assertTrue(service.passerCommande(panier));
    }
}