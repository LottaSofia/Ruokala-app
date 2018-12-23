package com.example.lottasofiatuominen.yliopistonruokalat;

import android.content.Context;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLFile {
    Yliopisto yliopisto = Yliopisto.getInstance();
    Ravintola rav;
    Ruoka ruoka;
    Arvostelu arvostelu;

    public void writeXML(Context context) {
        XmlSerializer ser = Xml.newSerializer();
        StringWriter writer= new StringWriter();

        try {
            ser.setOutput(writer);
            ser.startDocument("UTF-8",true);
            ser.startTag("","Reviews");
            for (int i = 0; i < yliopisto.getRavintolat().size(); i++) {
                rav = (Ravintola) yliopisto.getRavintolat().get(i);
                for (int j = 0; j < rav.getRuokalista().size(); j++) {
                    ruoka = (Ruoka) rav.getRuokalista().get(j);
                    for (int k = 0; k < ruoka.getArvostelut().size(); k++) {

                        ser.startTag("","Review");
                        ser.startTag("","food");
                        ser.text(ruoka.getRuoka());
                        ser.endTag("","food");

                        arvostelu = (Arvostelu) ruoka.getArvostelut().get(k);
                        ser.startTag("", "rate");
                        ser.text(String.valueOf(arvostelu.getArvosana()));
                        ser.endTag("", "rate");

                        ser.startTag("", "words");
                        ser.text(arvostelu.getArvostelu());
                        ser.endTag("", "words");

                        ser.startTag("","name");
                        ser.text(arvostelu.getArvostelija());
                        ser.endTag("", "name");

                        ser.endTag("","Review");
                    }


                }
            }
            ser.endTag("","Reviews");
            ser.endDocument();
            OutputStreamWriter ops = new OutputStreamWriter((context.openFileOutput("myXML.xml",Context.MODE_PRIVATE)));
            ops.write(writer.toString());
            ops.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList readXML() {
        Ruoka ruoka;
        ArrayList<String> arrayList = new ArrayList();
        File file = new File("/data/user/0/com.example.lottasofiatuominen.yliopistonruokalat/files/myXML.xml");
        System.out.println("Täällä");
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new FileInputStream(file.getPath())));
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("Review");
            System.out.println(nList.getLength());
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println(nList.getLength() + "Elementti on: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.print("Ruoka: ");
                    System.out.println(element.getElementsByTagName("food").item(0).getTextContent());
                    try { // if the food has a rating it is added to the arraylist that's been created for the activity "selaaArvosteluja"
                        System.out.println(element.getElementsByTagName("rate").item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("words").item(0).getTextContent());

                        arrayList.add(element.getElementsByTagName("food").item(0).getTextContent());
                        arrayList.add(element.getElementsByTagName("rate").item(0).getTextContent());
                        arrayList.add(element.getElementsByTagName("words").item(0).getTextContent());
                        arrayList.add(element.getElementsByTagName("name").item(0).getTextContent());

                    } catch (Exception e) {

                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            System.out.println("*************************DONE*************************");
            return arrayList;
        }


    }
}
