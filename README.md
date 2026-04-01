# 📚 Online Course Registration System (Java Mini Project)

## 🔹 Overview

The **Online Course Registration System** is a Java-based mini project designed to manage course enrollment efficiently. It allows users to register, log in, browse available courses, and enroll in them. The system uses an Apache-based database (MySQL) for storing user and course data.

---

## 🚀 Features

* 🔐 User Authentication (Login & Signup)
* 📖 View Available Courses
* 📝 Course Registration / Enrollment
* 🧾 Manage Student Records
* 🗂️ Database Integration using Apache MySQL
* ⚡ Simple and user-friendly interface

---

## 🛠️ Technologies Used

* **Java** (Core Java / JDBC)
* **Apache Server** (XAMPP/WAMP for MySQL)
* **MySQL Database**
* **HTML, CSS** (for UI, if applicable)

---

## 📂 Project Structure

```
Online-Course-Registration/
│
├── src/                # Java source files
├── database/           # SQL scripts / schema
├── assets/             # UI files (HTML/CSS)
├── README.md
└── lib/                # JDBC drivers
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/abdulrahman2202/Online-Course-Registration.git
cd Online-Course-Registration
```

### 2️⃣ Setup Apache & MySQL

* Install **XAMPP/WAMP**
* Start **Apache** and **MySQL**
* Open **phpMyAdmin**
* Create a database (e.g., `course_registration`)
* Import the SQL file from `/database`

---

### 3️⃣ Configure Database Connection

Update your Java database configuration file:

```java
String url = "jdbc:mysql://localhost:3306/course_registration";
String user = "root";
String password = "";
```

---

### 4️⃣ Run the Project

* Open the project in your IDE (Eclipse / IntelliJ)
* Add JDBC driver (MySQL Connector)
* Run the main Java file

---

## 🔑 Authentication System

* **Signup Page:** Allows new users to create an account
* **Login Page:** Authenticates existing users
* User credentials are securely stored in the database

---


## 📌 Future Improvements

* Add admin panel for course management
* Implement password encryption
* Add email notifications
* Improve UI with modern frameworks

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork this repository and submit a pull request.
