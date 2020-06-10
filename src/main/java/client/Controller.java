package client;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static client.LoginPanelView.LOGIN_ACTION_COMMAND;

public class Controller implements ActionListener {
    private ChatMessengerAppl parent;
    private Command command;

    // Паттерн Синглтон
    private Controller() {}
    public static Controller getInstance() {
        return ControllerHolder.INSTANCE;
    }

    private static class ControllerHolder {
        private static final Controller INSTANCE = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(e);
        command.execute();
    }

    private void action(ActionEvent e) {
        String commandName = e.getActionCommand();
        switch (commandName) {
            case LOGIN_ACTION_COMMAND:
                LoginPanelView view = Utility.findParent(
                        (Component)e.getSource(), LoginPanelView.class);
                if(! EmailValidator.getInstance().isValid(view.getUserNameField().getText()) ||
                   ! InetAddressValidator.getInstance().isValid(view.getServerIpAdressField().getText())) {
                    command = new LoginErrorCommand(view);
                } else {
                    parent.getModel().setCurrentUser(view.getUserNameField().getText());
                    parent.getModel().setServerIpAddress(view.getServerIpAdressField().getText());
                    command = new ShowChatViewCommand(parent, view);
                    
                }
        }
    }

    public void setParent(ChatMessengerAppl parent) {
        this.parent = parent;
    }
}
