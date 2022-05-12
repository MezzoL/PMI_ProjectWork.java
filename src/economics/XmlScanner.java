
package economics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 *
 */
public class XmlScanner {

    /**
     * @param filepath
     * @return
     */
    public ArrayList<Client> readUsersFromXml(String filepath) {
        ArrayList<Client> students = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            /*System.out.println(rootElement.getNodeName());
            System.out.println(rootElement.getNodeType());
            System.out.println("Element node short value: " + Node.ELEMENT_NODE);
            System.out.println("Text node short value: " + Node.TEXT_NODE);*/
            //System.out.println(rootElement.getTextContent());
            NodeList childNodesList = rootElement.getChildNodes();
            /*System.out.println(childNodesList.getLength());
            System.out.println("---------------");*/
            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //System.out.println(node.getNodeName());
                    //System.out.println(node.getTextContent());
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", index = "", birthDate = "", balance = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        /*System.out.println(childNodesOfUserTag.item(j).getNodeType()
                                  + " " + childNodesOfUserTag.item(j).getNodeName());*/
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfUserTag.item(j).getTextContent();
                                case "index" -> index = childNodesOfUserTag.item(j).getTextContent();
                                case "birthDate" -> birthDate = childNodesOfUserTag.item(j).getTextContent();
                                case "balance" -> balance = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    students.add(new Client(name, Integer.parseInt(index),Integer.parseInt(birthDate),
                            Double.parseDouble(balance)));
                }
            }
            //System.out.println("Number of element nodes: " + numberOfElementNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }


    /**
     * @param filepath
     * @param tagName
     * @return
     */
    public int numberOfOccurrence(String filepath, String tagName) {
        int numberOfOccurrence = 0;
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);
            Element rootElement = document.getDocumentElement();
            NodeList tagNameNodeList = rootElement.getElementsByTagName(tagName);
            numberOfOccurrence = tagNameNodeList.getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfOccurrence;
    }


    /**
     * @param clients
     * @param filepath
     */
    public void saveUsersToXml(ArrayList<Client> clients, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("clients");
            document.appendChild(rootElement);

            for (Client client : clients) {
                Element studentElement = document.createElement("student");
                rootElement.appendChild(studentElement);
                createChildElement(document, studentElement, "name", client.getName());
                createChildElement(document, studentElement, "index", String.valueOf(client.getIndex()));
                createChildElement(document, studentElement, "birthDate", String.valueOf(client.getBirthDate()));
                createChildElement(document, studentElement, "balance", String.valueOf(client.getBalance()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param document
     * @param parent
     * @param tagName
     * @param value
     */
    private void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
    
}
