-- Run this in phpMyAdmin (XAMPP) -> SQL tab
CREATE DATABASE IF NOT EXISTS course_app;
USE course_app;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(150) NOT NULL,
  teacher VARCHAR(100) NOT NULL,
  seats INT NOT NULL
);

CREATE TABLE IF NOT EXISTS enrollments (
  user_id INT,
  course_id INT,
  PRIMARY KEY (user_id, course_id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

INSERT INTO courses(title, teacher, seats) VALUES
('Java Basics', 'Alice', 30),
('Web Dev', 'Bob', 25),
('Data Structures', 'Carol', 20);
