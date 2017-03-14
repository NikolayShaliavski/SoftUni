package com.softuni.store.services.implementations;

import com.softuni.store.entities.game.Game;
import com.softuni.store.models.bindingModels.GameAddModel;
import com.softuni.store.models.bindingModels.GameEditModel;
import com.softuni.store.models.viewModels.GameAdminView;
import com.softuni.store.models.viewModels.GameCartView;
import com.softuni.store.models.viewModels.GameHomeView;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        Game game = this.modelParser.convert(gameAddModel, Game.class, propertyMap);
        game.setReleaseDate(date);
        this.gameRepository.create(game);
    }

    @Override
    public void edit(GameEditModel gameEditModel) {
        Game game = this.modelParser.convert(gameEditModel, Game.class);
        this.gameRepository.edit(game);
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
    public List<GameHomeView> getAllGames() {
        List<Game> allGames = this.gameRepository.getAllGames();
        List<GameHomeView> allGameHomeViews = new ArrayList<>();

        for (Game game : allGames) {
            GameHomeView view = this.modelParser.convert(game, GameHomeView.class);
            view.setDescription(game.getDescription().substring(0, 300));
            allGameHomeViews.add(view);
        }
        return allGameHomeViews;
    }

    @Override
    public List<GameHomeView> getUserOwnedGames(Long id) {
        List<Game> allGames = this.gameRepository.getUserOwnedGames(id);
        List<GameHomeView> allGameHomeViews = new ArrayList<>();

        for (Game game : allGames) {
            GameHomeView view = this.modelParser.convert(game, GameHomeView.class);
            view.setDescription(game.getDescription().substring(0, 300));
            allGameHomeViews.add(view);
        }
        return allGameHomeViews;
    }

    @Override
    public GameCartView getGameCartViewById(Long id) {
        Game game = this.gameRepository.findById(id);
        GameCartView gameView = this.modelParser.convert(game, GameCartView.class);
        return gameView;
    }
}
