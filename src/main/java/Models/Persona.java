package Models;

import java.util.Date;

public class Persona{
    private String name;
    private int age;
    private Sex sex;
    private String phone;
    private Status status;
    private String registration_date;
    //private String user_category;


    public Persona() {
        this.status = Status.PASSIVE;
    }

    public Persona(String name, int age, Sex sex, String phone, String registration_date) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.status = Status.PASSIVE;
        this.registration_date = registration_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
