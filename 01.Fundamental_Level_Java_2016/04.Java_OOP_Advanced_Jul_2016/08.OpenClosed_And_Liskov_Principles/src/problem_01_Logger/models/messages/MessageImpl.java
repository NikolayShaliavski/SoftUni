package problem_01_Logger.models.messages;

import problem_01_Logger.contracs.Message;
import problem_01_Logger.enums.ReportLevel;

/**
 * Created by Nikolay Shalyavski on 29.7.2016 г..
 */
public class MessageImpl implements Message {

    private String date;
    private ReportLevel levelType;
    private String text;

    public MessageImpl(String date,
                       ReportLevel levelType,
                       String text) {
        this.date = date;
        this.levelType = levelType;
        this.text = text;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public ReportLevel getLevelType() {
        return this.levelType;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
