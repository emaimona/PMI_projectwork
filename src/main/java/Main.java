import DAL.DAL;
import Models.Issue;
import Models.Persona;
import Models.Sex;
import Models.User;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import splash.Splash;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        /* DO I NEED A COUNTER FOR ALL OBJECT? */
        Date date = new Date();

        Splash n = new Splash();
        DAL t = new DAL();

        //User user = new User("ST", "0");
        //t.registerUser(user);

        //Persona p = new Persona("Emanuel", 12, Sex.MASCULINE, "48484", "12/12/334");
        //t.registerPersona(p, "PR10349");
        //t.incrementLastId(t.persona_xml);
        //System.out.println(t.getLastId(t.persona_xml));
        //System.out.println("000"+1);
        //t.record("Papa" +System.currentTimeMillis());
        //t.registerIssue(new Issue("ST","Something went wrong", date.toString()));
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
