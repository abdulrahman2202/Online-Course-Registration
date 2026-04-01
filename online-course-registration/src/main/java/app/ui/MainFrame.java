package app.ui;

import app.dao.CourseDao;
import app.model.Course;
import app.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private final User user;
    private final CourseDao courseDao = new CourseDao();
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID","Title","Teacher","Seats"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);

    public MainFrame(User user) {
        this.user = user;

        setTitle("Course App - Hello, " + user.name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 420);
        setLocationRelativeTo(null);

        JButton refresh = new JButton("Refresh");
        JButton enroll = new JButton("Enroll");
        JButton mine   = new JButton("My Enrollments");

        refresh.addActionListener(e -> loadCourses());
        enroll.addActionListener(e -> enrollSelected());
        mine.addActionListener(e -> showMyCourses());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(refresh); top.add(enroll); top.add(mine);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadCourses();
    }

    private void loadCourses() {
        model.setRowCount(0);
        List<Course> list = courseDao.listAll();
        for (Course c : list) {
            model.addRow(new Object[]{c.id, c.title, c.teacher, c.seats});
        }
    }

    private void enrollSelected() {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(this, "Pick a course first."); return; }
        int courseId = (Integer) model.getValueAt(row, 0);
        int seats    = (Integer) model.getValueAt(row, 3);
        if (seats <= 0) { JOptionPane.showMessageDialog(this, "No seats left."); return; }

        try {
            boolean ok = courseDao.enroll(user.id, courseId);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Enrolled! 🎉");
                loadCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Already enrolled or no seats.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void showMyCourses() {
        var list = courseDao.myCourses(user.id);
        StringBuilder sb = new StringBuilder("Your courses:\n");
        if (list.isEmpty()) sb.append("(none)");
        for (Course c : list) sb.append("• ").append(c.title).append(" (").append(c.teacher).append(")\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
