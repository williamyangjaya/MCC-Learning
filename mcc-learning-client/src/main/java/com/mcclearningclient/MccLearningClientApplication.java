package com.mcclearningclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MccLearningClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MccLearningClientApplication.class, args);
    }
    
//    public static void main(String[] args) throws ParserConfigurationException, SAXException, TransformerException, IOException {
//        SpringApplication.run(MccLearningClientApplication.class, args);
//
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//        File file = new File("D:/Data/My Documents/Working Directory Project/mcc-learning-client/pom.xml");
//        Document document = documentBuilder.parse(file);
//        NodeList list = document.getElementsByTagName("Connector");
//        for (int i = 0; i < list.getLength(); i++) {
//            Node node = list.item(i);
//            NamedNodeMap map = node.getAttributes();
//            if (map.getNamedItem("port").getNodeValue().equalsIgnoreCase("8080")
//                    && map.getNamedItem("protocol").getNodeValue().equalsIgnoreCase("HTTP/1.1")) {
//                map.getNamedItem("relaxedQueryChars").setNodeValue("|[]{}");
//            }
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            document.setXmlStandalone(true);
//            DOMSource source = new DOMSource(document);
//            StreamResult result = new StreamResult(file);
//            transformer.transform(source, result);
//        }
//    }

}
