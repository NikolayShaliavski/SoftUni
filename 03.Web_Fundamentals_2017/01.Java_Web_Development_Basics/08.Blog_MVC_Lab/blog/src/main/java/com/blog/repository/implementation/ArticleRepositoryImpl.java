package com.blog.repository.implementation;

import com.blog.entities.Article;
import com.blog.repository.ArticleRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(ArticleRepository.class)
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Article article) {
        this.entityManager.persist(article);
    }

    @Override
    public void delete(Article article) {
        this.entityManager.remove(article);
    }

    @Override
    public void update(Article article) {
        this.entityManager.refresh(article);
    }

    @Override
    public Article retrieve(Long id) {
        return this.entityManager.find(Article.class, id);
    }

    @Override
    public Boolean exists(Long id) {
        Article article = this.entityManager.find(Article.class, id);
        return article != null;
    }
}
