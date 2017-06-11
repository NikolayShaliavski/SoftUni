package com.softuni.store.services;

import com.softuni.store.models.bindingModels.GameAddModel;
import com.softuni.store.models.bindingModels.GameEditModel;
import com.softuni.store.models.viewModels.GameAdminView;
import com.softuni.store.models.viewModels.GameDetailsView;
import com.softuni.store.models.viewModels.GameEditView;
import com.softuni.store.models.viewModels.GameView;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface GameService {

    void create(GameAddModel gameAddModel) throws ParseException;

    void edit(GameEditModel gameEditModel) throws ParseException;

    void delete(Long id);

    GameDetailsView getGameDetailsViewById(Long id);

    List<GameAdminView> getAllGamesAdminView();

    Set<GameView> getAllGames();

    GameView getGameCartViewById(Long id);

    GameEditView getGameEditView(Long id);
}
