package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class LoginPanelView extends AbstractView {
    final static Logger LOGGER = LogManager.getLogger(LoginPanelView.class);
    public static final String LOGIN_ACTION_COMMAND = "login";

    private JPanel loginPanel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField userNameField;
    private JTextField serverIpAdressField;
    private JLabel errorLabel;

    // синглтон
    private LoginPanelView () {
        super();
        initialize();
    }

    public static LoginPanelView getInstance() {
        return LoginPanelViewHolder.INSTANCE;
    }

    private static class LoginPanelViewHolder {
        private static final LoginPanelView INSTANCE = new LoginPanelView();
    }

    @Override
    public void initialize() {
        this.setName("loginPanelView");
        this.setLayout(new BorderLayout());
        this.add(getLoginPanel(), BorderLayout.CENTER);
        InputMap im = getLoginButton().getInputMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
        im.put(KeyStroke.getKeyStroke("released ENTER"), "released");
    }

    @Override
    public void clearFields() {

    }

    public JPanel getLoginPanel() {
        if (loginPanel == null) {
            loginPanel = new JPanel();
            loginPanel.setLayout(new BorderLayout());
            loginPanel.add(getMainPanel(), BorderLayout.CENTER);
            addLabelField(getMainPanel(), "server ip-address:", getServerIpAdressField());
            addLabelField(getMainPanel(), "name of user:", getUserNameField());
            loginPanel.add(getLoginButton(), BorderLayout.SOUTH);
        }
        return loginPanel;
    }

    public JPanel getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        }
        return mainPanel;
    }

    public JButton getLoginButton() {
        if (loginButton == null) {
            loginButton = new JButton();
            loginButton.setText("Login...");
            loginButton.setName("loginButton");
            loginButton.setActionCommand(LOGIN_ACTION_COMMAND);
            loginButton.addActionListener(parent.getController());
        }
        return loginButton;
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public JTextField getServerIpAdressField() {
        return serverIpAdressField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
