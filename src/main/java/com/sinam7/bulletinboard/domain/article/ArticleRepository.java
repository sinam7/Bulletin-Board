package com.sinam7.bulletinboard.domain.article;

import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ArticleRepository {

    private static final Map<Long, Article> articleStore = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    public Long save(Article article) {
        article.setId(++sequence);
        article.setTimestamp(new Timestamp(System.currentTimeMillis()));
        articleStore.put(article.getId(), article);
        return article.getId();
    }

    public Article findById(Long id) {
        return articleStore.get(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(articleStore.values());
    }

    public boolean update(Long id, Article updateArticle) {
        if (articleStore.containsKey(id)) {
            updateArticle.setId(id);
            articleStore.put(id, updateArticle);
            return true;
        }

        return false;
    }

    public void remove(Long id) {
        articleStore.remove(id);
    }
}
