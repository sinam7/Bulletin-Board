package com.sinam7.bulletinboard.web.article;

import com.sinam7.bulletinboard.domain.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
