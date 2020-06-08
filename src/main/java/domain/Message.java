package domain;

import java.util.Calendar;

public class Message {
    private Long id;
    private String text;
    private String userNameFrom, userNameTo;
    private Calendar moment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserNameFrom() {
        return userNameFrom;
    }

    public void setUserNameFrom(String userNameFrom) {
        this.userNameFrom = userNameFrom;
    }

    public String getUserNameTo() {
        return userNameTo;
    }

    public void setUserNameTo(String userNameTo) {
        this.userNameTo = userNameTo;
    }

    public Calendar getMoment() {
        return moment;
    }

    public void setMoment(Calendar moment) {
        this.moment = moment;
    }
}
