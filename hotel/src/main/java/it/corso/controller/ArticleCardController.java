package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Article;
import it.corso.service.ArticleService;

@Controller
@RequestMapping("/articlecard")
public class ArticleCardController {
	
	@Autowired
	private ArticleService articleService;
	
	private Article article;
	
	@GetMapping
	public String getPage(
			@RequestParam("id") int id,
			@RequestParam(name= "fe", required= false) String fError,
			Model model) {
		
		boolean formError = fError == null ? false : true; 
		article= articleService.getArticleById(id);
		model.addAttribute("title", "Article Card");
		model.addAttribute("article", article);
		model.addAttribute("formError", formError);
		return "article-card";
	}
	
	@PostMapping("/editarticle")
	public String editArticle(
		@RequestParam("description") String description,
		@RequestParam("price") String price) {
		
		if(!articleService.checkArticleData(description, price))
			return "redirect:/articlecard?id" + article.getId() + "&fe";
		
		article.setDescription(description);
		article.setPrice(Double.parseDouble(price));
		articleService.updateArticle(article);
		return "redirect:/";
	}
}
