# Online Course Registration (Java + MySQL with XAMPP)

## Prerequisites
- IntelliJ IDEA (Community is fine)
- JDK 17+
- XAMPP (start **MySQL**; Apache not required for this app)
- Maven (bundled in IntelliJ)

## Setup
1. Open XAMPP Control Panel ➜ Start **MySQL**.
2. Go to **phpMyAdmin** ➜ http://localhost/phpmyadmin/
3. Open the **SQL** tab and paste `schema.sql`. Click **Go**.
4. In IntelliJ, open this project (open the `pom.xml`). Maven will download dependencies.
5. If you changed the MySQL root password, edit it in `src/main/java/app/DB.java`.
6. Run `app.Main`.

## Features
- Sign Up / Log In
- List courses
- Enroll (with seat count and duplicate-check)
- View your enrollments

## Notes
- Passwords are plaintext for demo. Use hashing for real apps.
- If driver errors occur, ensure mysql-connector-j dependency is downloaded and MySQL is running.
