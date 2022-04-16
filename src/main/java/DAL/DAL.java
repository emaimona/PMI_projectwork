package DAL;

import Models.Category;
import Models.Issue;
import Models.Persona;
import Models.User;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DAL{
    public final int LOGIN_SIZE = 2;
    public final int MINUMUN_PASSWORD_SIZE = 1;
    public final String path = "src/main/resources/_xmlfiles/";
    public final String user_xml = path.concat("user.xml");
    public final String student_xml = path.concat("student.xml");
    public final String persona_xml = path.concat("persona.xml");
    public final String issue_xml = path.concat("issue.xml");


    public int incrementLastId(Document document) {
        int val = -1;
        try {
            Element id = (Element) document.getElementsByTagName("last_id").item(0);
            val = Integer.parseInt(id.getTextContent()) + 1;
            id.setTextContent(val+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public int incrementLastId(Document document, String category) {
        int val = -1;
        try {
            Element id = (Element) document.getElementsByTagName("last_"+category+"_id").item(0);
            val = Integer.parseInt(id.getTextContent()) + 1;
            id.setTextContent(val+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }


    public int getLastId(String path) {
        int id = 0;
        try {
            Document document = getDocument(path);
            Element size = (Element) document.getElementsByTagName("last_id").item(0);
            id = Integer.parseInt(size.getTextContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getLastId(String path, String category) {
        int id = 0;
        try {
            Document document = getDocument(path);
            Element size = (Element) document.getElementsByTagName("last_"+category+"_id").item(0);
            id = Integer.parseInt(size.getTextContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private void update(Document document, String path, String ident) {

    }

    public void registerIssue(Issue issue_class) {
        try {
            Document document = getDocument(issue_xml);

            int id = incrementLastId(document);
            Element root = (Element) document.getElementsByTagName("Issues").item(0);

            Element issue = document.createElement("issue");
            issue.setAttribute("id", id+"");
            root.appendChild(issue);

            String[] class_atrr = {"login_name", "message", "date"};
            String[] values = {issue_class.getLogin_name(), issue_class.getMessage(), issue_class.getDate()};
            appendChilds(document, issue, class_atrr, values);
            transform(document,issue_xml, "no");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void registerPersona(Persona persona_class, String login_name) {
        try {
            Document document = getDocument(persona_xml);

            Element root = (Element) document.getElementsByTagName("Persona").item(0);
            Element persona = document.createElement("persona");

           // String category = login_name;
            //String generated_code = formatID(incrementLastId(document));
            persona.setAttribute("id", login_name);
            root.appendChild(persona);

            String[] class_attr = {"name", "age", "sex", "phone", "registration_date", "status"};
            String[] values = {persona_class.getName(), Integer.toString(persona_class.getAge()),
                    persona_class.getSex().toString(), persona_class.getPhone(),
                    persona_class.getRegistration_date(), persona_class.getStatus().name()};

            appendChilds(document, persona, class_attr, values);
            transform(document, persona_xml,"no");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(path);
        document.getDocumentElement().normalize();
        return document;
    }

    private void appendChilds(Document document, Element element, String[] class_attr, String[] values) {
        for(int i = 0; i< class_attr.length; i++) {
            Element text = document.createElement(class_attr[i]);
            text.appendChild(document.createTextNode(values[i]));
            element.appendChild(text);
        }
    }

    private void transform(Document document, String path, String ident) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, ident);

        DOMSource dom = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        t.transform(dom, streamResult);
    }

    public String registerUser(User user_class) {
        String generated_code = "";
        String category = "";

        try {
            Document document = getDocument(user_xml);

            Element root = (Element) document.getElementsByTagName("Users").item(0);

            Element user = document.createElement("user");

            category = user_class.getLogin_name();
            generated_code = formatID(incrementLastId(document, category));

            user.setAttribute("id", category + generated_code);
            root.appendChild(user);

            /*Element login = document.createElement("login");
            login.appendChild(document.createTextNode(user_class.getLogin_name()));
            user.appendChild(login);*/

            Element password = document.createElement("password");
            password.appendChild(document.createTextNode(user_class.getPassword()));
            user.appendChild(password);

            transform(document,user_xml,"no");
        } catch(Exception e) {
            e.printStackTrace();
        }

        //Logim name
        return category+generated_code;
    }

    public void updatePassword(User user) {
        try {
            Document document = getDocument(user_xml);
            NodeList list = document.getElementsByTagName("user");
            for(int i=0; i<list.getLength(); i++) {
                if(list.item(i).getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element)list.item(i);
                    if(element.getAttribute("id").equals(user.getLogin_name())) {
                        element.getElementsByTagName("password").item(0).setTextContent(user.getPassword());
                        transform(document, user_xml, "no");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(User user) {
        try {
            Document document = getDocument(user_xml);
            NodeList list = document.getElementsByTagName("user");
            for(int i=0; i<list.getLength(); i++) {
                if(list.item(i).getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element)list.item(i);
                    if(element.getAttribute("id").equals(user.getLogin_name())) {
                        return (element.getElementsByTagName("password").item(0).getTextContent().equals(user.getPassword()));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean authenticate(User user) {
        /*if(login_name.length() != LOGIN_SIZE || password.length() < MINUMUN_PASSWORD_SIZE)
            return false;*/
        return validateUser(user);
    }

    public String formatID(int number) {
        if (number == 0)
            return "0000";

        int length = (int) (Math.log10(number) + 1);
        String id = "";
        for (int i=0; i+length < 4; i++) {
            id += "0";
        }
        id += number;
        return id;
    }
}
