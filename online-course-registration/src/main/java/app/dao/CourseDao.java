package app.dao;

import app.DB;
import app.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    public List<Course> listAll() {
        String sql = "SELECT id, title, teacher, seats FROM courses ORDER BY id";
        List<Course> list = new ArrayList<>();
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("teacher"),
                        rs.getInt("seats")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean enroll(int userId, int courseId) throws SQLException {
        String already = "SELECT 1 FROM enrollments WHERE user_id=? AND course_id=?";
        String takeSeat = "UPDATE courses SET seats = seats - 1 WHERE id=? AND seats > 0";
        String insert = "INSERT INTO enrollments(user_id, course_id) VALUES(?,?)";

        try (Connection c = DB.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps1 = c.prepareStatement(already)) {
                ps1.setInt(1, userId);
                ps1.setInt(2, courseId);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) { c.rollback(); return false; }
                }
            }
            try (PreparedStatement ps2 = c.prepareStatement(takeSeat)) {
                ps2.setInt(1, courseId);
                if (ps2.executeUpdate() == 0) { c.rollback(); return false; }
            }
            try (PreparedStatement ps3 = c.prepareStatement(insert)) {
                ps3.setInt(1, userId);
                ps3.setInt(2, courseId);
                ps3.executeUpdate();
            }
            c.commit();
            return true;
        }
    }

    public List<Course> myCourses(int userId) {
        String sql = "SELECT c.id, c.title, c.teacher, c.seats " +
                     "FROM courses c " +
                     "JOIN enrollments e ON e.course_id = c.id " +
                     "WHERE e.user_id = ? " +
                     "ORDER BY c.id";
        List<Course> list = new ArrayList<>();
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Course(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("teacher"),
                            rs.getInt("seats")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
