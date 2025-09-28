// 代码生成时间: 2025-09-29 00:00:44
import io.micronaut.core.io.ResourceLoader;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@Controller("/xml")
public class XmlDataParser {

    private static final Logger LOG = LoggerFactory.getLogger(XmlDataParser.class);

    @Inject
    private ResourceLoader resourceLoader;

    /**
     * Endpoint to parse XML data and return a string representation.
     * @param xmlData The XML data to parse.
     * @return A string representation of the parsed XML data.
     */
    @Get(value = "/parse", produces = MediaType.TEXT_PLAIN)
    public String parseXml(String xmlData) {
        try {
            // Convert XML string to a Document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlData));
            org.w3c.dom.Document doc = dBuilder.parse(is);

            // Use JAXB to unmarshal the XML Document
            JAXBContext jaxbContext = JAXBContext.newInstance(Object.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object xmlObject = unmarshaller.unmarshal(doc);

            // Convert the unmarshalled object back to XML string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlObject), new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            LOG.error("Error parsing XML data", e);
            return "Error parsing XML data: " + e.getMessage();
        }
    }
}