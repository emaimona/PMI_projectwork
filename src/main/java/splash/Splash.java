package splash;

import DAL.DAL;
import Models.*;

import java.util.Date;
import java.util.Scanner;

public class Splash extends DAL {
    private final int MAX_ATTEMPTS = 3;

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
        System.out.println("\t\t\t*** SCHOOL MANAGEMENT SYSTEM ***\n");
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

    protected void student_menu(String login_code) {
        StudentMenu std = new StudentMenu();
        Scanner scanner = new Scanner(System.in);

        header("Student Menu");
        System.out.println("Select one of the options!" +
                "\n1 - Consult personal data."+
                "\n2 - Consult registered courses."+
                "\n3 - Report an issue."+
                "\n4 - Change your password."+
                "\n5 - Logout."+
                "\n6 - Exit.");

        String option = scanner.nextLine();
        if(option.equals("1")) {
            std.personalData(login_code);
        } else if(option.equals("2")) {
            std.registeredCourses(login_code);
        } else if(option.equals("3")) {
            std.writeIssue(login_code);
        } else if(option.equals("4")) {
            std.changePassword(login_code);
        }
        else if(option.equals("5")) {
            logout("You are logged out!");
        } else if(option.equals("6")) {
            exit("");
        } else {
            systemPause("Wrong option!");
            student_menu(login_code);
        }

    }

    protected void professor_menu(String login_code) {
        ProfessorMenu pfm = new ProfessorMenu();
        Scanner scanner = new Scanner(System.in);

        header("Professor Menu");
        System.out.println("Select one of the options!" +
                "\n1 - Consult personal data."+
                "\n2 - Consult attributed subjects."+
                "\n3 - Report an issue."+
                "\n4 - Change your password."+
                "\n5 - Logout."+
                "\n6 - Exit.");

        String option = scanner.nextLine();
        if(option.equals("1")) {
            pfm.personalData(login_code);
        } else if(option.equals("2")) {
            pfm.teachingSubject(login_code);
        } else if(option.equals("3")) {
            pfm.writeIssue(login_code);
        } else if(option.equals("4")) {
            pfm.changePassword(login_code);
        }
        else if(option.equals("5")) {
            logout("You are logged out!");
        } else if(option.equals("6")) {
            exit("");
        } else {
            systemPause("Wrong option!");
            student_menu(login_code);
        }
    }

