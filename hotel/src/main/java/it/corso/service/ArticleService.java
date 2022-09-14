package it.corso.service;
import java.util.List;
import it.corso.model.Article;

public interface ArticleService {

	String REGEX= "[a-zA-Z0-9\\s]{1,255}";
	boolean checkArticleData(String... data);
	void createArticle(Article a);
	List<Article> getArticles();
	Article getArticleById(int id);
	void updateArticle(Article a);
	void deleteArticle(Article a);
}
