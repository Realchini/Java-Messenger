package domain.xml;

import domain.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            Message.Builder builder = Message.newMessage();
            for (int i=0; i<attributes.getLength(); i++) {
                String attrName = attributes.getLocalName(i);
                String attrValue = attributes.getValue(i);
                LOGGER.trace(attrName + "=" + attrValue);
                switch (attrName) {
                    case "sender":
                        builder.from(attrValue);
                        break;
                    case "receiver":
                        builder.to(attrValue);
                        break;
                    case "id":
                        builder.id(Long.valueOf(attrValue));
                        break;
                    case "moment":
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                        try {
                            calendar.setTime(format.parse(attrValue));
                        }
                        catch (ParseException e) {
                            LOGGER.error(e.getMessage());
                            e.printStackTrace();
                        }
                        builder.moment(calendar);
                }
            }
            message = builder.build();
        }
        LOGGER.trace(">");
    }
}
