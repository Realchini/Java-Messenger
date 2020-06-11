package client;

import java.util.Timer;

public class ShowChatViewCommand implements Command {
    private ChatMessengerAppl appl;
    private LoginPanelView view;
    public ShowChatViewCommand(ChatMessengerAppl parent, LoginPanelView view) {
        appl = parent;
        this.view = view;
    }

    @Override
    public void execute() {
        Utility.messagesUpdate(appl);
        appl.getModel().setLoggedUser(view.getUserNameField().getText());
        view.clearFields();
        view.setVisible(false);
        appl.setTimer(new Timer());
        appl.getTimer().scheduleAtFixedRate(new UpdateMessageTask(appl),
                ChatMessengerAppl.DELAY, ChatMessengerAppl.PERIOD);
        appl.showChatPanelView();
    }
}
