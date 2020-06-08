package domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Message implements Serializable, Comparable<Message> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getId().equals(message.getId()) &&
                getText().equals(message.getText()) &&
                getUserNameFrom().equals(message.getUserNameFrom()) &&
                getUserNameTo().equals(message.getUserNameTo()) &&
                getMoment().equals(message.getMoment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getUserNameFrom(), getUserNameTo(), getMoment());
    }

    public int compareTo(Message o) {
        if (getMoment().equals(o.getMoment()))
            return getId().compareTo(o.getId());
        else return getMoment().compareTo(o.getMoment());
        // return 0;
    }
}
