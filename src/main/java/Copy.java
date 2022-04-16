import Models.User;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class Copy {
    public final int LOGIN_SIZE = 2;
    public final int MINUMUN_PASSWORD_SIZE = 1;
    public final String path = "src/main/resources/_xmlfiles/";
    public final String user_xml = path.concat("user.xml");
    public final String student_xml = path.concat("student.xml");

    protected boolean register_user(User user) {

        return true;
    }

    public void update() {
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(user_xml);

            // Get the parent node
            Node entreprise = doc.getFirstChild();
            //System.out.println(entreprise);
            // Get the employee element
            Node employee = doc.getElementsByTagName("Users").item(0);

            // Add a new node
            Element job = doc.createElement("job");
            job.appendChild(doc.createTextNode("Commercial"));
            employee.appendChild(job);
            // write the content to the xml file
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(new FileOutputStream(user_xml, true));
            transformer.transform(src, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void record (String value){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(user_xml);

            //Document dc = db.parse(user_xml);

            Element r = (Element) document.getElementsByTagName("Users").item(0);

            //Element root = document.createElement("Users");
            //document.appendChild(root);

            Element user = document.createElement("user");
            r.appendChild(user);

            Attr attr = document.createAttribute("id");
            long i = System.currentTimeMillis();
            attr.setValue("_"+i);
            user.setAttributeNode(attr);

            Element text = document.createElement("Text");
            text.appendChild(document.createTextNode(value));
            user.appendChild(text);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");


            DOMSource dom = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new FileOutputStream(user_xml));

            t.transform(dom, streamResult);
            System.out.println("Success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean authenticate(String login_name, String password) {
        if(login_name.length() != LOGIN_SIZE || password.length() < MINUMUN_PASSWORD_SIZE)
            return false;

        if(password.equals("0"))
            return true;

        return false;
    }


}


/*  try {
            String valor = "Texto";
            String path = "src/main/resources/_xmlfiles/person.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.newDocument();

            //root element
            Element root = d.createElement("Posts");
            d.appendChild(root);

            //post element
            Element post = d.createElement("post");
            root.appendChild(post);

            //defining attribute
            Attr attr = d.createAttribute("id");
            long id = System.currentTimeMillis();
            attr.setValue("_"+id);
            post.setAttributeNode(attr);

            //defining the value of the post
            Element text = d.createElement("Text");
            text.appendChild(d.createTextNode(valor));
            post.appendChild(text);

            //Building the xml
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource domSource = new DOMSource(d);
            StreamResult streamresult = new StreamResult(new File(path));

            //Joining all the content
            t.transform(domSource, streamresult);
            System.out.println("done");


        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Dir: "+System.getProperty("user.dir"));
        }*/