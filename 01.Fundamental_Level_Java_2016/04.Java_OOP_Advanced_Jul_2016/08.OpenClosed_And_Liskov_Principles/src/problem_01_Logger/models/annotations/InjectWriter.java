package problem_01_Logger.models.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Nikolay Shalyavski on 30.7.2016 г..
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectWriter {

    String name();
}
