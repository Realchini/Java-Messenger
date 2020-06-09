package client;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractView extends JPanel {
    protected static ChatMessengerAppl parent;

    public static void setParent(ChatMessengerAppl parent) {
        AbstractView.parent = parent;
    }

    public AbstractView() {
        super();
    }

    public abstract void initialize();
    public abstract void clearFields();
    //public abstract void initModel(boolean kek);

    protected void addLabelField(JPanel panel, String labelText, Component field) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(field);
        panel.add(label);
        panel.add(field);
    }
}
