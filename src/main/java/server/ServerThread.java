package server;

import domain.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThread extends Thread{
    final static Logger LOGGER = LogManager.getLogger(ServerThread.class);

    private final Socket socket;
    private final AtomicInteger messageId;
    private final Map<Long, Message> messageList;
    private final BufferedReader in;
    private final PrintWriter out;

    public ServerThread(Socket socket, AtomicInteger messageId, Map<Long, Message> messagesList) throws IOException {
        this.socket = socket;
        this.messageId = messageId;
        this.messageList = messagesList;
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
