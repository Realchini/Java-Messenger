package domain.xml;

import domain.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageParser extends DefaultHandler {
    final static Logger LOGGER = LogManager.getLogger(MessageParser.class);

    private Message message;
    private List<Message> messages;
    private AtomicInteger id;
    private String thisElement;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        LOGGER.debug("Start Document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // super.startElement(uri, localName, qName, attributes);
        thisElement = qName;
        LOGGER.debug("Start Element");
        LOGGER.trace("<" + qName);
        if ("message".equals(qName)) {
            message = Message.newMessage().build();
        }
        LOGGER.trace(">");
    }
}
