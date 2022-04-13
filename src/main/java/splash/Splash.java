package splash;

import java.io.IOException;
import java.util.Scanner;

public class Splash {
    //private int attempt;

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

    public boolean login(int attempt){
        if(attempt == 0)
            logout("Please consult the administrator of the system...");

        header("Login");
        System.out.println("Please input your data! "+attempt+" attempts!");
        System.out.print("Password: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        if(s.equals("0")) {
            attempt--;
            System.out.println("Password incorrect");
            clearScreen();
            return login(attempt);
        }

        return true;
    }

    public void logout(String sms) {
        System.out.println(sms);
        System.exit(0);
    }

    public void main() {
        if(login(3))
            System.out.println("Success");
        else {

        }

    }


    // Getters and Settters

    /*private void reduce_attempts() {
        this.attempt--;
    }*/
}
