package splash;

import Models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdministratorMenu extends Splash{



    public void manageCourses(String login_code) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select one of the accounts!" +
                "\n1 - Create a course."+
                "\n2 - Update the name of the course."+
                "\n3 - Update the description of the course."+
                "\n4 - Add a subject."+
                "\n5 - Register a student."+
                "\n6 - Delete a subject."+
                "\n7 - Delete a student."+
                "\n8 - Close");
        String option = scanner.nextLine();
        if(option.equals("1")) {
            menuCreateCourse(login_code);
        } else if(option.equals("2")) {
            menuUpdateNameCourse(login_code);
        } else if(option.equals("3")) {
            menuUpdateDescriptionCourse(login_code);
        } else if(option.equals("4")) {
            menuAddSubjectCourse(login_code);
        } else if(option.equals("5")) {
            menuRegisterStudentCourse(login_code);
        } else if(option.equals("6")) {
            menuDeleteSubjectCourse(login_code);
        } else if(option.equals("7")) {
            menuDeleteStudentCourse(login_code);
        }
        else if(option.equals("8")) {
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            manageCourses(login_code);
        }
    }

    public void printResumeOFTheSystem(String login_code) {
        header("System Resume");

        System.out.println("Courses: " + getLastId(course_xml));
        System.out.println("Subjects: " + getLastId(subject_xml));
        System.out.println("Administrators: " + getLastId(user_xml, Category.AD.name()));
        System.out.println("Professors: " + getLastId(user_xml, Category.PR.name()));
        System.out.println("Students: " + getLastId(user_xml, Category.ST.name()));
        System.out.println("Issues reported: " + getLastId(issue_xml));

        systemPause("");
        administrator_menu(login_code);
    }


    public void consulSubjects(String login_code) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select one of the options!" +
                "\n1 - Consult a subject."+
                "\n2 - List all subject."+
                "\n3 - Close");
        String option = scanner.nextLine();
        if(option.equals("1")) {
            System.out.print("Input the Id of the subject: ");
            String id = scanner.nextLine().toUpperCase();
            if( !objectExists(subject_xml, "subject", id)) {
                systemPause("Subject with this Id does not exists! You need to start over again!");
                consulSubjects(login_code);
            }
            Subject subject = getSubject(id);
            printSubject(subject);
            systemPause("");
            consulSubjects(login_code);
        } else if(option.equals("2")) {
            printAllSubjects();
            systemPause("");
            consulSubjects(login_code);
        } else if(option.equals("3")) {
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            consulSubjects(login_code);
        }
    }



    public void consultCourses(String login_code) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select one of the options!" +
                "\n1 - Consult a course."+
                "\n2 - List all courses."+
                "\n3 - Close");
        String option = scanner.nextLine();
        if(option.equals("1")) {
            System.out.print("Input the Id of the course: ");
            String id = scanner.nextLine().toUpperCase();
            if( !objectExists(course_xml, "course", id)) {
                systemPause("Course with this Id does not exists! You need to start over again!");
                consultCourses(login_code);
            }
            Course course = getCourse(id);
            printCourse(course);
            systemPause("");
            consultCourses(login_code);
        } else if(option.equals("2")) {
            printAllCourses();
            systemPause("");
            consultCourses(login_code);
        } else if(option.equals("3")) {
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            consultCourses(login_code);
        }
    }

    public void consultAccounts(String login_code) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select one of the options!" +
                "\n1 - Consult an account."+
                "\n2 - List all accounts."+
                "\n3 - Close");
        String option = scanner.nextLine();
        if(option.equals("1")) {
            System.out.print("Input the Id of the user: ");
            String id = scanner.nextLine().toUpperCase();
            if( !objectExists(user_xml, "user", id)) {
                systemPause("User with this Id does not exists! You need to start over again!");
                consultAccounts(login_code);
            }
            Persona persona = getPersona(id);
            printPersona(persona);
            systemPause("");
            consultAccounts(login_code);
        } else if(option.equals("2")) {
            printAllUsers();
            systemPause("");
            consultAccounts(login_code);
        } else if(option.equals("3")) {
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            consultAccounts(login_code);
        }
    }

    private void menuDeleteStudentCourse(String login_code) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the student: ");
        String id = scanner.nextLine().toUpperCase();
        if(!id.startsWith("ST") || !objectExists(user_xml, "user", id)) {
            systemPause("Student with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        System.out.print("Input the Id of the course: ");
        String course_id = scanner.nextLine().toUpperCase();
        if(!objectExists(course_xml, "course", course_id)) {
            systemPause("Course with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        deleteStudentCourse(id, course_id);
        System.out.println("Student with id '"+ id+"' deleted Sucessfully!");
        systemPause("");
        manageCourses(login_code);
    }

    private void menuDeleteSubjectCourse(String login_code) {
        Subject subject = new Subject();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the subject: ");
        subject.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(subject_xml, "subject", subject.getId())) {
            systemPause("Subject with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        System.out.print("Input the Id of the course: ");
        String course_id = scanner.nextLine().toUpperCase();
        if(!objectExists(course_xml, "course", course_id)) {
            systemPause("Course with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        deleteSubjectCourse(subject, course_id);
        System.out.println("Subject with id '"+ subject.getId()+"' deleted Sucessfully!");
        systemPause("");
        manageCourses(login_code);
    }


    private void menuRegisterStudentCourse(String login_code) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the student: ");
        String id = scanner.nextLine().toUpperCase();
        if(!id.startsWith("ST") || !objectExists(user_xml, "user", id)) {
            systemPause("Student with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        System.out.print("Input the Id of the course: ");
        String course_id = scanner.nextLine().toUpperCase();
        if(!objectExists(course_xml, "course", course_id)) {
            systemPause("Course with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        addStudentCourse(id, course_id);
        System.out.println("Student with id '"+ id+"' added Sucessfully!");
        systemPause("");
        manageCourses(login_code);
    }

    private void menuAddSubjectCourse(String login_code) {
        Subject subject = new Subject();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the subject: ");
        subject.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(subject_xml, "subject", subject.getId())) {
            systemPause("Subject with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }
        subject = getSubject(subject.getId());

        System.out.print("Input the Id of the course: ");
        String course_id = scanner.nextLine().toUpperCase();
        if(!objectExists(course_xml, "course", course_id)) {
            systemPause("Course with this Id does not exists! You need to start over again!");
            manageCourses(login_code);
        }

        addSubjectCourse(subject, course_id);
        System.out.println("Subject with id '"+ subject.getId()+"' added Sucessfully!");
        systemPause("");
        manageCourses(login_code);
    }

    private void menuUpdateDescriptionCourse(String login_code) {
        Course course = new Course();

        ArrayList<String> class_attbr = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the course: ");
        course.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(course_xml, "course", course.getId())) {
            systemPause("Course with this Id does not exists! You need to start over again!");
            menuUpdateSubject(login_code);
        }

        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");
        System.out.print("Description: ");
        course.setDescription(scanner.nextLine());
        class_attbr.add("description");
        values.add(course.getDescription());

        updateCourse(course, class_attbr, values);
        System.out.println("Course with id '"+ course.getId()+"' updated Successfully!");
        systemPause("");
        manageCourses(login_code);
    }

    private void menuUpdateNameCourse(String login_code) {
        Course course = new Course();

        ArrayList<String> class_attbr = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the course: ");
        course.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(course_xml, "course", course.getId())) {
            systemPause("Course with this Id does not exists! You need to start over again!");
            menuUpdateSubject(login_code);
        }

        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");
        System.out.print("Name of the course: ");
        course.setName(scanner.nextLine());
        class_attbr.add("name");
        values.add(course.getName());

        updateCourse(course, class_attbr, values);
        System.out.println("Course with id '"+ course.getId()+"' updated Successfully!");
        systemPause("");
        manageCourses(login_code);
    }

    private void menuCreateCourse(String login_code) {
        Course course = new Course();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");

        System.out.print("Name of the course: ");
        course.setName(scanner.nextLine());

        System.out.print("Id of the course: ");
        course.setId(scanner.nextLine().toUpperCase());

        if(course.getId().equals("")) {
            systemPause("Course Id can not be empty! You need to start over again!");
            menuCreateCourse(login_code);
        }
        if(objectExists(course_xml, "course", course.getId())) {
            systemPause("Course with this Id already exists! You need to start over again!");
            menuCreateCourse(login_code);
        }

        System.out.print("Description: ");
        course.setDescription(scanner.nextLine());
        
        course.setRegistration_date(currentDateTime());
        createCourse(course);
        System.out.println("Course with id '"+ course.getId()+"' Created Successfully!");
        systemPause("");
        manageCourses(login_code);
    }


    public void manageSubjects(String login_code) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select one of the accounts!" +
                "\n1 - Create a subject."+
                "\n2 - Update a subject."+
                "\n3 - Delete a subject."+
                "\n4 - Close");
        String option = scanner.nextLine();
        if(option.equals("1")) {
            menuCreateSubject(login_code);
        } else if(option.equals("2")) {
            menuUpdateSubject(login_code);
        } else if(option.equals("3")) {
           menuDeleteSubject(login_code);
        }
        else if(option.equals("4")) {
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            menuOpenAcconuts(login_code);
        }
    }

    public void menuDeleteSubject(String login_code) {
        Subject subject = new Subject();
        Scanner scanner = new Scanner(System.in);
        System.out.println("NOTICE: By deliting a subject, you are also deleting it from all courses!" +
                "\n Be conscious on that, Recordes should never be deleted!");
        System.out.print("Input the Id of the subject or press 'Enter' to skip: ");
        subject.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(subject_xml, "subject", subject.getId())) {
            systemPause("Subject with this does not exists! You need to start over again!");
            manageSubjects(login_code);
        }

        deleteAllSubjectEntryFromCourse(subject);
        deleteSubject(subject);
        System.out.println("Subject with id '"+ subject.getId()+"' Deleted Successfully!");
        systemPause("");
        manageSubjects(login_code);

    }

    public void menuUpdateSubject(String login_code) {
        Subject subject = new Subject();

        ArrayList<String> class_attbr = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the Id of the subject: ");
        subject.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(subject_xml, "subject", subject.getId())) {
            systemPause("Subject with this Id does not exists! You need to start over again!");
            manageSubjects(login_code);
        }

        System.out.println("Select one of the options!" +
                "\n1 - Update Name."+
                "\n2 - Update Description."+
                "\n3 - Update Professor login name."+
                "\n4 - Close");

        String option = scanner.nextLine();
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");
        if(option.equals("1")) {
            System.out.print("Name of the subject: ");
            subject.setName(scanner.nextLine());
            class_attbr.add("name");
            values.add(subject.getName());
        } else if(option.equals("2")) {
            System.out.print("Description: ");
            subject.setDescription(scanner.nextLine());
            class_attbr.add("description");
            values.add(subject.getDescription());
        } else if(option.equals("3")) {
            System.out.print("Professor login name: ");
            subject.setProfessor_code(scanner.nextLine().toUpperCase());
            if(!subject.getProfessor_code().startsWith("PR") || !userExists(subject.getProfessor_code())) {
                systemPause("Professor's login name incorrect! You need to start over again!");
                menuUpdateSubject(login_code);
            }
            class_attbr.add("professor_code");
            values.add(subject.getProfessor_code());
        } else if(option.equals("4")) {
            systemPause("");
            manageSubjects(login_code);
        } else {
            systemPause("Wrong option!");
            manageSubjects(login_code);
        }
        subject.setRegistration_date(currentDateTime());
        updateSubject(subject, class_attbr, values);
        System.out.println("Subject with id '"+ subject.getId()+"' updated Successfully!");
        systemPause("");
        manageSubjects(login_code);
    }

    public void menuCreateSubject(String login_code) {
        Subject subject = new Subject();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");

        System.out.print("Name of the subject: ");
        subject.setName(scanner.nextLine());

        System.out.print("Id of the subject: ");
        subject.setId(scanner.nextLine().toUpperCase());
        if(objectExists(subject_xml, "subject", subject.getId())) {
            systemPause("Subject with this Id already exists! You need to start over again!");
            menuCreateSubject(login_code);
        }

        System.out.print("Description: ");
        subject.setDescription(scanner.nextLine());

        System.out.print("Professor login name: ");
        subject.setProfessor_code(scanner.nextLine().toUpperCase());
        //System.out.println(subject.getProfessor_code());
        if(!subject.getProfessor_code().startsWith("PR") || !userExists(subject.getProfessor_code())) {
            systemPause("Professor's login name incorrect! You need to start over again!");
            menuCreateSubject(login_code);
        }
        subject.setRegistration_date(currentDateTime());
        registerSubject(subject);
        System.out.println("Subject with id '"+ subject.getId()+"' Created Successfully!");
        systemPause("");
        manageSubjects(login_code);
    }

    public void menuUpdateAccounts(String login_code) {
        Persona persona = new Persona();
        ArrayList<String> class_attbr = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input the id of the user: ");
        persona.setId(scanner.nextLine().toUpperCase());
        if(!objectExists(user_xml, "user", persona.getId())) {
            systemPause("User with this Id does not exists! You need to start over again!");
            administrator_menu(login_code);
        }

        System.out.println("Select one of the options!" +
                "\n1 - Update Name."+
                "\n2 - Update Age."+
                "\n3 - Update Sex."+
                "\n4 - Update phone."+
                "\n5 - Update status."+
                "\n6 - Change password."+
                "\n7 - Close");

        String option = scanner.nextLine();
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");
        if(option.equals("1")) {
            System.out.print("Name of the user: ");
            persona.setName(scanner.nextLine());
            class_attbr.add("name");
            values.add(persona.getName());
        } else if(option.equals("2")) {
            System.out.print("Age: ");
            persona.setAge(scanner.nextLine());
            class_attbr.add("age");
            values.add(persona.getAge());
        } else if(option.equals("3")) {
            try {
                System.out.print("Sex ('0' for male, and '1' for female): ");
                int i = scanner.nextInt();
                if (i == 0)
                    persona.setSex(Sex.MASCULINE);
                else
                    persona.setSex(Sex.FEMININE);
                scanner.nextLine();
                class_attbr.add("sex");
                values.add(persona.getSex().toString());
            } catch (Exception e) {
                systemPause("Data type incorrect! You need to start over again!");
                menuUpdateAccounts(login_code);
            }

        }
       else if(option.equals("4")) {
            System.out.print("Phone: ");
            persona.setPhone(scanner.nextLine());
            class_attbr.add("phone");
            values.add(persona.getPhone());
        } else if(option.equals("5")) {
           try {
            System.out.print("Status ('0' for Passive, and '1' for Active): ");
            int i = scanner.nextInt();
            if (i == 0)
                persona.setStatus(Status.PASSIVE);
            else
                persona.setStatus(Status.ACTIVE);
            scanner.nextLine();
            class_attbr.add("status");
            values.add(persona.getStatus().toString());
        } catch (Exception e) {
            systemPause("Data type incorrect! You need to start over again!");
            menuUpdateAccounts(login_code);
        }
        } else if(option.equals("6")) {
            System.out.print("Please input the new password: ");
            String password = scanner.nextLine();
            updatePassword(new User(persona.getId().toUpperCase(), password));
            systemPause("Password updated successfuly!!");
            administrator_menu(login_code);
        } else if(option.equals("7")) {
            systemPause("");
            administrator_menu(login_code);
        } else {
            systemPause("Wrong option!");
            administrator_menu(login_code);
        }

        updatePersona(persona, class_attbr, values);
        System.out.println("User with id '"+ persona.getId()+"' updated Successfully!");
        systemPause("");
        administrator_menu(login_code);
    }

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
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");

        System.out.print("Full name: ");
        persona.setName(scanner.nextLine());

        try {
            System.out.print("Age: ");
            persona.setAge(scanner.nextInt()+"");

            System.out.print("Sex ('0' for male, and '1' for female): ");
            int i = scanner.nextInt();
            if(i == 0)
                persona.setSex(Sex.MASCULINE);
            else
                persona.setSex(Sex.FEMININE);

        } catch (Exception e) {
            systemPause("Data type incorrect! You to need start over again!");
            menuOpenAcconuts(login_code);
        }
        scanner.nextLine();
        System.out.print("Phone: ");
        persona.setPhone(scanner.nextLine());
        persona.setRegistration_date(currentDateTime());

        String login_name = registerUser(new User(Category.ST.name(), ""));
        registerPersona(persona, login_name);
        System.out.println("User account Created Successfully!");
        System.out.println("The User is advised to change the password immediately!");
        System.out.println("The login name is "+login_name+" and the password is by default the empty string (just press 'Enter')!\n");
        menuOpenAcconuts(login_code);
    }

    public void openProfessorAccount(String login_code) {
        Scanner scanner = new Scanner(System.in);
        Persona persona = new Persona();
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");

        System.out.print("Full name: ");
        persona.setName(scanner.nextLine());

        try {
            System.out.print("Age: ");
            persona.setAge(scanner.nextInt()+"");

            System.out.print("Sex ('0' for male, and '1' for female): ");
            int i = scanner.nextInt();
            if(i == 0)
                persona.setSex(Sex.MASCULINE);
            else
                persona.setSex(Sex.FEMININE);

        } catch (Exception e) {
            systemPause("Data type incorrect! You need to start over again!");
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
        System.out.println("Please do not input Empty data on the fields!\nMake sure to  input the correct data type!");

        System.out.print("Full name: ");
        persona.setName(scanner.nextLine());

        try {
            System.out.print("Age: ");
            persona.setAge(scanner.nextInt()+"");

            System.out.print("Sex ('0' for male, and '1' for female): ");
            int i = scanner.nextInt();
            if(i == 0)
                persona.setSex(Sex.MASCULINE);
            else
                persona.setSex(Sex.FEMININE);

        } catch (Exception e) {
            systemPause("Data type incorrect! You need to start over again!");
            menuOpenAcconuts(login_code);
        }
        scanner.nextLine();
        System.out.print("Phone: ");
        persona.setPhone(scanner.nextLine());
        persona.setRegistration_date(currentDateTime());

        String login_name = registerUser(new User(Category.AD.name(), ""));
        registerPersona(persona, login_name);
        System.out.println("User account Created Successfully!");
        System.out.println("The User is advised to change the password immediately!");
        System.out.println("The login name is "+login_name+" and the password is by default the empty string (just press 'Enter')!\n");
        menuOpenAcconuts(login_code);
    }

}
