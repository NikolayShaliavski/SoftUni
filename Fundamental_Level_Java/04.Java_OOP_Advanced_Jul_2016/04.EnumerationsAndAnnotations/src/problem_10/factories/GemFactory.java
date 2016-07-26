package problem_10.factories;

import problem_10.contracts.Gem;
import problem_10.enums.GemsEnum;
import problem_10.models.gems.Amethyst;
import problem_10.models.gems.Emerald;
import problem_10.models.gems.Ruby;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class GemFactory {

    public static Gem createGem(GemsEnum type) {

        Gem gem = null;
        switch (type) {
            case RUBY:
                gem = new Ruby();
                break;
            case EMERALD:
                gem = new Emerald();
                break;
            case AMETHYST:
                gem = new Amethyst();
                break;
        }
        return gem;
    }
}
