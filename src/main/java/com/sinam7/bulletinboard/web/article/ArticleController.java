package com.sinam7.bulletinboard.web.article;

import com.sinam7.bulletinboard.domain.article.Article;
import com.sinam7.bulletinboard.domain.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping
    public String articleList(Model model) {
        log.info("articleList() call");
        model.addAttribute("articles", articleRepository.findAll());
        return "article/articleList";
    }

    @GetMapping("/{id}")
    public String article(@PathVariable("id") Long id, Model model) {
        log.info("article({}) call", id);
        model.addAttribute("article", articleRepository.findById(id));
        return "article/articleView";
    }

    // TODO 새 글 작성시 로그인 여부 검증
    @GetMapping("/add")
    public String addArticleForm(@ModelAttribute("article") Article article) {
        log.info("addArticleForm() call - GET");
        return "article/addArticleForm";
    }

    @PostMapping("/add")
    public String addArticle(@ModelAttribute("article") Article article) {
        log.info("addArticle() call - POST");
        Long id = articleRepository.save(article);
        return "redirect:/board/" + id;
    }

    // TODO 글 수정시 작성자 일치 여부 검증
    @GetMapping("/{id}/edit")
    public String editArticleForm(@PathVariable("id") Long id, Model model) {
        log.info("editArticle({}) call", id);
        model.addAttribute("article", articleRepository.findById(id));
        return "article/editArticleForm";
    }

    @PostMapping("/{id}/edit")
    public String editArticle(@PathVariable("id") Long id, @ModelAttribute("article") Article article) {
        log.info("editArticle({}) call", id);
        articleRepository.update(id, article);
        return "redirect:/board/" + id;
    }
}
