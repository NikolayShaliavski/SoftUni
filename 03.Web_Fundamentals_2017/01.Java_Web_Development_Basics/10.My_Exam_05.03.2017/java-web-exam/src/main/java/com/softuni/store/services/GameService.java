package com.softuni.store.services;

import com.softuni.store.models.bindingModels.GameAddModel;
import com.softuni.store.models.bindingModels.GameEditModel;
import com.softuni.store.models.viewModels.GameAdminView;
import com.softuni.store.models.viewModels.GameCartView;
import com.softuni.store.models.viewModels.GameHomeView;
import com.softuni.store.models.viewModels.GameDetailsView;

import java.text.ParseException;
import java.util.List;

public interface GameService {

    void create(GameAddModel gameAddModel) throws ParseException;

    void edit(GameEditModel gameEditModel);

    GameDetailsView getGameDetailsViewById(Long id);

    List<GameAdminView> getAllGamesAdminView();

    List<GameHomeView> getAllGames();

    List<GameHomeView> getUserOwnedGames(Long id);

    GameCartView getGameCartViewById(Long id);
}
