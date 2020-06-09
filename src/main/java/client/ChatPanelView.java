package client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class ChatPanelView extends AbstractView{

    final static Logger LOGGER = LogManager.getLogger(ChatPanelView.class);

    private JScrollPane messagesListPanel;
    private JTextPane messagesTextPane;
    private JPanel textMessagePanel;
    private JButton sendMessageButton;
    private JTextField textMessageField;
    private JButton logoutButton;

    private ChatPanelView() {
        super();
        initialize();
    }

    public static ChatPanelView getInstance() {
        return ChatPanelViewHolder.INSTANCE;
    }

    private static class ChatPanelViewHolder {
        private static final ChatPanelView INSTANCE = new ChatPanelView();
    }

    @Override
    public void initialize() {
        this.setName("chatPanelView");
        this.setLayout(new BorderLayout());
        JPanel header = new JPanel(new BorderLayout());
        header.add(new JLabel("Hello, "+parent.getModel().getCurrentUser() + "!"), BorderLayout.WEST);
        header.add(getLogoutButton(), BorderLayout.EAST);
        this.add(header, BorderLayout.NORTH);
        this.add(getMessagesListPanel(), BorderLayout.CENTER);
        this.add(getTextMessagePanel(), BorderLayout.SOUTH);
        InputMap im = getSendMessageButton().getInputMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
        im.put(KeyStroke.getKeyStroke("released ENTER"), "released");
    }

    @Override
    public void clearFields() {

    }

    public void initModel() {

    }

    public JScrollPane getMessagesListPanel() {
        return messagesListPanel;
    }

    public JTextPane getMessagesTextPane() {
        return messagesTextPane;
    }

    public JPanel getTextMessagePanel() {
        return textMessagePanel;
    }

    public JButton getSendMessageButton() {
        return sendMessageButton;
    }

    public JTextField getTextMessageField() {
        return textMessageField;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
