package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class ChatMessengerAppl extends JFrame {
    final static Logger LOGGER = LogManager.getLogger(ChatMessengerAppl.class);

    private static final Model MODEL;
    private static final Controller CONTROLLER;
    private static final ViewFactory VIEWS;
    public static final int FRAME_WIDTH = 400;
    public static final int FRAME_HEIGHT = 600;

    static {
        MODEL = Model.getInstance();
        CONTROLLER = Controller.getInstance();
        VIEWS = ViewFactory.getInstance();
        LOGGER.trace("MVC instantiated: "+ MODEL + ";"  + CONTROLLER + ";" + VIEWS);
    }

    private Timer timer;

    public ChatMessengerAppl() {
        super();
        initialize();
    }

    private void initialize() {
        AbstractView.setParent(this);
        MODEL.setParent(this);
        MODEL.initialize();
        CONTROLLER.setParent(this);
        VIEWS.viewRegister("login", LoginPanelView.getInstance());
        VIEWS.viewRegister("chat", ChatPanelView.getInstance());
        timer = new Timer("Server request for update messages");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setTitle("Chat Messenger");
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(getLoginPanelView(), BorderLayout.CENTER);
        this.setContentPane(contentPanel);
    }

    public static Model getModel() {
        return MODEL;
    }

    public static Controller getController() {
        return CONTROLLER;
    }

    public static ViewFactory getViews() {
        return VIEWS;
    }

    public Timer getTimer() {
        return timer;
    }
}
