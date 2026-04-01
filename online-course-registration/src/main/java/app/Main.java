package app;

import app.model.User;
import app.ui.LoginFrame;
import app.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame((User u) -> new MainFrame(u).setVisible(true));
            login.setVisible(true);
        });
    }
}
