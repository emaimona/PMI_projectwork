package Models;

import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private String description;
    private ArrayList<String> students;
    private ArrayList<String> subjects;
    private String registration_date;

    public Course(String id, String name, String description, ArrayList<String> students, ArrayList<String> subjects, String registration_date) {
        this.id = id.toUpperCase();
        this.name = name;
        this.description = description;
        this.students = students;
        this.subjects = subjects;
        this.registration_date = registration_date;
    }

    public Course() {
        students = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getstudents() {
        return students;
    }

    public void addStudent(String student_id) {
        this.students.add(student_id);
    }

    public ArrayList<String> getsubjects() {
        return subjects;
    }

    public void addSubject(String subject_id) {
        this.subjects.add(subject_id);
    }
}
