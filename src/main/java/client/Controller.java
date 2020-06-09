package client;

public class Controller {
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

    public void setParent(ChatMessengerAppl parent) {
        this.parent = parent;
    }
}
