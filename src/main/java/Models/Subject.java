package Models;

public class Subject {
    private String id;
    private String name;
    private String description;
    private String professor_code;
    private String registration_date;

    public Subject(String name, String description, String professor_code) {
        this.name = name;
        this.description = description;
        this.professor_code = professor_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public Subject() {
    }

    public String getName() {
        return name;
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

    public String getProfessor_code() {
        return professor_code;
    }

    public void setProfessor_code(String professor_code) {
        this.professor_code = professor_code;
    }
}
