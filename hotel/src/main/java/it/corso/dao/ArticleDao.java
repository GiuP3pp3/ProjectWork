package it.corso.dao;

import java.util.List;

import it.corso.model.Article;

// Questo è il CRUD
public interface ArticleDao {

	void createArticle(Article a);
	Article getArticleById(int id);
	List<Article> getArticles();
	void updateArticle(Article a);
	void deleteArticle(Article a);
}
