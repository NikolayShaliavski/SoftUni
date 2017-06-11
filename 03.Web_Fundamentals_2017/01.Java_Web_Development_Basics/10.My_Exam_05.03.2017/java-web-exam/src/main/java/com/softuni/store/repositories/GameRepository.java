package com.softuni.store.repositories;

import com.softuni.store.entities.game.Game;

import java.util.List;

public interface GameRepository {

    void create(Game game);

    void edit(Game game);

    void delete(Long id);

    Game findById(Long id);

    List<Game> getAllGames();

    List<Game> getUserOwnedGames(Long id);
}
