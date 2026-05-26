package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;

public class CommandeService {

    public boolean ajouterAuPanier(Panier panier, Article article, int quantite) {
        if (article == null || panier == null) {
            return false;
        }
        if (article.getStock() < quantite) {
            return false;
        }
        for (int i = 0; i < quantite; i++) {
            panier.ajouterArticle(article);
        }
        article.setStock(article.getStock() - quantite);
        return true;
    }

    public boolean passerCommande(Panier panier) {
        if (panier == null || panier.getArticles().isEmpty()) {
            return false;
        }
        panier.vider();
        return true;
    }

    public double calculerTotal(Panier panier) {
        if (panier == null) {
            return 0.0;
        }
        return panier.getTotal();
    }
}