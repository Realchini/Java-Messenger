package client;

import domain.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Model {
    private ChatMessengerAppl parent;
    private String currentUser;
    private String loggedUser;
    private String lastMessageText;
    private Set<Message> messages;
    private Long lastMessageId;
    private String serverIpAddress = "127.0.0.1";

    // Singleton паттерн
    private Model() {}

    public static Model getInstance() {
        return ModelHolder.INSTANCE;
    }

    public String messagesToString() {
        return messages.toString();
    }

    public void addMessages(List<Message> messages) {
        this.getMessages().addAll(messages);
        parent.getChatPanelView(false).modelChangeedNotification(messages.toString());
    }

    private static class ModelHolder {
        private static final Model INSTANCE = new Model();
    }

    // Setting up the model's field
    public void initialize() {
        setMessages(new TreeSet<Message>(){
            @Override
            public String toString() {
                StringBuilder result = new StringBuilder("<html><body id='body'>");
                Iterator<Message> i = iterator();
                while(i.hasNext()) {
                    result.append(i.next().toString()).append("\n");
                }
                return result.append("</body></html>").toString();
            }
        });
        lastMessageId = 0L;
        currentUser = "";
        loggedUser = "";
        lastMessageText = "";
    }

    // геттеры и сеттеры
    public ChatMessengerAppl getParent() {
        return parent;
    }

    public void setParent(ChatMessengerAppl parent) {
        this.parent = parent;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public void setLastMessageText(String lastMessageText) {
        this.lastMessageText = lastMessageText;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }

    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }
}
