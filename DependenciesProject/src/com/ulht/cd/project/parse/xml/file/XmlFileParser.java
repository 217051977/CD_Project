package com.ulht.cd.project.parse.xml.file;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlFileParser {
	public XmlFileParser() {
		
	}
	private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public String getWebServiceResponse(String httpConnectionResponse, String functionName) {
		Document doc = parseXmlFile(httpConnectionResponse);
		NodeList nodeList = doc.getElementsByTagName("ns2:" + functionName + "Response");
		return nodeList.item(0).getTextContent();
	}
}
