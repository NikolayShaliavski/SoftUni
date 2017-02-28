package com.blog.repository;

import com.blog.entities.Article;

public interface ArticleRepository {

    void create(Article article);

    void delete(Article article);

    void update(Article article);

    Article retrieve(Long id);

    Boolean exists(Long id);
}