    protected void administrator_menu(String login_code) {
        AdministratorMenu adm = new AdministratorMenu();
        Scanner scanner = new Scanner(System.in);

        header("Administrator Menu");
        System.out.println("Select one of the options!" +
                "\n1 - Open an account."+
                "\n2 - Update an account."+
                "\n3 - Manage a Course."+
                "\n4 - Manage a Subject."+
                "\n5 - Consult accounts."+
                "\n6 - Consult courses."+
                "\n7 - Consult subjects."+
                "\n8 - Read Professor issues."+
                "\n9 - Read Students issues."+
                "\n10 - Resume of the System."+
                "\n11 - Logout."+
                "\n12 - Exit.");

        String option = scanner.nextLine();
        if(option.equals("1")) {
            adm.menuOpenAcconuts(login_code);
        } else if(option.equals("2")) {
            adm.menuUpdateAccounts(login_code);
        } else if(option.equals("3")) {
            adm.manageCourses(login_code);
        } else if(option.equals("4")) {
            adm.manageSubjects(login_code);
        } else if(option.equals("5")) {
            adm.consultAccounts(login_code);
        } else if(option.equals("6")) {
            adm.consultCourses(login_code);
        } else if(option.equals("7")) {
            adm.consulSubjects(login_code);
        } else if(option.equals("8")) {
            adm.printAllIssues(Category.PR.name());
            systemPause();
            administrator_menu(login_code);
        } else if(option.equals("9")) {
            adm.printAllIssues(Category.ST.name());
            systemPause();
            administrator_menu(login_code);
        } else if(option.equals("10")) {
            adm.printResumeOFTheSystem(login_code);
        } else if(option.equals("11")) {
            logout("You are logged out!");
        } else if(option.equals("12")) {
            exit("");
        } else {
            systemPause("Wrong option!");
            administrator_menu(login_code);
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




    // ======> Prints
    public void printPersona(Persona persona_class){
        String category = getCategory(Category.valueOf(persona_class.getId().substring(0,2).toUpperCase()));

        String[] class_attr = {"Login name: ", "Category: ", "Name: ", "Age: ", "Sex: ", "Phone: ", "Status: ", "Registration_date: "};
        String[] values = {persona_class.getId(), category, persona_class.getName(), persona_class.getAge(),
                persona_class.getSex().toString(), persona_class.getPhone(), persona_class.getStatus().name(),
                persona_class.getRegistration_date()};

        for (int i=0; i<class_attr.length; i++) {
            System.out.println(class_attr[i].concat(values[i]));
        }
    }

    public void printIssues(Issue issue){
        String[] class_attr = {"Writer id: ", "Date: ", "Message:\n"};
        String[] values = {issue.getLogin_name(), issue.getDate(), issue.getMessage()};

        for (int i=0; i<class_attr.length; i++) {
            System.out.println(class_attr[i].concat(values[i]));
        }
    }



    public void printSubject(Subject subject){
        String[] class_attr = {"Subject id: ", "Subject name: ", "Professor code: ", "Description: ", "Registration date: "};
        String[] values = {subject.getId(), subject.getName(), subject.getProfessor_code(), subject.getDescription(), subject.getRegistration_date()};

        for (int i=0; i<class_attr.length; i++) {
            System.out.println(class_attr[i].concat(values[i]));
        }
    }

    public void printProfessorSubject(Subject subject){
        String[] class_attr = {"Subject id: ", "Subject name: ", "Description: ", "Registration date: "};
        String[] values = {subject.getId(), subject.getName(),subject.getDescription(), subject.getRegistration_date()};

        for (int i=0; i<class_attr.length; i++) {
            System.out.println(class_attr[i].concat(values[i]));
        }
    }


    public void printStudentCourse(Course course){

        String[] class_attr = {"Course id: ", "Course name: ", "Description: ", "Registration date: "};
        String[] values = {course.getId(), course.getName(),course.getDescription(), course.getRegistration_date()};

        for (int i=0; i<class_attr.length; i++) {
            System.out.println(class_attr[i].concat(values[i]));
        }
        //print subjects
        System.out.println("Registered subjects: ");
        if (course.getsubjects().size() != 0) {
            for (int i = 0; i < course.getsubjects().size(); i++) {
                Subject s = getSubject(course.getsubjects().get(i));
                System.out.println("[@id, @name, @Professor] - " + s.getId() +", " + s.getName()+ ", " +s.getProfessor_code());
            }
        } else
            System.out.println("There is no subject registered yet...");

    }


    public void printCourse(Course course){

        String[] class_attr = {"Course id: ", "Course name: ", "Description: ", "Registration date: "};
        String[] values = {course.getId(), course.getName(),course.getDescription(), course.getRegistration_date()};

        for (int i=0; i<class_attr.length; i++) {
            System.out.println(class_attr[i].concat(values[i]));
        }

        //print students
        System.out.println("Registered students: ");
        if (course.getstudents().size() != 0) {
            for (int i=0; i<course.getstudents().size(); i++) {
                System.out.println(i+1 +" - " + course.getstudents().get(i));
            }
        } else
            System.out.print("There is no student registered yet..");
        System.out.println();


        //print subjects
        System.out.println("Registered subjects: ");
        if (course.getsubjects().size() != 0) {
            for (int i = 0; i < course.getsubjects().size(); i++) {
                Subject s = getSubject(course.getsubjects().get(i));
                System.out.println("[@id, @name, @Professor] - " + s.getId() +", " + s.getName()+ ", " +s.getProfessor_code());
            }
        } else
            System.out.println("There is no subject registered yet...");

    }

    public String getCategory(Category category) {
        String cat = "";

        if (category.equals(Category.ST)) {
            cat = "Student";
        } else if (category.equals(Category.PR)) {
            cat = "Professor";
        } else if (category.equals(Category.AD)) {
            cat = "Administration";
        }
        return cat;
    }



    public String login(int attempt){
        if(attempt == 0)
            return null;

        header("Login");
        System.out.println("Please input your data correctly! "+attempt+" attempts!");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Login name: ");
        String login = scanner.nextLine().toUpperCase();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(login, password);

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
