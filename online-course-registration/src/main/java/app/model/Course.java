package app.model;

public class Course {
    public int id;
    public String title;
    public String teacher;
    public int seats;

    public Course(int id, String title, String teacher, int seats) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.seats = seats;
    }
}
