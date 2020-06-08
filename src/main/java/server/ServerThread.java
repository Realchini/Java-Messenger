package server;

import domain.Message;
import domain.xml.MessageBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ServerThread extends Thread{
    final static Logger LOGGER = LogManager.getLogger(ServerThread.class);

    private final Socket socket;
    private final AtomicInteger messageId;
    private final Map<Long, Message> messagesList;
    private final BufferedReader in;
    private final PrintWriter out;

    public ServerThread(Socket socket, AtomicInteger messageId, Map<Long, Message> messagesList) throws IOException {
        this.socket = socket;
        this.messageId = messageId;
        this.messagesList = messagesList;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }

    @Override
    public void run() {
        try{
            LOGGER.debug("New socket thread is starting...");
            String requestLine = in.readLine();
            LOGGER.debug(requestLine);
            switch (requestLine) {
                case "GET":
                    LOGGER.debug("get");
                    Long lastId = Long.valueOf(in.readLine());
                    LOGGER.debug(lastId);
                    List<Message> newMessages = messagesList.entrySet().stream()
                            .filter(message -> message.getKey().compareTo(lastId) > 0)
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList());
                    LOGGER.debug(newMessages);
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
                    Document document = builder.newDocument();
                    String xmlContent = MessageBuilder.buildDocument(document, messagesList.values());
                    LOGGER.trace("Echoing: "+xmlContent);
                    out.println(xmlContent);
                    out.println("END");
                    out.flush();
                    break;
                case "PUT":
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            out.println("ERROR");
            out.flush();
        }
    }
}
