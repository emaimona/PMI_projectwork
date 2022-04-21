package splash;

import Models.Issue;
import Models.Persona;
import Models.Subject;
import Models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ProfessorMenu extends Splash{
    public void writeIssue(String login_code) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please write down your issue and press 'Enter' to finish.");
        String message = scanner.nextLine();
        registerIssue(new Issue(login_code, message, currentDateTime()));
        systemPause("Issue reported successfully!");
        professor_menu(login_code);
    }

    public void changePassword(String login_code) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please input your current password: ");
        String password = scanner.nextLine();
        if (authenticate(new User(login_code.toUpperCase(), password))) {
            System.out.print("Please input your new password: ");
            password = scanner.nextLine();
            updatePassword(new User(login_code.toUpperCase(), password));
            systemPause("Password updated successfully!!");
        } else {
            systemPause("The current password is wrong!");
        }
        professor_menu(login_code);
    }

    public void personalData(String login_code) {
        Persona persona = getPersona(login_code);
        printPersona(persona);
        systemPause("");
        professor_menu(login_code);
    }

    public void teachingSubject(String login_code) {
        ArrayList<String> list = getTeachingSubjects(login_code);
        for(int i=0; i< list.size(); i++) {
            Subject subject = getSubject(list.get(i));
            printProfessorSubject(subject);
            System.out.println();
        }
        systemPause((list.size() == 0)? "You do not have any subject attributed yet.":"");
        professor_menu(login_code);
    }
}
