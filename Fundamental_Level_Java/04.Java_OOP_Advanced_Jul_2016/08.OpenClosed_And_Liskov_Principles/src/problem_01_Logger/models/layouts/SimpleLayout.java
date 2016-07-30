package problem_01_Logger.models.layouts;

import problem_01_Logger.contracs.Layout;

/**
 * Created by Nikolay Shalyavski on 29.7.2016 г..
 */
public class SimpleLayout implements Layout {

    @Override
    public String getLayout() {
        return "%s - %s - %s";
    }
}
