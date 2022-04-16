package splash;

import java.util.Scanner;

public class AdministratorMenu extends Splash{

    public void create() {
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
            //student_menu(login_code);
        }
    }

    public void register() {

    }

    public void delete() {

    }

    public void readReports() {

    }
}
