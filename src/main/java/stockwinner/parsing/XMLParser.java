package stockwinner.parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;

public class XMLParser {

    public static void main(String[] args){
        try {
            File fXmlFile = new File("kursy_dzienne_microsoft.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("kursy_dzienne_microsoft.xml");
            for (int i=0; i< nList.getLength(); i++){
                Node nNode = nList.item(i);
                System.out.println("CURRENT: " + nNode.getNodeName());
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("DATE: " + eElement.getAttribute("timestamp"));
                    System.out.println(eElement.getElementsByTagName("timestamp").item(0).getTextContent());
                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
