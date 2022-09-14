package it.corso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import it.corso.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public void createArticle(Article a) {
		
		// String sql = "INSERT INTO articles (description,price) VALUES ('"+ a.getDescription() + "','" + a.getPrice() + ")";
		manager.persist(a);
	}

	@Override
	public Article getArticleById(int id) {
		
		return manager.find(Article.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticles() {
		
		// String sql= "SELECT * FROM articles";
		String jpql= "SELECT a FROM Article a";  //preferibile quando si lavora in ORM, perché opera sulla classe, non sulla tabella
		// return manager.createNativeQuery(sql).getResultlist();
		return manager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public void updateArticle(Article a) {
		
		manager.merge(a);
	}

	@Override
	@Transactional
	public void deleteArticle(Article a) {
		
		manager.remove(manager.merge(a));   // una sorta di bug di hybernate che non ci consente di cancellare direttamente l'article quando non è attached, quindi con il merge facciamo un attach fittizio
	}
}
