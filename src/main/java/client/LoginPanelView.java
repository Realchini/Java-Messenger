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
        getErrorLabel().setVisible(false);
        getUserNameField().setText("");
        getServerIpAdressField().setText(parent.getModel().getServerIpAddress());
    }

    public void initModel() {
        parent.getModel().setCurrentUser("");
        parent.getModel().setLoggedUser("");
        getUserNameField().requestFocusInWindow();
        parent.getRootPane().setDefaultButton(getLoginButton());
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
        if (userNameField == null) {
            userNameField = new JTextField(12);
            userNameField.setName("userNameField");
        }
        return userNameField;
    }

    public JTextField getServerIpAdressField() {
        if (serverIpAdressField == null) {
            serverIpAdressField = new JTextField(12);
            serverIpAdressField.setName("serverIpAdressField");
        }
        return serverIpAdressField;
    }

    public JLabel getErrorLabel() {
        if (errorLabel == null) {
            errorLabel = new JLabel("Wrong server ip adress or user name");
            errorLabel.setForeground(Color.red);
        }
        return errorLabel;
    }

    private void setErrorLabelText (String errorText) {
        getErrorLabel().setText(errorText);
    }
}
