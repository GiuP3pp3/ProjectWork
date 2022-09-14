package it.corso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.corso.model.Article;
import it.corso.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name= "fe", required= false) String fError) {
		
		boolean formError= fError == null ? false : true;
		model.addAttribute("title", "New Article");
		model.addAttribute("article", new Article());
		model.addAttribute("formError", formError);
		return "article";
	}
	
	@PostMapping("/savestandard")
	public String saveStandard(
			@RequestParam("artDescription") String description,
			@RequestParam("artPrice") String price) {
		
		if(!articleService.checkArticleData(description,price))
			return "redirect:/article?fe";
		
		Article article= new Article();
		article.setDescription(description);
		article.setPrice(Double.parseDouble(price));
		articleService.createArticle(article);
		//... invocare un metodo del service a cui passare l'aricolo perchè vada
		// registrato nel db (noi non stiamo ancora lavorando nel db, quindi
		// non è il nostro caso)
		return "redirect:/";
	}
	
	@PostMapping
	public String saveArticle(
			@Valid @ModelAttribute("article") Article article,
			BindingResult result) {
		
		if(result.hasErrors())
			return "article";
		
		articleService.createArticle(article);
		return "redirect:/";
	}
	
	@PostMapping("/saveclient")
	@ResponseBody
	public String saveClient(
			@RequestParam("description") String description,
			@RequestParam("price") String price) {
		
		Article article= new Article();
		article.setDescription(description);
		article.setPrice(Double.parseDouble(price));
		articleService.createArticle(article);
		return "save success";
	}
}
