package splash;

import DAL.DAL;
import Models.Category;
import Models.Student;
import Models.User;

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
            logout("You have entered your data incorrectly many times.\n" +
                    "Please, contact the administrator of the system to recover your access data!");
        else {
            next_menu(login_code);
            System.out.println("You logged in successfully!");
        }

    }

    private void student_menu(String login_code) {
        Scanner scanner = new Scanner(System.in);
        header("Student Menu");
        System.out.println("Select one of the options!" +
                "1 - ");
    }

    private void professor_menu(String login_code) {
    }

    private void administrator_menu(String login_code) {
    }

    public void next_menu(String login_code) {
        String category = login_code.substring(0,2).toUpperCase();

        System.out.println(category);
        student_menu(category);

        if (category == Category.ST.toString()) {
            System.out.println("ddd");
            student_menu(login_code);
        } else if (category == Category.PR.toString()) {
            professor_menu(login_code);
        } else if (category == Category.AD.toString()) {
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

        User user = new User(login, password);

        if(!authenticate(user)) {
            attempt--;
            System.out.println("Password or Login incorrect!\n");
            clearScreen();
            return login(attempt);
        } else
            return login;

    }

    public void logout(String sms) {
        System.out.println(sms);
        System.exit(0);
        main_menu();
    }
}
