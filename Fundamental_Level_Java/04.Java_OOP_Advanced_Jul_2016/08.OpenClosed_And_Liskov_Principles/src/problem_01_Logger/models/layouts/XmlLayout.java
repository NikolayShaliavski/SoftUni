package problem_01_Logger.models.layouts;

import problem_01_Logger.contracs.Layout;

/**
 * Created by Nikolay Shalyavski on 29.7.2016 г..
 */
public class XmlLayout implements Layout {

    @Override
    public String getLayout() {
        return "<log>\r\n\t<date>%s</date>\r\n\t<level>%s</level>\r\n\t<message>%s</message>\r\n</log>";
    }
}
