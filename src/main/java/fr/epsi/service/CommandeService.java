package fr.epsi.service;

import fr.epsi.model.Panier;

public class CommandeService {

    /**
     * Calcule le total du panier
     * Lève IllegalArgumentException si panier null ou vide
     */
    public double calculerTotal(Panier panier) {
        if (panier == null || panier.getArticles().isEmpty()) {
            throw new IllegalArgumentException("Le panier ne peut pas être null ou vide");
        }
        return panier.getTotal();
    }

    /**
     * Applique une remise entre 0 et 100%
     * Lève IllegalArgumentException si pourcentage invalide
     */
    public double appliquerRemise(double total, int pourcentage) {
        if (pourcentage < 0 || pourcentage > 100) {
            throw new IllegalArgumentException("Le pourcentage doit être entre 0 et 100");
        }
        return total * (1 - pourcentage / 100.0);
    }

    /**
     * Catégorise la commande selon le total
     * PETITE < 50€, MOYENNE entre 50€ et 200€, GRANDE >= 200€
     */
    public String categoriserCommande(double total) {
        if (total < 50) {
            return "PETITE";
        } else if (total < 200) {
            return "MOYENNE";
        } else {
            return "GRANDE";
        }
    }
}