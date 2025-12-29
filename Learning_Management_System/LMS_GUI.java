import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/* ================= USER CLASS ================= */
class User {
    String name, role;

    User(String name, String role) {
        this.name = name;
        this.role = role;
    }
}

/* ================= COURSE CLASS ================= */
class Course {
    String title, instructor;

    Course(String title, String instructor) {
        this.title = title;
        this.instructor = instructor;
    }
}

/* ================= MAIN GUI CLASS ================= */
public class LMS_GUI {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        new LoginFrame();
    }
}

/* ================= LOGIN FRAME ================= */
class LoginFrame extends JFrame {

    LoginFrame() {
        setTitle("Online Learning Management System");
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Select Your Role", JLabel.CENTER);
        JButton adminBtn = new JButton("Admin");
        JButton instructorBtn = new JButton("Instructor");
        JButton studentBtn = new JButton("Student");

        add(label);
        add(adminBtn);
        add(instructorBtn);
        add(studentBtn);

        adminBtn.addActionListener(e -> new AdminDashboard());
        instructorBtn.addActionListener(e -> new InstructorDashboard());
        studentBtn.addActionListener(e -> new StudentDashboard());

        setVisible(true);
    }
}

/* ================= ADMIN DASHBOARD ================= */
class AdminDashboard extends JFrame {

    AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        JButton addUser = new JButton("Add User");
        JButton addCourse = new JButton("Add Course");
        JButton viewData = new JButton("View Data");
        JButton exit = new JButton("Exit");

        add(addUser);
        add(addCourse);
        add(viewData);
        add(exit);

        addUser.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter User Name:");
            String role = JOptionPane.showInputDialog("Enter Role:");

            if (name != null && role != null) {
                LMS_GUI.users.add(new User(name, role));
                JOptionPane.showMessageDialog(this, "User Added Successfully");
            }
        });

        addCourse.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter Course Title:");
            String instructor = JOptionPane.showInputDialog("Enter Instructor Name:");

            if (title != null && instructor != null) {
                LMS_GUI.courses.add(new Course(title, instructor));
                JOptionPane.showMessageDialog(this, "Course Added Successfully");
            }
        });

        viewData.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Users:\n");
            for (User u : LMS_GUI.users)
                sb.append(u.name + " - " + u.role + "\n");

            sb.append("\nCourses:\n");
            for (Course c : LMS_GUI.courses)
                sb.append(c.title + " - " + c.instructor + "\n");

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        exit.addActionListener(e -> dispose());

        setVisible(true);
    }
}

/* ================= INSTRUCTOR DASHBOARD ================= */
class InstructorDashboard extends JFrame {

    InstructorDashboard() {
        setTitle("Instructor Dashboard");
        setSize(400, 250);
        setLayout(new GridLayout(3, 1));

        JButton viewCourses = new JButton("View Courses");
        JButton grade = new JButton("Grade Assignment");
        JButton exit = new JButton("Exit");

        add(viewCourses);
        add(grade);
        add(exit);

        viewCourses.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Courses:\n");
            for (Course c : LMS_GUI.courses)
                sb.append(c.title + " by " + c.instructor + "\n");

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        grade.addActionListener(e -> {
            String student = JOptionPane.showInputDialog("Student Name:");
            String marks = JOptionPane.showInputDialog("Enter Marks:");
            JOptionPane.showMessageDialog(this,
                    "Assignment graded for " + student + " : " + marks);
        });

        exit.addActionListener(e -> dispose());

        setVisible(true);
    }
}

/* ================= STUDENT DASHBOARD ================= */
class StudentDashboard extends JFrame {

    StudentDashboard() {
        setTitle("Student Dashboard");
        setSize(400, 250);
        setLayout(new GridLayout(3, 1));

        JButton enroll = new JButton("Enroll Course");
        JButton submit = new JButton("Submit Assignment");
        JButton exit = new JButton("Exit");

        add(enroll);
        add(submit);
        add(exit);

        enroll.addActionListener(e -> {
            String course = JOptionPane.showInputDialog("Enter Course Title:");
            JOptionPane.showMessageDialog(this,
                    "Enrolled in course: " + course);
        });

        submit.addActionListener(e -> {
            String course = JOptionPane.showInputDialog("Course Title:");
            String text = JOptionPane.showInputDialog("Assignment Text:");
            JOptionPane.showMessageDialog(this,
                    "Assignment submitted for " + course);
        });

        exit.addActionListener(e -> dispose());

        setVisible(true);
    }
}