package it.corso.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Article;
import it.corso.service.ArticleService;

@Controller
@RequestMapping(path = {"/", "/index", "/home"})
public class IndexController
{
	@Autowired
	ArticleService articleService;
	
	@GetMapping
	public String getPage(Model model)
	{
		model.addAttribute("title", "Home Page");
		model.addAttribute("articles", articleService.getArticles());
		return "index";
	}
	
	@GetMapping("/deletearticle")
	public String deleteArticle(@RequestParam("id") int id) {
		
		Article article= articleService.getArticleById(2);
		articleService.deleteArticle(article);
		return "redirect:/";
	}
}