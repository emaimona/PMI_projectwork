import splash.Splash;

public class Main {
    public static void main(String[] args) {

        /* DO I NEED A COUNTER FOR ALL OBJECT? */

        Splash n = new Splash();
        n.main_menu();
        System.out.println();
    }

    /*try {
            FileOutputStream fos = new FileOutputStream(new File("./users.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(s);
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
}
