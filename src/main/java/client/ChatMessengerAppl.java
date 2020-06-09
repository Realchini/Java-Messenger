package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class ChatMessengerAppl extends JFrame {
    final static Logger LOGGER = LogManager.getLogger(ChatMessengerAppl.class);

    private static final Model MODEL;
    private static final Controller CONTROLLER;
    private static final ViewFactory VIEWS;

    static {
        MODEL = Model.getInstance();
        CONTROLLER = Controller.getInstance();
        VIEWS = ViewFactory.getInstance();
        LOGGER.trace("MVC instantiated: "+ MODEL + ";"  + CONTROLLER + ";" + VIEWS);
    }
}
