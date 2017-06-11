package com.softuni.store.services.implementations;

import com.softuni.store.entities.game.Game;
import com.softuni.store.models.bindingModels.GameAddModel;
import com.softuni.store.models.bindingModels.GameEditModel;
import com.softuni.store.models.viewModels.GameAdminView;
import com.softuni.store.models.viewModels.GameEditView;
import com.softuni.store.models.viewModels.GameView;
import com.softuni.store.models.viewModels.GameDetailsView;
import com.softuni.store.repositories.GameRepository;
import com.softuni.store.services.GameService;
import com.softuni.store.utils.parser.interfaces.ModelParser;
import org.modelmapper.PropertyMap;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless
public class GameServiceImpl implements GameService{

    @Inject
    private GameRepository gameRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(GameAddModel gameAddModel) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(gameAddModel.getReleaseDate());
        PropertyMap<GameAddModel, Game> propertyMap = new PropertyMap<GameAddModel, Game>() {
            @Override
            protected void configure() {
                skip(source.getReleaseDate(), destination.getReleaseDate());
            }
        };
        Game game = this.modelParser.convert(gameAddModel, GameAddModel.class, Game.class, propertyMap);
        game.setReleaseDate(date);
        this.gameRepository.create(game);
    }

    @Override
    public void edit(GameEditModel gameEditModel) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(gameEditModel.getReleaseDate());
        PropertyMap<GameEditModel, Game> propertyMap = new PropertyMap<GameEditModel, Game>() {
            @Override
            protected void configure() {
                skip(source.getReleaseDate(), destination.getReleaseDate());
            }
        };
        Game game = this.modelParser.convert(gameEditModel, GameEditModel.class, Game.class, propertyMap);
        game.setReleaseDate(date);
        this.gameRepository.edit(game);
    }

    @Override
    public void delete(Long id) {
        this.gameRepository.delete(id);
    }

    @Override
    public GameDetailsView getGameDetailsViewById(Long id) {
        Game game = this.gameRepository.findById(id);
        GameDetailsView gameView = this.modelParser.convert(game, GameDetailsView.class);
        return gameView;
    }

    @Override
    public List<GameAdminView> getAllGamesAdminView() {
        List<Game> allGames = this.gameRepository.getAllGames();
        List<GameAdminView> allGameHomeViews = new ArrayList<>();

        for (Game game : allGames) {
            GameAdminView view = this.modelParser.convert(game, GameAdminView.class);
            allGameHomeViews.add(view);
        }
        return allGameHomeViews;
    }

    @Override
    public Set<GameView> getAllGames() {
        List<Game> allGames = this.gameRepository.getAllGames();
        Set<GameView> allGameViews = new LinkedHashSet<>();

        for (Game game : allGames) {
            GameView view = this.modelParser.convert(game, GameView.class);
            String gameDescription = game.getDescription();
            if (gameDescription.length() > 300) {
                view.setDescription(game.getDescription().substring(0, 300) + " ...");
            } else {
                view.setDescription(game.getDescription());
            }
            allGameViews.add(view);
        }
        return allGameViews;
    }

    @Override
    public GameView getGameCartViewById(Long id) {
        Game game = this.gameRepository.findById(id);
        GameView gameView = this.modelParser.convert(game, GameView.class);
        String description = gameView.getDescription();
        if (description != null && description.length() > 300) {
            gameView.setDescription(description.substring(0, 300) + " ...");
        }
        return gameView;
    }

    @Override
    public GameEditView getGameEditView(Long id) {
        Game game = this.gameRepository.findById(id);
        GameEditView gameView = null;
        if (game != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(game.getReleaseDate());
            PropertyMap<Game, GameEditView> propertyMap = new PropertyMap<Game, GameEditView>() {
                @Override
                protected void configure() {
                    skip(source.getReleaseDate(), destination.getReleaseDate());
                }
            };
            gameView = this.modelParser.convert(game, Game.class, GameEditView.class, propertyMap);
            gameView.setReleaseDate(date);
        }
        return gameView;
    }
}
