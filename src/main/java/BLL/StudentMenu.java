package BLL;

import Models.*;
import PL.Splash;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMenu extends Splash {


    public void writeIssue(String login_code) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please write down your issue and press 'Enter' to finish.");
        String message = scanner.nextLine();
        registerIssue(new Issue(login_code, message, currentDateTime()));
        systemPause("Issue reported successfully!");
        student_menu(login_code);
    }

    public void changePassword(String login_code) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please input your current password: ");
        String password = scanner.nextLine();
        if (authenticate(new User(login_code.toUpperCase(), password))) {
            System.out.print("Please input your new password: ");
            password = scanner.nextLine();
            updatePassword(new User(login_code.toUpperCase(), password));
            systemPause("Password updated successfuly!!");
        } else {
            systemPause("The current password is wrong!");
        }
        student_menu(login_code);
    }

    public void personalData(String login_code) {
        Persona persona = getPersona(login_code);
        printPersona(persona);
        systemPause("");
        student_menu(login_code);
    }

    public void registeredCourses(String login_code) {
        ArrayList<String> list = getRegisteredCourses(login_code);
        for(int i=0; i< list.size(); i++) {
            Course course = getCourse(list.get(i));
            printStudentCourse(course);
            System.out.println("\n");
        }
        systemPause((list.size() == 0)? "You do not have any registered courses yet..":"");
        student_menu(login_code);
    }
}
