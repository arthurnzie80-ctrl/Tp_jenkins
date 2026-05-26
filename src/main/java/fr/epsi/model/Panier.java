package fr.epsi.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Article> articles = new ArrayList<>();

    public void ajouterArticle(Article article) {
        articles.add(article);
    }

    public void retirerArticle(Article article) {
        articles.remove(article);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public double getTotal() {
        return articles.stream()
                .mapToDouble(Article::getPrix)
                .sum();
    }

    public void vider() {
        articles.clear();
    }
}