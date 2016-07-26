package pr0304Barracks.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Nikolay Shalyavski on 26.7.2016 г..
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandName {

    String name();
}
