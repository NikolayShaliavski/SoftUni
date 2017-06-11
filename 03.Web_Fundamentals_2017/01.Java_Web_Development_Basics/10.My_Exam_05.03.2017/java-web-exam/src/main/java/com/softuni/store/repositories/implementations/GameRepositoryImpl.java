package com.softuni.store.repositories.implementations;

import com.softuni.store.entities.game.Game;
import com.softuni.store.repositories.GameRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Game game) {
        this.entityManager.persist(game);
    }

    @Override
    public void edit(Game game) {
        this.entityManager.merge(game);
    }

    @Override
    public void delete(Long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Game AS g " +
                "WHERE g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Game findById(Long id) {
        return this.entityManager.find(Game.class, id);
    }

    @Override
    public List<Game> getAllGames() {
        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g ");
        List<Game> allGames = query.getResultList();
        return allGames;
    }

    @Override
    public List<Game> getUserOwnedGames(Long id) {
        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g " +
                "JOIN g.owners AS u " +
                "WHERE u.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
