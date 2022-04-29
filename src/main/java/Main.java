import DAL.DAL;
import Models.*;
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
        Splash window = new Splash();
        window.main_menu();



        /* The text belloe is Just for some texts*/

        //Date date = new Date();
        //DAL t = new DAL();
        //User user = new User("PR", "0");
        //t.registerUser(user);
        //Persona p = new Persona("ST0007","Emanuel", "12", Sex.MASCULINE, "48484", n.currentDateTime());
        //t.registerPersona(p, "PR");
        //t.incrementLastId(t.persona_xml);
        //System.out.println(t.getLastId(t.persona_xml));
        //System.out.println("000"+1);
        //t.record("Papa" +System.currentTimeMillis());
        //t.registerIssue(new Issue("ST","Something went wrong", date.toString()));
        //String s = t.formatID(t.getLastId(t.user_xml));
        //Persona po = t.writerPersona("AD0002");
        //n.printPersona(po);
        //Subject s = new Subject();
        //s.setId("KIM");
        //t.updateSubject(s, ["e"], {""});
        //t.deleteAllSubjectEntryFromCourse(s);

        //Issue issue = t.getIssues("ST0007");
        //System.out.println(t.getRegisteredCourses("ST").toString());
        //System.out.println(t.getTeachingSubjects("PR0003"));
        System.out.println();
    }

}