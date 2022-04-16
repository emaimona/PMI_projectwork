package splash;

import DAL.DAL;
import Models.Category;
import Models.Issue;
import Models.Student;
import Models.User;

import java.util.Date;
import java.util.Scanner;

public class Splash extends DAL {
    private final int MAX_ATTEMPTS = 1;

    public Splash() {

    }

    public void clearScreen() {
       /* try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                Runtime.getRuntime().exec("cls");
        } catch (Exception e) {
            e.printStackTrace();
        }
*//*        try {

            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}*/
    }

    public void header(String area){
        System.out.println("\t\t\t*** SCHOOL MANAGMENT SYSTEM ***\n");
        System.out.println("===> "+ area + "\n");
    }


    // ------- MENUS
    public void main_menu() {
        String login_code = login(MAX_ATTEMPTS);

        if(login_code == null)
            exit("You have entered your data incorrectly many times.\n" +
                    "Please, contact the administrator of the system to recover your access data!");
        else {
            systemPause("You logged in successfully!");
            next_menu(login_code);
        }

    }

    private void student_menu(String login_code) {
        Scanner scanner = new Scanner(System.in);

        header("Student Menu");
        System.out.println("Select one of the options!" +
                "\n1 - Consult personal data."+
                "\n2 - Consult registered coursees."+
                "\n3 - Consult grades."+
                "\n4 - Consult schedule."+
                "\n5 - Report an issue."+
                "\n6 - Logout."+
                "\n7 - Exit.");

        String option = scanner.nextLine();
        if(option.equals("1")) {

        } else if(option.equals("2")) {

        } else if(option.equals("3")) {

        } else if(option.equals("4")) {

        } else if(option.equals("5")) {
            System.out.println("Please write down your issue and press 'Enter' to finish.");
            String message = scanner.nextLine();
            registerIssue(new Issue(login_code, message, currentDateTime()));
            systemPause("Issue reported successfully!");
            student_menu(login_code);
        }
        else if(option.equals("6")) {
            logout("You are logged out!");
        } else if(option.equals("7")) {
            exit("");
        } else {
            systemPause("Wrong option!");
            student_menu(login_code);
        }

    }

    private void professor_menu(String login_code) {
        Scanner scanner = new Scanner(System.in);
    }

    private void administrator_menu(String login_code) {
        Scanner scanner = new Scanner(System.in);

        header("Administrator Menu");
        System.out.println("Select one of the options!" +
                "\n1 - Create Student account."+
                "\n2 - Create Professor account."+
                "\n3 - Create a Course."+
                "\n4 - Create a Subject."+
                "\n1 - Consult Consult ."+
                "\n2 - Consult registered coursees."+
                "\n3 - Consult grades."+
                "\n4 - Consult schedule."+
                "\n5 - Report an issue."+
                "\n6 - Logout."+
                "\n7 - Exit.");

        String option = scanner.nextLine();
        if(option.equals("1")) {

        } else if(option.equals("2")) {

        } else if(option.equals("3")) {

        } else if(option.equals("4")) {

        } else if(option.equals("5")) {

        }
        else if(option.equals("6")) {
            logout("You are logged out!");
        } else if(option.equals("7")) {
            exit("");
        } else {
            systemPause("Wrong option!");
            student_menu(login_code);
        }
    }

    public void next_menu(String login_code) {
        String category = login_code.substring(0,2).toUpperCase();

        if (category.equals(Category.ST.toString())) {
            student_menu(login_code);
        } else if (category.equals(Category.PR.toString())) {
            professor_menu(login_code);
        } else if (category.equals(Category.AD.toString())) {
            administrator_menu(login_code);
        }
    }






    public String login(int attempt){
        if(attempt == 0)
            return null;

        header("Login");
        System.out.println("Please input your data correctly! "+attempt+" attempts!");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Login name: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(login.toUpperCase(), password);

        if(!authenticate(user)) {
            attempt--;
            System.out.println("Password or Login incorrect!\n");
            systemPauseExit();
            clearScreen();
            return login(attempt);
        } else
            return login;

    }

    public void logout(String sms) {
        System.out.println(sms);
        systemPause();
        main_menu();
    }

    public void exit(String sms) {
        System.out.println(sms);
        System.exit(0);
    }

    public void systemPause() {
        System.out.println("Please press Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    public void systemPause(String sms) {
        System.out.println(sms);
        systemPause();
    }

    public void systemPauseExit() {
        System.out.println("Please input '0' to Exit or press Enter to continue...");
        String code = new Scanner(System.in).nextLine();
        if(code.equals("0"))
            exit("");
    }

    public String currentDateTime() {
        return (new Date()).toString();
    }
}
