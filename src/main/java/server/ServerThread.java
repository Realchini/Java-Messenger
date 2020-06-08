package server;

import domain.Message;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThread extends Thread{
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
}
