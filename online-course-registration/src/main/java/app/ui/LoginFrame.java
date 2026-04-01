package app.ui;

import app.dao.UserDao;
import app.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public interface OnLogin { void loggedIn(User user); }

    private final UserDao userDao = new UserDao();

    public LoginFrame(OnLogin onLogin) {
        setTitle("Course App - Login / Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 280);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        JPanel login = new JPanel(new GridLayout(3,2,8,8));
        JTextField loginEmail = new JTextField();
        JPasswordField loginPass = new JPasswordField();
        JButton loginBtn = new JButton("Log In");

        login.add(new JLabel("Email:"));    login.add(loginEmail);
        login.add(new JLabel("Password:")); login.add(loginPass);
        login.add(new JLabel());            login.add(loginBtn);

        loginBtn.addActionListener(e -> {
            try {
                User u = userDao.login(loginEmail.getText().trim(),
                        new String(loginPass.getPassword()));
                if (u != null) {
                    JOptionPane.showMessageDialog(this, "Welcome, " + u.name + "!");
                    onLogin.loggedIn(u);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong email or password.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        JPanel signup = new JPanel(new GridLayout(4,2,8,8));
        JTextField suName = new JTextField();
        JTextField suEmail = new JTextField();
        JPasswordField suPass = new JPasswordField();
        JButton suBtn = new JButton("Create Account");

        signup.add(new JLabel("Name:"));     signup.add(suName);
        signup.add(new JLabel("Email:"));    signup.add(suEmail);
        signup.add(new JLabel("Password:")); signup.add(suPass);
        signup.add(new JLabel());            signup.add(suBtn);

        suBtn.addActionListener(e -> {
            String name = suName.getText().trim();
            String email = suEmail.getText().trim();
            String pass = new String(suPass.getPassword());
            try {
                if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill everything.");
                    return;
                }
                if (userDao.emailExists(email)) {
                    JOptionPane.showMessageDialog(this, "Email already used.");
                    return;
                }
                userDao.register(name, email, pass);
                JOptionPane.showMessageDialog(this, "Account created. You can log in now.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        tabs.addTab("Log In", login);
        tabs.addTab("Sign Up", signup);
        add(tabs);
    }
}
