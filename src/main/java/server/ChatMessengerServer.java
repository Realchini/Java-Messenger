package server;

import domain.Message;
import domain.xml.MessageParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatMessengerServer {
    final static Logger LOGGER = LogManager.getLogger(ChatMessengerServer.class)
    private static final int PORT = 7070;
    private static final int SERVER_TIMEOUT = 500;
    private static final String XML_FILE_NAME = "resources/messages.xml";
    private static volatile boolean stop = false;
    private static AtomicInteger id = new AtomicInteger(0);
    private static Map<Long, Message> messagesList = Collections.synchronizedSortedMap(new TreeMap<Long, Message>());

    public static void main(String[] args) throws IOException {
        // Load xml files with previous messeges
        loadMessageXMLFile();

        // Run thread with quit command handler
        quitCommandThread();

        // Create new Socket Server
        ServerSocket serverSocket = new ServerSocket(PORT);
        LOGGER.info("Server started on port: "+PORT);

        // loop of request in sockets with timeout
        while(!stop) {
            serverSocket.setSoTimeout(SERVER_TIMEOUT);
            Socket socket;
            try {
                socket = serverSocket.accept();
                try {
                    new ServerThread(socket, id, messagesList);
                } catch(IOException e) {
                    LOGGER.error("IO error");
                    socket.close();
                }
            }
            catch(SocketTimeoutException e) { }
        }

        // Write messeges into xml file
        saveMessagesXMLFile();
        LOGGER.info("Server stopped.");
        serverSocket.close();
    }

    private static void loadMessageXMLFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        List<Message> messages = new ArrayList<>();
        MessageParser saxp = new MessageParser(id, messages);
        InputStream is = new ByteArrayInputStream(Files.readAllBytes(Paths.get(XML_FILE_NAME)));
        parser.parse(is, saxp);
        for (Message message: messages) {
            messagesList.put(message.getId(), message);
        }
        id.incrementAndGet();
        is.close();
    }

    private static void quitCommandThread() {
        new Thread() {
            @Override
            public void run() {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while(true) {
                    String buf;
                    try{
                        buf = br.readLine();
                        if("quit".equals(buf)) {
                            stop = true;
                            break;
                        }
                        else {
                           LOGGER.warn("Type 'quit' for server trmination.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
