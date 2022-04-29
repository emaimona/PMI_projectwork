import PL.Splash;

public class Main {
    public static void main(String[] args) {

        Splash window = new Splash();
        window.main_menu();
        System.out.println();
    }

}

/* The text bellow is Just for some tests */
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