package it.corso.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.ArticleDao;
import it.corso.model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	/* private List<Article> createArticleList(){
		List<Article> articles= new ArrayList<>();
		for(int i= 1; i <= 10; i++) {
			
			Article article= new Article();
			article.setId(i);
			article.setDescription("Articolo " + i);
			article.setPrice((new Random().nextDouble() + 0.1) * 100);
			articles.add(article);
		}
		return articles;
	} */

	@Override
	public boolean checkArticleData(String... data) {
		
		if(Pattern.matches(REGEX, data[0])) {
			
			try {
				Double.parseDouble(data[1].replace(",", "."));
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public void createArticle(Article a) {
		
		articleDao.createArticle(a);
		
	}

	@Override
	public Article getArticleById(int id) {
		
		return articleDao.getArticleById(id);
	}

	@Override
	public void updateArticle(Article a) {
		
		articleDao.updateArticle(a);
	}

	@Override
	public void deleteArticle(Article a) {
		
		articleDao.deleteArticle(a);
	}
}
