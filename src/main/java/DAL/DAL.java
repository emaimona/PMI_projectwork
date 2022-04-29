package DAL;

import Models.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import splash.Splash;

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
import java.util.ArrayList;
import java.util.Arrays;

public class DAL {
    public final int LOGIN_SIZE = 2;
    public final int MINUMUN_PASSWORD_SIZE = 1;
    public final String path = "src/main/resources/_xmlfiles/";
    public final String user_xml = path.concat("user.xml");
    public final String student_xml = path.concat("student.xml");
    public final String persona_xml = path.concat("persona.xml");
    public final String issue_xml = path.concat("issue.xml");
    public final String subject_xml = path.concat("subject.xml");
    public final String course_xml = path.concat("course.xml");


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

    public void updatePersona(Persona persona, ArrayList<String> class_attbr, ArrayList<String> values) {
        try {
            Document document = getDocument(persona_xml);
            NodeList node =  document.getElementsByTagName("persona");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getAttribute("id").equals(persona.getId()))) {
                        for (int j=0; j<class_attbr.size(); j++) {
                            element.getElementsByTagName(class_attbr.get(j)).item(0).setTextContent(values.get(j));
                        }
                        transform(document, persona_xml, "no");
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Course getCourse(String course_id) {
        Course course = null;
        try {
            Document document = getDocument(course_xml);

            NodeList node = document.getElementsByTagName("course");
            for (int i=0; i< node.getLength(); i++) {
                Element element = (Element) node.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getAttribute("id").equals(course_id)) {
                    course = new Course();
                    course.setId(course_id);
                    course.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    course.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    course.setRegistration_date(element.getElementsByTagName("registration_date").item(0).getTextContent());

                    // Adding students
                    NodeList student_node = element.getElementsByTagName("student");
                    for (int k=0; k<student_node.getLength(); k++) {
                        Element student_element = (Element) student_node.item(k);
                        if(student_element.getNodeType() == Node.ELEMENT_NODE) {
                            //if(student_element.getTextContent() != "");
                            course.addStudent(student_element.getTextContent());
                        }
                    }

                    // Adding subjects
                    NodeList subject_node = element.getElementsByTagName("subject");
                    for (int k=0; k<subject_node.getLength(); k++) {
                        Element subject_element = (Element) subject_node.item(k);
                        if(subject_element.getNodeType() == Node.ELEMENT_NODE) {
                            //if(subject_element.getTextContent() != "");
                                course.addSubject(subject_element.getTextContent());
                        }
                    }

                    break;
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return course;
    }


    public Issue getIssues(String login_name) {
        Issue issue = null;
        try {
            Document document = getDocument(issue_xml);

            NodeList node = document.getElementsByTagName("issue");
            for (int i=0; i< node.getLength(); i++) {
                Element element = (Element) node.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getElementsByTagName("login_name").item(0).getTextContent().equals(login_name)) {
                    issue = new Issue();
                    issue.setLogin_name(login_name);
                    issue.setDate(element.getElementsByTagName("date").item(0).getTextContent());
                    issue.setMessage(element.getElementsByTagName("message").item(0).getTextContent());
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return issue;
    }



    public Subject getSubject(String subject_id) {
        Subject subject = null;
        try {
            Document document = getDocument(subject_xml);

            NodeList node = document.getElementsByTagName("subject");
            for (int i=0; i< node.getLength(); i++) {
                Element element = (Element) node.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getAttribute("id").equals(subject_id)) {
                    subject = new Subject();
                    subject.setId(subject_id);
                    subject.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    subject.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    subject.setRegistration_date(element.getElementsByTagName("registration_date").item(0).getTextContent());
                    subject.setProfessor_code(element.getElementsByTagName("professor_code").item(0).getTextContent());
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return subject;
    }


    public Persona getPersona(String login_name) {
        Persona persona = null;
        try {
            Document document = getDocument(persona_xml);

            NodeList node = document.getElementsByTagName("persona");
            for (int i=0; i< node.getLength(); i++) {
                Element element = (Element) node.item(i);
                if(element.getNodeType() == Node.ELEMENT_NODE && element.getAttribute("id").equals(login_name)) {
                    persona = new Persona();
                    persona.setId(login_name);
                    persona.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    persona.setPhone(element.getElementsByTagName("phone").item(0).getTextContent());
                    persona.setRegistration_date(element.getElementsByTagName("registration_date").item(0).getTextContent());
                    persona.setAge(element.getElementsByTagName("age").item(0).getTextContent());
                    persona.setSex(Sex.valueOf(element.getElementsByTagName("sex").item(0).getTextContent()));
                    persona.setStatus(Status.valueOf(element.getElementsByTagName("status").item(0).getTextContent()));
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return persona;
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
            String[] values = {persona_class.getName(), persona_class.getAge(),
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

    public void updateCourse(Course course, ArrayList<String> class_attbr, ArrayList<String> values) {
        try {
            Document document = getDocument(course_xml);
            NodeList node =  document.getElementsByTagName("course");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getAttribute("id").equals(course.getId()))) {
                        for (int j=0; j<class_attbr.size(); j++) {
                            element.getElementsByTagName(class_attbr.get(j)).item(0).setTextContent(values.get(j));
                        }
                        transform(document, course_xml, "no");
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createCourse(Course course_class) {
        try {
            Document document = getDocument(course_xml);
            incrementLastId(document);

            Element root = (Element) document.getElementsByTagName("Courses").item(0);
            Element course = document.createElement("course");
            course.setAttribute("id", course_class.getId());
            root.appendChild(course);

            Element name = document.createElement("name");
            name.setTextContent(course_class.getName());
            course.appendChild(name);
            Element desc = document.createElement("description");
            desc.setTextContent(course_class.getDescription());
            course.appendChild(desc);
            Element date = document.createElement("registration_date");
            date.setTextContent(course_class.getRegistration_date());
            course.appendChild(date);

            Element subjects = document.createElement("subjects");
            Element students = document.createElement("students");
            course.appendChild(subjects);
            course.appendChild(students);

            transform(document, course_xml,"no");

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void addStudentCourse(String student_id, String courseId) {
        try {
            Document document = getDocument(course_xml);

            NodeList node =  document.getElementsByTagName("course");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getAttribute("id").equals(courseId))) {
                        Element students =  (Element) element.getElementsByTagName("students").item(0);
                        Element sub = document.createElement("student");
                        sub.setTextContent(student_id);
                        students.appendChild(sub);
                        transform(document, course_xml, "no");

                        updateStatusToActive(student_id);
                    }
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void updateStatusToActive(String persona_id) {
        updatePersona(new Persona(persona_id),
                new ArrayList<>(Arrays.asList("status")),
                new ArrayList<>(Arrays.asList(Status.ACTIVE.name())));
    }


    public void addSubjectCourse(Subject subject, String courseId) {
        try {
            Document document = getDocument(course_xml);

            NodeList node =  document.getElementsByTagName("course");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getAttribute("id").equals(courseId))) {
                        Element subjects =  (Element) element.getElementsByTagName("subjects").item(0);
                        Element sub = document.createElement("subject");
                        sub.setTextContent(subject.getId());
                        subjects.appendChild(sub);
                        transform(document, course_xml, "no");

                        updateStatusToActive(subject.getProfessor_code());
                    }
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public void deleteSubjectCourse(Subject subject, String courseId) {
        try {
            Document document = getDocument(course_xml);

            NodeList node =  document.getElementsByTagName("course");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if (element.getAttribute("id").equals(courseId)) {
                        Element subjects =  (Element) element.getElementsByTagName("subjects").item(0);
                        NodeList sub = subjects.getElementsByTagName("subject");
                        for(int j=0; j<sub.getLength(); j++) {
                            Node node1 = sub.item(j);
                            if (node1.getNodeType() == Node.ELEMENT_NODE ) {
                                Element elementsub = (Element)node1;
                                if(elementsub.getTextContent().equals(subject.getId()))
                                    subjects.removeChild(elementsub);
                            }
                        }
                        transform(document, course_xml, "no");
                    }
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public void deleteStudentCourse(String student_id, String courseId) {
        try {
            Document document = getDocument(course_xml);

            NodeList node =  document.getElementsByTagName("course");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if (element.getAttribute("id").equals(courseId)) {
                        Element students =  (Element) element.getElementsByTagName("students").item(0);
                        NodeList sub = students.getElementsByTagName("student");
                        for(int j=0; j<sub.getLength(); j++) {
                            Node node1 = sub.item(j);
                            if (node1.getNodeType() == Node.ELEMENT_NODE ) {
                                Element elementsub = (Element)node1;
                                if(elementsub.getTextContent().equals(student_id))
                                    students.removeChild(elementsub);
                            }
                        }
                        transform(document, course_xml, "no");
                    }
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }


    public ArrayList<String> getTeachingSubjects(String professor_id) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Document document = getDocument(subject_xml);
            NodeList node =  document.getElementsByTagName("subject");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getElementsByTagName("professor_code").item(0).getTextContent().equals(professor_id))) {
                        list.add(element.getAttribute("id"));
                    }
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    public void deleteSubject(Subject subject) {
        try {
            Document document = getDocument(subject_xml);
            NodeList node =  document.getElementsByTagName("subject");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getAttribute("id").equals(subject.getId()))) {
                        document.getElementsByTagName("Subjects").item(0).removeChild(element);
                        transform(document, subject_xml, "no");
                    }
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllSubjectEntryFromCourse(Subject subject){
        try {
            Document document = getDocument(course_xml);
            NodeList node =  document.getElementsByTagName("course");
            for (int k=0; k<node.getLength(); k++) {
                Element element = (Element) node.item(k);
                //System.out.println(element.getAttribute("id"));
                deleteSubjectCourse(subject, element.getAttribute("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printAllIssues(String category){
        try {
            Splash s = new Splash();
            Document document = getDocument(issue_xml);
            NodeList node =  document.getElementsByTagName("issue");
            for (int k=0; k<node.getLength(); k++) {
                Element element = (Element) node.item(k);
                Issue issue = getIssues(element.getElementsByTagName("login_name").item(0).getTextContent());
                //System.out.println(issue.getLogin_name());
                if (issue != null && issue.getLogin_name().startsWith(category)){
                        s.printIssues(issue);
                        System.out.println();
                        System.out.println();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void printAllSubjects(){
        try {
            Splash s = new Splash();
            Document document = getDocument(subject_xml);
            NodeList node =  document.getElementsByTagName("subject");
            for (int k=0; k<node.getLength(); k++) {
                Element element = (Element) node.item(k);
                //System.out.println(element.getAttribute("id"));
                Subject subject = getSubject(element.getAttribute("id"));
                if (subject != null){
                    s.printSubject(subject);
                    System.out.println();
                    System.out.println();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void printAllCourses(){
        try {
            Splash s = new Splash();
            Document document = getDocument(course_xml);
            NodeList node =  document.getElementsByTagName("course");
            for (int k=0; k<node.getLength(); k++) {
                Element element = (Element) node.item(k);
                //System.out.println(element.getAttribute("id"));
                Course course = getCourse(element.getAttribute("id"));
                if (course != null){
                    s.printCourse(course);
                    System.out.println();
                    System.out.println();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printAllUsers(){
        try {
            Splash s = new Splash();
            Document document = getDocument(user_xml);
            NodeList node =  document.getElementsByTagName("user");
            for (int k=0; k<node.getLength(); k++) {
                Element element = (Element) node.item(k);
                //System.out.println(element.getAttribute("id"));
                Persona p = getPersona(element.getAttribute("id"));
                if (p != null){
                    s.printPersona(p);
                    System.out.println();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getRegisteredCourses(String studentId){
        ArrayList<String> list = new ArrayList<>();
        try {
            Document document = getDocument(course_xml);

            NodeList node =  document.getElementsByTagName("course");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element students = (Element) ((Element) n).getElementsByTagName("students").item(0);
                    NodeList sub = students.getElementsByTagName("student");
                    for(int j=0; j<sub.getLength(); j++) {
                        Node node1 = sub.item(j);
                        if (node1.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementsub = (Element) node1;
                            if (elementsub.getTextContent().equals(studentId)) {
                                list.add(((Element) n).getAttribute("id"));
                                break;
                            }

                        }

                    }
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public void deleteAllStudentEntryFromCourse(String studentId){
        try {
            Document document = getDocument(course_xml);
            NodeList node =  document.getElementsByTagName("course");
            for (int k=0; k<node.getLength(); k++) {
                Element element = (Element) node.item(k);
                System.out.println(element.getAttribute("id"));
                deleteStudentCourse(studentId, element.getAttribute("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void updateSubject(Subject subject, ArrayList<String> class_attbr, ArrayList<String> values) {
        try {
            Document document = getDocument(subject_xml);
            NodeList node =  document.getElementsByTagName("subject");
            for(int i=0; i<node.getLength(); i++) {
                Node n = node.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;
                    if ((element.getAttribute("id").equals(subject.getId()))) {
                        for (int j=0; j<class_attbr.size(); j++) {
                            element.getElementsByTagName(class_attbr.get(j)).item(0).setTextContent(values.get(j));
                        }
                        transform(document, subject_xml, "no");
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void registerSubject(Subject subject) {
        try {
            Document document = getDocument(subject_xml);
            incrementLastId(document);
            Element root = (Element) document.getElementsByTagName("Subjects").item(0);
            Element sub = document.createElement("subject");
            sub.setAttribute("id", subject.getId());
            root.appendChild(sub);

            String[] class_attbr = {"name", "description", "professor_code", "registration_date"};
            String[] values = {subject.getName(), subject.getDescription(), subject.getProfessor_code(), subject.getRegistration_date()};
            appendChilds(document, sub, class_attbr, values);
            transform(document, subject_xml, "no");

        } catch(Exception e) {
            e.printStackTrace();
        }
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

    public boolean userExists(String login_name) {
        try {
            Document document = getDocument(user_xml);
            NodeList list = document.getElementsByTagName("user");
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) list.item(i);
                    if (element.getAttribute("id").equals(login_name)) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean objectExists(String path, String object_name, String id) {
        try {
            Document document = getDocument(path);
            NodeList list = document.getElementsByTagName(object_name);
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) list.item(i);
                    if (element.getAttribute("id").equals(id)) {
                        return true;
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
