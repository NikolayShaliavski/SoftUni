package problem_10.contracts;

import problem_10.enums.GemsEnum;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public interface GemCreator {

    Gem createGem(String[] params, GemsEnum type);
}
