package splash;

import Models.Category;
import Models.Persona;
import Models.Sex;
import Models.User;

import java.util.Scanner;

public class AdministratorMenu extends Splash{


    public void menuOpenAcconuts(String login_code) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select one of the accounts!" +
                "\n1 - Open a Student account."+
                "\n2 - Open a Professor acoount."+
                "\n3 - Open an Administration account."+
                "\n4 - Close");
        String option = scanner.nextLine();
        if(option.equals("1")) {
            openStudentAccount(login_code);
        } else if(option.equals("2")) {
            openProfessorAccount(login_code);
        } else if(option.equals("3")) {
            openAdministrationAccount(login_code);
        }
        else if(option.equals("4")) {
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            menuOpenAcconuts(login_code);
        }
    }

    public void openStudentAccount(String login_code) {
        Scanner scanner = new Scanner(System.in);
        Persona persona = new Persona();
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  inpunt the correct data type!");

        System.out.print("Full name: ");
        persona.setName(scanner.nextLine());

        try {
            System.out.print("Age: ");
            persona.setAge(scanner.nextInt());

            System.out.print("Sex ('0' for male, and '1' for female): ");
            int i = scanner.nextInt();
            if(i == 0)
                persona.setSex(Sex.MASCULINE);
            else
                persona.setSex(Sex.FEMININE);

        } catch (Exception e) {
            systemPause("Data type incorrect! You need start over again!");
            menuOpenAcconuts(login_code);
        }
        scanner.nextLine();
        System.out.print("Phone: ");
        persona.setPhone(scanner.nextLine());
        persona.setRegistration_date(currentDateTime());

        String login_name = registerUser(new User(Category.ST.name(), ""));
        registerPersona(persona, login_name);
        System.out.println("User account Created Sucessfully!");
        System.out.println("The User is advised to change the password immediately!");
        System.out.println("The login name is "+login_name+" and the password is by default the empty string (just press 'Enter')!\n");
        menuOpenAcconuts(login_code);
    }

    public void openProfessorAccount(String login_code) {
        Scanner scanner = new Scanner(System.in);
        Persona persona = new Persona();
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  inpunt the correct data type!");

        System.out.print("Full name: ");
        persona.setName(scanner.nextLine());

        try {
            System.out.print("Age: ");
            persona.setAge(scanner.nextInt());

            System.out.print("Sex ('0' for male, and '1' for female): ");
            int i = scanner.nextInt();
            if(i == 0)
                persona.setSex(Sex.MASCULINE);
            else
                persona.setSex(Sex.FEMININE);

        } catch (Exception e) {
            systemPause("Data type incorrect! You need start over again!");
            menuOpenAcconuts(login_code);
        }
        scanner.nextLine();
        System.out.print("Phone: ");
        persona.setPhone(scanner.nextLine());
        persona.setRegistration_date(currentDateTime());

        String login_name = registerUser(new User(Category.PR.name(), ""));
        registerPersona(persona, login_name);
        System.out.println("User account Created Sucessfully!");
        System.out.println("The User is advised to change the password immediately!");
        System.out.println("The login name is "+login_name+" and the password is by default the empty string (just press 'Enter')!\n");
        menuOpenAcconuts(login_code);
    }


    public void openAdministrationAccount(String login_code) {
        Scanner scanner = new Scanner(System.in);
        Persona persona = new Persona();
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  inpunt the correct data type!");

        System.out.print("Full name: ");
        persona.setName(scanner.nextLine());

        try {
            System.out.print("Age: ");
            persona.setAge(scanner.nextInt());

            System.out.print("Sex ('0' for male, and '1' for female): ");
            int i = scanner.nextInt();
            if(i == 0)
                persona.setSex(Sex.MASCULINE);
            else
                persona.setSex(Sex.FEMININE);

        } catch (Exception e) {
            systemPause("Data type incorrect! You need start over again!");
            menuOpenAcconuts(login_code);
        }
        scanner.nextLine();
        System.out.print("Phone: ");
        persona.setPhone(scanner.nextLine());
        persona.setRegistration_date(currentDateTime());

        String login_name = registerUser(new User(Category.AD.name(), ""));
        registerPersona(persona, login_name);
        System.out.println("User account Created Sucessfully!");
        System.out.println("The User is advised to change the password immediately!");
        System.out.println("The login name is "+login_name+" and the password is by default the empty string (just press 'Enter')!\n");
        menuOpenAcconuts(login_code);
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);

        header("Administrator Menu");
        System.out.println("Select one of the options!" +
                "\n1 - Open an account."+
                "\n2 - Update an account."+
                "\n3 - Manage a Course."+
                "\n4 - Mange a Subject."+
                "\n1 - Consult Consult ."+
                "\n2 - Consult registered coursees."+
                "\n3 - Consult grades."+
                "\n4 - Consult schedule."+
                "\n5 - Read issues."+
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
