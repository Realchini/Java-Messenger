package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private ChatMessengerAppl parent;
    private Command command;

    // Паттерн Синглтон
    private Controller() {}
    public static Controller getInstance() {
        return ControllerHolder.INSTANCE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private static class ControllerHolder {
        private static final Controller INSTANCE = new Controller();
    }

    public void setParent(ChatMessengerAppl parent) {
        this.parent = parent;
    }
}
